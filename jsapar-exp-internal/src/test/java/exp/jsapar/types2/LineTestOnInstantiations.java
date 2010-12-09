package exp.jsapar.types2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import exp.jsapar.exception.AnnotationException;
import exp.jsapar.types2.LineDefinitions.LineUserClass1;
import exp.jsapar.types2.LineDefinitions.LineUserClass2;
import exp.jsapar.types2.LineDefinitions.LineUserClass3;
import exp.jsapar.types2.LineDefinitions.LineUserClass4;
import exp.jsapar.types2.LineDefinitions.LineUserClass5;

/**
 * Unit tests for testing the instantiation of Line objects with divergent data
 * types.
 * 
 * @author JsaPar Developer
 */
public class LineTestOnInstantiations {

	/**
	 * Tests the instantiation of a empty Line object.
	 */
	@Test
	public void instantiateEmptyLine() {
		// TODO
	}

	/**
	 * Tests the instantiation of a Line object with empty Cell object.
	 */
	@Test
	public void instantiateLineWithEmptyCell() {
		// TODO
	}

	// ------------------------------------------------------------------------
	// Instantiations of a Line object using user objects with JsaPar
	// annotations.
	// ------------------------------------------------------------------------

	/**
	 * Tests the instantiation of a Line object with a given annotated user
	 * object.
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
	 * Tests the instantiation of a Line object with a given annotated user
	 * object that holds arrays of data types that are annotated with a @Cell
	 * annotation.
	 */
	@Test
	public void instantiateLineWithAnnotatedUserClassContainingArrays() {
		LineUserClass2 uc2 = LineDefinitions.getLineDefinitions().new LineUserClass2();
		// Line line = new Line(uc2);
		// TODO how to deal with arrays of data types that are annotated?
	}

	/**
	 * Tests the instantiation of a Line object with a given annotated user
	 * object that doesn't have the @Line annotation.
	 */
	@Test(expected = AnnotationException.class)
	public void instantiateLineWithAnnotatedUserObjectWithoutLineAnnotation() {
		LineUserClass3 uc3 = LineDefinitions.getLineDefinitions().new LineUserClass3();
		@SuppressWarnings("unused")
		Line line = new Line(uc3);
		fail("Should have thrown a AnnotationException() exception.");
	}

	/**
	 * Tests the instantiation of a Line object with a given annotated user
	 * object that doesn't have any @Cell annotations.
	 */
	@Test(expected = AnnotationException.class)
	public void instantiateLineWithAnnotatedUserObjectWithoutCellAnnotations() {
		LineUserClass4 uc4 = LineDefinitions.getLineDefinitions().new LineUserClass4();
		@SuppressWarnings("unused")
		Line line = new Line(uc4);
		fail("Should have thrown a AnnotationException() exception.");
	}

	/**
	 * Tests the instantiation of a Line object with a user object that doesn't
	 * have any annotations.
	 */
	@Test(expected = AnnotationException.class)
	public void instantiateLineWithAnnotatedUserObjectWithoutAnyAnnotations() {
		LineUserClass5 uc5 = LineDefinitions.getLineDefinitions().new LineUserClass5();
		@SuppressWarnings("unused")
		Line line = new Line(uc5);
		fail("Should have thrown a AnnotationException() exception.");
	}
}