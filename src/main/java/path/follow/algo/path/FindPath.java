package path.follow.algo.path;

import path.follow.algo.graph.UnidirectionalGraph;

/**
 * Finds path on graph.
 *
 * @param <VERTEX>
 * @author ivan.gavlik
 */
public interface FindPath<VERTEX> {

    /**
     * Get instance of FindPath.
     *
     * @param findPathInstance {@link FindPathInstance}
     * @param unidirectionalGraph {@link UnidirectionalGraph}
     * @param <VERTEX> VERTEX
     * @return new instance of {@link FindPath}
     */
    static <VERTEX> FindPath<VERTEX> getInstance(FindPathInstance findPathInstance, UnidirectionalGraph<VERTEX> unidirectionalGraph) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Represents type of FindPath.
     */
    enum FindPathInstance {
        /**
         * Find path is implemented using depth first algorithm.
         */
        DEPTH_FIRST
    }

    /**
     * Return true if path form source vertex to destination exist.
     *
     * @param source VERTEX
     * @param destination VERTEX
     * @return true if path form source to destination exist.
     */
    boolean hasPathTo(VERTEX source, VERTEX destination);

    /**
     * Return path from source to destination if exist.
     *
     * @param source VERTEX
     * @param destination VERTEX
     * @return path if exist
     */
    Iterable<VERTEX> pathTo(VERTEX source, VERTEX destination);
}
