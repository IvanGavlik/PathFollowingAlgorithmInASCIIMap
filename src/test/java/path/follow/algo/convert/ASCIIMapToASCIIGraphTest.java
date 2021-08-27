package path.follow.algo.convert;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import path.follow.algo.graph.UnidirectionalGraph;
import path.follow.algo.graph.vertex.CharacterNode;
import path.follow.algo.stub.ASCIIMapStub;
import path.follow.algo.stub.UnidirectionalGraphStub;

import java.util.ArrayList;

/**
 * Tests for {@link ASCIIMapToASCIIGraph}.
 *
 * @author ivan.gavlik
 */
public class ASCIIMapToASCIIGraphTest {


    /**
     * Test basic ASCIi map.
     *
     * Input:
     * ASCIi map that contains
     * @ - start character
     * x - end character
     * + - try changer direction
     * A - try changer direction
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
        final ASCIIGraph graph = ASCIIMapToASCIIGraph.convert(ASCIIMapStub.getBasicASCIIMap() , startChar, endChar);

        Assertions.assertEquals(startChar, graph.getStart().getValue());
        Assertions.assertEquals(endChar, graph.getEnd().getValue());
        final UnidirectionalGraph<CharacterNode> expectedGraph = UnidirectionalGraphStub.getBasicASCIIGraph();
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
            ASCIIMapToASCIIGraph.convert(ASCIIMapStub.getBasicASCIIMap(), null, 'X');
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            ASCIIMapToASCIIGraph.convert(ASCIIMapStub.getBasicASCIIMap(), '@', null);
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
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            ASCIIMapToASCIIGraph.convert(new ArrayList<String>(), '@', 'x');
        });
    }

    /**
     * Test when empty start or end character do not exist in ASCIIMap.
     *
     * Input
     * empty ASCIIMap
     * not existing start Character
     * not existing end Character
     *
     * Output
     * {@link IllegalArgumentException}
     */
    @Test
    @SuppressWarnings("checkstyle:MagicNumber")
    public void noStartNoEndCharacter() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            ASCIIMapToASCIIGraph.convert(ASCIIMapStub.getBasicASCIIMap(), 'Z', 'x');
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            ASCIIMapToASCIIGraph.convert(ASCIIMapStub.getBasicASCIIMap(), '@', '%');
        });
    }

}
