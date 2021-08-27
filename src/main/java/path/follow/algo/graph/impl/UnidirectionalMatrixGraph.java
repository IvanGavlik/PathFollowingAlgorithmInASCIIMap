package path.follow.algo.graph.impl;


import path.follow.algo.graph.UnidirectionalGraph;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;

/**
 * Implementation of unidirectional graph using matrix (map).
 *
 * @param <VERTEX>
 * @author ivan.gavlik
 */
public class UnidirectionalMatrixGraph<VERTEX> implements UnidirectionalGraph<VERTEX> {

    // HashMap to store the edges in the graph
    private Map<VERTEX, List<VERTEX>> map = new HashMap<>();

    /**
     * Add a new vertex to the graph.
     *
     * @param vertex VERTEX
     */
    @Override
    public void addVertex(final VERTEX vertex) {
        this.map.put(vertex, new LinkedList<>());
    }

    /**
     * Add a new edge to the graph.
     *
     * @param source VERTEX
     * @param destination VERTEX
     */
    @Override
    public void addEdge(final VERTEX source, final VERTEX destination) {
        if (!map.containsKey(source)) {
            addVertex(source);
        }

        if (!map.containsKey(destination)) {
            addVertex(destination);
        }

        map.get(source).add(destination);
    }

    /**
     * Return list of neighbors of vertex.
     *
     * @param vertex VERTEX
     * @return List<VERTEX>
     */
    @Override
    public List<VERTEX> getAdj(final VERTEX vertex) {
        List<VERTEX> adj = this.map.get(vertex);
        if (adj == null) {
            adj = new ArrayList<>(0);
        }
        return adj;
    }

    /**
     * Get size of vertices.
     *
     * @return size of vertices
     */
    @Override
    public int getVertexCount() {
        return map.keySet().size();
    }

    /**
     * Get the size of edges.
     *
     * @return size of edges
     */
    @Override
    public int getEdgesCount() {
        int count = 0;
        for (Map.Entry<VERTEX, List<VERTEX>> v : map.entrySet()) {
            count += v.getValue() != null ? v.getValue().size() : 0;
        }

        return count;
    }

    /**
     * Whether the vertex is present or not.
     *
     * @param vertex VERTEX
     * @return true if has vertex
     */
    @Override
    public boolean hasVertex(final VERTEX vertex) {
        return map.containsKey(vertex);
    }

    /**
     *  Whether the edge is present or not.
     *
     * @param source VERTEX
     * @param destination VERTEX
     * @return true if has edge
     */
    @Override
    public boolean hasEdge(final VERTEX source, final VERTEX destination) {
        return map.get(source) != null && map.get(source).contains(destination);
    }

    /**
     * Prints the adjacency's list of each vertex.
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        for (Map.Entry<VERTEX, List<VERTEX>> i : map.entrySet()) {
            builder.append(i.getKey().toString() + ": ");
            for (VERTEX j : i.getValue()) {
                builder.append(j.toString()).append(" ");
            }
            builder.append("\n");
        }

        return (builder.toString());
    }
}
