package exp.jsapar.types2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Unit tests for testing the instantiation of Cell objects with divergent
 * primitive data types.
 * 
 * @author JsaPar Developer
 */
public class CellTestOnAutoboxingInstantiations {

	// ------------------------------------------------------------------------
	// Cell value type: primitive byte
	// ------------------------------------------------------------------------

	/**
	 * Tests the instantiation of a cell with a primitive byte cell value and
	 * cell name.
	 */
	@Test
	public void instantiateValidByteCell() {
		String cellName = "test";
		byte cellValue = 1;

		Cell cell = new Cell(cellName, cellValue);

		assertEquals(cellName, cell.getName());
		assertEquals(cellValue, cell.getValue());
	}

	/**
	 * Tests the instantiation of a cell with a primitive byte cell value and
	 * cell name that is empty.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void instantiateInvalidByteCellWithNameEmpty() {
		String cellName = ""; // must be empty.
		byte cellValue = 8;

		Cell cell = new Cell(cellName, cellValue);

		fail("Should have thrown an IllegalArgumentException!");
		assertEquals(cellName, cell.getName());
		assertEquals(cellValue, cell.getValue());
	}

	/**
	 * Tests the instantiation of a cell with a primitive byte cell value and
	 * cell name that is null.
	 */
	@Test(expected = NullPointerException.class)
	public void instantiateInvalidByteCellWithNameNull() {
		String cellName = null; // must be null.
		byte cellValue = 6;

		Cell cell = new Cell(cellName, cellValue);

		assertEquals(cellName, cell.getName());
		assertEquals(cellValue, cell.getValue());
	}

	// ------------------------------------------------------------------------
	// Cell value type: primitive boolean
	// ------------------------------------------------------------------------

	// TODO
}
