package exp.jsapar.types2;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for testing the toString representation of Document objects with
 * divergent data types.
 * 
 * @author JsaPar Developer
 */
public class DocumentTestOnStringRepresentations {

	/**
	 * Holds a bunch of test documents to be used within these tests.
	 */
	private List<Document> testDocuments = null;

	/**
	 * Creates a bunch of documents to be used by the defined tests.
	 */
	@Before
	public void setupTestDocuments() {
		testDocuments = DocumentDefinitions.getExampleDocuments();
	}

	@Test
	public void getRepresentationOfAnEmptyDocument() {
		Document expected = testDocuments.get(0);
		Document actual = new Document();

		assertEquals(expected, actual);
	}

	@Test
	public void getRespresentationOfAnDocumentWithEmptySection() {
		Document expected = testDocuments.get(1);
		Document actual = new Document();
		Section s = new Section();
		actual.addSection(s);
		
		assertEquals(expected, actual);
	}
	
	// TODO add more tests
}