package path.follow.algo.load.impl;

import path.follow.algo.load.ASCIIMapLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Default implementation of {@link ASCIIMapLoader} returns hardcoded ASCII map.
 *
 * @author ivan.gavlik
 */
public class InternalASCIIMapLoader implements ASCIIMapLoader {
    /**
     * Returns hardcoded ASCII map.
     *
     * Output:
     *   @---A---+
     *           |
     *   x-B-+   C
     *       |   |
     *       +---+
     *
     * @return hardcoded ASCII map.
     */
    @Override
    public List<String> load() {
        final List<String> list = new ArrayList<>();

        list.add("  @---A---+");
        list.add("          |");
        list.add("  x-B-+   C");
        list.add("      |   |");
        list.add("      +---+");

        return list;
    }
}
