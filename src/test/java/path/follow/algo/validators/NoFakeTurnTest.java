package path.follow.algo.validators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests for NoFakeTurn.
 *
 * @author ivan.gavlik
 */
public class NoFakeTurnTest {


    /**
     * Fake turn is in map.
     */
    @Test
    public void fakeTurnExist() {
        final List<String> fakeTurn = new ArrayList<>();
        fakeTurn.add("@--A---B--+---x");

        Assertions.assertFalse(new NoFakeTurn().test(fakeTurn));
    }

    /**
     * Fake turn is not in map.
     */
    @Test
    public void fakeTurnDoesNotExist() {
        final List<String> fakeTurn = new ArrayList<>();
        fakeTurn.add("@--A---B--D---x");

        Assertions.assertTrue(new NoFakeTurn().test(fakeTurn));
    }

    /**
     * Test Invalid inputs.
     */
    @Test
    public void invalidInputs() {
        Assertions.assertFalse(new NoFakeTurn().test(null));
        Assertions.assertTrue(new NoFakeTurn().test(new ArrayList<>()));

        final List<String> map = new ArrayList<>();
        map.add("@");
        Assertions.assertTrue(new NoFakeTurn().test(map));

        final List<String> map2 = new ArrayList<>();
        map2.add("@x");
        Assertions.assertTrue(new NoFakeTurn().test(map2));
    }
}
