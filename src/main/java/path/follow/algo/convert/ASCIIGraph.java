package path.follow.algo.convert;

import path.follow.algo.graph.UnidirectionalGraph;
import path.follow.algo.graph.vertex.CharacterNode;

import java.util.Objects;

/**
 * Represents ASCIIGraph.
 *
 * It is unidirectional graph where vertices are {@link CharacterNode}
 * and with explicit access to start vertices and end vertices.
 *
 * @author ivan.gavlik
 */
public final class ASCIIGraph {

    private final UnidirectionalGraph<CharacterNode> graph;
    private final CharacterNode start;
    private final CharacterNode end;

    /**
     * Create new ASCIIGraph.
     *
     * @param graph {@link UnidirectionalGraph<CharacterNode>}
     * @param start node
     * @param end node
     */
    public ASCIIGraph(final UnidirectionalGraph<CharacterNode> graph,
                         final CharacterNode start,
                         final CharacterNode end) {
        this.graph = graph;
        this.start = start;
        this.end = end;
    }

    /**
     * Get graph.
     *
     * @return graph {@link UnidirectionalGraph<CharacterNode>}
     */
    public UnidirectionalGraph<CharacterNode> getGraph() {
        return graph;
    }

    /**
     * Get start node.
     *
     * @return graph {@link UnidirectionalGraph<CharacterNode>}
     */
    public CharacterNode getStart() {
        return start;
    }

    public CharacterNode getEnd() {
        return end;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ASCIIGraph graph1 = (ASCIIGraph) o;
        return Objects.equals(getGraph(), graph1.getGraph()) &&
                Objects.equals(getStart(), graph1.getStart()) &&
                Objects.equals(getEnd(), graph1.getEnd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGraph(), getStart(), getEnd());
    }

    @Override
    public String toString() {
        return "ASCIIGraph{" +
                "graph=" + graph +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
