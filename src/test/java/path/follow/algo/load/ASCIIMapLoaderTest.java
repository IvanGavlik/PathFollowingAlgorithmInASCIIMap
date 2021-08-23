package path.follow.algo.load;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import path.follow.algo.load.impl.FileASCIIMapLoader;
import path.follow.algo.load.impl.InternalASCIIMapLoader;

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
     *  filePath
     *
     *  Output
     *  if filePath set return new FileASCIIMapLoader
     *  else return InternalASCIIMapLoader
     */
    @Test()
    public void getInstance() {
        Assertions.assertEquals(InternalASCIIMapLoader.class, ASCIIMapLoader.getInstance(null).getClass());
        Assertions.assertEquals(InternalASCIIMapLoader.class, ASCIIMapLoader.getInstance("").getClass());
        Assertions.assertEquals(InternalASCIIMapLoader.class, ASCIIMapLoader.getInstance(" ").getClass());

        Assertions.assertEquals(FileASCIIMapLoader.class, ASCIIMapLoader.getInstance("somePath").getClass());
    }

}
