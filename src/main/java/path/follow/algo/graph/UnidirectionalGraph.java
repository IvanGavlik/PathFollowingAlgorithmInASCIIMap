package path.follow.algo.graph;

import path.follow.algo.graph.impl.UnidirectionalMatrixGraph;

import java.util.List;

/**
 * Unidirectional graph.
 *
 * @param <VERTEX> VERTEX
 * @author ivan.gavlik
 */
public interface UnidirectionalGraph<VERTEX> {

    /**
     * Get instance of UnidirectionalGraph.
     *
     * @param graphInstance {@link GraphInstance}
     * @param <VERTEX> VERTEX
     * @return new UnidirectionalGraph
     */
    static <VERTEX> UnidirectionalGraph<VERTEX> getInstance(final GraphInstance graphInstance) {
        switch (graphInstance) {
            case MATRIX:
                return new UnidirectionalMatrixGraph<>();
            case LIST:
            default:
                throw new RuntimeException("Not implemented");
        }
    }

    /**
     * Represents types of UnidirectionalGraph.
     *
     * @author ivan.gavlik
     */
    enum GraphInstance {
        /**
         * Graph instance is implemented using adjacency matrix.
         */
        MATRIX,

        /**
         * Graph instance is implemented using adjacency list.
         */
        LIST
    }


    /**
     * Add a new vertex to the graph.
     *
     * @param vertex VERTEX
     */
    void addVertex(VERTEX vertex);


    /**
     * Add a new edge to the graph.
     *
     * @param source VERTEX
     * @param destination VERTEX
     */
    void addEdge(VERTEX source, VERTEX destination);

    /**
     * Return list of neighbors of vertex.
     *
     * @param vertex VERTEX
     * @return List<VERTEX>
     */
    List<VERTEX> getAdj(VERTEX vertex);

    /**
     * Get size of vertices.
     *
     * @return size of vertices
     */
    int getVertexCount();

    /**
     * Get the size of edges.
     *
     * @return size of edges
     */
    int getEdgesCount();

    /**
     * Whether the vertex is present or not.
     *
     * @param vertex VERTEX
     * @return true if has vertex
     */
    boolean hasVertex(VERTEX vertex);

    /**
     *  whether the edge is present or not.
     *
     * @param source VERTEX
     * @param destination VERTEX
     * @return true if has edge
     */
    boolean hasEdge(VERTEX source, VERTEX destination);
}
