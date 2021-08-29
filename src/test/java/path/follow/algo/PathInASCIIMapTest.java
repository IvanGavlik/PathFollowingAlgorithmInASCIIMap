package path.follow.algo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import path.follow.algo.validation.ValidationException;


/**
 * Tests for PathInASCIIMap.
 *
 * @author ivan.gavlik
 */
public class PathInASCIIMapTest {

    /**
     * Test input params.
     */
    @Test
    public void programInputParams() {
        // if input params are not specified use default map
        final String[] programArgs = {};
        final PathInASCIIMap.MapPath path = PathInASCIIMap.find(programArgs);
        Assertions.assertEquals("ACB", path.getLetters());
        Assertions.assertEquals("@---A---+|C|+---+|+-B-x", path.getPath());

        // param must have id (-f -s or -e)
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            final String[] programArgs2 = { "test" };
            PathInASCIIMap.find(programArgs2);
        });

        //  second and third input param are start and end
        final String[] programArgs3 = {"-f", "src//test//resources//form#To&.txt", "-s", "#", "-e", "&"};
        final PathInASCIIMap.MapPath path3 = PathInASCIIMap.find(programArgs3);
        Assertions.assertEquals("ACB", path3.getLetters());
        Assertions.assertEquals("#---A---+|C|+---+|+-B-&", path3.getPath());

        //  second and third input param are start and end - case there are not in file
        Assertions.assertThrows(ValidationException.class, () -> {
            final String[] programArgs4 = {"-f", "src//test//resources//form#To&.txt", "-s", "%", "-e", "$"};
            PathInASCIIMap.find(programArgs4);
        });

        // second input param is not set
        final String[] programArgs4 = {"-f", "src//test//resources//form@To&.txt", "-e", "&"};
        final PathInASCIIMap.MapPath path4 = PathInASCIIMap.find(programArgs4);
        Assertions.assertEquals("ACB", path4.getLetters());
        Assertions.assertEquals("@---A---+|C|+---+|+-B-&", path4.getPath());


        // input param does not have value
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            final String[] programArgs5 = {"-f", "src//test//resources//form#To&.txt", "-s", "-e", "$"};
            PathInASCIIMap.find(programArgs5);
    });

    }

    /**
     * Basic test.
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
        final String[] programArgs = {"-f", pathToInputFile};
        final PathInASCIIMap.MapPath path = PathInASCIIMap.find(programArgs);
        Assertions.assertEquals(expectedLetters, path.getLetters());
        Assertions.assertEquals(expectedPath, path.getPath());
    }


    /**
     * Test errors.
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
        Assertions.assertThrows(ValidationException.class, () -> {
            final String[] programArgs = {"-f", pathToInputFile};
            PathInASCIIMap.find(programArgs);
        });

    }

    /**
     * Two directions
     */
    @ParameterizedTest
    @CsvSource({
            "src/test/resources/twoDirections.txt",
    })
    public void twoDirections(final String pathToInputFile) {
        final String[] programArgs = {"-f", pathToInputFile};
        final PathInASCIIMap.MapPath path = PathInASCIIMap.find(programArgs);
        Assertions.assertEquals("AB", path.getLetters());
        Assertions.assertEquals("@---A---+|+--B---+||x", path.getPath());

    }
}
