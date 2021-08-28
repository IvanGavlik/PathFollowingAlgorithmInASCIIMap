package path.follow.algo.load;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import path.follow.algo.load.impl.InternalASCIIMapLoader;
import path.follow.algo.load.impl.TerminalASCIIMapLoader;

/**
 * Test for {@link ASCIIMapLoader}.
 *
 * Note only for static methods.
 *
 * @author ivan.gavlik
 */
public class ASCIIMapLoaderTest {


    /**
     *  Test create new ASCIIMapLoaderTest instance.
     *
     *  Input
     *  program args
     *
     *  Output
     *  if program arguments exist set return new {@link TerminalASCIIMapLoader}
     *  else return {@link InternalASCIIMapLoader}
     */
    @Test()
    public void getInstance() {
        Assertions.assertEquals(InternalASCIIMapLoader.class, ASCIIMapLoader.getInstance(null).getClass());
        final String[] empty = new String[0];
        Assertions.assertEquals(InternalASCIIMapLoader.class, ASCIIMapLoader.getInstance(empty).getClass());

        final String[] programArgs = {"-f", "src//test//resources//form#To&.txt", "-s", "#", "-e", "&"};
        Assertions.assertEquals(TerminalASCIIMapLoader.class, ASCIIMapLoader.getInstance(programArgs).getClass());
    }

}
