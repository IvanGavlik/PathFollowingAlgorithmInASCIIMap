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
import java.util.Objects;
import java.util.Optional;
import java.util.LinkedHashSet;
import java.util.Set;


/**
 * App - starts application.
 *
 * @author ivan.gavlik
 */
public final class PathInASCIIMap {

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

        final ProgramInput input = new ProgramInput(args);

        final ASCIIMapLoader asciiMapLoader = ASCIIMapLoader.getInstance(input.getFile());
        final List<String> asciiMap = asciiMapLoader.load();

        new Validation<>(asciiMap)
                .add(new MustHaveOneCharacter(input.getStart()))
                .add(new MustHaveOneCharacter(input.getEnd()))
                .add(new NoMultipleStartingPaths(input.getStart()))
                .add(new NoFakeTurn())
                .add(new PathIsNotBroken())
                .isValidOrElseThrow();

        final ASCIIGraph graph = ASCIIMapToASCIIGraph.convert(asciiMap, input.getStart(), input.getEnd());

        final FindPath<CharacterNode> findPath = new DepthFirstPath(graph.getGraph(), graph.getStart());

        final Iterable<CharacterNode> nodePath = findPath.pathTo(graph.getEnd());

        final List<Character> excludeStartAndEndCharacter = new ArrayList<>();
        excludeStartAndEndCharacter.add(input.getStart());
        excludeStartAndEndCharacter.add(input.getEnd());

        return new MapPath(nodePath, excludeStartAndEndCharacter);
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
            unieqeLetters.forEach(el -> lettersBuilder.append(el.getValue()));

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

    /**
     * Represents input of program.
     * Can have zero, one, two or three params
     * -f X for file path (default: lod internal map)
     * -s X for start char (default: @)
     * -e X for end char (default: x)
     *
     * if param is omitted default is used
     * -s and -e for value can not have + (it marks change or direction in map)
     *
     * Example 1.
     * -f //documents//map//basic.txt -s % -e #
     * Loads maps from file /documents//map//basic.txt, start char is % end char is #
     *
     * Example 2.
     * -f //documents//map//basic.txt -e #
     * Loads maps from file /documents//map//basic.txt, start char is @ (default) end char is #
     *
     * Example 3.
     *  -s $ -e #
     * Loads default map, start char is $ end char is #.
     * Default map only have path from @ and x so other params will not find any path.
     *
     */
    static final class ProgramInput {
        private static final String FILE_PARAM = "-f";
        private static final String START_CHAR_PARAM = "-s";
        private static final String END_CHAR_PARAM = "-e";

        private static final Character DEFAULT_START = '@';
        private static final Character DEFAULT_END = 'x';


        private String file;
        private Character start;
        private Character end;

        /**
         * Create new instance of {@link ProgramInput}.
         *
         * @param args {@link String[])
         */
        @SuppressWarnings({"checkstyle:MagicNumber"})
        ProgramInput(final String[] args) {
            if (Objects.isNull(args) || args.length == 0) {
                file = null;
                start = DEFAULT_START;
                end = DEFAULT_END;
            }
            for (int i = 0; i < args.length; i += 2) {
                final String input = args[i];
                switch (input) {
                    case FILE_PARAM:
                        file = getValueAt(args, i + 1)
                                .orElseThrow(IllegalArgumentException::new);
                        break;
                    case START_CHAR_PARAM:
                        start = getValueAt(args, i + 1)
                                .map(el -> el.charAt(0))
                                .orElseThrow(IllegalArgumentException::new);
                        break;
                    case END_CHAR_PARAM:
                        end = getValueAt(args, i + 1)
                            .map( el -> el.charAt(0))
                            .orElseThrow(IllegalArgumentException::new);
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
            }
        }

        @SuppressWarnings({"checkstyle:IllegalCatch"})
        private Optional<String> getValueAt(final String[] args, final int index) {
            try {
                return Optional.of(args[index]);
            } catch (Exception ex) {
                return Optional.empty();
            }
        }


        public String getFile() {
            return this.file;
        }

        public Character getStart() {
            return this.start != null ? this.start : DEFAULT_START;
        }

        public Character getEnd() {
            return this.end != null ? this.end : DEFAULT_END;
        }
    }
}
