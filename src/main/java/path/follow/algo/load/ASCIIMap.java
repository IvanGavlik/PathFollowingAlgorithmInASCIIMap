package path.follow.algo.load;

import java.util.List;

/**
 * Represents ASCII map.
 *
 * @author ivan.gavlik
 */
public interface ASCIIMap {

    /**
     * Default start character.
     */
    Character DEFAULT_START = '@';

    /**
     * Default end character.
     */
    Character DEFAULT_END = 'x';


    /**
     * Return ASCII map where each element represents row in map.
     *
     * @return {@link List<String>}
     */
    List<String> getMap();

    /**
     * Get path start character.
     *
     * @return {@link Character}
     */
    Character getStart();

    /**
     * Get path end character.
     *
     * @return {@link Character}
     */
    Character getEnd();
}
