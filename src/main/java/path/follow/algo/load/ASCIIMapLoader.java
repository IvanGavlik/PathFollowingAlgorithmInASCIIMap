package path.follow.algo.load;

import java.util.List;

/**
 * Loader for ASCII map.
 *
 * @author ivan.gavlik
 */
public interface ASCIIMapLoader {

    /**
     * Load data from input.
     *
     * @return List<String> where each row in map is element in List.
     */
    List<String> load();

    /**
     * Create new ASCIIMapLoader.
     *
     * @param loaderInstance {@link LoaderInstance}
     * @return loader for ASCII map {@link ASCIIMapLoader}
     */
    static ASCIIMapLoader getInstance(final LoaderInstance loaderInstance) {
        throw  new RuntimeException("Not implemented");
    }

    /**
     * Represents type of ASCIIMapLoader.
     */
    enum LoaderInstance {
        /**
         * Load ASCII map from file.
         */
        FILE,

        /**
         * Load ASCII map from console.
         */
        CONSOLE,
    }
}
