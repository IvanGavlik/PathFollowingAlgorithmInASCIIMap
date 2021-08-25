package path.follow.algo.validators;

import path.follow.algo.graph.vertex.CharacterNode;
import path.follow.algo.path.FindPath;
import path.follow.algo.validation.Validator;

import java.util.Objects;


/**
 * Validate that path is not broken
 *
 * Example of broken path.
 *   @--A-+
 *         |
 *
 *         B-x
 *
 * @author ivan.gavlik
 */
public class PathIsNotBroken implements Validator<FindPath<CharacterNode>> {

    private CharacterNode node;

    /**
     * Create new instance of PathIsNotBroken validator.
     *
     * @param node {@link CharacterNode}
     */
    public PathIsNotBroken(final CharacterNode node) {
        if (Objects.isNull(node)) {
            throw new IllegalArgumentException();
        }
        this.node = node;
    }

    @Override
    public String getMsgOnFail(final FindPath<CharacterNode> asciiGraph) {
        return "Path is broken";
    }

    @Override
    public boolean test(final FindPath<CharacterNode> asciiGraph) {
        if (Objects.isNull(asciiGraph)) {
            return false;
        }
        return asciiGraph.hasPathTo(this.node);
    }
}
