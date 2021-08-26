package path.follow.algo.convert;


/**
 * Represents direction,
 *
 * x and y are used to indicate movement in some direction.
 * Where x is movement on X Axis and y is movement on Y Axis
 *
 * @author ivan.gavlik
 */
public enum DirectionType { // todo RENAME Direction
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
    DirectionType(final int y, final int x) {
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
    private static DirectionType[] keepDirectionFirst(final DirectionType directionType) {
        switch (directionType) {
            case TOP:
                return new DirectionType[]{DirectionType.TOP, DirectionType.BOTTOM, DirectionType.RIGHT, DirectionType.LEFT};
            case BOTTOM:
                return new DirectionType[]{DirectionType.BOTTOM, DirectionType.TOP, DirectionType.RIGHT, DirectionType.LEFT};
            case RIGHT:
                return new DirectionType[]{DirectionType.LEFT, DirectionType.RIGHT, DirectionType.TOP, DirectionType.BOTTOM};
            case LEFT:
                return new DirectionType[]{DirectionType.RIGHT, DirectionType.LEFT, DirectionType.TOP, DirectionType.BOTTOM};
            default:
                throw new IllegalArgumentException();
        }
    }

    /**
     * Change direction.
     *
     * @param directionType Direction
     * @return list of Directions where current direction on Axis is returned last, and opposite directions first
     */
    private static DirectionType[] changedDirectionsFirst(final DirectionType directionType) {
        switch (directionType) {
            case TOP:
                return new DirectionType[]{DirectionType.LEFT, DirectionType.RIGHT, DirectionType.TOP, DirectionType.BOTTOM};
            case BOTTOM:
                return new DirectionType[]{DirectionType.LEFT, DirectionType.RIGHT, DirectionType.BOTTOM, DirectionType.TOP};
            case RIGHT:
                return new DirectionType[]{DirectionType.TOP, DirectionType.BOTTOM, DirectionType.RIGHT, DirectionType.LEFT};
            case LEFT:
                return new DirectionType[]{DirectionType.TOP, DirectionType.BOTTOM, DirectionType.LEFT, DirectionType.RIGHT};
            default:
                throw new IllegalArgumentException();
        }
    }

    /**
     * Get directions for passed character.
     *
     * If passed character is letter or + change current direction, else continue on same direction.
     *
     * @param directionType current {@link DirectionType}
     * @param character Character
     * @return directions for passed character
     */
    public static DirectionType[] getDirectionsForChar(final DirectionType directionType, final Character character) {
        if (character == null) {
            throw new IllegalArgumentException("Character is null");
        }
        if ( '+' == character) {
            return changedDirectionsFirst(directionType);
        }
        if  (Character.isLetter(character)) {
            return keepDirectionFirst(directionType);
        }
        return new DirectionType[]{directionType};
    }

}
