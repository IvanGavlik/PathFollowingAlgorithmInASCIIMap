package path.follow.algo.load.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import path.follow.algo.load.ASCIIMapLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests for {@link FileASCIIMapLoaderTest}.
 *
 * @author ivan.gavlik
 */
public class FileASCIIMapLoaderTest {

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
        final String fileNotExist = "resources//fileNotExist.txt";
        this.loader = new FileASCIIMapLoader(fileNotExist);
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
        final String emptyFile = "src//test//resources//empty.txt";
        this.loader = new FileASCIIMapLoader(emptyFile);
        Assertions.assertEquals(0, loader.load().size());
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
        final String basicFile = "src//test//resources//basic.txt";
        this.loader = new FileASCIIMapLoader(basicFile);
        Assertions.assertEquals(getMap(), loader.load());
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
