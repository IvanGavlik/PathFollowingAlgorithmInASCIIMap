package path.follow.algo.stub;

import path.follow.algo.validation.Validator;

/**
 * Validator related stub.
 *
 * @author ivan.gavlik
 */
public final class ValidatorStub {

    private ValidatorStub() { }

    /**
     * Does String contains passed String.
     *
     * @param input {@link String}
     * @return {@link Validator<String> }
     */
    public static Validator<String> stingHasLetterValidator(final String input) {

        @SuppressWarnings({"checkstyle:JavadocMethod", "checkstyle:JavadocType"})
        class HasLetter implements Validator<String> {

            private String c;

            HasLetter(final String input) {
                this.c = input;
            }

            @Override
            public String getMsgOnFail(final String s) {
                return "There is no " + c + " in " + s;
            }

            @Override
            public boolean test(final String s) {
                return s.contains(c);
            }
        }

        return new HasLetter(input);
    }

}


