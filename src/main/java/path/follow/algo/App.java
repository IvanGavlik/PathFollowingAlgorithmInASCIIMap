package path.follow.algo;

import path.follow.algo.graph.UnidirectionalGraph;
import path.follow.algo.load.ASCIIMapLoader;
import path.follow.algo.path.FindPath;

import java.util.List;

/**
 * App - starts application.
 *
 * @author ivan.gavlik
 */
public final class App {

    private App() { }

    /**
     * App starts here.
     *
     * @param args cmd arguments
     */
    public static void main(final String[] args) {
        final ASCIIMapLoader asciiMapLoader = ASCIIMapLoader.getInstance(ASCIIMapLoader.LoaderInstance.FILE);
        final List<String> asciiMap =  asciiMapLoader.load();

        final UnidirectionalGraph<String> graph = UnidirectionalGraph.getInstance(UnidirectionalGraph.GraphInstance.MATRIX, asciiMap);

        final FindPath<String> findPath = FindPath.getInstance(FindPath.FindPathInstance.DEPTH_FIRST, graph);
//        findPath.hasPathTo();
//        findPath.pathTo();
    }
}
