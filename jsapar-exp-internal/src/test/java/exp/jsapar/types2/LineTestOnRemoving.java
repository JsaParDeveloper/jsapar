package exp.jsapar.types2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigInteger;
import java.util.Date;

import org.junit.Test;

/**
 * Unit tests for testing the removal of Cell objects from a Line object.
 * 
 * @author JsaPar Developer
 */
public class LineTestOnRemoving {

    /**
     * Tests removing a cell from an empty Line object using an cell name.
     */
    @Test
    public void removingOfCellFromEmptyLineCellName() {
        Line line = new Line();
        Cell actual = line.removeCell("test");
        assertNull(actual);
    }

    /**
     * Tests removing a cell from an empty Line object using an index.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void removingOfCellFromEmptyLineIndex() {
        Line line = new Line();
        @SuppressWarnings("unused")
        Cell actual = line.removeCell(0);
        fail("Should have thrown an IndexOutOfBoundsException.");
    }

    /**
     * Tests removing a cell from a Line object with a valid index.
     */
    @Test
    public void removingOfCellFromLineIndexWithinBounds() {
        Cell c1 = new Cell("one", new Character('1'));
        Cell c2 = new Cell("two", new BigInteger("2"));
        Cell c3 = new Cell("three", new Double(3));
        Line line = new Line(c1, c2, c3);
        Cell actual = line.removeCell(0);
        assertEquals(c1, actual);
        assertEquals(line.getCell(1), c3);
        assertEquals(line.getNumberOfCells(), 2);
    }

    /**
     * Tests removing a cell from a Line object with an invalid index (out of bounds).
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void removingOfCellFromLineIndexOutOfBounds() {
        Cell c1 = new Cell("one", new Character('1'));
        Cell c2 = new Cell("two", new BigInteger("2"));
        Cell c3 = new Cell("three", new Double(3));
        Line line = new Line(c1, c2, c3);
        @SuppressWarnings("unused")
        Cell actual = line.removeCell(7);
        fail("Should have thrown an IndexOutOfBoundsException.");
    }

    /**
     * Tests removing a cell from a Line object with an existing cell name.
     */
    @Test
    public void removingOfCellFromLineExistingCellName() {
        Cell c1 = new Cell("sign", new Character('+'));
        Cell c2 = new Cell("number", new BigInteger("1"));
        Cell c3 = new Cell("digit", new Double(2));
        Line line = new Line(c1, c2, c3);
        Cell actual = line.removeCell("number");
        assertEquals(c2, actual);
        assertEquals(line.getNumberOfCells(), 2);
        assertEquals(line.getCell(1), c3);
    }

    /**
     * Tests removing a cell from a Line object with a non existing cell name.
     */
    @Test
    public void removingOfCellFromLineNonExistingCellName() {
        Cell c1 = new Cell("sign", new Character('+'));
        Cell c2 = new Cell("number", new BigInteger("1"));
        Cell c3 = new Cell("digit", new Double(2));
        Line line = new Line(c1, c2, c3);
        Cell actual = line.removeCell("whatever");
        assertNull(actual);
    }

    /**
     * Tests removing all cells from a Line object.
     */
    @Test
    public void removingOfCellsFromEmptyLine() {
        Cell c1 = new Cell("this", new Integer(5));
        Cell c2 = new Cell("is", null);
        Cell c3 = new Cell("just", new Double(7.2));
        Cell c4 = new Cell("a", new Float(8.7));
        Cell c5 = new Cell("test", new Date());
        Line line = new Line(c1, c2, c3, c4, c5);
        line.clear();
        assertTrue(line.isEmpty());
        assertEquals(line.getNumberOfCells(), 0);
    }
}