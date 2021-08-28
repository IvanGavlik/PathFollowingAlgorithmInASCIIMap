package path.follow.algo.load.impl;

import path.follow.algo.load.ASCIIMap;
import path.follow.algo.load.ASCIIMapLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.List;

/**
 * Terminal (Console) ASCII map loader.
 *
 *
 * Can have zero, one, two or three params
 * -f X for file path (default: lod internal map)
 * -s X for start char (default: @)
 * -e X for end char (default: x)
 *
 * if param is omitted default is used
 * -s and -e for value can not have + (it marks change or direction in map)
 *
 * Example 1.
 * -f //documents//map//basic.txt -s % -e #
 * Loads maps from file /documents//map//basic.txt, start char is % end char is #
 *
 * Example 2.
 * -f //documents//map//basic.txt -e #
 * Loads maps from file /documents//map//basic.txt, start char is @ (default) end char is #
 *
 * Example 3.
 *  -s $ -e #
 * Loads default map, start char is $ end char is #.
 * Default map only have path from @ and x so other params will not find any path.
 *
 * @author ivan.gavlik
 */
public final class TerminalASCIIMapLoader implements ASCIIMapLoader {

    private static final String FILE_PARAM = "-f";
    private static final String START_CHAR_PARAM = "-s";
    private static final String END_CHAR_PARAM = "-e";

    private String file;
    private Character start;
    private Character end;


    /**
     * Create new {@link TerminalASCIIMapLoader}.
     *
     * @param args {@link String[]}
     */
    @SuppressWarnings({"checkstyle:MagicNumber"})
    public TerminalASCIIMapLoader(final String[] args) {
        if (Objects.isNull(args) || args.length == 0) {
            file = null;
            start = ASCIIMap.DEFAULT_START;
            end = ASCIIMap.DEFAULT_END;
        }
        for (int i = 0; i < args.length; i += 2) {
            final String input = args[i];
            switch (input) {
                case FILE_PARAM:
                    file = getValueAt(args, i + 1)
                            .orElseThrow(IllegalArgumentException::new);
                    break;
                case START_CHAR_PARAM:
                    start = getValueAt(args, i + 1)
                            .map(el -> el.charAt(0))
                            .orElseThrow(IllegalArgumentException::new);
                    break;
                case END_CHAR_PARAM:
                    end = getValueAt(args, i + 1)
                            .map( el -> el.charAt(0))
                            .orElseThrow(IllegalArgumentException::new);
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }
    }

    @SuppressWarnings({"checkstyle:IllegalCatch"})
    private Optional<String> getValueAt(final String[] args, final int index) {
        try {
            return Optional.of(args[index]);
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    /**
     * Load ASCII map form file.
     *
     * @return List<String> where each row in map is element in List.
     * @throws IllegalStateException if load fails.
     */
    @Override
    public ASCIIMap load() throws IllegalStateException {
        final Path path = Paths.get(file);

        try (Stream<String> stream = Files.lines(path)) {
            final List<String> map = stream.collect(Collectors.toList());

            return new ASCIIMap() {
                @Override
                public List<String> getMap() {
                    return map != null ? map : new ArrayList<>();
                }

                @Override
                public Character getStart() {
                    return start != null ? start : DEFAULT_START;
                }

                @Override
                public Character getEnd() {
                    return end != null ? end : DEFAULT_END;
                }
            };
        }  catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
