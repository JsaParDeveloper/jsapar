package exp.jsapar.types2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import exp.jsapar.exception.AnnotationException;
import exp.jsapar.types2.LineDefinitions.LineUserClass1;
import exp.jsapar.types2.LineDefinitions.LineUserClass3;
import exp.jsapar.types2.LineDefinitions.LineUserClass4;
import exp.jsapar.types2.LineDefinitions.LineUserClass6;
import exp.jsapar.types2.LineDefinitions.LineUserClass7;
import exp.jsapar.utils.DateUtil;

/**
 * Unit tests for testing adding of Cell objects to a Line object with divergent data types.
 * 
 * @author JsaPar Developer
 */
public class LineTestOnAdding {

    /**
     * Tests the adding of cells to an empty Line object.
     */
    @Test
    public void addingOfCellsIntoEmptyLine() {
        String expected = "testvalue";
        Line line = new Line();
        Cell cell1 = new Cell("test", expected);
        Cell cell2 = new Cell("Test", new Integer(10));
        line.addCells(cell1);
        line.addCells(cell2);
        String actual = line.getCell("test").getValue();
        assertEquals(actual, expected);
    }

    /**
     * Tests the adding of many cells to a Line object.
     */
    @Test
    public void addingOfManyCellsIntoLine() {
        int expected = 5;
        Line line = new Line();
        line.addCells(new Cell("a", 1), new Cell("b", 2), new Cell("c", 3), new Cell("d", 4), new Cell("e", 5));
        int actual = line.getNumberOfCells();
        assertEquals(actual, expected);
    }

    /**
     * Tests the adding of cells to a Line object, invalid cell (null).
     */
    @Test(expected = NullPointerException.class)
    public void addingOfCellIntoLineUsingInvalidCellNull() {
        Line line = new Line();
        Cell cell = null;
        line.addCells(cell);
        fail("Adding of an invalid Cell should have failed.");
    }

    /**
     * Tests the adding of cells to a Line object, invalid cell (cell name null).
     */
    @Test(expected = NullPointerException.class)
    public void addingOfCellIntoLineUsingInvalidCellNameNull() {
        Line line = new Line();
        line.addCells(new Cell(null, "a"));
        fail("Adding of an invalid Cell should have failed.");
    }

    /**
     * Tests the adding of cells to a Line object, invalid cell (cell name empty).
     */
    @Test(expected = IllegalArgumentException.class)
    public void addingOfCellIntoLineUsingInvalidCellNameEmpty() {
        Line line = new Line();
        Cell cell = new Cell("", "a");
        line.addCells(cell);
        fail("Adding of an invalid Cell should have failed.");
    }

    /**
     * Tests the adding of cells to a Line object with duplicate cell names.
     */
    @Test(expected = IllegalArgumentException.class)
    public void addingOfCellIntoLineDuplicateCellNames() {
        Line line = new Line();
        Cell cellA = new Cell("a", new Double(5.4));
        Cell cellB = new Cell("a", new Date());
        line.addCells(cellA);
        line.addCells(cellB);
        fail("Adding of two Cells with same name should have failed.");
    }

    /**
     * Tests the adding of cells to a Line object using one annotated user object.
     */
    @Test
    public void addingOfCellsIntoLineUsingOneAnnotatedUserObject() {
        BigDecimal expected = new BigDecimal("24234234");
        LineUserClass1 uc1 = LineDefinitions.getLineDefinitions().new LineUserClass1("john", DateUtil.now(), expected,
                6);
        Line line = new Line();
        line.addCellsOfAnnotatedObjects(uc1);
        BigDecimal actual = line.getCell(2).getValue();
        assertEquals(actual, expected);
        assertEquals(line.getNumberOfCells(), 4);
    }

    /**
     * Tests the adding of cells to a Line object using many annotated user objects.
     */
    @Test
    public void addingOfCellsIntoLineUsingManyAnnotatedUserObjects() {
        LineUserClass1 uc1 = LineDefinitions.getLineDefinitions().new LineUserClass1("john", DateUtil.now(),
                new BigDecimal("24234234"), 6);
        LineUserClass6 uc2 = LineDefinitions.getLineDefinitions().new LineUserClass6();
        LineUserClass7 uc3 = LineDefinitions.getLineDefinitions().new LineUserClass7("nancy", 34);
        Line line = new Line();
        line.addCellsOfAnnotatedObjects(uc1, uc2, uc3);

        assertEquals(line.getCell("money").getValue(), 342.99);
        assertEquals(line.getCell(7).getValue(), 34);
        assertEquals(line.getNumberOfCells(), 8);
    }

    /**
     * Tests the adding of cells to a Line object using an invalid annotated user object (missing @Line
     * annotation).
     */
    @Test(expected = AnnotationException.class)
    public void addingOfCellsIntoLineUsingInvalidAnnotatedUserObjectsNoLine() {
        LineUserClass3 uc3 = LineDefinitions.getLineDefinitions().new LineUserClass3();
        Line line = new Line();
        line.addCellsOfAnnotatedObjects(uc3);
        fail("Should have thrown an AnnotationException() exception.");
    }

    /**
     * Tests the adding of cells to a Line object using an invalid annotated user object (missing @Cell
     * annotations).
     */
    @Test(expected = AnnotationException.class)
    public void addingOfCellsIntoLineUsingInvalidAnnotatedUserObjectsNoCells() {
        LineUserClass4 uc4 = LineDefinitions.getLineDefinitions().new LineUserClass4();
        Line line = new Line();
        line.addCellsOfAnnotatedObjects(uc4);
        fail("Should have thrown an AnnotationException() exception.");
    }

    /**
     * Tests the adding of cells to a Line object using a collection of cells.
     */
    @Test
    public void addingOfCellsIntoLineUsingCollectionOfCells() {
        Cell cell1 = new Cell("this", new Integer(5));
        Cell cell2 = new Cell("is", null);
        Cell cell3 = new Cell("just", new Double(7.2));
        Cell cell4 = new Cell("a", new Float(8.7));
        Cell cell5 = new Cell("test", new Date());
        List<Cell> cellList = new ArrayList<Cell>();
        cellList.add(cell1);
        cellList.add(cell2);
        cellList.add(cell3);
        cellList.add(cell4);
        cellList.add(cell5);
        Line line = new Line();
        line.addCells(cellList);
        assertEquals(line.getNumberOfCells(), 5);
        assertEquals(line.getCell("just").getValue(), new Double(7.2));
    }

    /**
     * Tests the adding of cells to a Line object using a empty collection of cells.
     */
    @Test
    public void addingOfCellsIntoLineUsingEmptyCollectionOfCells() {
        List<Cell> cellList = new ArrayList<Cell>();
        Line line = new Line();
        // it's a valid action to add an empty cell list!
        line.addCells(cellList);
        assertEquals(line.getNumberOfCells(), 0);
    }

    /**
     * Tests the adding of cells to a Line object using a invalid collection of cells (null
     * pointer).
     */
    @Test(expected = NullPointerException.class)
    public void addingOfCellsIntoLineUsingCollectionOfCellsNullPointer() {
        Line line = new Line();
        ArrayList<Cell> cellList = null;
        line.addCells(cellList);
    }
}