package path.follow.algo.load;


import path.follow.algo.load.impl.InternalASCIIMapLoader;
import path.follow.algo.load.impl.TerminalASCIIMapLoader;

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
     * @return {@link ASCIIMap}
     * @throws IllegalStateException if load fails.
     */
    ASCIIMap load() throws IllegalStateException;

    /**
     * Create new ASCIIMapLoader.
     *
     * If file path is null method returns default implementation {@link InternalASCIIMapLoader}.
     *
     * @param args arguments
     * @return loader for ASCII map {@link ASCIIMapLoader}
     */
    static ASCIIMapLoader getInstance(final String[] args) {
        if (Objects.isNull(args) || args.length == 0) {
            return new InternalASCIIMapLoader();
        }
        return new TerminalASCIIMapLoader(args);
    }

}
