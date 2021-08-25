package path.follow.algo.stub;

import path.follow.algo.graph.vertex.CharacterNode;
import path.follow.algo.path.FindPath;

/**
 *  FindPath related stub.
 *
 * @author ivan.gavlik
 */
public final class FindPathStub {

    private FindPathStub() { }


    /**
     * Find Path instance that always returns true
     * and have hardcoded path.
     *
     * @return stub of FindPath
     */
    public static FindPath<CharacterNode> getBasicFindPath() {
        @SuppressWarnings("checkstyle:JavadocType")
        class FindPathOk implements FindPath<CharacterNode> {

            @Override
            public boolean hasPathTo(final CharacterNode node) {
                return true;
            }

            @Override
            public Iterable<CharacterNode> pathTo(final CharacterNode node) {
                return ASCIIGraphStub.getBasicPath();
            }
        }
        return new FindPathOk();
    }

    /**
     * Find Path instance that always returns false
     * and have hardcoded broken path.
     *
     * @return stub of FindPath
     */
    public static FindPath<CharacterNode> getBasicBrokenFindPath() {
        @SuppressWarnings("checkstyle:JavadocType")
        class FindPathBroken implements FindPath<CharacterNode> {

            @Override
            public boolean hasPathTo(final CharacterNode node) {
                return false;
            }

            @Override
            public Iterable<CharacterNode> pathTo(final CharacterNode destination) {
                return ASCIIGraphStub.getBasicBrokenPath();
            }
        }
        return new FindPathBroken();
    }



}

