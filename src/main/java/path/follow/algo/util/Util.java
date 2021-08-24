package path.follow.algo.util;

import java.util.Objects;

/**
 * Util class.
 *
 * @author ivan.gavlik
 */
public final class Util {

    private Util() {
        // should not called this is util class
    }

    /**
     * True if passed String have value.
     *
     * @param value String
     * @return true if passed String have value.
     */
    public static boolean haveValue(final String value) {
        if (Objects.isNull(value) || value.trim().isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * Get first character {@link Character} from string or null.
     *
     * @param value String
     * @return first character {@link Character} of string or null
     */
    public static Character getFirstCharacter(final String value) {
        final int firstLetter = 0;
        if (!Objects.isNull(value)) {
            return Character.valueOf(value.charAt(firstLetter));
        }
        return null;
    }
}
