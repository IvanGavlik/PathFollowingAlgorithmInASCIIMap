package path.follow.algo.validators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import path.follow.algo.stub.ASCIIMapStub;

import java.util.ArrayList;
import java.util.List;

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
        final List<String> map = new ArrayList<>();
        map.add("@--+");
        map.add("   |");
        map.add("   x");
        Assertions.assertTrue(new PathIsNotBroken().test(map));

        Assertions.assertTrue(new PathIsNotBroken().test(ASCIIMapStub.getBasicASCIIMap().getMap()));

        final List<String> map2 = new ArrayList<>();
        map2.add("@x");
        Assertions.assertTrue(new PathIsNotBroken().test(map2));

    }

    /**
     * Test path is broken.
     */
    @Test
    public void patBroken() {
        final List<String> map = new ArrayList<>();
        map.add("-----");
        map.add("     ");
        map.add("     x");
        Assertions.assertFalse(new PathIsNotBroken().test(map));

        final List<String> map2 = new ArrayList<>();
        map2.add("@- +");
        Assertions.assertFalse(new PathIsNotBroken().test(map2));

        final List<String> map3 = new ArrayList<>();
        map3.add("@");
        map3.add(" ");
        map3.add("A");
        Assertions.assertFalse(new PathIsNotBroken().test(map3));


        final List<String> map4 = new ArrayList<>();
        map4.add("@--A-+");
        map4.add("     |");
        map4.add("      ");
        map4.add("      B-x");
        Assertions.assertFalse(new PathIsNotBroken().test(map4));
    }

    /**
     * Test on invalid input.
     */
    @Test
    public void onInvalidInput() {
        Assertions.assertFalse(new PathIsNotBroken().test(null));
        Assertions.assertTrue(new PathIsNotBroken().test(new ArrayList<>()));
    }

}
