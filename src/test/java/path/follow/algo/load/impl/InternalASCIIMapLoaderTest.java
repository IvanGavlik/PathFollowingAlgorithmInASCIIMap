package path.follow.algo.load.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import path.follow.algo.load.ASCIIMapLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests for InternalASCIIMapLoader.
 *
 * @author ivan.gavlik
 */
public class InternalASCIIMapLoaderTest {

    private ASCIIMapLoader loader = new InternalASCIIMapLoader();

    /**
     * Check if InternalASCIIMapLoader returns correct hardcoded ASCII map.
     */
    @Test()
    public void checkLoad() {
        final List<String> loadedMap = this.loader.load();
        Assertions.assertEquals(getMap(), loadedMap);
    }

    private List<String> getMap() {
        final List<String> list = new ArrayList<>();

        list.add("  @---A---+");
        list.add("          |");
        list.add("  x-B-+   C");
        list.add("      |   |");
        list.add("      +---+");

        return list;
    }

}

