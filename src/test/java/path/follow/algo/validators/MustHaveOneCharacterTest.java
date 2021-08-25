package path.follow.algo.validators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests for MustHaveOneCharacter.
 *
 * @author ivan.gavlik
 */
public class MustHaveOneCharacterTest {


    /**
     * Test have one character.
     */
    @Test
    public void oneCharacter() {
        final List<String> data = new ArrayList<>();
        data.add("21a5vgfkdsč");
        Assertions.assertTrue(new MustHaveOneCharacter('a').test(data));
    }

    /**
     * Test does not have one character.
     */
    @Test
    public void oneCharacterNot() {
        final List<String> data = new ArrayList<>();
        data.add("21a5vgfkdsč");
        Assertions.assertFalse(new MustHaveOneCharacter('l').test(data));
    }

    /**
     * Test on null.
     */
    @Test
    public void onNull() {
        Assertions.assertFalse(new MustHaveOneCharacter('l').test(null));
        final List<String> data = new ArrayList<>();
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> Assertions.assertFalse(new MustHaveOneCharacter(null).test(data)));
    }
}
