package exp.jsapar.types2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Unit tests for testing the insertion of a Cell object within a Line object.
 * 
 * @author JsaPar Developer
 */
public class LineTestOnInserting {

    /**
     * Tests the insertion of a cell into an empty Line object, at the first position (index=0).
     */
    @Test
    public void insertionOfCellIntoEmptyLineFirstPosition() {
        Cell c1 = new Cell("score", new Double(6.9));
        Line line = new Line();
        line.insertCell(c1);
        assertEquals(line.getCell(0), c1);
    }

    /**
     * Tests the insertion of a cell into an empty Line object, at a specified position (index<>0).
     */
    @Test
    public void insertionOfCellIntoEmptyLineSpecifiedPosition() {
        Cell c1 = new Cell("score1", new Double(6.9));
        Cell c2 = new Cell("score2", new Character('='));
        Cell c3 = new Cell("score3", new Float(6.9));
        Line line = new Line(c1, c2);
        line.insertCell(1, c3);
        assertEquals(line.getCell(1), c3);
        assertEquals(line.getCell(2), c2);
    }

    /**
     * Tests the insertion of a cell into an empty Line object, after a non existed cell in the
     * list, using a cell name. This insertion will silently result in an add operation.
     */
    @Test
    public void insertionOfCellIntoEmptyLineAfterSpecifiedNonExistingCellName() {
        Cell c1 = new Cell("score", new Double(6.9));
        Cell c2 = new Cell("length", new Double(4.5));
        Cell c3 = new Cell("thick", new Double(8.9));
        Line line = new Line(c1, c2);
        line.insertCell("missed_shot", c3);
        assertEquals(c3, line.getCell(2));
    }

    /**
     * Tests the insertion of a cell into an empty Line object, after an existed cell in the list,
     * using a cell name.
     */
    @Test
    public void insertionOfCellIntoEmptyLineAfterSpecifiedExistingCellName() {
        Cell c1 = new Cell("score1", new Double(4.9));
        Cell c2 = new Cell("score2", new Double(5.9));
        Cell c3 = new Cell("score3", new Double(6.9));
        Cell c4 = new Cell("score4", new Double(7.9));
        Line line = new Line(c1, c2, c3);
        line.insertCell("score2", c4);
        assertEquals(line.getCell(2), c4);
        assertEquals(line.getCell(3), c3);
    }

    /**
     * Tests the insertion of an invalid cell (null) into a Line object.
     */
    @Test(expected = NullPointerException.class)
    public void insertionOfCellIntoLineNull() {
        Cell c1 = new Cell("score", new Double(6.9));
        Cell c2 = new Cell("record", new Double(9.0));
        Line line = new Line(c1, c2);
        line.insertCell(null);
        fail("Should have thrown a NullPointerException.");
    }

    /**
     * Tests the insertion of an invalid cell (null) into a Line object, at the given position.
     */
    @Test(expected = NullPointerException.class)
    public void insertionOfCellIntoLineNullWithPosition() {
        Cell c1 = new Cell("score", new Double(6.9));
        Cell c2 = new Cell("record", new Double(9.0));
        Line line = new Line(c1, c2);
        line.insertCell(1, null);
        fail("Should have thrown a NullPointerException.");
    }

    /**
     * Tests the insertion of a cell into a Line object, with a negative position.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void insertionOfCellIntoLineWithNegativePosition() {
        Cell c1 = new Cell("score", new Double(6.9));
        Cell c2 = new Cell("record", new Double(9.0));
        Cell c3 = new Cell("number", new Double(6.9));
        Line line = new Line(c1, c2);
        line.insertCell(-2, c3);
        fail("Should have thrown an IndexOutOfBoundsException.");
    }

    /**
     * Tests the insertion of a cell into a Line object, at the first position (index=0).
     */
    @Test
    public void insertionOfCellIntoLineFirstPosition() {
        Cell c1 = new Cell("score", new Double(6.9));
        Cell c2 = new Cell("record", new Double(9.0));
        Cell c3 = new Cell("length", new Double(4.5));
        Line line = new Line(c1, c2);
        line.insertCell(c3);
        assertEquals(line.getCell(0), c3);
        assertEquals(line.getCell(2), c2);
    }

