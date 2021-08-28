package path.follow.algo.stub;

import path.follow.algo.load.ASCIIMap;

import java.util.ArrayList;
import java.util.List;

/**
 * ASCIIMap related stub.
 *
 * @author ivan.gavlik
 */
public final class ASCIIMapStub {

    private ASCIIMapStub() { }

    /**
     * ASCII map.
     *  @-A-+
     *      |
     *      x
     *
     * start char @
     * end char x
     *
     * @return ASCII map {@link  ASCIIMapStubImpl}
     */
    public static ASCIIMap getBasicASCIIMap() {
        return new ASCIIMapStubImpl();
    }

    /**
     * ASCIIMap related stub.
     *
     * @author ivan.gavlik
     */
    static class ASCIIMapStubImpl implements ASCIIMap {

        @Override
        public List<String> getMap() {
            final List<String> list = new ArrayList<>();
            list.add("@-A-+");
            list.add("    |");
            list.add("    x");
            return list;
        }

        @Override
        public Character getStart() {
            return ASCIIMap.DEFAULT_START;
        }

        @Override
        public Character getEnd() {
            return ASCIIMap.DEFAULT_END;
        }
    }

}
