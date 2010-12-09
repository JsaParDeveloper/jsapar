package exp.jsapar.types2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

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

	/**
	 * Tests the instantiation of a Line object with a given annotated user
	 * class object.
	 */
	@Test
	public void instantiateLineWithAnnotatedUserClassObject() {
		LineDefinitions.LineUserClass1 userClassObj = LineDefinitions.getLineUserClass1();
		Line line = new Line(userClassObj);
		List<Cell> cells = line.getCells();
		assertEquals(cells.size(), 4);
		assertTrue(line.containsCell("age"));
		assertEquals(line.getCell("age").getValue(), 40);
	}

}
