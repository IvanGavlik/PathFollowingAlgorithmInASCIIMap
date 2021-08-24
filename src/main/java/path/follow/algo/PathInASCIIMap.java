package path.follow.algo;

import path.follow.algo.convert.ASCIIGraph;
import path.follow.algo.convert.ASCIIMapToASCIIGraph;
import path.follow.algo.graph.vertex.CharacterNode;
import path.follow.algo.load.ASCIIMapLoader;
import path.follow.algo.path.FindPath;

import java.util.List;
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
    private static final Character DEFAULT_START = '@';
    private static final Character DEFAULT_END = 'x';

    private PathInASCIIMap() { }

    /**getEdgesCount
     * App starts here.
     *
     * @param args cmd arguments
     */
    @SuppressWarnings("checkstyle:Regexp")
    public static void main(final String[] args) {
        final MapPath path = find(args);
        System.out.println("Letters " + path.getLetters());
        System.out.println("Path as characters " + path.getPath());
    }

    /**
     * Find and return path if exist.
     *
     * @param args program args
     * @return MapPath
     */
    public static MapPath find(final String[] args) {

        final ASCIIMapLoader asciiMapLoader = ASCIIMapLoader.getInstance(args[FIRST_PARAM]);
        final List<String> asciiMap = asciiMapLoader.load();

        final Character startChar = haveValue(args[SECOND_PARAM]) ?
                getFirstCharacter(args[SECOND_PARAM]) : DEFAULT_START;
        final Character endChar = haveValue(args[THIRD_PARAM]) ?
                getFirstCharacter(args[THIRD_PARAM]) : DEFAULT_END;
        final ASCIIGraph graph = ASCIIMapToASCIIGraph.convert(asciiMap, startChar, endChar);

        final FindPath<CharacterNode> findPath = FindPath.getInstance(FindPath.FindPathInstance.DEPTH_FIRST, graph.getGraph());
        return new MapPath(null, null);
    }



    /**
     * Represents output of program.
     * Output is collected letters on path and path as characters.
     */
    static final class MapPath {
        private final List<Character> letters;
        private final List<Character> path;

        /**
         * Create new MapPath.
         *
         * @param letters {@link List<Character>}
         * @param path {@link List<Character>}
         */
        MapPath(final List<Character> letters, final List<Character> path) {
            this.letters = letters;
            this.path = path;
        }

        public List<Character> getLetters() {
            return letters;
        }

        public List<Character> getPath() {
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
