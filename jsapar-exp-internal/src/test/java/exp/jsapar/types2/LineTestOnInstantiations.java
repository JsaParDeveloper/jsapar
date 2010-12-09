package exp.jsapar.types2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import exp.jsapar.exception.AnnotationException;
import exp.jsapar.types2.LineDefinitions.LineUserClass1;
import exp.jsapar.types2.LineDefinitions.LineUserClass2;
import exp.jsapar.types2.LineDefinitions.LineUserClass3;
import exp.jsapar.types2.LineDefinitions.LineUserClass4;
import exp.jsapar.types2.LineDefinitions.LineUserClass5;

/**
 * Unit tests for testing the instantiation of Line objects with divergent data types.
 * 
 * @author JsaPar Developer
 */
public class LineTestOnInstantiations {

    // TODO add more tests: more exceptional situations must be tested.
    
    /**
     * Tests the instantiation of a empty Line object.
     */
    @Test
    public void instantiateEmptyLine() {
        Line line = new Line();
        assertTrue(line.isEmpty());
    }

    /**
     * Tests the instantiation of a Line object with one Cell object.
     */
    @Test
    public void instantiateLineWithOneCell() {
        Cell cell = new Cell("test");
        Line line = new Line(cell); 
        assertFalse(line.isEmpty());
        assertEquals(line.getNumberOfCells(), 1);
    }
    
    /**
     * Tests the instantiation of a Line object with many Cell objects.
     */
    @Test
    public void instantiateLineWithManyCells() {
        Cell cell1 = new Cell("this", new Integer(5));
        Cell cell2 = new Cell("is", null);
        Cell cell3 = new Cell("just", new Double(7.2));
        Cell cell4 = new Cell("a", new Float(8.7));
        Cell cell5 = new Cell("test", new Date());
        Line line = new Line(cell1, cell2, cell3, cell4, cell5); 
        assertFalse(line.isEmpty());
        assertEquals(line.getCell("just").getValue(), new Double(7.2));
    }
    
    /**
     * Tests the instantiation of a Line object with many Cell objects.
     */
    @Test
    public void instantiateLineWithArrayOfCells() {
        Cell cell1 = new Cell("this", new Integer(5));
        Cell cell2 = new Cell("is", null);
        Cell cell3 = new Cell("just", new Double(7.2));
        Cell cell4 = new Cell("a", new Float(8.7));
        Cell cell5 = new Cell("test", new Date());
        Cell[] cellArray = {cell1, cell2, cell3, cell4, cell5};
        Line line = new Line(cellArray); 
        assertFalse(line.isEmpty());
        assertEquals(line.getCell("a").getValue(), new Float(8.7));
    }    
    
    /**
     * Tests the instantiation of a empty Line object.
     */
    @Test
    public void instantiateLineWithCollectionOfCells() {
        Cell cell1 = new Cell("this", new Integer(5));
        Cell cell2 = new Cell("is", null);
        Cell cell3 = new Cell("just", new Double(7.2));
        Cell cell4 = new Cell("a", new Float(8.7));
        Cell cell5 = new Cell("test", new Date());
        List<Cell> cellCollection = new ArrayList<Cell>();
        cellCollection.add(cell1);
        cellCollection.add(cell2);
        cellCollection.add(cell3);
        cellCollection.add(cell4);
        cellCollection.add(cell5);
        Line line = new Line(cellCollection); 
        assertFalse(line.isEmpty());
        assertEquals(line.getCell("a").getValue(), new Float(8.7));
        assertEquals(line.getCell("is").getValue(), null);
        
    }
    // ------------------------------------------------------------------------
    // Instantiations of a Line object using user objects with JsaPar
    // annotations.
    // ------------------------------------------------------------------------

    /**
     * Tests the instantiation of a Line object with a given annotated user object.
     */
    @Test
    public void instantiateLineWithAnnotatedUserClass() {
        LineUserClass1 uc1 = LineDefinitions.getLineDefinitions().new LineUserClass1();
        Line line = new Line(uc1);
        List<Cell> cells = line.getCells();
        assertEquals(cells.size(), 4);
        assertTrue(line.containsCell("age"));
        assertEquals(line.getCell("age").getValue(), 40);
    }

    /**
     * Tests the instantiation of a Line object with a given annotated user object that holds arrays
     * of data types that are annotated with a @Cell annotation.
     */
    @Test
    public void instantiateLineWithAnnotatedUserClassContainingArrays() {
        LineUserClass2 uc2 = LineDefinitions.getLineDefinitions().new LineUserClass2();
        // Line line = new Line(uc2);
        // TODO how to deal with arrays of data types that are annotated?
    }

    /**
     * Tests the instantiation of a Line object with a given annotated user object that doesn't have
     * the @Line annotation.
     */
    @Test(expected = AnnotationException.class)
    public void instantiateLineWithAnnotatedUserObjectWithoutLineAnnotation() {
        LineUserClass3 uc3 = LineDefinitions.getLineDefinitions().new LineUserClass3();
        @SuppressWarnings("unused")
        Line line = new Line(uc3);
        fail("Should have thrown a AnnotationException() exception.");
    }

    /**
     * Tests the instantiation of a Line object with a given annotated user object that doesn't have
     * any @Cell annotations.
     */
    @Test(expected = AnnotationException.class)
    public void instantiateLineWithAnnotatedUserObjectWithoutCellAnnotations() {
        LineUserClass4 uc4 = LineDefinitions.getLineDefinitions().new LineUserClass4();
        @SuppressWarnings("unused")
        Line line = new Line(uc4);
        fail("Should have thrown a AnnotationException() exception.");
    }

    /**
     * Tests the instantiation of a Line object with a user object that doesn't have any
     * annotations.
     */
    @Test(expected = AnnotationException.class)
    public void instantiateLineWithAnnotatedUserObjectWithoutAnyAnnotations() {
        LineUserClass5 uc5 = LineDefinitions.getLineDefinitions().new LineUserClass5();
        @SuppressWarnings("unused")
        Line line = new Line(uc5);
        fail("Should have thrown a AnnotationException() exception.");
    }
}