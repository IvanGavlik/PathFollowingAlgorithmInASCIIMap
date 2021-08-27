package path.follow.algo;

import path.follow.algo.convert.ASCIIGraph;
import path.follow.algo.convert.ASCIIMapToASCIIGraph;
import path.follow.algo.graph.vertex.CharacterNode;
import path.follow.algo.load.ASCIIMapLoader;
import path.follow.algo.path.FindPath;
import path.follow.algo.path.impl.DepthFirstPath;
import path.follow.algo.validation.Validation;
import path.follow.algo.validators.MustHaveOneCharacter;
import path.follow.algo.validators.NoFakeTurn;
import path.follow.algo.validators.NoMultipleStartingPaths;
import path.follow.algo.validators.PathIsNotBroken;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Objects;

import static path.follow.algo.util.Util.getFirstCharacter;
import static path.follow.algo.util.Util.haveValue;

/**
 * App - starts application.
 *
 * @author ivan.gavlik
 */
public final class PathInASCIIMap {

    private static final int FIRST_PARAM = 0;
    private static final int SECOND_PARAM = 1;
    private static final int THIRD_PARAM = 2;
    private static final int THREE = 3;
    private static final Character DEFAULT_START = '@';
    private static final Character DEFAULT_END = 'x';

    private PathInASCIIMap() { }

    /**
     * App starts here.
     *
     * @param args cmd arguments
     */
    @SuppressWarnings("checkstyle:Regexp")
    public static void main(final String[] args) {
        final MapPath path = find(args);
        System.out.print("Letters: " + path.getLetters());
        System.out.println();
        System.out.print("Path as characters: " + path.getPath());
    }

    /**
     * Find and return path if exist.
     *
     * @param args program args
     * @return {@link MapPath}
     */
    @SuppressWarnings({"checkstyle:ParameterAssignment", "checkstyle:FinalParameters"})
    public static MapPath find(String[] args) {
        args = (args == null || args.length == 0) ? new String[THREE] : args;

        final ASCIIMapLoader asciiMapLoader = ASCIIMapLoader.getInstance(getParamOrNull(args, FIRST_PARAM));
        final List<String> asciiMap = asciiMapLoader.load();

        final Character startChar = haveValue(getParamOrNull(args, SECOND_PARAM)) ?
                getFirstCharacter(args[SECOND_PARAM]) : DEFAULT_START;
        final Character endChar = haveValue(getParamOrNull(args, THIRD_PARAM)) ?
                getFirstCharacter(args[THIRD_PARAM]) : DEFAULT_END;

        new Validation<>(asciiMap)
                .add(new MustHaveOneCharacter(startChar))
                .add(new MustHaveOneCharacter(endChar))
                .add(new NoMultipleStartingPaths(startChar))
                .add(new NoFakeTurn())
                .add(new PathIsNotBroken())
                .isValidOrElseThrow();

        final ASCIIGraph graph = ASCIIMapToASCIIGraph.convert(asciiMap, startChar, endChar);

        final FindPath<CharacterNode> findPath = new DepthFirstPath(graph.getGraph(), graph.getStart());

        final Iterable<CharacterNode> nodePath = findPath.pathTo(graph.getEnd());

        final List<Character> excludeStartAndEndCharacter = new ArrayList<>();
        excludeStartAndEndCharacter.add(startChar);
        excludeStartAndEndCharacter.add(endChar);

        return new MapPath(nodePath, excludeStartAndEndCharacter);
    }


    @SuppressWarnings({"checkstyle:IllegalCatch"})
    private static String getParamOrNull(final String[] args, final int index) {
        try {
            return args[index];
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Represents output of program.
     * Output is collected letters on path as characters.
     */
    static final class MapPath {
        private final String letters;
        private final String path;

        /**
         * Create new MapPath.
         *
         * @param letters {@link List<Character>}
         * @param path {@link List<Character>}
         */
        MapPath(final String letters, final String path) {
            this.letters = letters;
            this.path = path;
        }


        /**
         * Create new MapPath form nodes path {@link Iterable<CharacterNode>}.
         *
         * @param nodes {@link List<Character>}
         * @param exclude exclude valued form letter result
         */
        @SuppressWarnings({"checkstyle:ParameterAssignment", "checkstyle:FinalParameters"})
        MapPath(final Iterable<CharacterNode> nodes, List<Character> exclude) {
            if (exclude == null) {
                exclude = new ArrayList<>();
            }

            final StringBuilder pathBuilder = new StringBuilder();
            final Set<CharacterNode> unieqeLetters = new LinkedHashSet<>();
            final List<Character> finalExclude = exclude;
            nodes.forEach(el -> {
                final Character character = el.getValue();
                if ( Character.isLetter(character) && !finalExclude.contains(character)) {
                    unieqeLetters.add(el);
                }
                pathBuilder.append(character);
            });

            final StringBuilder lettersBuilder = new StringBuilder();
            unieqeLetters.forEach(el -> {
                lettersBuilder.append(el.getValue());
            });

            this.letters = lettersBuilder.toString();
            this.path = pathBuilder.toString();
        }

        public String getLetters() {
            return letters;
        }

        public String getPath() {
            return path;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            final MapPath path1 = (MapPath) o;
            return Objects.equals(getLetters(), path1.getLetters()) &&
                    Objects.equals(getPath(), path1.getPath());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getLetters(), getPath());
        }

        @Override
        public String toString() {
            return "Path{" +
                    "letters=" + letters +
                    ", path=" + path +
                    '}';
        }
    }

}
