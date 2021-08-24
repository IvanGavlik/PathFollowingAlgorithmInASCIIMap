package path.follow.algo.path.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import path.follow.algo.convert.ASCIIGraph;
import path.follow.algo.graph.vertex.CharacterNode;
import path.follow.algo.path.FindPath;
import path.follow.algo.stub.ASCIIGraphStub;


import java.util.ArrayList;

/**
 * Tests for {@link DepthFirstPath}.
 *
 * @author ivan.gavlik
 */
public class DepthFirstPathTest {

    private FindPath findPath;

    /**
     * Test find path.
     */
    @Test
    public void findPath() {
        final ASCIIGraph graph = ASCIIGraphStub.getBasicASCIIGraph();
        this.findPath = new DepthFirstPath(graph.getGraph(), graph.getStart());
        final boolean havePath = this.findPath.hasPathTo(graph.getEnd());
        Assertions.assertTrue(havePath);
        Assertions.assertFalse(this.findPath.hasPathTo(new CharacterNode(1, 1, 'Z')));
        Assertions.assertFalse(this.findPath.hasPathTo(null));
        Assertions.assertFalse(this.findPath.hasPathTo(new CharacterNode(1, 1, null)));

        final Iterable<CharacterNode> path = this.findPath.pathTo(graph.getEnd());
        Assertions.assertEquals(ASCIIGraphStub.getBasicPath().toString(), path.toString());
        Assertions.assertEquals(new ArrayList(), this.findPath.pathTo(new CharacterNode(1, 1, 'Z')));
        Assertions.assertEquals(new ArrayList(), this.findPath.pathTo(null));
        Assertions.assertEquals(new ArrayList(), this.findPath.pathTo(new CharacterNode(1, 1, null)));
    }


}
