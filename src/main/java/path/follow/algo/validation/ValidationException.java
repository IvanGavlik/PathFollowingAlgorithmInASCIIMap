package path.follow.algo.validation;

/**
 * ValidationException.
 *
 * @author ivan.gavlik
 */
public class ValidationException extends RuntimeException {

    /**
     * Create new {@link ValidationException}.
     *
     * @param msg {@link String}
     */
    public  ValidationException(final String msg) {
        super(msg);
    }
}
