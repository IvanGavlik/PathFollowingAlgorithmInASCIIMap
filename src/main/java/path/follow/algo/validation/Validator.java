package path.follow.algo.validation;

import java.util.function.Predicate;

/**
 * Validator.
 *
 * Extend to create your custom validator on data T
 *
 * @param <T>
 *
 * @author ivan.gavlik
 */
public interface Validator<T> extends Predicate<T> {

    /**
     * Get custom msg when validation fail.
     *
     * @param t validated T
     * @return msg
     */
    String getMsgOnFail(T t);
}
