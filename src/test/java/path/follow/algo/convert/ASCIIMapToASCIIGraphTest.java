package path.follow.algo.convert;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import path.follow.algo.graph.UnidirectionalGraph;
import path.follow.algo.graph.vertex.CharacterNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests for {@link ASCIIMapToASCIIGraph}.
 *
 * @author ivan.gavlik
 */
public class ASCIIMapToASCIIGraphTest {


    /**
     * Test basic ASCIi map.
     *
     * Have all elements important for control flow and bossiness logic.
     *
     * Input:
     * ASCIi map that contains
     * @ - start character
     * x - end character
     * + - try changer direction
     * A - try change direction
     * | - keep direction
     * - - keep direction
     *
     * Output:
     * ASCIIGraph
     *  - start node
     *  - end node
     *  - graph
     */
    @Test
    public void basic() {
        final Character startChar = '@';
        final Character endChar = 'x';
        final ASCIIGraph graph = ASCIIMapToASCIIGraph.convert(getBasicASCIIMap() , startChar, endChar);

        Assertions.assertEquals(startChar, graph.getStart().getValue());
        Assertions.assertEquals(endChar, graph.getEnd().getValue());
        final UnidirectionalGraph<CharacterNode> expectedGraph = getBasicASCIIGraph();
        Assertions.assertEquals(expectedGraph.getEdgesCount(), graph.getGraph().getEdgesCount());
        Assertions.assertEquals(expectedGraph.getVertexCount(), graph.getGraph().getVertexCount());
        Assertions.assertEquals(expectedGraph.toString(), graph.getGraph().toString());
    }

    /**
     * Test when null is passed.
     *
     * Input
     * one of params is null
     *
     * Output
     * {@link IllegalArgumentException}
     */
    @Test
    public void testNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            ASCIIMapToASCIIGraph.convert(null, '@', 'X');
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            ASCIIMapToASCIIGraph.convert(getBasicASCIIMap(), null, 'X');
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            ASCIIMapToASCIIGraph.convert(getBasicASCIIMap(), '@', null);
        });
    }

    /**
     * Test when empty ASCIIMap is passed.
     *
     * Input
     * empty ASCIIMap
     *
     * Output
     * ASCIIGraph with empty list
     * and start and end are null
     */
    @Test
    @SuppressWarnings("checkstyle:MagicNumber")
    public void testEmpty() {
        final ASCIIGraph graph = ASCIIMapToASCIIGraph.convert(new ArrayList<String>(), '@', 'x');
        Assertions.assertNotNull(graph);
        Assertions.assertNotNull(graph.getGraph());
        Assertions.assertEquals(0, graph.getGraph().getVertexCount());
        Assertions.assertEquals(0, graph.getGraph().getEdgesCount());
        Assertions.assertNull(graph.getStart());
        Assertions.assertNull(graph.getEnd());
    }

    private List<String> getBasicASCIIMap() {
        final List<String> list = new ArrayList<>();
        list.add("@-A-+");
        list.add("    |");
        list.add("    x");
        return list;
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    private UnidirectionalGraph<CharacterNode> getBasicASCIIGraph() {
        final UnidirectionalGraph<CharacterNode> graph = UnidirectionalGraph.getInstance(UnidirectionalGraph.GraphInstance.MATRIX);

        final CharacterNode c00 = new CharacterNode(0, 0, '@');
        graph.addVertex(c00);

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

        final CharacterNode c42 = new CharacterNode(4, 2, 'x');
        graph.addVertex(c42);

        graph.addEdge(c00, c10);

        graph.addEdge(c10, c00);
        graph.addEdge(c10, c20);

        graph.addEdge(c20, c30);
        graph.addEdge(c20, c10);

        graph.addEdge(c30, c20);
        graph.addEdge(c30, c40);

        graph.addEdge(c40, c41);
        graph.addEdge(c40, c30);

        graph.addEdge(c41, c42);
        graph.addEdge(c41, c40);

        graph.addEdge(c42, c41);

        return graph;
    }
}
