package path.follow.algo.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import path.follow.algo.stub.ValidatorStub;

/**
 * Tests for Validation.
 *
 * @author ivan.gavlik
 */
public class ValidationTest {

    private Validation<String> stringValidation;

    /**
     * Simple validation positive.
     */
    @Test
    public void simpleValidationPositive() {
        final String data = "abc";
        this.stringValidation = new Validation<>(data);
        final boolean valid = this.stringValidation
                .add(ValidatorStub.stingHasLetterValidator("a"))
                .isValid();
        Assertions.assertTrue(valid);

        final Validation.ValidateResult<String> v = this.stringValidation
                .removeAll()
                .add(ValidatorStub.stingHasLetterValidator("a"))
                .getValidationResult();

        Assertions.assertTrue(v.isValid());
        Assertions.assertEquals(data, v.getData());
        Assertions.assertNull(v.getMsg());

        this.stringValidation
                .removeAll()
                .add(ValidatorStub.stingHasLetterValidator("a"))
                .isValidOrElseThrow();
    }


    /**
     * Simple validation negative.
     */
    @Test
    public void simpleValidationNegative() {
        final String data = "abc";
        this.stringValidation = new Validation<>(data);
        final boolean valid = this.stringValidation
                .add(ValidatorStub.stingHasLetterValidator("d"))
                .isValid();
        Assertions.assertFalse(valid);

        final Validation.ValidateResult<String> v = this.stringValidation
                .removeAll()
                .add(ValidatorStub.stingHasLetterValidator("f"))
                .getValidationResult();

        Assertions.assertFalse(v.isValid());
        Assertions.assertEquals(data, v.getData());
        Assertions.assertNotNull(v.getMsg());

        Assertions.assertThrows(ValidationException.class, () -> this.stringValidation
                .removeAll()
                .add(ValidatorStub.stingHasLetterValidator("f"))
                .isValidOrElseThrow());
    }

    /**
     * Simple validation when validator or data null.
     */
    @Test
    public void onNull() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            this.stringValidation = new Validation<>(null);
            this.stringValidation
                    .removeAll()
                    .add(ValidatorStub.stingHasLetterValidator("f"))
                    .isValidOrElseThrow();
        });

        final String data = "abc";
        this.stringValidation = new Validation<>(data);
        final boolean valid = this.stringValidation
                .add(null)
                .isValid();
        Assertions.assertTrue(valid);

    }


}
