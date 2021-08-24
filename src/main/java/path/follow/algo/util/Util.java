package path.follow.algo.util;

import java.util.Objects;

/**
 * Util class.
 *
 * @author ivan.gavlik
 */
public final class Util {

    private Util() { }

    /**
     * True if passed String have value.
     *
     * @param value String
     * @return true if passed String have value.
     */
    public static boolean haveValue(final String value) {
        return !Objects.isNull(value) && !value.trim().isEmpty();
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
            return value.charAt(firstLetter);
        }
        return null;
    }
}
