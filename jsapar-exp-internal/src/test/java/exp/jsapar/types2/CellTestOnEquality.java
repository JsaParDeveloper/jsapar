package exp.jsapar.types2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;

/**
 * Unit tests for testing the equality of Cell objects with divergent data
 * types.
 * 
 * @author JsaPar Developer
 */
public class CellTestOnEquality {

	// ------------------------------------------------------------------------
	// Cell value type: java.lang.Byte
	// ------------------------------------------------------------------------

	/**
	 * Tests equality of cells with Byte values. Unequal cells.
	 */
	@Test
	public void equalityOfByteCellsUnequal() {
		Cell cellOne = null;
		Cell cellTwo = null;

		cellOne = new Cell("one", new Byte("6"));
		cellTwo = new Cell("two", new Byte("0"));

		assertNotSame(cellOne, cellTwo);
	}

	/**
	 * Tests equality of cells with Byte values. Equal cells.
	 */
	@Test
	public void equalityOfByteCellsEqual() {
		Cell cellOne = null;
		Cell cellTwo = null;

		cellOne = new Cell("same", new Byte("4"));
		cellTwo = new Cell("same", new Byte("4"));

		assertEquals(cellOne, cellTwo);
	}

	// ------------------------------------------------------------------------
	// Cell value type: java.lang.Boolean
	// ------------------------------------------------------------------------

	/**
	 * Tests equality of cells with Boolean values. Unequal cells.
	 */
	@Test
	public void equalityOfBooleanCellsUnequal() {
		Cell cellOne = null;
		Cell cellTwo = null;

		cellOne = new Cell("one", new Boolean(true));
		cellTwo = new Cell("two", new Boolean(false));

		assertNotSame(cellOne, cellTwo);
	}

	/**
	 * Tests equality of cells with Boolean values. Equal cells.
	 */
	@Test
	public void equalityOfBooleanCellsEqual() {
		Cell cellOne = null;
		Cell cellTwo = null;

		cellOne = new Cell("same", new Boolean(false));
		cellTwo = new Cell("same", new Boolean(false));

		assertEquals(cellOne, cellTwo);
	}

	// ------------------------------------------------------------------------
	// Cell value type: java.lang.Character
	// ------------------------------------------------------------------------

	/**
	 * Tests equality of cells with Character values. Unequal cells.
	 */
	@Test
	public void equalityOfCharacterCellsUnequal() {
		Cell cellOne = null;
		Cell cellTwo = null;

		cellOne = new Cell("one", new Character('a'));
		cellTwo = new Cell("two", new Character('b'));

		assertNotSame(cellOne, cellTwo);
	}

	/**
	 * Tests equality of cells with Character values. Equal cells.
	 */
	@Test
	public void equalityOfCharacterCellsEqual() {
		Cell cellOne = null;
		Cell cellTwo = null;

		cellOne = new Cell("same", new Character('p'));
		cellTwo = new Cell("same", new Character('p'));

		assertEquals(cellOne, cellTwo);
	}

	// ------------------------------------------------------------------------
	// Cell value type: java.lang.Double
	// ------------------------------------------------------------------------

	/**
	 * Tests equality of cells with Double values. Unequal cells.
	 */
	@Test
	public void equalityOfDoubleCellsUnequal() {
		Cell cellOne = null;
		Cell cellTwo = null;

		cellOne = new Cell("one", new Double(12.50));
		cellTwo = new Cell("two", new Double(50.75));

		assertNotSame(cellOne, cellTwo);
	}

	/**
	 * Tests equality of cells with Double values. Equal cells.
	 */
	@Test
	public void equalityOfDoubleCellsEqual() {
		Cell cellOne = null;
		Cell cellTwo = null;

		cellOne = new Cell("same", new Double(15.90));
		cellTwo = new Cell("same", new Double(15.90));

		assertEquals(cellOne, cellTwo);
	}

	// ------------------------------------------------------------------------
	// Cell value type: java.lang.Enum
	// ------------------------------------------------------------------------

	/**
	 * Enum for testing purposes.
	 */
	private enum MultipleChoice {
		A, B, C, D
	};

	/**
	 * Tests equality of cells with Enum values. Unequal cells.
	 */
	@Test
	public void equalityOfEnumCellsUnequal() {
		Cell cellOne = null;
		Cell cellTwo = null;

		cellOne = new Cell("one", MultipleChoice.A);
		cellTwo = new Cell("two", MultipleChoice.B);

		assertNotSame(cellOne, cellTwo);
	}

	/**
	 * Tests equality of cells with Enum values. Equal cells.
	 */
	@Test
	public void equalityOfEnumCellsEqual() {
		Cell cellOne = null;
		Cell cellTwo = null;

		cellOne = new Cell("same", MultipleChoice.C);
		cellTwo = new Cell("same", MultipleChoice.C);

		assertEquals(cellOne, cellTwo);
	}

	// ------------------------------------------------------------------------
	// Cell value type: java.lang.Float
	// ------------------------------------------------------------------------

	/**
	 * Tests equality of cells with Float values. Unequal cells.
	 */
	@Test
	public void equalityOfFloatCellsUnequal() {
		Cell cellOne = null;
		Cell cellTwo = null;

		cellOne = new Cell("one", new Float(9.5));
		cellTwo = new Cell("two", new Float(3.9));

		assertNotSame(cellOne, cellTwo);
	}

	/**
	 * Tests equality of cells with Float values. Equal cells.
	 */
	@Test
	public void equalityOfFloatCellsEqual() {
		Cell cellOne = null;
		Cell cellTwo = null;

		cellOne = new Cell("same", new Float(6.28));
		cellTwo = new Cell("same", new Float(6.28));

		assertEquals(cellOne, cellTwo);
	}