    /**
     * Tests the insertion of a cell into a Line object, at a specified position (index<>0).
     */
    @Test
    public void insertionOfCellIntoLineSpecifiedPosition() {
        Cell c1 = new Cell("score", new Double(6.9));
        Cell c2 = new Cell("record", new Double(9.0));
        Cell c3 = new Cell("length", new Double(4.5));
        Line line = new Line(c1, c2);
        line.insertCell(1, c3);
        assertEquals(line.getCell(1), c3);
        assertEquals(line.getCell(2), c2);
    }

    /**
     * Tests the insertion of a cell into a Line object, at the end of the list.<br>
     * This insertion will silently result in an add operation.
     */
    @Test
    public void insertionOfCellIntoLineEndOfList() {
        Cell c1 = new Cell("score", new Double(6.9));
        Cell c2 = new Cell("record", new Double(9.0));
        Cell c3 = new Cell("length", new Double(4.5));
        Line line = new Line(c1, c2);
        line.insertCell(line.getNumberOfCells(), c3);
        assertEquals(line.getCell(2), c3);
        assertEquals(line.getCell(0), c1);
    }

    /**
     * Tests the insertion of a cell into a Line object, at a position beyond the boundary of the
     * list.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void insertionOfCellIntoLineBeyondArrayBoundary() {
        Cell c1 = new Cell("record", new Double(9.0));
        Cell c2 = new Cell("length", new Double(4.5));
        Cell c3 = new Cell("score", new Double(6.9));
        Line line = new Line(c1, c2);
        line.insertCell(5, c3);
        fail("Should have thrown an IndexOutOfBoundsException.");
    }

    /**
     * Tests the insertion of a cell into a Line object, at a position beyond the boundary of the
     * list with an invalid cell (null).
     */
    @Test(expected = NullPointerException.class)
    public void insertionOfCellIntoLineBeyondArrayBoundaryNullCell() {
        Cell c1 = new Cell("record", new Double(9.0));
        Cell c2 = new Cell("length", new Double(4.5));
        Line line = new Line(c1, c2);
        line.insertCell(5, null);
        fail("Should have thrown a NullPointerException.");
    }

    /**
     * Tests the insertion of a cell into a Line object, using an empty cell name.
     */
    @Test(expected = IllegalArgumentException.class)
    public void insertionOfCellIntoLineEmptyCellName() {
        Cell c1 = new Cell("score", new Double(6.9));
        Cell c2 = new Cell("length", new Double(4.5));
        Cell c3 = new Cell("thick", new Double(8.9));
        Line line = new Line(c1, c2);
        line.insertCell("", c3);
        fail("Should have thrown an IllegalArgumentException.");
    }

    /**
     * Tests the insertion of an invalid cell (null) into a Line object, using an empty cell name.
     */
    @Test(expected = NullPointerException.class)
    public void insertionOfCellIntoLineEmptyCellNameNullCell() {
        Cell c1 = new Cell("score", new Double(6.9));
        Cell c2 = new Cell("length", new Double(4.5));
        Line line = new Line(c1, c2);
        line.insertCell("", null);
        fail("Should have thrown a NullPointerException.");
    }

    /**
     * Tests the insertion of a cell into a Line object, after a non existed cell in the list, using
     * a cell name. This insertion will silently result in an add operation.
     */
    @Test
    public void insertionOfCellIntoLineAfterSpecifiedNonExistingCellName() {
        Cell c1 = new Cell("score", new Double(6.9));
        Cell c2 = new Cell("length", new Double(4.5));
        Cell c3 = new Cell("thick", new Double(8.9));
        Line line = new Line(c1, c2);
        line.insertCell("missed_shot", c3);
        assertEquals(c3, line.getCell(2));
    }

    /**
     * Tests the insertion of a cell into a Line object, after an existed cell in the list, using a
     * cell name.
     */
    @Test
    public void insertionOfCellIntoLineAfterSpecifiedExistingCellName() {
        Cell c1 = new Cell("score1", new Double(4.9));
        Cell c2 = new Cell("score2", new Double(5.9));
        Cell c3 = new Cell("score3", new Double(6.9));
        Cell c4 = new Cell("score4", new Double(7.9));
        Line line = new Line(c1, c2, c3);
        line.insertCell("score2", c4);
        assertEquals(line.getCell(2), c4);
        assertEquals(line.getCell(3), c3);
    }
}