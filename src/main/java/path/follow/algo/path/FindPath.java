package path.follow.algo.path;

/**
 * Finds path on graph.
 *
 * @param <VERTEX>
 * @author ivan.gavlik
 */
public interface FindPath<VERTEX> {

    /**
     * Return true if path form source vertex to destination exist.
     *
     * Source is on creating new FindPath instance
     *
     * @param destination VERTEX
     * @return true if path form source to destination exist.
     */
    boolean hasPathTo(VERTEX destination);

    /**
     * Return path from source to destination if exist.
     *
     * Source is on creating new FindPath instance
     *
     * @param destination VERTEX
     * @return path if exist or empty {@link Iterable}
     */
    Iterable<VERTEX> pathTo(VERTEX destination);
}
