package path.follow.algo.validators;

import path.follow.algo.convert.Direction;
import path.follow.algo.validation.Validator;


import java.util.List;
import java.util.Objects;

/**
 * Validate that there are no multiple starting paths
 * from start node.
 *
 * @author ivan.gavlik
 */
public class NoMultipleStartingPaths implements Validator<List<String>> {

    private Character start;

    /**
     * Create new instance of NoMultipleStartingPaths.
     *
     * @param start {@link Character}
     */
    public NoMultipleStartingPaths(final Character start) {
        if (Objects.isNull(start)) {
            throw new IllegalArgumentException();
        }
        this.start = start;
    }

    @Override
    public String getMsgOnFail(final List<String> map) {
        return "There are multiple starting paths from start " + this.start;
    }

    @Override
    @SuppressWarnings("checkstyle:IllegalCatch")
    public boolean test(final List<String> map) {
        if (map == null || map.isEmpty()) {
            return false;
        }

        /*
         * For start char in map check
         * that there is only one neighbour
         */
        int success = 0;
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).length(); j++) {
                if (start == map.get(i).charAt(j)) {

                   for (Direction d: Direction.values()) {
                       try {
                           final char c = map.get(i + d.getY()).charAt(j + d.getX());
                           if ( c != ' ') {
                               success += 1; // do not count spaces
                           }
                       } catch (RuntimeException ex) { }
                   }
                }
            }
        }

        return success == 1;
    }
}
