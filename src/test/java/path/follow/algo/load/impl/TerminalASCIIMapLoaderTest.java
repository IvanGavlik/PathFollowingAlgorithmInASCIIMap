package path.follow.algo.load.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import path.follow.algo.load.ASCIIMap;
import path.follow.algo.load.ASCIIMapLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests for {@link TerminalASCIIMapLoader}.
 *
 * @author ivan.gavlik
 */
public class TerminalASCIIMapLoaderTest {

    private ASCIIMapLoader loader;

    /**
     * File not found.
     *
     * Input
     * Path to the file that does not exist.
     *
     * Output
     * IllegalStateException
     */
    @Test()
    public void fileNotFound() {
        final String[] fileNotExist = {"-f", "resources//fileNotExist.txt"};
        this.loader = new TerminalASCIIMapLoader(fileNotExist);
        Assertions.assertThrows(IllegalStateException.class, () -> {
            this.loader.load();
        });
    }

    /**
     * Empty file.
     *
     * Input
     * Path to the empty file.
     *
     * Output
     * Empty List<String>
     */
    @Test()
    public void fileEmpty() {
        final String[] emptyFile = {"-f", "src//test//resources//empty.txt"};
        this.loader = new TerminalASCIIMapLoader(emptyFile);
        final ASCIIMap map = loader.load();
        Assertions.assertEquals(0, map.getMap().size());
        Assertions.assertEquals(ASCIIMap.DEFAULT_START, map.getStart());
        Assertions.assertEquals(ASCIIMap.DEFAULT_END, map.getEnd());
    }

    /**
     * Basic test.
     *
     * Input:
     *   @---A---+
     *           |
     *   x-B-+   C
     *       |   |
     *       +---+
     *
     * Output:
     * List<String> where each element is one row in input map
     */
    @Test()
    public void basic() {
        final String[] basicFile = {"-f", "src//test//resources//basic.txt"};
        this.loader = new TerminalASCIIMapLoader(basicFile);
        final ASCIIMap map =  loader.load();
        Assertions.assertEquals(getMap(), map.getMap());
        Assertions.assertEquals(ASCIIMap.DEFAULT_START, map.getStart());
        Assertions.assertEquals(ASCIIMap.DEFAULT_END, map.getEnd());

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
