package path.follow.algo.convert;


import path.follow.algo.graph.UnidirectionalGraph;
import path.follow.algo.graph.vertex.CharacterNode;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Converts ASCII map into Unidirectional graph.
 *
 * @author ivan.gavlik
 */
public final class ASCIIMapToASCIIGraph {

    private static final int ASCII_MAP_SIZE = 127;

    private ASCIIMapToASCIIGraph() { }

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

        final UnidirectionalGraph<CharacterNode> graph =
                UnidirectionalGraph.getInstance(UnidirectionalGraph.GraphInstance.MATRIX);

        final CharacterNode startNode = getNodeFromMap(map, start);
        final CharacterNode destinationNode = getNodeFromMap(map, end);

        convertUtil(map, graph, startNode, getStartDirection(map, startNode), destinationNode);

        return new ASCIIGraph(graph, startNode, destinationNode);
    }

    private static CharacterNode getNodeFromMap(final List<String> map, final Character character) {
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).length(); j++) {
                if (map.get(i).charAt(j) == character) {
                    return getNodeFromMap(map, i, j).get();
                }
            }
        }
        throw new IllegalArgumentException("Character does't exist in map" + character);
    }

    private static Direction getStartDirection(final List<String> map, final CharacterNode node) {

        final Optional<Direction> nodeStartDirection = Stream.of(Direction.values())
                .filter(el -> getNodeFromMap(map, getYMove(node, el), getXMove(node, el)).isPresent())
                .findFirst();

        if (!nodeStartDirection.isPresent()) {
            throw new IllegalArgumentException("Character node does't have direction" + node);
        }
        return nodeStartDirection.get();
    }

    /**
     *  Recursive function converts ASCII map into Unidirectional graph.
     *
     *  Algorithm.
     *  By using currentDirection and current Node finds next node and next direction.
     *  Add edge in map on relation current - next node
     *  Calls itself and as parameter for current Node sets next node
     *  and for current direction sets next direction.
     *  It calls itself until final destination is not found or path is broken.
     *
     *  Next direction depends on current node and current direction.
     *  It will continue in same direction, but if current node is + or letter
     *  it will try change direction.
     *
     *
     * @param map ASCII map input
     * @param graph {@link UnidirectionalGraph<CharacterNode>} result
     * @param current starting point in ASCII map {@link CharacterNode}
     * @param currentDirection {@link Direction}
     * @param finalDestination {@link CharacterNode}
     */
    private static void convertUtil(final List<String> map, final UnidirectionalGraph<CharacterNode> graph,
                                    final CharacterNode current, final Direction currentDirection,
                                    final CharacterNode finalDestination) {
        if (current.equals(finalDestination)) {
            return;
        }

        final Optional<CharacterNode> nextOptional =
                getNodeFromMap(map, getYMove(current, currentDirection), getXMove(current, currentDirection));
        if (!nextOptional.isPresent()) {
            return;
        }

        final CharacterNode next = nextOptional.get();
        graph.addEdge(current, next);

        final Optional<?> nextDirectionOptional =
                 Stream.of(Direction.getDirectionsForChar(currentDirection, next.getValue()))
                .map(direction -> {
                    final Optional<CharacterNode> tempNext =
                            getNodeFromMap(map, getYMove(next, direction), getXMove(next, direction));
                    // tempNext exist and it is not current
                    if (tempNext.isPresent() && !tempNext.get().equals(current)) {
                        return Optional.of(direction);
                    } else {
                        return Optional.empty();
                    }
                })
                .filter(el -> el.isPresent())
                .map(el -> el.get())
                .findFirst();

        if (!nextDirectionOptional.isPresent()) {
            return;
        }
        if (!(nextDirectionOptional.get() instanceof Direction)) {
            // this should never happen
            throw new ClassCastException("nextDirectionOptional#get must be DirectionType");
        }
        final Direction nextDirection = (Direction) nextDirectionOptional.get();

        convertUtil(map, graph, next, nextDirection, finalDestination);

    }

    private static int getXMove(final CharacterNode n, final Direction directionType) {
        return n.getX() + directionType.getX();
    }

    private static int getYMove(final CharacterNode n, final Direction directionType) {
        return n.getY() + directionType.getY();
    }

    /**
     * Get character node {@link Optional<CharacterNode>} form map at postilion i (Y - Axis), y (X - Axis).
     *
     * IF there is no value form ASCII table https://www.asciitable.com/ or value is space returns empty optional.
     *
     * @param map ASCIIMap {@link List<String>}
     * @param y Y Axis
     * @param x X Axis
     * @return CharacterNode {@link Optional<CharacterNode>}
     */
    @SuppressWarnings("checkstyle:IllegalCatch")
    private static Optional<CharacterNode> getNodeFromMap(final List<String> map, final int y, final int x) {
        try {
            final char c = map.get(y).charAt(x);
            if ((int) c <= ASCII_MAP_SIZE && c != ' ') {
                return Optional.of(new CharacterNode(y, x, c));
            }
            return Optional.empty();
        } catch (RuntimeException ex) {
            return Optional.empty();
        }
    }


}
