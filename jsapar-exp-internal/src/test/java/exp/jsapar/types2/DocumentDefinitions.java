package exp.jsapar.types2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * A collection of predefined Document objects. These Documents are used for the
 * Document unit tests. Because of the tremendous amount of code, these
 * Documents are defined here and not in the unit tests itself to prevent
 * unreadable unit tests.<br>
 * <br>
 * These definitions are package-private and for testing purposes only!
 * 
 * @author JsaPar Developer
 */
class DocumentDefinitions {

	/**
	 * A (package-private) enum definition used for testing purposes.
	 */
	enum Gender {
		MALE, FEMALE
	};

	public static List<Document> getExampleDocuments() {
		List<Document> testDocuments = new ArrayList<Document>();

		// TODO add more Documents for testing purposes.

		Document doc = null;
		Section section = null;
		Line line = null;
		Cell cell = null;

		// add a document.
		doc = new Document();
		testDocuments.add(doc);

		// add a document with one section.
		doc = new Document();
		section = new Section();
		doc.addSection(section);
		testDocuments.add(doc);

		// add a document with one section and one line.
		doc = new Document();
		section = new Section();
		line = new Line();
		section.addLine(line);
		doc.addSection(section);
		testDocuments.add(doc);

		// add a document with one section, one line and one cell.
		doc = new Document();
		section = new Section();
		line = new Line();
		cell = new Cell("movie", "A View To A Kill");
		line.addCell(cell);
		section.addLine(line);
		doc.addSection(section);
		testDocuments.add(doc);

		// add a document with one section, one line and a few cells with
		// different types.
		doc = new Document();
		section = new Section();
		line = new Line();
		cell = new Cell("name", "robert");
		line.addCell(cell);
		cell = new Cell("age", new Integer(18));
		line.addCell(cell);
		cell = new Cell("moneyinthebank", new BigDecimal(12345678987.65));
		line.addCell(cell);
		cell = new Cell("gender", Gender.MALE);
		line.addCell(cell);
		section.addLine(line);
		doc.addSection(section);
		testDocuments.add(doc);

		// add a document with one section, a few lines with different lengths
		// of cells, and cells having different types.
		doc = new Document();
		section = new Section();
		line = new Line();
		cell = new Cell("name", "peter");
		line.addCell(cell);
		cell = new Cell("birthdayinmilliseconds", new Long(1000000000));
		// Mon Jan 12 08:46:40 EST 1970 = 1000000000 milliseconds.
		line.addCell(cell);
		cell = new Cell("favoritecolor", "red");
		line.addCell(cell);
		cell = new Cell("girlfriend", "monique");
		line.addCell(cell);
		cell = new Cell("hasgirlfriend", new Boolean(true));
		line.addCell(cell);
		section.addLine(line);
		line = new Line();
		cell = new Cell("name", "natascha");
		line.addCell(cell);
		cell = new Cell("birthdayinmilliseconds", new Long(2000000000));
		// Fri Jan 23 22:33:20 EST 1970 = 2000000000 milliseconds.
		line.addCell(cell);
		cell = new Cell("favoritesport", "baseball");
		line.addCell(cell);
		section.addLine(line);
		doc.addSection(section);
		testDocuments.add(doc);

		return testDocuments;
	}
}