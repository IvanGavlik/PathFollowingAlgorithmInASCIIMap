package path.follow.algo.convert;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Tests for {@link DirectionType}.
 *
 * @author ivan.gavlik
 */
public class DirectionTypeTest {


    /**
     * Test get Direction For Character.
     *
     * Input:
     * some Character
     *
     * Output:
     * if letter or + change direction else keep it.
     */
    @Test
    public void getDirection() {
        // on letter change direction
        DirectionType[] directions = DirectionType.getDirectionsForChar(DirectionType.LEFT, 'A');
        DirectionType[] expectChangeDirection = new DirectionType[]{DirectionType.RIGHT, DirectionType.LEFT,
                DirectionType.TOP, DirectionType.BOTTOM};
        Assertions.assertEquals(true, Arrays.equals(expectChangeDirection, directions));

        directions = DirectionType.getDirectionsForChar(DirectionType.TOP, 'A');
        expectChangeDirection = new DirectionType[]{DirectionType.TOP, DirectionType.BOTTOM,
                DirectionType.RIGHT, DirectionType.LEFT};
        Assertions.assertEquals(true, Arrays.equals(expectChangeDirection, directions));

        // on + change direction
        directions = DirectionType.getDirectionsForChar(DirectionType.LEFT, '+');
        expectChangeDirection = new DirectionType[]{DirectionType.TOP, DirectionType.BOTTOM,
                DirectionType.LEFT, DirectionType.RIGHT};
        Assertions.assertEquals(true, Arrays.equals(expectChangeDirection, directions));

        directions = DirectionType.getDirectionsForChar(DirectionType.TOP, '+');
        expectChangeDirection = new DirectionType[]{DirectionType.LEFT, DirectionType.RIGHT,
                DirectionType.TOP, DirectionType.BOTTOM};
        Assertions.assertEquals(true, Arrays.equals(expectChangeDirection, directions));

        // on other ASCII value keep direction
        directions = DirectionType.getDirectionsForChar(DirectionType.LEFT, '&');
        expectChangeDirection = new DirectionType[]{DirectionType.LEFT};
        Assertions.assertEquals(true, Arrays.equals(expectChangeDirection, directions));

        directions = DirectionType.getDirectionsForChar(DirectionType.TOP, '&');
        expectChangeDirection = new DirectionType[]{DirectionType.TOP};
        Assertions.assertEquals(true, Arrays.equals(expectChangeDirection, directions));

        // on other ASCII value keep direction
        directions = DirectionType.getDirectionsForChar(DirectionType.LEFT, '#');
        expectChangeDirection = new DirectionType[]{DirectionType.LEFT};
        Assertions.assertEquals(true, Arrays.equals(expectChangeDirection, directions));

        directions = DirectionType.getDirectionsForChar(DirectionType.TOP, '#');
        expectChangeDirection = new DirectionType[]{DirectionType.TOP};
        Assertions.assertEquals(true, Arrays.equals(expectChangeDirection, directions));
    }


    /**
     * Test change direction when null is passed.
     *
     * Input:
     * null
     *
     * Output:
     * IllegalArgumentException
     */
    @Test
    public void changeDirectionOnNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            DirectionType.getDirectionsForChar(DirectionType.TOP, null);
        });
    }

    /**
     * Test change direction when space is passed.
     *
     * Input:
     * space
     *
     * Output:
     * keep direction
     */
    @Test
    public void changeDirectionOnSpace() {
        DirectionType[] directions = DirectionType.getDirectionsForChar(DirectionType.LEFT, ' ');
        DirectionType[] expectChangeDirection = new DirectionType[]{DirectionType.LEFT};
        Assertions.assertEquals(true, Arrays.equals(expectChangeDirection, directions));

        directions = DirectionType.getDirectionsForChar(DirectionType.TOP, ' ');
        expectChangeDirection = new DirectionType[]{DirectionType.TOP};
        Assertions.assertEquals(true, Arrays.equals(expectChangeDirection, directions));
    }
}
