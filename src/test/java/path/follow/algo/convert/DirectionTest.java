package path.follow.algo.convert;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Tests for {@link Direction}.
 *
 * @author ivan.gavlik
 */
public class DirectionTest {


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
        // on letter keep direction first
        Direction[] directions = Direction.getDirectionsForChar(Direction.LEFT, 'A');
        Direction[] expectedDirections = new Direction[]{Direction.LEFT, Direction.RIGHT,
                Direction.TOP, Direction.BOTTOM};
        Assertions.assertEquals(true, Arrays.equals(expectedDirections, directions));

        directions = Direction.getDirectionsForChar(Direction.TOP, 'A');
        expectedDirections = new Direction[]{Direction.TOP, Direction.BOTTOM,
                Direction.RIGHT, Direction.LEFT};
        Assertions.assertEquals(true, Arrays.equals(expectedDirections, directions));

        // on + change direction
        directions = Direction.getDirectionsForChar(Direction.LEFT, '+');
        expectedDirections = new Direction[]{Direction.TOP, Direction.BOTTOM,
                Direction.LEFT, Direction.RIGHT};
        Assertions.assertEquals(true, Arrays.equals(expectedDirections, directions));

        directions = Direction.getDirectionsForChar(Direction.TOP, '+');
        expectedDirections = new Direction[]{Direction.LEFT, Direction.RIGHT,
                Direction.TOP, Direction.BOTTOM};
        Assertions.assertEquals(true, Arrays.equals(expectedDirections, directions));

        // on other ASCII value keep direction
        directions = Direction.getDirectionsForChar(Direction.LEFT, '&');
        expectedDirections = new Direction[]{Direction.LEFT};
        Assertions.assertEquals(true, Arrays.equals(expectedDirections, directions));

        directions = Direction.getDirectionsForChar(Direction.TOP, '&');
        expectedDirections = new Direction[]{Direction.TOP};
        Assertions.assertEquals(true, Arrays.equals(expectedDirections, directions));

        directions = Direction.getDirectionsForChar(Direction.RIGHT, '#');
        expectedDirections = new Direction[]{Direction.RIGHT};
        Assertions.assertEquals(true, Arrays.equals(expectedDirections, directions));

        directions = Direction.getDirectionsForChar(Direction.BOTTOM, '#');
        expectedDirections = new Direction[]{Direction.BOTTOM};
        Assertions.assertEquals(true, Arrays.equals(expectedDirections, directions));
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
            Direction.getDirectionsForChar(Direction.TOP, null);
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
        Direction[] directions = Direction.getDirectionsForChar(Direction.LEFT, ' ');
        Direction[] expectChangeDirection = new Direction[]{Direction.LEFT};
        Assertions.assertEquals(true, Arrays.equals(expectChangeDirection, directions));

        directions = Direction.getDirectionsForChar(Direction.TOP, ' ');
        expectChangeDirection = new Direction[]{Direction.TOP};
        Assertions.assertEquals(true, Arrays.equals(expectChangeDirection, directions));
    }
}
