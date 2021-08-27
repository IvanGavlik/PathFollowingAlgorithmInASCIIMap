package path.follow.algo.validators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests for NoMultipleStartingPaths.
 *
 * @author ivan.gavlik
 */
public class NoMultipleStartingPathsTest {

    /**
     * Input has multiple starting paths.
     */
    @Test
    public void multipleStartingPaths() {
        final List<String> map = new ArrayList<>();
        map.add("---@----");
        Assertions.assertFalse(new NoMultipleStartingPaths('@').test(map));

        final List<String> map2 = new ArrayList<>();
        map2.add("|");
        map2.add("@");
        map2.add("|");
        Assertions.assertFalse(new NoMultipleStartingPaths('@').test(map2));

        final List<String> map3 = new ArrayList<>();
        map3.add("|");
        map3.add("@----");
        Assertions.assertFalse(new NoMultipleStartingPaths('@').test(map3));

        final List<String> map4 = new ArrayList<>();
        map4.add("@----");
        map4.add("|");
        Assertions.assertFalse(new NoMultipleStartingPaths('@').test(map4));

        final List<String> map5 = new ArrayList<>();
        map5.add("-@-");
        Assertions.assertFalse(new NoMultipleStartingPaths('@').test(map5));
    }

    /**
     * Input does not have multiple starting paths.
     */
    @Test
    public void noMultipleStartingPaths() {
        final List<String> map = new ArrayList<>();
        map.add("@----");
        Assertions.assertTrue(new NoMultipleStartingPaths('@').test(map));

        final List<String> map2 = new ArrayList<>();
        map2.add("");
        map2.add("@");
        map2.add("|");
        Assertions.assertTrue(new NoMultipleStartingPaths('@').test(map2));

        final List<String> map3 = new ArrayList<>();
        map3.add("|");
        map3.add("@");
        Assertions.assertTrue(new NoMultipleStartingPaths('@').test(map3));

        final List<String> map4 = new ArrayList<>();
        map4.add("---@");
        Assertions.assertTrue(new NoMultipleStartingPaths('@').test(map4));
    }

    /**
     * Test invalid input.
     */
    @Test
    public void invalidInput() {
        Assertions.assertFalse(new NoMultipleStartingPaths('@').test(null));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new NoMultipleStartingPaths(null).test(new ArrayList<>());
        });

        Assertions.assertTrue(new NoMultipleStartingPaths('@').test(new ArrayList<>()));

        final List<String> map = new ArrayList<>();
        map.add("--");
        Assertions.assertTrue(new NoMultipleStartingPaths('@').test(map));


        final List<String> map2 = new ArrayList<>();
        map2.add("@");
        Assertions.assertTrue(new NoMultipleStartingPaths('@').test(map2));
    }
}
