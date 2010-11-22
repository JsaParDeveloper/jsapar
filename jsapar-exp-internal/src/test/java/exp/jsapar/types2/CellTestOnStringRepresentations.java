package exp.jsapar.types2;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;

/**
 * Unit tests for testing the toString representation of Cell objects with
 * divergent data types.
 * 
 * @author JsaPar Developer
 */
public class CellTestOnStringRepresentations {

	// ------------------------------------------------------------------------
	// Cell value type: java.lang.Byte
	// ------------------------------------------------------------------------

	/**
	 * Test textual representation of a cell with Byte value.
	 */
	@Test
	public void toStringRepresentationOfByteCell() {
		String cellName = "byte";
		String expectedResult = "[name: byte, value: 16]";
		String actualResult = null;
		Byte cellValue = new Byte("16");

		Cell cell = new Cell(cellName, cellValue);

		actualResult = cell.toString();
		assertEquals(expectedResult, actualResult);
	}

	// ------------------------------------------------------------------------
	// Cell value type: java.lang.Boolean
	// ------------------------------------------------------------------------

	/**
	 * Test textual representation of a cell with Boolean value.
	 */
	@Test
	public void toStringRepresentationOfBooleanCell() {
		String cellName = "byte";
		String expectedResult1 = "[name: byte, value: true]";
		String expectedResult2 = "[name: byte, value: false]";
		String actualResult1 = null;
		String actualResult2 = null;
		Boolean cellValue = new Boolean(true);

		Cell cell = new Cell(cellName, cellValue);

		actualResult1 = cell.toString();
		cell.setValue(false);
		actualResult2 = cell.toString();
		assertEquals(expectedResult1, actualResult1);
		assertEquals(expectedResult2, actualResult2);
	}

	// ------------------------------------------------------------------------
	// Cell value type: java.lang.Character
	// ------------------------------------------------------------------------

	/**
	 * Test textual representation of a cell with Character value.
	 */
	@Test
	public void toStringRepresentationOfCharacterCell() {
		String cellName = "character";
		String expectedResult = "[name: character, value: G]";
		String actualResult = null;
		Character cellValue = new Character('G');

		Cell cell = new Cell(cellName, cellValue);

		actualResult = cell.toString();
		assertEquals(expectedResult, actualResult);
	}

	// ------------------------------------------------------------------------
	// Cell value type: java.lang.Double
	// ------------------------------------------------------------------------

	/**
	 * Test textual representation of a cell with Double value.
	 */
	@Test
	public void toStringRepresentationOfDoubleCell() {
		String cellName = "double";
		String expectedResult = "[name: double, value: 23.67]";
		String actualResult = null;
		Double cellValue = new Double("23.67");

		Cell cell = new Cell(cellName, cellValue);

		actualResult = cell.toString();
		assertEquals(expectedResult, actualResult);
	}

	// ------------------------------------------------------------------------
	// Cell value type: java.lang.Enum
	// ------------------------------------------------------------------------

	private enum Seasons {
		Spring, Summer, Autumn, Winter;
	};

	/**
	 * Test textual representation of a cell with Enum value.
	 */
	@Test
	public void toStringRepresentationOfEnumCell() {
		String cellName = "enum";
		String expectedResult = "[name: enum, value: Summer]";
		String actualResult = null;
		Enum<Seasons> cellValue = Seasons.Summer;

		Cell cell = new Cell(cellName, cellValue);

		actualResult = cell.toString();
		assertEquals(expectedResult, actualResult);
	}

	// ------------------------------------------------------------------------
	// Cell value type: java.lang.Float
	// ------------------------------------------------------------------------

	/**
	 * Test textual representation of a cell with Float value.
	 */
	@Test
	public void toStringRepresentationOfFloatCell() {
		String cellName = "float";
		String expectedResult = "[name: float, value: 16.1]";
		String actualResult = null;
		Float cellValue = new Float(16.1);

		Cell cell = new Cell(cellName, cellValue);

		actualResult = cell.toString();
		assertEquals(expectedResult, actualResult);
	}

	// ------------------------------------------------------------------------
	// Cell value type: java.lang.Integer
	// ------------------------------------------------------------------------

	/**
	 * Test textual representation of a cell with Integer value.
	 */
	@Test
	public void toStringRepresentationOfIntegerCell() {
		String cellName = "integer";
		String expectedResult = "[name: integer, value: -7]";
		String actualResult = null;
		Integer cellValue = new Integer(-7);

		Cell cell = new Cell(cellName, cellValue);

		actualResult = cell.toString();
		assertEquals(expectedResult, actualResult);
	}

	// ------------------------------------------------------------------------
	// Cell value type: java.lang.Long
	// ------------------------------------------------------------------------

	/**
	 * Test textual representation of a cell with Long value.
	 */
	@Test
	public void toStringRepresentationOfLongCell() {
		String cellName = "long";
		String expectedResult = "[name: long, value: 35625211]";
		String actualResult = null;
		Long cellValue = new Long(35625211L);

		Cell cell = new Cell(cellName, cellValue);

		actualResult = cell.toString();
		assertEquals(expectedResult, actualResult);
	}

	// ------------------------------------------------------------------------
	// Cell value type: java.lang.Short
	// ------------------------------------------------------------------------

	/**
	 * Test textual representation of a cell with Short value.
	 */
	@Test
	public void toStringRepresentationOfShortCell() {
		String cellName = "short";
		String expectedResult = "[name: short, value: 45]";
		String actualResult = null;
		Short cellValue = new Short((short) 45);

		Cell cell = new Cell(cellName, cellValue);

		actualResult = cell.toString();
		assertEquals(expectedResult, actualResult);
	}

	// ------------------------------------------------------------------------
	// Cell value type: java.lang.String
	// ------------------------------------------------------------------------

	/**
	 * Test textual representation of a cell with String value.
	 */
	@Test
	public void toStringRepresentationOfStringCell() {
		String cellName = "string";
		String expectedResult = "[name: string, value: don't worry, be happy!]";
		String actualResult = null;
		String cellValue = new String("don't worry, be happy!");

		Cell cell = new Cell(cellName, cellValue);

		actualResult = cell.toString();
		assertEquals(expectedResult, actualResult);
	}

	// ------------------------------------------------------------------------
	// Cell value type: java.math.BigDecimal
	// ------------------------------------------------------------------------

	/**
	 * Test textual representation of a cell with BigDecimal value.
	 */
	@Test
	public void toStringRepresentationOfBigDecimalCell() {
		String cellName = "bigdecimal";
		String expectedResult = "[name: bigdecimal, value: 982367]";
		String actualResult = null;
		BigDecimal cellValue = new BigDecimal(982367);

		Cell cell = new Cell(cellName, cellValue);

		actualResult = cell.toString();
		assertEquals(expectedResult, actualResult);
	}

	// ------------------------------------------------------------------------
	// Cell value type: java.math.BigInteger
	// ------------------------------------------------------------------------

	/**
	 * Test textual representation of a cell with BigInteger value.
	 */
	@Test
	public void toStringRepresentationOfBigIntegerCell() {
		String cellName = "biginteger";
		String expectedResult = "[name: biginteger, value: 455000]";
		String actualResult = null;
		BigInteger cellValue = new BigInteger("455000");

		Cell cell = new Cell(cellName, cellValue);

		actualResult = cell.toString();
		assertEquals(expectedResult, actualResult);
	}

	// ------------------------------------------------------------------------
}