package path.follow.algo.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link Util}.
 *
 * @author ivan.gavlik
 */
public class UtilTest {


    /**
     * Test have value.
     */
    @Test
    public void haveValue() {
        Assertions.assertEquals(true, Util.haveValue(" A"));
        Assertions.assertEquals(false, Util.haveValue(null));
        Assertions.assertEquals(false, Util.haveValue(" "));
    }

    /**
     * Test get first character.
     */
    @Test
    public void firstCharacter() {
        Assertions.assertEquals('A', Util.getFirstCharacter("AB"));
        Assertions.assertEquals(' ', Util.getFirstCharacter(" B"));
        Assertions.assertEquals(' ', Util.getFirstCharacter(" "));
        Assertions.assertEquals('#', Util.getFirstCharacter("# "));
        Assertions.assertNull(Util.getFirstCharacter(null));

    }
}
