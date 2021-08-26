package path.follow.algo.validators;

import path.follow.algo.validation.Validator;
import java.util.ArrayList;
import java.util.List;

/**
 * Validate that + does not have fake turns.
 *
 * Example of fake turn
 *   --A--+--B
 *
 * @author ivan.gavlik
 */
public class NoFakeTurn implements Validator<List<String>> {

    private static final char TURN_CHAR = '+';

    @Override
    public String getMsgOnFail(final List<String> map) {
        return "+ does not change direction";
    }

    @Override
    @SuppressWarnings("checkstyle:CyclomaticComplexity")
    public boolean test(final List<String> map) {

        if (map == null || map.isEmpty()) {
            return false;
        }

        /*
         * For every x char in matrix check
         * that there is at least one change of direction
         */
        final List<Boolean> eachX = new ArrayList<>();
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).length(); j++) {
                if (TURN_CHAR == map.get(i).charAt(j)) {

                    final boolean havePathUpLeft = haveChar(map, i - 1, j) && haveChar(map, i, j + 1);
                    final boolean havePathUpRight = haveChar(map, i + 1, j) && haveChar(map, i, j + 1);
                    final boolean havePathDownLeft = haveChar(map, i - 1, j) && haveChar(map, i, j - 1);
                    final boolean havePathDownRight = haveChar(map, i + 1, j) && haveChar(map, i, j - 1);

                    final boolean haveChangeForX = havePathUpLeft || havePathUpRight || havePathDownLeft || havePathDownRight;
                    eachX.add(haveChangeForX);

                }
            }
        }

        return eachX.stream().filter(el -> !el).count() == 0;
    }

    @SuppressWarnings("checkstyle:IllegalCatch")
    private boolean haveChar(final List<String> map, final int i, final int j) {
        try {
            map.get(i).charAt(j);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
