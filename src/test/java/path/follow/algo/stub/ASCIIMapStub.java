package path.follow.algo.stub;

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
     * @return ASCII map {@link  List<String>}
     */
    public static List<String> getBasicASCIIMap() {
        final List<String> list = new ArrayList<>();
        list.add("@-A-+");
        list.add("    |");
        list.add("    x");
        return list;
    }
}
