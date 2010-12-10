package exp.jsapar.types2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit tests for testing the instantiation of Document objects with divergent
 * data types.
 * 
 * @author JsaPar Developer
 */
public class DocumentTestOnInstantiations {

	// ------------------------------------------------------------------------

	/**
	 * Tests the instantiation of a String Document object.
	 */
	@Test
	public void instantiateStringDocument() {
		// define some values
		String firstName1 = "Mary";
		String lastName1 = "Lou";
		String firstName2 = "Barry";
		String lastName2 = "White";

		String firstName = "First name";
		String lastName = "Last name";

		// fill a Document object
		Cell cell1 = null;
		Cell cell2 = null;
		Line line1 = null;
		Cell cell3 = null;
		Cell cell4 = null;
		Line line2 = null;

		cell1 = new Cell(firstName, firstName1);
		cell2 = new Cell(lastName, lastName1);
		line1 = new Line();
		line1.addCells(cell1);
		line1.insertCell(cell2, 0); // inserted before cel1!
		cell3 = new Cell(firstName, firstName2);
		cell4 = new Cell(lastName, lastName2);
		line2 = new Line();
		line2.addCells(cell3);
		line2.insertCell(cell4, 0); // inserted before cel3!

		Section sec = new Section();
		sec.addLine(line1);
		sec.insertLine(line2, 0); // inserted before lin1!

		Document doc = new Document();
		doc.addSection(sec);

		// test the Document object
		assertEquals(doc.getSection(0).getLine(0).getCell(0).getName(),
				lastName);
		assertEquals(doc.getSection(0).getLine(0).getCell(0).getValue(),
				lastName2);
		assertEquals(doc.getSection(0).getLine(0).getCell(1).getName(),
				firstName);
		assertEquals(doc.getSection(0).getLine(0).getCell(1).getValue(),
				firstName2);

		assertEquals(doc.getSection(0).getLine(1).getCell(0).getName(),
				lastName);
		assertEquals(doc.getSection(0).getLine(1).getCell(0).getValue(),
				lastName1);
		assertEquals(doc.getSection(0).getLine(1).getCell(1).getName(),
				firstName);
		assertEquals(doc.getSection(0).getLine(1).getCell(1).getValue(),
				firstName1);
	}

	// ------------------------------------------------------------------------

	// TODO implement more tests
}