package path.follow.algo;

import path.follow.algo.graph.UnidirectionalGraph;
import path.follow.algo.load.ASCIIMapLoader;
import path.follow.algo.path.FindPath;

import java.util.List;
import java.util.Objects;

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
        final ASCIIMapLoader asciiMapLoader = ASCIIMapLoader.getInstance(ASCIIMapLoader.LoaderInstance.FILE);
        final List<String> asciiMap =  asciiMapLoader.load();

        final UnidirectionalGraph<String> graph = UnidirectionalGraph.getInstance(UnidirectionalGraph.GraphInstance.MATRIX, asciiMap);

        final FindPath<String> findPath = FindPath.getInstance(FindPath.FindPathInstance.DEPTH_FIRST, graph);
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
