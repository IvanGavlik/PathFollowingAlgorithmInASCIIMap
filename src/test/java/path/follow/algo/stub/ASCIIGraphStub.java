package path.follow.algo.stub;

import path.follow.algo.convert.ASCIIGraph;
import path.follow.algo.graph.vertex.CharacterNode;

import java.util.ArrayList;
import java.util.List;


/**
 * ASCIIGraph related stub.
 *
 * @author ivan.gavlik
 */
public final class ASCIIGraphStub {

    private ASCIIGraphStub() { }

    /**
     * Graph.
     *         @-A-+
     *             |
     *             x
     *
     * where @ is start
     * and x is end
     *
     * @return {@link ASCIIGraph}
     */
    @SuppressWarnings("checkstyle:MagicNumber")
    public static ASCIIGraph getBasicASCIIGraph() {
        return new ASCIIGraph(UnidirectionalGraphStub.getBasicASCIIGraph(),
                UnidirectionalGraphStub.BASIC_START,
                UnidirectionalGraphStub.BASIC_END);
    }

    /**
     * Path form start to end noede for {@link #getBasicASCIIGraph()}.
     *
     * @return path {@link List<CharacterNode>}
     */
    @SuppressWarnings("checkstyle:MagicNumber")
    public static List<CharacterNode> getBasicPath() {
        final List<CharacterNode> path = new ArrayList<>();
        path.add(UnidirectionalGraphStub.BASIC_START);
        path.add(new CharacterNode(0, 1, '-'));
        path.add(new CharacterNode(0, 2, 'A'));
        path.add(new CharacterNode(0, 3, '-'));
        path.add(new CharacterNode(0, 4, '+'));
        path.add(new CharacterNode(1, 4, '|'));
        path.add(UnidirectionalGraphStub.BASIC_END);
        return path;
    }

    /**
     * Get broken path for basic graph
     * where start is @
     * and end x
     *
     * Basic Graph.
     *         @-A-+
     *
     *             x
     *
     * @return path {@link List<CharacterNode>}
     */
    @SuppressWarnings("checkstyle:MagicNumber")
    public static List<CharacterNode> getBasicBrokenPath() {
        final List<CharacterNode> path = new ArrayList<>();

        path.add(UnidirectionalGraphStub.BASIC_START);
        path.add(new CharacterNode(1, 0, '-'));
        path.add(new CharacterNode(2, 0, 'A'));
        path.add(new CharacterNode(3, 0, '-'));
        path.add(new CharacterNode(4, 0, '+'));

        path.add(UnidirectionalGraphStub.BASIC_END);

        return path;
    }
}
