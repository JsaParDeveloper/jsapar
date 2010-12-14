package exp.jsapar.types2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.junit.Test;

/**
 * Unit tests for testing the replacement of a Cell object with another Cell object within a Line
 * object.
 * 
 * @author JsaPar Developer
 */
public class LineTestOnReplacing {

    /**
     * Tests replacing a cell object from an empty Line object using another cell object with the
     * same cell name.
     */
    @Test
    public void replacingOfCellsInLineByCellEmptyLine() {
        Line line = new Line();
        Cell cell = new Cell("test", new Integer(15));
        Cell replacedCell = line.replaceCell(cell);
        assertNull(replacedCell);
    }

    /**
     * Tests replacing a cell from a Line object using another cell object with the same cell name.
     */
    @Test
    public void replacingOfCellsInLineByCell() {
        Cell c1 = new Cell("Test", new Character('?'));
        Cell c2 = new Cell("test", new Double(7.2));
        Cell c3 = new Cell("Test", new Integer(3));
        Line line = new Line(c1, c2);
        Cell replacedCell = line.replaceCell(c3);
        assertEquals(replacedCell, c1);
        assertEquals(c3, line.getCell(0));
    }

    /**
     * Tests replacing a cell from a Line object using an invalid cell object (null).
     */
    @Test(expected = NullPointerException.class)
    public void replacingOfCellsInLineByCellNull() {
        Cell c1 = new Cell("Test", new Character('?'));
        Cell c2 = new Cell("test", new Double(7.2));
        Line line = new Line(c1, c2);
        @SuppressWarnings("unused")
        Cell replacedCell = line.replaceCell(null);
    }

    // ------------------------------------------------------------------------

    // replaceCell(int , Cell)

    // = valid cell: valid index, invalid index, case sensitive cellname.

    /**
     * Tests replacing a cell object from a Line object using a valid index, and case sensitive cell
     * names.
     */
    @Test
    public void replacingOfCellsInEmptyLineByIndex() {
        Cell c1 = new Cell("Test", new Character('?'));
        Cell c2 = new Cell("test", new Double(7.2));
        Cell c3 = new Cell("words", new Integer(50));
        Cell c4 = new Cell("warning", new Character('!'));
        Line line = new Line(c1, c2, c3);
        Cell replacedCell = line.replaceCell(1, c4);
        assertEquals(replacedCell, c2);
        assertEquals(c4, line.getCell(1));
    }

    /**
     * Tests replacing a cell object from a Line object using an invalid index.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void replacingOfCellsInLineByIndexInvalidIndex() {
        Cell c1 = new Cell("Test", new Character('?'));
        Cell c2 = new Cell("test", new Double(7.2));
        Cell c3 = new Cell("words", new Integer(50));
        Cell c4 = new Cell("warning", new Character('!'));
        Line line = new Line(c1, c2, c3);
        @SuppressWarnings("unused")
        Cell replacedCell = line.replaceCell(-8, c4);
        fail("Should have thrown an IndexOutOfBoundsException.");
    }

    /**
     * Tests replacing a cell object from a Line object using an index with an invalid cell object
     * (null).
     */
    @Test(expected = NullPointerException.class)
    public void replacingOfCellsInLineByIndexCellNull() {
        Cell c1 = new Cell("Test", new Character('*'));
        Cell c2 = new Cell("Case", new Character('@'));
        Line line = new Line(c1, c2);
        @SuppressWarnings("unused")
        Cell replacedCell = line.replaceCell(1, null);
    }

    /**
     * Tests replacing a cell object from a Line object using an index (within bounds).
     */
    @Test
    public void replacingOfCellsInLineByIndexWithinBounds() {
        Cell c1 = new Cell("words", new Integer(50));
        Cell c2 = new Cell("test", new Double(7.2));
        Cell c3 = new Cell("box", new Character('?'));
        Cell c4 = new Cell("gap", new BigDecimal("344"));
        Line line = new Line(c1, c2, c3);
        Cell replacedCell = line.replaceCell(0, c4);
        assertEquals(replacedCell, c1);
        assertEquals(line.getCell(0), c4);
    }

