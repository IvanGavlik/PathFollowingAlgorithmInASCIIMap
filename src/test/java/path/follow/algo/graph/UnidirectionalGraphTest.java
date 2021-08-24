package path.follow.algo.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import path.follow.algo.graph.impl.UnidirectionalMatrixGraph;

/**
 * Tests for {@link UnidirectionalGraph}.
 *
 * Note only for static methods.
 *
 * @author ivan.gavlik
 */
public class UnidirectionalGraphTest {


    /**
     *  Test create new UnidirectionalGraph instance.
     *
     *  Input
     *  {@link UnidirectionalGraph.GraphInstance}
     *
     *  Output
     *  Corresponding instance or {@link RuntimeException}.
     */
    @Test
    public void getInstance() {
        final UnidirectionalGraph graph = UnidirectionalGraph.getInstance(UnidirectionalGraph.GraphInstance.MATRIX);
        Assertions.assertEquals(UnidirectionalMatrixGraph.class, graph.getClass());


        Assertions.assertThrows(RuntimeException.class, () -> {
            UnidirectionalGraph.getInstance(UnidirectionalGraph.GraphInstance.LIST);
        });
    }
}
