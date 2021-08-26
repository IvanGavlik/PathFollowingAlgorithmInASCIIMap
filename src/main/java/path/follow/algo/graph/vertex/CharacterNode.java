package path.follow.algo.graph.vertex;

import java.util.Objects;

/**
 * Represents character node in ASCII map.
 *
 * @author ivan.gavlik
 */
public final class CharacterNode {
    private final int x;
    private final int y;
    private final Character value;

    /**
     * Create new CharacterNode.
     *
     * @param y position on Y Axis in map
     * @param x position on X Axis in map
     * @param value Character
     */
    public CharacterNode(final int y, final int x, final Character value) {
        this.y = y;
        this.x = x;
        this.value = value;
    }

    /**
     * Get position on X Axis.
     *
     * @return position on X Axis in map
     */
    public int getX() {
        return x;
    }

    /**
     * Get position on Y Axis.
     *
     * @return position on Y Axis in map
     */
    public int getY() {
        return y;
    }

    /**
     * Get value of CharacterNode.
     *
     * @return value
     */
    public Character getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CharacterNode that = (CharacterNode) o;
        return getX() == that.getX() &&
                getY() == that.getY() &&
                Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY(), getValue());
    }

    @Override
    public String toString() {
        return "CharacterNode{" +
                "x=" + x +
                ", y=" + y +
                ", value=" + value +
                '}';
    }
}
