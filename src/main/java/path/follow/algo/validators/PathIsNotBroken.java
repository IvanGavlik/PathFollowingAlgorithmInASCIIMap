package path.follow.algo.validators;

import path.follow.algo.validation.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Validate that path is not broken.
 *
 * Example of broken path.
 *   @--A-+
 *         |
 *
 *         B-x
 *
 * @author ivan.gavlik
 */
public class PathIsNotBroken implements Validator<List<String>> {

    @Override
    public String getMsgOnFail(final List<String> map) {
        return "Path is broken";
    }

    @Override
    @SuppressWarnings("checkstyle:CyclomaticComplexity")
    public boolean test(final List<String> map) {
        if (Objects.isNull(map)) {
            return false;
        }

        /*
         * For every element map check
         * that there is at least neighbour
         */
        final List<Boolean> eachEl = new ArrayList<>();
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).length(); j++) {

                final boolean havePathUpLeft = haveChar(map, i - 1, j) && haveChar(map, i, j + 1);
                final boolean havePathUpRight = haveChar(map, i + 1, j) && haveChar(map, i, j + 1);
                final boolean havePathDownLeft = haveChar(map, i - 1, j) && haveChar(map, i, j - 1);
                final boolean havePathDownRight = haveChar(map, i + 1, j) && haveChar(map, i, j - 1);

                final boolean haveNeighbour = havePathUpLeft || havePathUpRight || havePathDownLeft || havePathDownRight;
                eachEl.add(haveNeighbour);

                }
            }

            return eachEl.stream().filter( el -> !el).count() == 0;
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
