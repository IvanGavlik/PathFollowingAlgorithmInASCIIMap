package path.follow.algo.validators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import path.follow.algo.graph.vertex.CharacterNode;
import path.follow.algo.path.FindPath;
import path.follow.algo.stub.FindPathStub;
import path.follow.algo.stub.UnidirectionalGraphStub;

import java.util.ArrayList;

/**
 * Tests for PathIsNotBroken.
 *
 * @author ivan.gavlik
 */
public class PathIsNotBrokenTest {

    /**
     * Test path in not broken.
     */
    @Test
    public void patNotBroken() {
        final boolean result =  new PathIsNotBroken(UnidirectionalGraphStub.BASIC_END)
                .test(FindPathStub.getBasicFindPath());
        Assertions.assertTrue(result);
    }

    /**
     * Test path is broken.
     */
    @Test
    public void patBroken() {
        final boolean result =  new PathIsNotBroken(UnidirectionalGraphStub.BASIC_END)
                .test(FindPathStub.getBasicBrokenFindPath());
        Assertions.assertFalse(result);
    }

    /**
     * Test on null.
     */
    @Test
    public void onNull() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new PathIsNotBroken(null).test(FindPathStub.getBasicBrokenFindPath()));

        final boolean result =  new PathIsNotBroken(UnidirectionalGraphStub.BASIC_END)
                .test(null);
        Assertions.assertFalse(result);
    }

    /**
     * Test on empty.
     */
    @Test
    public void onEmpty() {

        @SuppressWarnings("checkstyle:JavadocType")
        class FindPathEmpty implements FindPath<CharacterNode> {

            @Override
            public boolean hasPathTo(final CharacterNode node) {
                return false;
            }

            @Override
            public Iterable<CharacterNode> pathTo(final CharacterNode node) {
                return new ArrayList<>();
            }
        }

        final boolean result =  new PathIsNotBroken(UnidirectionalGraphStub.BASIC_END)
                .test(new FindPathEmpty());
        Assertions.assertFalse(result);
    }
}
