package path.follow.algo.graph.impl;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import path.follow.algo.graph.UnidirectionalGraph;

/**
 * Tests for {@link UnidirectionalGraph}.
 *
 * @author ivan.gavlik
 */
public class UnidirectionalMatrixGraphTest {


    private UnidirectionalGraph<Integer> graph;

    /**
     * Initialise graph before each test.
     */
    @BeforeEach
    public void init() {
        this.graph = UnidirectionalGraph.getInstance(UnidirectionalGraph.GraphInstance.MATRIX);
    }

    /**
     * Test empty graph.
     */
    @Test
    public void testEmpty() {
        Assertions.assertEquals(0, this.graph.getEdgesCount());
        Assertions.assertEquals(0, this.graph.getVertexCount());
        Assertions.assertEquals(0, this.graph.getAdj(1).size());
    }

    /**
     * Test basic graph.
     */
    @Test
    @SuppressWarnings("checkstyle:MagicNumber")
    public void basicGraph() {

        // add one vertex
        this.graph.addVertex(0);

        Assertions.assertEquals(1, this.graph.getVertexCount());
        Assertions.assertEquals(0, this.graph.getEdgesCount());
        Assertions.assertEquals(0, this.graph.getAdj(1).size());
        Assertions.assertEquals(true, this.graph.hasVertex(0));
        Assertions.assertEquals(false, this.graph.hasVertex(1));

        // add one more vertex
        this.graph.addVertex(1);

        Assertions.assertEquals(2, this.graph.getVertexCount());
        Assertions.assertEquals(0, this.graph.getEdgesCount());
        Assertions.assertEquals(0, this.graph.getAdj(1).size());
        Assertions.assertEquals(true, this.graph.hasVertex(0));
        Assertions.assertEquals(true, this.graph.hasVertex(1));


        // add edge form 0 to 1
        this.graph.addEdge(0, 1);
        Assertions.assertEquals(2, this.graph.getVertexCount());
        Assertions.assertEquals(1, this.graph.getEdgesCount());
        Assertions.assertEquals(1, this.graph.getAdj(0).size());
        Assertions.assertEquals(0, this.graph.getAdj(1).size());
        Assertions.assertEquals(true, this.graph.hasVertex(0));
        Assertions.assertEquals(true, this.graph.hasVertex(1));
        Assertions.assertEquals(true, this.graph.hasEdge(0, 1));
        Assertions.assertEquals(false, this.graph.hasEdge(1, 0));
    }

    /**
     * Test edge on same vertex.
     */
    @Test
    @SuppressWarnings("checkstyle:MagicNumber")
    public void edgeOnSameVertex() {

        // add one vertex
        this.graph.addVertex(0);

        Assertions.assertEquals(1, this.graph.getVertexCount());
        Assertions.assertEquals(0, this.graph.getEdgesCount());
        Assertions.assertEquals(0, this.graph.getAdj(1).size());
        Assertions.assertEquals(true, this.graph.hasVertex(0));
        Assertions.assertEquals(false, this.graph.hasVertex(1));

        // add edge form 0 to 0
        this.graph.addEdge(0, 0);
        Assertions.assertEquals(1, this.graph.getVertexCount());
        Assertions.assertEquals(1, this.graph.getEdgesCount());
        Assertions.assertEquals(1, this.graph.getAdj(0).size());
        Assertions.assertEquals(0, this.graph.getAdj(1).size());
        Assertions.assertEquals(true, this.graph.hasVertex(0));
        Assertions.assertEquals(false, this.graph.hasVertex(1));
        Assertions.assertEquals(true, this.graph.hasEdge(0, 0));
        Assertions.assertEquals(false, this.graph.hasEdge(0, 1));
        Assertions.assertEquals(false, this.graph.hasEdge(1, 0));
    }
}