	// ------------------------------------------------------------------------
	// Cell value type: java.lang.Integer
	// ------------------------------------------------------------------------

	/**
	 * Tests equality of cells with Integer values. Unequal cells.
	 */
	@Test
	public void equalityOfIntegerCellsUnequal() {
		Cell cellOne = null;
		Cell cellTwo = null;

		cellOne = new Cell("one", new Integer(1));
		cellTwo = new Cell("two", new Integer(5));

		assertNotSame(cellOne, cellTwo);
	}

	/**
	 * Tests equality of cells with Integer values. Equal cells.
	 */
	@Test
	public void equalityOfIntegerCellsEqual() {
		Cell cellOne = null;
		Cell cellTwo = null;

		cellOne = new Cell("same", new Integer(987));
		cellTwo = new Cell("same", new Integer(987));

		assertEquals(cellOne, cellTwo);
	}

	// ------------------------------------------------------------------------
	// Cell value type: java.lang.Long
	// ------------------------------------------------------------------------

	/**
	 * Tests equality of cells with Long values. Unequal cells.
	 */
	@Test
	public void equalityOfLongCellsUnequal() {
		Cell cellOne = null;
		Cell cellTwo = null;

		cellOne = new Cell("one", new Long(987654321));
		cellTwo = new Cell("two", new Long(123456789));

		assertNotSame(cellOne, cellTwo);
	}

	/**
	 * Tests equality of cells with Long values. Equal cells.
	 */
	@Test
	public void equalityOfLongCellsEqual() {
		Cell cellOne = null;
		Cell cellTwo = null;

		cellOne = new Cell("same", new Long(123456789));
		cellTwo = new Cell("same", new Long(123456789));

		assertEquals(cellOne, cellTwo);
	}

	// ------------------------------------------------------------------------
	// Cell value type: java.lang.Short
	// ------------------------------------------------------------------------

	/**
	 * Tests equality of cells with Short values. Unequal cells.
	 */
	@Test
	public void equalityOfShortCellsUnequal() {
		Cell cellOne = null;
		Cell cellTwo = null;

		cellOne = new Cell("one", new Short((short) 321));
		cellTwo = new Cell("two", new Short((short) 123));

		assertNotSame(cellOne, cellTwo);
	}

	/**
	 * Tests equality of cells with Short values. Equal cells.
	 */
	@Test
	public void equalityOfShortCellsEqual() {
		Cell cellOne = null;
		Cell cellTwo = null;

		cellOne = new Cell("same", new Short((short) 123));
		cellTwo = new Cell("same", new Short((short) 123));

		assertEquals(cellOne, cellTwo);
	}

	// ------------------------------------------------------------------------
	// Cell value type: java.lang.String
	// ------------------------------------------------------------------------

	/**
	 * Tests equality of cells with String values. Unequal cells.
	 */
	@Test
	public void equalityOfStringCellsUnequal() {
		Cell cellOne = null;
		Cell cellTwo = null;

		cellOne = new Cell("one", "Hello");
		cellTwo = new Cell("two", "World");

		assertNotSame(cellOne, cellTwo);
	}

	/**
	 * Tests equality of cells with String values. Equal cells.
	 */
	@Test
	public void equalityOfStringCellsEqual() {
		Cell cellOne = null;
		Cell cellTwo = null;

		cellOne = new Cell("same", "G'day, mate!");
		cellTwo = new Cell("same", "G'day, mate!");

		assertEquals(cellOne, cellTwo);
	}

	// ------------------------------------------------------------------------
	// Cell value type: java.math.BigDecimal
	// ------------------------------------------------------------------------

	/**
	 * Tests equality of cells with BigDecimal values. Unequal cells.
	 */
	@Test
	public void equalityOfBigDecimalCellsUnequal() {
		Cell cellOne = null;
		Cell cellTwo = null;

		cellOne = new Cell("one", new BigDecimal(9900000));
		cellTwo = new Cell("two", new BigDecimal(9450000));

		assertNotSame(cellOne, cellTwo);
	}

	/**
	 * Tests equality of cells with BigDecimal values. Equal cells.
	 */
	@Test
	public void equalityOfBigDecimalCellsEqual() {
		Cell cellOne = null;
		Cell cellTwo = null;

		cellOne = new Cell("same", new BigDecimal(876100));
		cellTwo = new Cell("same", new BigDecimal(876100));

		assertEquals(cellOne, cellTwo);
	}

	// ------------------------------------------------------------------------
	// Cell value type: java.math.BigInteger
	// ------------------------------------------------------------------------

	/**
	 * Tests equality of cells with BigInteger values. Unequal cells.
	 */
	@Test
	public void equalityOfBigIntegerCellsUnequal() {
		Cell cellOne = null;
		Cell cellTwo = null;

		cellOne = new Cell("one", new BigInteger("17830"));
		cellTwo = new Cell("two", new BigInteger("250"));

		assertNotSame(cellOne, cellTwo);
	}

	/**
	 * Tests equality of cells with BigInteger values. Equal cells.
	 */
	@Test
	public void equalityOfBigIntegerCellsEqual() {
		Cell cellOne = null;
		Cell cellTwo = null;

		cellOne = new Cell("same", new BigInteger("70545"));
		cellTwo = new Cell("same", new BigInteger("70545"));

		assertEquals(cellOne, cellTwo);
	}

	// ------------------------------------------------------------------------
}