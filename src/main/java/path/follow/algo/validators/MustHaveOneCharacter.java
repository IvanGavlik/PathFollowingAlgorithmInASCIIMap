package path.follow.algo.validators;

import path.follow.algo.validation.Validator;

import java.util.List;
import java.util.Objects;

/**
 * Validator that check if input has specific character.
 *
 * @author ivan.gavlik
 */
public class MustHaveOneCharacter implements Validator<List<String>> {

    private Character value;
    /**
     * Create new {@link MustHaveOneCharacter} instance.
     * @param c {@link Character} to check
     */
    public MustHaveOneCharacter(final Character c) {
        if (Objects.isNull(c)) {
            throw new IllegalArgumentException();
        }
        this.value = c;
    }

    @Override
    public String getMsgOnFail(final List<String> map) {
        final StringBuilder sb = new StringBuilder("Map ");
        sb.append(map)
                .append(" doest not have one ")
                .append(value);

        return sb.toString();
    }

    @Override
    public boolean test(final List<String> map) {
        if (map == null || map.isEmpty()) {
            return false;
        }
        final long count = map.stream()
                .flatMapToInt(CharSequence::chars)
                .filter(el -> el == value)
                .count();
         return count == 1;
    }
}
