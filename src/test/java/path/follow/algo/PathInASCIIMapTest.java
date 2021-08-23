package path.follow.algo;

import org.junit.jupiter.api.Test;


/**
 * Tests for PathInASCIIMap.
 *
 * @author ivan.gavlik
 */
public class PathInASCIIMapTest {

    /**
     * Instance of {@link PathInASCIIMap}.
     */
    private PathInASCIIMap findFath;

    /**
     * Basic test.
     *
     * Input:
     *   @---A---+
     *           |
     *   x-B-+   C
     *       |   |
     *       +---+
     *
     * Output:
     * Letters ACB
     * Path as characters @---A---+|C|+---+|+-B-x
     */
    @Test
    public void basic() {

    }

    /**
     * Go straight through intersections.
     *
     * Input:
     *   @
     *   | +-C--+
     *   A |    |
     *   +---B--+
     *     |      x
     *     |      |
     *     +---D--+
     *
     * Output:
     * Letters ABCD
     * Path as characters @|A+---B--+|+--C-+|-||+---D--+|x
     */
    public void intersections() {

    }

    /**
     * letters on turns.
     *
     * Input:
     *   @---A---+
     *           |
     *   x-B-+   |
     *       |   |
     *       +---C
     *
     * Output:
     * Letters ACB
     * Path as characters @---A---+|||C---+|+-B-x
     */
      public void lettersOnTurns() {

      }

    /**
     * Do not collect a letter from the same location twice.
     *
     * Input:
     *      +-O-N-+
     *      |     |
     *      |   +-I-+
     *  @-G-O-+ | | |
     *      | | +-+ E
     *      +-+     S
     *              |
     *              x
     *
     * Output:
     * Letters GOONIES
     * Path as characters @-G-O-+|+-+|O||+-O-N-+|I|+-+|+-I-+|ES|x
     */
    public void letterSameLocation() {

    }

    /**
     * Keep direction, even in a compact space.
     *
     * Input:
     *  +-L-+
     *  |  +A-+
     * @B+ ++ H
     *  ++    x
     *
     *  Output:
     *  Letters BLAH
     *  Path as characters @B+++B|+-L-+A+++A-+Hx
     */
    public void keepDirection() {

    }

    /**
     * No start.
     *
     * Input;
     *      -A---+
     *           |
     *   x-B-+   C
     *       |   |
     *       +---+
     *
     * Output:
     * RuntimeException
     */
    public void noStart() {

    }

    /**
     * No end.
     *
     * Input:
     *    @--A---+
     *           |
     *     B-+   C
     *       |   |
     *       +---+
     *
     * Output:
     * RuntimeException
     */
    public void noEnd() {

    }

    /**
     * Multiple starts.
     *
     * Input:
     *    @--A-@-+
     *           |
     *   x-B-+   C
     *       |   |
     *       +---+
     *
     * Output:
     * RuntimeException
     */
    public void multipleStarts() {

    }

    /**
     * Multiple ends.
     *
     * Input:
     *    @--A---+
     *           |
     *   x-Bx+   C
     *       |   |
     *       +---+
     *
     * Output:
     * RuntimeException
     */
    public void multipleEnds() {

    }

    /**
     * T forks.
     *
     * Input
     *        x-B
     *           |
     *    @--A---+
     *           |
     *      x+   C
     *       |   |
     *       +---+
     *
     * Output:
     * RuntimeException
     */
    public void tForks() {

    }

    /**
     * Broken path.
     *
     * Input:
     *    @--A-+
     *         |
     *
     *         B-x
     *
     * Output:
     * RuntimeException
     */
    public void brokenPath() {
    }
}
