package exp.jsapar.types2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;

/**
 * Unit tests for testing the equality of Line objects with divergent data
 * types.
 * 
 * @author JsaPar Developer
 */
public class LineTestOnEquality {

	// ------------------------------------------------------------------------

	/**
	 * Test equality of lines with divergent cell values. Unequal lines.
	 */
	@Test
	public void equalityOfSameLengthLinesUnequal() {
		Line expectedLine = new Line();
		Line actualLine = new Line();

		Cell cell1 = null;
		Cell cell2 = null;
		Cell cell3 = null;
		Cell cell4 = null;

		cell1 = new Cell("firstname", "felipe");
		cell2 = new Cell("lastname", "rodriques");
		cell3 = new Cell("age", 22);
		cell4 = new Cell("shoesize", 11);
		expectedLine.addCells(cell1);
		expectedLine.addCells(cell2);
		expectedLine.addCells(cell3);
		expectedLine.addCells(cell4);

		Cell cell5 = null;
		Cell cell6 = null;
		Cell cell7 = null;
		Cell cell8 = null;

		cell5 = new Cell("firstname", "danielle");
		cell6 = new Cell("lastname", "rodriques");
		cell7 = new Cell("age", 25);
		cell8 = new Cell("shoesize", 9);
		actualLine.addCells(cell5);
		actualLine.addCells(cell6);
		actualLine.addCells(cell7);
		actualLine.addCells(cell8);

		assertNotSame(expectedLine, actualLine);
	}

	/**
	 * Test equality of lines with divergent cell values. Equal lines.
	 */
	@Test
	public void equalityOfSameLengthLinesEqual() {
		Line expectedLine = new Line();
		Line actualLine = new Line();

		Cell cell1 = null;
		Cell cell2 = null;
		Cell cell3 = null;
		Cell cell4 = null;

		cell1 = new Cell("firstname", "dusty");
		cell2 = new Cell("lastname", "springfield");
		cell3 = new Cell("age", 60);
		cell4 = new Cell("shoesize", 9);
		expectedLine.addCells(cell1);
		expectedLine.addCells(cell2);
		expectedLine.addCells(cell3);
		expectedLine.addCells(cell4);

		Cell cell5 = null;
		Cell cell6 = null;
		Cell cell7 = null;
		Cell cell8 = null;

		cell5 = new Cell("firstname", "dusty");
		cell6 = new Cell("lastname", "springfield");
		cell7 = new Cell("age", 60);
		cell8 = new Cell("shoesize", 9);
		actualLine.addCells(cell5);
		actualLine.addCells(cell6);
		actualLine.addCells(cell7);
		actualLine.addCells(cell8);

		assertEquals(expectedLine, actualLine);
	}

	// ------------------------------------------------------------------------

	private enum CarColors {
		BLUE, RED, WHITE
	};

	/**
	 * Test equality of lines with divergent cell values. Different cell list
	 * length.
	 */
	@Test
	public void equalityOfLinesWithDifferentCellListLengths() {
		Line expectedLine = new Line();
		Line actualLine = new Line();

		Cell cell1 = null;
		Cell cell2 = null;

		cell1 = new Cell("carbrand", "ferrari");
		cell2 = new Cell("cartype", "testarossa");
		expectedLine.addCells(cell1);
		expectedLine.addCells(cell2);

		Cell cell5 = null;
		Cell cell6 = null;
		Cell cell7 = null;

		cell5 = new Cell("carbrand", "ferrari");
		cell6 = new Cell("cartype", "testarossa");
		cell7 = new Cell("carcolor", CarColors.RED);
		actualLine.addCells(cell5);
		actualLine.addCells(cell6);
		actualLine.addCells(cell7);

		assertNotSame(expectedLine, actualLine);
	}

	// ------------------------------------------------------------------------

	// TODO implement more tests

}