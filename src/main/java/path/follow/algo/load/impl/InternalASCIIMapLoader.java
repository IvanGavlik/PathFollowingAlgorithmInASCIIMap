package path.follow.algo.load.impl;

import path.follow.algo.load.ASCIIMap;
import path.follow.algo.load.ASCIIMapLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Default implementation of {@link ASCIIMapLoader} returns hardcoded ASCII map.
 *
 * @author ivan.gavlik
 */
public class InternalASCIIMapLoader implements ASCIIMapLoader {

    private final ASCIIMap asciiMap;

    /**
     * Create new instance of {@link InternalASCIIMapLoader}.
     */
    public InternalASCIIMapLoader() {
        this.asciiMap = new DefaultASCIIMap();
    }

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
     * start char @
     * end char x
     *
     * @return ASCII map {@link ASCIIMap}.
     */
    @Override
    public ASCIIMap load() {
        return this.asciiMap;
    }


    /**
     * Default ASCII map.
     *
     * @author ivan.gavlik
     */
     static final class DefaultASCIIMap implements ASCIIMap {

         @Override
         public List<String> getMap() {
             final List<String> list = new ArrayList<>();
             list.add("  @---A---+");
             list.add("          |");
             list.add("  x-B-+   C");
             list.add("      |   |");
             list.add("      +---+");
             return list;
         }

         @Override
         public Character getStart() {
             return DEFAULT_START;
         }

         @Override
         public Character getEnd() {
             return DEFAULT_END;
         }
    }

}
