package path.follow.algo.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 *
 * Use this class to apply validators {@link Validator} on data <DATA>
 * and get result that indicates is data valid.
 *
 * @param <DATA>
 *
 * @author ivan.gavlik
 */
public final class Validation<DATA> {

    private DATA data;
    private List<Validator<DATA>> validators = new ArrayList<>();

    /**
     * Create validation for data DATA.
     *
     * @param data to be validate
     */
    public Validation(final DATA data) {
        if (Objects.isNull(data)) {
            throw new IllegalArgumentException();
        }
        this.data = data;
    }

    /**
     * Add validator.
     *
     * @param validator {@link Validator<DATA>}
     * @return {@link Validation<DATA>}
     */
    public Validation<DATA> add(final Validator<DATA> validator) {
        if (Objects.isNull(validator)) {
            return this;
        }
        this.validators.add(validator);
        return this;
    }

    /**
     * Remove validator.
     *
     * @param validator {@link Validator<DATA>}
     * @return {@link Validation<DATA>}
     */
    public Validation<DATA> remove(final Validator<DATA> validator) {
        if (Objects.isNull(validator)) {
            return this;
        }
        this.validators.remove(validator);
        return this;
    }

    /**
     * Remove all validators.
     *
     * @return {@link Validation<DATA>}
     */
    public Validation<DATA> removeAll() {
        this.validators.clear();
        return this;
    }

    /**
     * Apply validators on data.
     * Throw {@link ValidationException} if data is not valid.
     *
     * @throws ValidationException if validation fails.
     */
    public void isValidOrElseThrow() throws ValidationException {
        this.validators.forEach(el -> {
            final boolean result = el.test(this.data);
            if (!result) {
                throw new ValidationException(el.getMsgOnFail(this.data));
            }
        });
    }
    /**
     * Apply validators on data and return boolean.
     *
     * @return true if data in valid, false otherwise
     */
    public boolean isValid() {
        final long count = this.validators.stream()
                .map(el -> el.test(this.data))
                .filter(el -> !el)
                .count();
        return count == 0L;
    }

    /**
     * Apply validators on data and return {@link ValidateResult<DATA>}.
     *
     * @return {@link ValidateResult<DATA>}
     */
    public ValidateResult<DATA> getValidationResult() {
        final Optional<ValidateResult<DATA>> result = this.validators.stream()
                .map(el -> new ValidateResult<>(this.data, el.test(this.data), el.getMsgOnFail(this.data)))
                .filter(el -> !el.isValid)
                .findFirst();

        return result.orElseGet(() -> new ValidateResult<>(this.data, true, null));
    }

    /**
     * Represents validation result.
     *
     * IF data is valid msg is null
     * @param <DATA>
     */
    static final class ValidateResult<DATA> {
        private final DATA data;
        private final boolean isValid;
        private final String msg;

        /**
         * Create new intance of {@link ValidateResult<DATA>}.
         *
         * @param data DATA
         * @param isValid boolean
         * @param msg {@link String}
         */
        ValidateResult(final DATA data, final boolean isValid, final String msg) {
            this.data = data;
            this.isValid = isValid;
            this.msg = msg;
        }

        public DATA getData() {
            return data;
        }

        public boolean isValid() {
            return isValid;
        }

        public String getMsg() {
            return msg;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            final ValidateResult<?> that = (ValidateResult<?>) o;
            return isValid() == that.isValid() &&
                    Objects.equals(getData(), that.getData()) &&
                    Objects.equals(getMsg(), that.getMsg());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getData(), isValid(), getMsg());
        }

        @Override
        public String toString() {
            return "ValidateResult{" +
                    "data=" + data +
                    ", isValid=" + isValid +
                    ", msg='" + msg + '\'' +
                    '}';
        }
    }

}
