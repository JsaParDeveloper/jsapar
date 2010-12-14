package exp.jsapar.types2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit tests for testing the instantiation of Document objects with divergent data types.
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
        Cell cell1 = new Cell(firstName, firstName1);
        Cell cell2 = new Cell(lastName, lastName1);
        Cell cell3 = new Cell(firstName, firstName2);
        Cell cell4 = new Cell(lastName, lastName2);


        Line line1 = new Line(cell1);
        line1.insertCell(cell2); // inserted before cel1!
        Line line2 = new Line(cell3);
        line2.insertCell(cell4); // inserted before cel3!

        Section sec = new Section(line1);
        sec.insertLine(line2); // inserted before lin1!

        Document doc = new Document(sec);

        Section actualSection = doc.getSection(0);
        Line actualLine1 = actualSection.getLine(0);
        Line actualLine2 = actualSection.getLine(1);
        
        // test the Document object
        assertEquals(actualLine1.getCell(0).getName(), lastName);
        assertEquals(actualLine1.getCell(0).getValue(), lastName2);
        assertEquals(actualLine1.getCell(1).getName(), firstName);
        assertEquals(actualLine1.getCell(1).getValue(), firstName2);

        assertEquals(actualLine2.getCell(0).getName(), lastName);
        assertEquals(actualLine2.getCell(0).getValue(), lastName1);
        assertEquals(actualLine2.getCell(1).getName(), firstName);
        assertEquals(actualLine2.getCell(1).getValue(), firstName1);
    }

    // ------------------------------------------------------------------------

    // TODO implement more tests
}