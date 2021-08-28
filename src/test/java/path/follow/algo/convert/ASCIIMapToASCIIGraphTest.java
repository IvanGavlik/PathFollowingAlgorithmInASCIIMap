package path.follow.algo.convert;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import path.follow.algo.graph.UnidirectionalGraph;
import path.follow.algo.graph.vertex.CharacterNode;
import path.follow.algo.load.ASCIIMap;
import path.follow.algo.stub.ASCIIMapStub;
import path.follow.algo.stub.UnidirectionalGraphStub;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests for {@link ASCIIMapToASCIIGraph}.
 *
 * @author ivan.gavlik
 */
public class ASCIIMapToASCIIGraphTest {


    /**
     * Test basic ASCIi map.
     *
     * Input:
     * ASCIi map that contains
     * @ - start character
     * x - end character
     * + - try changer direction
     * A - try changer direction
     * | - keep direction
     * - - keep direction
     *
     * Output:
     * ASCIIGraph
     *  - start node
     *  - end node
     *  - graph
     */
    @Test
    public void basic() {
        final ASCIIGraph graph = ASCIIMapToASCIIGraph.convert(ASCIIMapStub.getBasicASCIIMap());

        Assertions.assertEquals(ASCIIMap.DEFAULT_START, graph.getStart().getValue());
        Assertions.assertEquals(ASCIIMap.DEFAULT_END, graph.getEnd().getValue());
        final UnidirectionalGraph<CharacterNode> expectedGraph = UnidirectionalGraphStub.getBasicASCIIGraph();
        Assertions.assertEquals(expectedGraph.getEdgesCount(), graph.getGraph().getEdgesCount());
        Assertions.assertEquals(expectedGraph.getVertexCount(), graph.getGraph().getVertexCount());
        Assertions.assertEquals(expectedGraph.toString(), graph.getGraph().toString());
    }

    /**
     * Test when null is passed in {@link ASCIIMap}.
     *
     * Input
     * one of values in {@link ASCIIMap} is null
     *
     * Output
     * {@link IllegalArgumentException}
     */
    @Test
    public void testNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings({"checkstyle:JavadocType"})
            class ASCIIMapImplementation implements ASCIIMap {

                @Override
                public List<String> getMap() {
                    return null;
                }

                @Override
                public Character getStart() {
                    return DEFAULT_START;
                }

                @Override
                public Character getEnd() {
                    return DEFAULT_END;
                }
            }
            final ASCIIMap map = new ASCIIMapImplementation();
            ASCIIMapToASCIIGraph.convert(map);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings({"checkstyle:JavadocType"})
            class ASCIIMapImplementation implements ASCIIMap {

                @Override
                public List<String> getMap() {
                    return new ArrayList<>();
                }

                @Override
                public Character getStart() {
                    return null;
                }

                @Override
                public Character getEnd() {
                    return DEFAULT_END;
                }
            }
            final ASCIIMap map = new ASCIIMapImplementation();
            ASCIIMapToASCIIGraph.convert(map);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings({"checkstyle:JavadocType"})
            class ASCIIMapImplementation implements ASCIIMap {

                @Override
                public List<String> getMap() {
                    return new ArrayList<>();
                }

                @Override
                public Character getStart() {
                    return DEFAULT_START;
                }

                @Override
                public Character getEnd() {
                    return null;
                }
            }

            final ASCIIMap map = new ASCIIMapImplementation();
            ASCIIMapToASCIIGraph.convert(map);
        });
    }

    /**
     * Test when empty ASCIIMap is passed.
     *
     * Input
     * empty ASCIIMap
     *
     * Output
     * ASCIIGraph with empty list
     * and start and end are null
     */
    @Test
    @SuppressWarnings("checkstyle:MagicNumber")
    public void testEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings({"checkstyle:JavadocType"})
            class ASCIIMapImplementation implements ASCIIMap {

                @Override
                public List<String> getMap() {
                    return new ArrayList<>();
                }

                @Override
                public Character getStart() {
                    return DEFAULT_START;
                }

                @Override
                public Character getEnd() {
                    return DEFAULT_END;
                }
            }

            final ASCIIMap map = new ASCIIMapImplementation();
            ASCIIMapToASCIIGraph.convert(map);
        });
    }

    /**
     * Test when empty start or end character do not exist in ASCIIMap.
     *
     * Input
     * empty ASCIIMap
     * not existing start Character
     * not existing end Character
     *
     * Output
     * {@link IllegalArgumentException}
     */
    @Test
    @SuppressWarnings("checkstyle:MagicNumber")
    public void noStartNoEndCharacter() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings({"checkstyle:JavadocType"})
            class ASCIIMapImplementation implements ASCIIMap {

                @Override
                public List<String> getMap() {
                    return ASCIIMapStub.getBasicASCIIMap().getMap();
                }

                @Override
                public Character getStart() {
                    return 'Z';
                }

                @Override
                public Character getEnd() {
                    return DEFAULT_END;
                }
            }

            final ASCIIMap map = new ASCIIMapImplementation();
            ASCIIMapToASCIIGraph.convert(map);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings({"checkstyle:JavadocType"})
            class ASCIIMapImplementation implements ASCIIMap {

                @Override
                public List<String> getMap() {
                    return ASCIIMapStub.getBasicASCIIMap().getMap();
                }

                @Override
                public Character getStart() {
                    return DEFAULT_START;
                }

                @Override
                public Character getEnd() {
                    return '%';
                }
            }

            final ASCIIMap map = new ASCIIMapImplementation();
            ASCIIMapToASCIIGraph.convert(map);
        });
    }

}
