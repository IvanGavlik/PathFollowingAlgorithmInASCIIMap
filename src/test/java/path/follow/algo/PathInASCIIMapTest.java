package path.follow.algo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import path.follow.algo.validation.ValidationException;


/**
 * Tests for PathInASCIIMap.
 *
 * @author ivan.gavlik
 */
public class PathInASCIIMapTest {

    //TODO TEST CONFIGURATION FOR FIND METHOD

    /**
     * Basic test.
     *
     * Input:
     *
     * Output:
     *
     * @param pathToInputFile Path to test file {@link String}
     * @param expectedLetters Expected letters {@link String}
     * @param expectedPath Expected Path {@link String}
     */
    @ParameterizedTest
    @CsvSource({
            "src//test//resources//path//basic.txt, ACB, @---A---+|C|+---+|+-B-x",
            "src//test//resources//path//intersections.txt, ABCD, @|A+---B--+|+--C-+|-||+---D--+|x",
            "src//test//resources//path//lettersOnTurns.txt, ACB, @---A---+|||C---+|+-B-x",
            "src//test//resources//path//letterSameLocation.txt, GOONIES, @-G-O-+|+-+|O||+-O-N-+|I|+-+|+-I-+|ES|x",
            "src//test//resources//path//keepDirection.txt, BLAH, @B+++B|+-L-+A+++A-+Hx",
    })
    public void testPaths(final String pathToInputFile, final String expectedLetters, final String expectedPath) {
        final String[] programArgs = {pathToInputFile};
        final PathInASCIIMap.MapPath path = PathInASCIIMap.find(programArgs);
        Assertions.assertEquals(expectedLetters, path.getLetters());
        Assertions.assertEquals(expectedPath, path.getPath());
    }


    /**
     * Basic test.
     *
     * Input:
     *
     * Output:
     *
     * @param pathToInputFile Path to test file {@link String}
     */
    @ParameterizedTest
    @CsvSource({
            "src//test//resources//path//noStart.txt",
            "src//test//resources//path//noEnd.txt",
            "src//test//resources//path//multipleStarts.txt",
            "src//test//resources//path//multipleEnds.txt",
            "src//test//resources//path//tForks.txt",
            "src//test//resources//path//brokenPath.txt",
            "src//test//resources//path//multipleStartingPaths.txt",
            "src//test//resources//path//fakeTurn.txt",
    })
    public void testPathsOnError(final String pathToInputFile) {
        final String[] programArgs = {pathToInputFile};
        Assertions.assertThrows(ValidationException.class, () -> {
            PathInASCIIMap.find(programArgs);
        });

    }
}