    /**
     * Tests replacing a cell object from a Line object using an index (out of bounds).
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void replacingOfCellsInLineByIndexOutOfBounds() {
        Cell c1 = new Cell("words", new Integer(50));
        Cell c2 = new Cell("test", new Double(7.2));
        Cell c3 = new Cell("box", new Character('?'));
        Line line = new Line(c1, c2);
        @SuppressWarnings("unused")
        Cell replacedCell = line.replaceCell(6, c3);
        fail("Should have thrown an IndexOutOfBoundsException.");
    }

    /**
     * Tests replacing a cell object from a Line object using a valid index and a case sensitive
     * duplicate cell name.
     */
    @Test(expected = IllegalArgumentException.class)
    public void replacingOfCellsInLineByIndexValidIndexCellNameCaseSensitive() {
        Cell c1 = new Cell("test", new Double(7.2));
        Cell c2 = new Cell("Test", new Character('?'));
        Cell c3 = new Cell("words", new Integer(50));
        Cell c4 = new Cell("Test", new Character('!'));
        Line line = new Line(c1, c2, c3);
        @SuppressWarnings("unused")
        Cell replacedCell = line.replaceCell(0, c4);
        fail("Should have thrown an IllegalArgumentException.");
    }

    /**
     * Tests replacing a cell object from a Line object using a valid index and a case sensitive
     * duplicate cell name, with same cell name.
     */
    @Test
    public void replacingOfCellsInLineByIndexValidIndexCellNameCaseSensitiveSameCellName() {
        Cell c1 = new Cell("test", new Double(7.2));
        Cell c2 = new Cell("Test", new Character('?'));
        Cell c3 = new Cell("words", new Integer(50));
        Cell c4 = new Cell("Test", new Character('!'));
        Line line = new Line(c1, c2, c3);
        Cell replacedCell = line.replaceCell(1, c4);
        assertEquals(c2, replacedCell);
        assertEquals(c4, line.getCell(1));
    }
    
    // ------------------------------------------------------------------------

    /**
     * Tests replacing a cell object from a Line object using a cell name.
     */
    @Test
    public void replacingOfCellsInLineByCellName() {
        Cell c1 = new Cell("words", new Integer(50));
        Cell c2 = new Cell("test", new Double(7.2));
        Cell c3 = new Cell("box", new Character('?'));
        Cell c4 = new Cell("warning", new Character('!'));
        Line line = new Line(c1, c2, c3);
        Cell replacedCell = line.replaceCell("test", c4);
        assertEquals(replacedCell, c2);
    }

    /**
     * Tests replacing a cell object from an empty Line object using a cell name.
     */
    @Test
    public void replacingOfCellsInEmptyLineByCellName() {
        Line line = new Line();
        Cell c1 = new Cell("test", "case");
        Cell replacedCell = line.replaceCell("find", c1);
        assertNull(replacedCell);
    }

    /**
     * Tests replacing a cell object from a Line object using an empty cell name.
     */
    @Test(expected = IllegalArgumentException.class)
    public void replacingOfCellsInLineByCellNameEmpty() {
        Cell c1 = new Cell("words", new Integer(50));
        Cell c2 = new Cell("test", new Double(7.2));
        Cell c3 = new Cell("box", new Character('#'));
        Line line = new Line(c1, c2);
        @SuppressWarnings("unused")
        Cell replacedCell = line.replaceCell("", c3);
        fail("Should have thown an IllegalArgumentException.");
    }

    /**
     * Tests replacing a cell object from a Line object using a cell name that is null.
     */
    @Test(expected = NullPointerException.class)
    public void replacingOfCellsInLineByCellNameNull() {
        Cell c1 = new Cell("words", new Integer(50));
        Cell c2 = new Cell("test", new Double(7.2));
        Cell c3 = new Cell("box", new Character('#'));
        Line line = new Line(c1, c2);
        @SuppressWarnings("unused")
        Cell replacedCell = line.replaceCell(null, c3);
    }

    /**
     * Tests replacing a cell object from a Line object using a cell that is null.
     */
    @Test(expected = NullPointerException.class)
    public void replacingOfCellsInLineByCellNameCellNull() {
        Cell c1 = new Cell("words", new Integer(50));
        Cell c2 = new Cell("test", new Double(7.2));
        Line line = new Line(c1, c2);
        @SuppressWarnings("unused")
        Cell replacedCell = line.replaceCell("test", null);
    }

    /**
     * Tests replacing a cell object from a Line object using a cell that is case sensitive.
     */
    @Test
    public void replacingOfCellsInLineByCellNameCellCaseSensitive() {
        Cell c1 = new Cell("words", new Integer(50));
        Cell c2 = new Cell("test", new Double(7.2));
        Cell c3 = new Cell("Test", new Double(9.2));
        Line line = new Line(c1, c2);
        Cell replacedCell = line.replaceCell("test", c3);
        assertEquals(replacedCell, c2);
        assertEquals(line.getCell(1), c3);
    }
}