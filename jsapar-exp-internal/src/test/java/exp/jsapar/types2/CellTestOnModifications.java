package exp.jsapar.types2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit tests for testing the modifications of Cell objects with divergent data types.
 * 
 * @author JsaPar Developer
 */
public class CellTestOnModifications {

    // ------------------------------------------------------------------------
    // Cell value type: change from no type (null) to String value
    // ------------------------------------------------------------------------

    /**
     * Tests modification of cell with Byte to String value.
     */
    @Test
    public void modificationOfCellNullToString() {
        Cell cellOne = null;
        Cell cellTwo = null;

        cellOne = new Cell("one", null);
        cellTwo = new Cell("two", new String("typed"));

        // change the value type from no type (null) to String
        cellOne.setValue(cellTwo.getValue());

        assertEquals(cellOne.getValue(), cellTwo.getValue());
        assertEquals(cellOne.getValue(), "typed");
    }

    // ------------------------------------------------------------------------
    // Cell value type: change from empty type to String value
    // ------------------------------------------------------------------------

    /**
     * Tests modification of cell that is empty to String value.
     */
    @Test
    public void modificationOfCellEmptyToString() {
        Cell cellOne = null;
        Cell cellTwo = null;

        cellOne = new Cell("one"); // no type!
        cellTwo = new Cell("two", new String("wood"));

        // change the value type from no type (null) to String
        cellOne.setValue(cellTwo.getValue());

        assertEquals(cellOne.getValue(), cellTwo.getValue());
        assertEquals(cellOne.getValue(), "wood");
    }

    // ------------------------------------------------------------------------
    // Cell value type: change from Byte to String value
    // ------------------------------------------------------------------------

    /**
     * Tests modification of cell with Byte to String value.
     */
    @Test
    public void modificationOfCellByteToString() {
        Cell cellOne = null;
        Cell cellTwo = null;

        cellOne = new Cell("one", new Byte("6"));
        cellTwo = new Cell("two", new String("0"));

        // change the value type from Byte to String
        cellOne.setValue(cellTwo.getValue());

        assertEquals(cellOne.getValue(), cellTwo.getValue());
        assertEquals(cellOne.getValue(), "0");
    }

    // TODO add more tests
}