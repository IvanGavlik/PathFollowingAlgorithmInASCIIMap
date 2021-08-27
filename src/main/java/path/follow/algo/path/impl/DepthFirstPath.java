package path.follow.algo.path.impl;

import path.follow.algo.graph.UnidirectionalGraph;
import path.follow.algo.graph.vertex.CharacterNode;
import path.follow.algo.path.FindPath;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of Depth-first search (DFS) algorithm on graph
 * where vertex is {@link CharacterNode}.
 *
 * @author ivan.gavlik
 */
public final class DepthFirstPath implements FindPath<CharacterNode> {

    private boolean[][] marked;    // marked[v] = is there an s-v path?
    private List<CharacterNode> edgeTo;        // edgeTo[v] = last edge on s-v path


    /**
     * Computes a path between source {@link CharacterNode} and
     * every other vertex in graph {@link CharacterNode}.
     *
     * @param graph {@link UnidirectionalGraph<CharacterNode>}
     * @param source {@link CharacterNode}
     */
    public DepthFirstPath(final UnidirectionalGraph<CharacterNode> graph, final CharacterNode source) {
        this.edgeTo = new ArrayList<>(graph.getVertexCount());
        this.marked = new boolean[graph.getVertexCount()][graph.getEdgesCount()];
        dfs(graph, source);
    }


    /**
     * Return true if path form source vertex to destination exist.
     *
     * Source is set on creating new FindPath instance
     *
     * @param destination VERTEX
     * @return true if path form source to destination exist.
     */
    @Override
    public boolean hasPathTo(final CharacterNode destination) {
        if (destination == null) {
            return false;
        }
        return marked[destination.getX()][destination.getY()];
    }


    /**
     * Return path from source to destination if exist.
     *
     * Source is set on creating new FindPath instance
     *
     * @param destination VERTEX
     * @return path if exist or empty {@link Iterable}
     */
    @Override
    public Iterable<CharacterNode> pathTo(final CharacterNode destination) {
        if (!hasPathTo(destination)) {
            return new ArrayList<>();
        }
        edgeTo.add(destination);
        return edgeTo;
    }

    // depth first search from source
    private void dfs(final UnidirectionalGraph<CharacterNode> graph, final CharacterNode source) {
        marked[source.getX()][source.getY()] = true;
        for (CharacterNode w : graph.getAdj(source)) {
            edgeTo.add(source);
            if (!marked[w.getX()][w.getY()]) {
                dfs(graph, w);
            }
        }
    }

}
