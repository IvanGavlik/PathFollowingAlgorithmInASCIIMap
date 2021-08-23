package path.follow.algo.load.impl;

import path.follow.algo.load.ASCIIMapLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.List;

/**
 * Load ASCII map form file.
 *
 * @author ivan.gavlik
 */
public final class FileASCIIMapLoader implements ASCIIMapLoader {

    private final String filePath;

    /**
     * Create new FileASCIIMapLoader.
     *
     * @param filePath File path
     */
    public FileASCIIMapLoader(final String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load ASCII map form file.
     *
     * @return List<String> where each row in map is element in List.
     * @throws IllegalStateException if load fails.
     */
    @Override
    public List<String> load() throws IllegalStateException {
        final Path path = Paths.get(filePath);

        try (Stream<String> stream = Files.lines(path)) {
            return stream.collect(Collectors.toList());
        }  catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
