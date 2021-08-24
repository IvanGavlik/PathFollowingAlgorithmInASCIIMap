package path.follow.algo.stub;

import path.follow.algo.graph.UnidirectionalGraph;
import path.follow.algo.graph.vertex.CharacterNode;

/**
 * UnidirectionalGraph related stub.
 *
 * @author ivan.gavlik
 */
public final class UnidirectionalGraphStub {

    /**
     * Start node in {@link #getBasicASCIIGraph()}.
     */
    @SuppressWarnings("checkstyle:MagicNumber")
    public static final CharacterNode BASIC_START = new CharacterNode(0, 0, '@');

    /**
     * End node in {@link #getBasicASCIIGraph()}.
     */
    @SuppressWarnings("checkstyle:MagicNumber")
    public static final CharacterNode BASIC_END = new CharacterNode(4, 2, 'x');

    private UnidirectionalGraphStub() { }

    /**
     * Unidirectional graph.
     *         @-A-+
     *             |
     *             x
     *
     * @return {@link UnidirectionalGraph<CharacterNode>}
     */
    @SuppressWarnings("checkstyle:MagicNumber")
    public static UnidirectionalGraph<CharacterNode> getBasicASCIIGraph() {
        final UnidirectionalGraph<CharacterNode> graph = UnidirectionalGraph.getInstance(UnidirectionalGraph.GraphInstance.MATRIX);

        graph.addVertex(BASIC_START);

        final CharacterNode c10 = new CharacterNode(1, 0, '-');
        graph.addVertex(c10);

        final CharacterNode c20 = new CharacterNode(2, 0, 'A');
        graph.addVertex(c20);

        final CharacterNode c30 = new CharacterNode(3, 0, '-');
        graph.addVertex(c30);

        final CharacterNode c40 = new CharacterNode(4, 0, '+');
        graph.addVertex(c40);

        final CharacterNode c41 = new CharacterNode(4, 1, '|');
        graph.addVertex(c41);

        graph.addVertex(BASIC_END);

        graph.addEdge(BASIC_START, c10);

        graph.addEdge(c10, BASIC_START);
        graph.addEdge(c10, c20);

        graph.addEdge(c20, c30);
        graph.addEdge(c20, c10);

        graph.addEdge(c30, c20);
        graph.addEdge(c30, c40);

        graph.addEdge(c40, c41);
        graph.addEdge(c40, c30);

        graph.addEdge(c41, BASIC_END);
        graph.addEdge(c41, c40);

        graph.addEdge(BASIC_END, c41);

        return graph;
    }

}
