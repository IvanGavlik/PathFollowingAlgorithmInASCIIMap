package path.follow.algo.load;

import path.follow.algo.load.impl.FileASCIIMapLoader;
import path.follow.algo.load.impl.InternalASCIIMapLoader;

import java.util.List;
import java.util.Objects;

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
     * @throws IllegalStateException if load fails.
     */
    List<String> load() throws IllegalStateException;

    /**
     * Create new ASCIIMapLoader.
     *
     * If file path is null method returns default implementation {@link InternalASCIIMapLoader}.
     *
     * @param filePath path to the file
     * @return loader for ASCII map {@link ASCIIMapLoader}
     */
    static ASCIIMapLoader getInstance(final String filePath) {
        if (Objects.isNull(filePath) || filePath.trim().isEmpty()) {
            return new InternalASCIIMapLoader();
        }
        return new FileASCIIMapLoader(filePath);
    }

}
