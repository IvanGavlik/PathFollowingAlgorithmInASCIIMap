package path.follow.algo.convert;


import path.follow.algo.graph.UnidirectionalGraph;
import path.follow.algo.graph.vertex.CharacterNode;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

/**
 * Converts ASCII map into Unidirectional graph.
 *
 * @author ivan.gavlik
 */
public final class ASCIIMapToASCIIGraph {

    private static final int ASCII_MAP_SIZE = 127;

    private ASCIIMapToASCIIGraph() {
        // should not called this is util class
    }

    /**
     * Convert ASCII map into Unidirectional graph.
     *
     * If is letter or plus in map algorithm will try to change direction.
     * Space are ignored.
     *
     *
     * @param map ASCIIMap  {@link List<String>} where each row in map in one element in list.
     * @param start {@link Character}
     * @param end {@link Character}
     * @return {@link ASCIIGraph}
     */
    public static ASCIIGraph convert(final List<String> map, final Character start, final Character end) {
        if (map == null || start == null || end == null) {
            throw new IllegalArgumentException("map " + map + " start " + start + " end " + end);
        }
        final UnidirectionalGraph<CharacterNode> graph = UnidirectionalGraph.getInstance(UnidirectionalGraph.GraphInstance.MATRIX);

        final AtomicReference<CharacterNode> startNode = new AtomicReference<>();
        final AtomicReference<CharacterNode> destinationNode = new AtomicReference<>();

        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).length(); j++) {

                final Optional<CharacterNode> currentOptional = getChar(i, j, map);
                if (!currentOptional.isPresent()) {
                    continue;
                }

                final CharacterNode current = currentOptional.get();

                final int finalI = i;
                final int finalJ = j;

                Stream.of(DirectionType.getDirectionForChar(DirectionType.LEFT, current.getValue()))
                        .map(direction -> getChar(finalI + direction.getX(), finalJ + direction.getY(), map))
                        .filter(destination -> destination.isPresent())
                        .forEach(destination -> {
                            if (current.getValue().equals(start)) {
                                startNode.set(current);
                            }
                            if (current.getValue().equals(end)) {
                                destinationNode.set(current);
                            }
                            graph.addEdge(current, destination.get());
                        });
            }
        }

        return new ASCIIGraph(graph, startNode.get(), destinationNode.get());
    }

    /**
     * Get character node {@link Optional<CharacterNode>} form map at postilion i (Y - Axis), y (X - Axis).
     *
     * IF there is no value form ASCII table https://www.asciitable.com/ or value is space returns empty optional.
     *
     * @param i X Axis
     * @param j Y Axis
     * @param map ASCIIMap {@link List<String>}
     * @return CharacterNode {@link Optional<CharacterNode>}
     */
    @SuppressWarnings("checkstyle:IllegalCatch")
    private static Optional<CharacterNode> getChar(final int i, final int j,  final List<String> map) {
        try {
            final char c = map.get(i).charAt(j);
            if ((int) c <= ASCII_MAP_SIZE && c != ' ') {
                return Optional.of(new CharacterNode(j, i, c));
            }
            return Optional.empty();
        } catch (RuntimeException ex) {
            return Optional.empty();
        }
    }


}
