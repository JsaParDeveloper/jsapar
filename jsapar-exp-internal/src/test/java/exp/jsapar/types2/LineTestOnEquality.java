package exp.jsapar.types2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Unit tests for testing the equality of Line objects with divergent data types.
 * 
 * @author JsaPar Developer
 */
public class LineTestOnEquality {

    // TODO implement more tests

    // ------------------------------------------------------------------------

    /**
     * Test equality of lines with divergent cell values. Unequal lines.
     */
    @Test
    public void equalityOfSameLengthLinesUnequal() {
        Cell c1 = new Cell("firstname", "felipe");
        Cell c2 = new Cell("lastname", "rodriques");
        Cell c3 = new Cell("age", 22);
        Cell c4 = new Cell("shoesize", 11);
        Line expectedLine = new Line(c1, c2, c3, c4);

        Cell c5 = new Cell("firstname", "danielle");
        Cell c6 = new Cell("lastname", "rodriques");
        Cell c7 = new Cell("age", 25);
        Cell c8 = new Cell("shoesize", 9);
        Line actualLine = new Line(c5, c6, c7, c8);

        assertNotSame(expectedLine, actualLine);
    }

    /**
     * Test equality of lines with divergent cell values. Equal lines.
     */
    @Test
    public void equalityOfSameLengthLinesEqual() {
        Cell c1 = new Cell("firstname", "dusty");
        Cell c2 = new Cell("lastname", "springfield");
        Cell c3 = new Cell("age", 60);
        Cell c4 = new Cell("shoesize", 9);
        Line expectedLine = new Line(c1, c2, c3, c4);

        Cell c5 = new Cell("firstname", "dusty");
        Cell c6 = new Cell("lastname", "springfield");
        Cell c7 = new Cell("age", 60);
        Cell c8 = new Cell("shoesize", 9);
        Line actualLine = new Line(c5, c6, c7, c8);

        assertEquals(expectedLine, actualLine);
    }

    // ------------------------------------------------------------------------

    /**
     * Enum for testing purposes.
     */
    @Ignore
    private enum CarColors {
        BLUE, RED, WHITE;
    };

    /**
     * Test equality of lines with divergent cell values. Different cell list length.
     */
    @Test
    public void equalityOfLinesWithDifferentCellListLengths() {
        Cell c1 = new Cell("carbrand", "ferrari");
        Cell c2 = new Cell("cartype", "testarossa");
        Line expectedLine = new Line(c1, c2);

        Cell c5 = new Cell("carbrand", "ferrari");
        Cell c6 = new Cell("cartype", "testarossa");
        Cell c7 = new Cell("carcolor", CarColors.RED);
        Line actualLine = new Line(c5, c6, c7);

        assertNotSame(expectedLine, actualLine);
    }

    // ------------------------------------------------------------------------

}