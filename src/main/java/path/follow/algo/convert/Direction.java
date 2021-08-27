package path.follow.algo.convert;


/**
 * Represents direction,
 *
 * x and y are used to indicate movement.
 * Where x is movement on X Axis and y is movement on Y Axis
 *
 * @author ivan.gavlik
 */
public enum Direction {
    /**
     * Move right one step.
     */
    RIGHT(0, -1),

    /**
     * Move left one step.
     */
    LEFT(0, 1),

    /**
     * Move top one step.
     */
    TOP(1, 0),

    /**
     * Move bottom one step.
     */
    BOTTOM(-1, 0);

    private int x;
    private int y;

    /**
     * Create direction.
     *
     * @param x X Axis
     * @param y Y Axis
     */
    Direction(final int y, final int x) {
        this.y = y;
        this.x = x;
    }

    /**
     * Get movement on X Axis.
     *
     * @return movement on X Axis.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Get movement on Y Axis.
     *
     * @return movement on Y Axis.
     */
    public int getY() {
        return this.y;
    }

    /**
     * Keep direction.
     *
     * @param directionType Direction
     * @return list of Directions where current direction on Axis is returned first and opposite directions last.
     */
    private static Direction[] keepDirectionFirst(final Direction directionType) {
        switch (directionType) {
            case TOP:
                return new Direction[]{Direction.TOP, Direction.BOTTOM, Direction.RIGHT, Direction.LEFT};
            case BOTTOM:
                return new Direction[]{Direction.BOTTOM, Direction.TOP, Direction.RIGHT, Direction.LEFT};
            case RIGHT:
                return new Direction[]{Direction.RIGHT, Direction.LEFT, Direction.TOP, Direction.BOTTOM};
            case LEFT:
                return new Direction[]{Direction.LEFT, Direction.RIGHT, Direction.TOP, Direction.BOTTOM};
            default:
                throw new IllegalArgumentException();
        }
    }

    /**
     * Change direction.
     *
     * @param directionType Direction
     * @return list of Directions where opposite directions is returned first.
     */
    private static Direction[] changedDirectionsFirst(final Direction directionType) {
        switch (directionType) {
            case TOP:
                return new Direction[]{Direction.LEFT, Direction.RIGHT, Direction.TOP, Direction.BOTTOM};
            case BOTTOM:
                return new Direction[]{Direction.LEFT, Direction.RIGHT, Direction.BOTTOM, Direction.TOP};
            case RIGHT:
                return new Direction[]{Direction.TOP, Direction.BOTTOM, Direction.RIGHT, Direction.LEFT};
            case LEFT:
                return new Direction[]{Direction.TOP, Direction.BOTTOM, Direction.LEFT, Direction.RIGHT};
            default:
                throw new IllegalArgumentException();
        }
    }

    /**
     * Get directions for passed character.
     *
     * If passed character is letter or + change current direction, else continue on same direction.
     *
     * @param directionType current {@link Direction}
     * @param character Character
     * @return directions for passed character
     */
    public static Direction[] getDirectionsForChar(final Direction directionType, final Character character) {
        if (character == null) {
            throw new IllegalArgumentException("Character is null");
        }
        if ( '+' == character) {
            return changedDirectionsFirst(directionType);
        }
        if  (Character.isLetter(character)) {
            return keepDirectionFirst(directionType);
        }
        return new Direction[]{directionType};
    }

}
