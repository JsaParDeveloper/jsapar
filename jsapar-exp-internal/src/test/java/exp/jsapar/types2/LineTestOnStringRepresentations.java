package exp.jsapar.types2;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;

/**
 * Unit tests for testing the toString representation of Line objects with divergent data types.
 * 
 * @author JsaPar Developer
 */
public class LineTestOnStringRepresentations {

    // TODO add more tests

    /**
     * Tests the textual representation of an empty Line.
     */
    @Test
    public void toStringRepresentationOfEmptyLine() {
        Line line = new Line();
        String expectedResult = ""; // should be empty
        String actualResult = line.toString();
        assertEquals(expectedResult, actualResult);
    }

    /**
     * Tests the textual representation of a Line with one Cell object.
     */
    @Test
    public void toStringRepresentationOfLineWithOneCell() {
        Cell cell = new Cell("luckyNumber", new Integer(7));
        Line line = new Line(cell);
        String expectedResult = "Cell 0: [name: luckyNumber, value: 7]\n";
        String actualResult = line.toString();
        assertEquals(expectedResult, actualResult);
    }

    /**
     * Tests the textual representation of a Line with many Cell objects.
     */
    @Test
    public void toStringRepresentationOfLineWithManyCells() {
        String expectedResult1 = "Cell 0: [name: this, value: 5]\n" + "Cell 1: [name: is, value: null]\n"
                + "Cell 2: [name: just, value: 7.2]\n" + "Cell 3: [name: a, value: 8.7]\n"
                + "Cell 4: [name: test, value: 34233623]\n";
        String expectedResult2 = "Cell 00: [name: this, value: 5]\n"
                + "Cell 01: [name: is, value: null]\n" + "Cell 02: [name: just, value: 7.2]\n"
                + "Cell 03: [name: a, value: 8.7]\n" + "Cell 04: [name: test, value: 34233623]\n"
                + "Cell 05: [name: This, value: 7]\n" + "Cell 06: [name: also is , value: 5]\n"
                + "Cell 07: [name: a great, value: null]\n" + "Cell 08: [name: testcase, value: 7.2]\n"
                + "Cell 09: [name: for, value: 8.7]\n" + "Cell 10: [name: testing, value: 2312233.22]\n";
        Cell cell1 = new Cell("this", new Integer(5));
        Cell cell2 = new Cell("is", null);
        Cell cell3 = new Cell("just", new Double(7.2));
        Cell cell4 = new Cell("a", new Float(8.7));
        Cell cell5 = new Cell("test", new BigInteger("34233623"));
        Cell cell6 = new Cell("This", new Integer(7));
        Cell cell7 = new Cell("also is ", new Integer(5));
        Cell cell8 = new Cell("a great", null);
        Cell cell9 = new Cell("testcase", new Double(7.2));
        Cell cell10 = new Cell("for", new Float(8.7));
        Cell cell11 = new Cell("testing", new BigDecimal("2312233.22"));

        Line line = new Line(cell1, cell2, cell3, cell4, cell5);
        String actualResult1 = line.toString();
        line.addCells(cell6, cell7, cell8, cell9, cell10, cell11);
        String actualResult2 = line.toString();
        assertEquals(expectedResult1, actualResult1);
        assertEquals(expectedResult2, actualResult2);
    }
}