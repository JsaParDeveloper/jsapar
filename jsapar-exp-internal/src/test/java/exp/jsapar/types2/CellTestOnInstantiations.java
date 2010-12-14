package exp.jsapar.types2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Unit tests for testing the instantiation of Cell objects with divergent data types.
 * 
 * @author JsaPar Developer
 */
public class CellTestOnInstantiations {

    // ------------------------------------------------------------------------
    // Cell value type: NULL
    // ------------------------------------------------------------------------

    /**
     * Tests the instantiation of a cell with a NULL cell type (and value) and cell name.
     */
    @Test
    public void instantiateValidNullCell() {
        String cellName = "test";

        Cell cell = new Cell(cellName, null);

        assertEquals(cellName, cell.getName());
        assertEquals(null, cell.getValue());
    }

    /**
     * Tests the instantiation of a cell with a NULL cell type (and value) and cell name that is
     * empty.
     */
    @Test(expected = IllegalArgumentException.class)
    public void instantiateInvalidNullCellWithNameEmpty() {
        String cellName = ""; // must be empty.

        Cell cell = new Cell(cellName, null);

        fail("Test should have thrown an IllegalArgumentException.");
        assertEquals(cellName, cell.getName());
        assertEquals(null, cell.getValue());
    }

    /**
     * Tests the instantiation of a cell with a NULL cell type (and value) and cell name that is
     * null.
     */
    @Test(expected = NullPointerException.class)
    public void instantiateInvalidNullCellWithNameNull() {
        String cellName = null; // must be null.

        Cell cell = new Cell(cellName, null);

        fail("Test should have thrown a NullPointerException.");
        assertEquals(cellName, cell.getName());
        assertEquals(null, cell.getValue());
    }

    // ------------------------------------------------------------------------
    // Cell value type: <not specified>
    // ------------------------------------------------------------------------

    /**
     * Tests the instantiation of a cell with a not specified cell type and cell name.
     */
    @Test
    public void instantiateValidCellNoSpecifiedCellType() {
        String cellName = "test";

        Cell cell = new Cell(cellName);

        assertEquals(cellName, cell.getName());
        assertEquals(null, cell.getValue());
    }

    /**
     * Tests the instantiation of a cell with a not specified cell type and cell name that is empty.
     */
    @Test(expected = IllegalArgumentException.class)
    public void instantiateValidCellNoSpecifiedCellTypeNameEmpty() {
        String cellName = ""; // must be empty.

        Cell cell = new Cell(cellName);

        fail("Test should have thrown an IllegalArgumentException.");
        assertEquals(cellName, cell.getName());
        assertEquals(null, cell.getValue());
    }

    /**
     * Tests the instantiation of a cell with a not specified cell type and cell name that is null.
     */
    @Test(expected = NullPointerException.class)
    public void instantiateValidCellNoSpecifiedCellTypeNameNull() {
        String cellName = null; // must be null.

        Cell cell = new Cell(cellName);

        fail("Test should have thrown a NullPointerException.");
        assertEquals(cellName, cell.getName());
        assertEquals(null, cell.getValue());
    }

    // ------------------------------------------------------------------------
    // Cell value type: java.lang.Byte
    // ------------------------------------------------------------------------

    /**
     * Tests the instantiation of a cell with a Byte cell value and cell name.
     */
    @Test
    public void instantiateValidByteCell() {
        String cellName = "test";
        Byte cellValue = new Byte("1");

        Cell cell = new Cell(cellName, cellValue);

        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    /**
     * Tests the instantiation of a cell with a Byte cell value which is null.
     */
    @Test
    public void instantiateValidByteCellWithNullValue() {
        String cellName = "test";
        Byte cellValue = null; // must be null.

        Cell cell = new Cell(cellName, cellValue);

        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    /**
     * Tests the instantiation of a cell with a Byte cell value and cell name that is empty.
     */
    @Test(expected = IllegalArgumentException.class)
    public void instantiateInvalidByteCellWithNameEmpty() {
        String cellName = ""; // must be empty.
        Byte cellValue = new Byte("D");

        Cell cell = new Cell(cellName, cellValue);

        fail("Test should have thrown an IllegalArgumentException.");
        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    /**
     * Tests the instantiation of a cell with a Byte cell value and cell name that is null.
     */
    @Test(expected = NullPointerException.class)
    public void instantiateInvalidByteCellWithNameNull() {
        String cellName = null; // must be null.
        Byte cellValue = new Byte("6");

        Cell cell = new Cell(cellName, cellValue);

        fail("Test should have thrown a NullPointerException.");
        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    // ------------------------------------------------------------------------
    // Cell value type: java.lang.Boolean
    // ------------------------------------------------------------------------

    /**
     * Tests the instantiation of a cell with a Boolean cell value and cell name.
     */
    @Test
    public void instantiateValidBooleanCell() {
        String cellName = "test";
        Boolean cellValue = new Boolean(true);

        Cell cell = new Cell(cellName, cellValue);

        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    /**
     * Tests the instantiation of a cell with a Boolean cell value which is null.
     */
    @Test
    public void instantiateValidBooleanCellWithNullValue() {
        String cellName = "test";
        Boolean cellValue = null; // must be null.

        Cell cell = new Cell(cellName, cellValue);

        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    /**
     * Tests the instantiation of a cell with a Boolean cell value and cell name that is empty.
     */
    @Test(expected = IllegalArgumentException.class)
    public void instantiateInvalidBooleanCellWithNameEmpty() {
        String cellName = ""; // must be empty.
        Boolean cellValue = new Boolean(false);

        Cell cell = new Cell(cellName, cellValue);

        fail("Test should have thrown an IllegalArgumentException.");
        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    /**
     * Tests the instantiation of a cell with a Boolean cell value and cell name that is null.
     */
    @Test(expected = NullPointerException.class)
    public void instantiateInvalidBooleanCellWithNameNull() {
        String cellName = null; // must be null.
        Boolean cellValue = new Boolean(true);

        Cell cell = new Cell(cellName, cellValue);

        fail("Test should have thrown a NullPointerException.");
        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    // ------------------------------------------------------------------------
    // Cell value type: java.lang.Character
    // ------------------------------------------------------------------------

    /**
     * Tests the instantiation of a cell with a Character cell value and cell name.
     */
    @Test
    public void instantiateValidCharacterCell() {
        String cellName = "test";
        Character cellValue = new Character('D');

        Cell cell = new Cell(cellName, cellValue);

        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    /**
     * Tests the instantiation of a cell with a Character cell value which is null.
     */
    @Test
    public void instantiateValidCharacterCellWithNullValue() {
        String cellName = "test";
        Character cellValue = null; // must be null.

        Cell cell = new Cell(cellName, cellValue);

        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    /**
     * Tests the instantiation of a cell with a Long cell value and cell name that is empty.
     */
    @Test(expected = IllegalArgumentException.class)
    public void instantiateInvalidCharacterCellWithNameEmpty() {
        String cellName = ""; // must be empty.
        Character cellValue = new Character('r');

        Cell cell = new Cell(cellName, cellValue);

        fail("Test should have thrown an IllegalArgumentException.");
        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    /**
     * Tests the instantiation of a cell with a Character cell value and cell name that is null.
     */
    @Test(expected = NullPointerException.class)
    public void instantiateInvalidCharacterCellWithNameNull() {
        String cellName = null; // must be null.
        Character cellValue = new Character('%');

        Cell cell = new Cell(cellName, cellValue);

        fail("Test should have thrown a NullPointerException.");
        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    // ------------------------------------------------------------------------
    // Cell value type: java.lang.Double
    // ------------------------------------------------------------------------

    /**
     * Tests the instantiation of a cell with a Double cell value and cell name.
     */
    @Test
    public void instantiateValidDoubleCell() {
        String cellName = "test";
        Double cellValue = new Double(453.98);

        Cell cell = new Cell(cellName, cellValue);

        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    /**
     * Tests the instantiation of a cell with a Double cell value which is null.
     */
    @Test
    public void instantiateValidDoubleCellWithNullValue() {
        String cellName = "test";
        Double cellValue = null; // must be null.

        Cell cell = new Cell(cellName, cellValue);

        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    /**
     * Tests the instantiation of a cell with a Double cell value and cell name that is empty.
     */
    @Test(expected = IllegalArgumentException.class)
    public void instantiateInvalidDoubleCellWithNameEmpty() {
        String cellName = ""; // must be empty.
        Double cellValue = new Double(2.02);

        Cell cell = new Cell(cellName, cellValue);

        fail("Test should have thrown an IllegalArgumentException.");
        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    /**
     * Tests the instantiation of a cell with a Double cell value and cell name that is null.
     */
    @Test(expected = NullPointerException.class)
    public void instantiateInvalidDoubleCellWithNameNull() {
        String cellName = null; // must be null.
        Double cellValue = new Double(1.23);

        Cell cell = new Cell(cellName, cellValue);

        fail("Test should have thrown a NullPointerException.");
        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    // ------------------------------------------------------------------------
    // Cell value type: java.lang.Enum
    // ------------------------------------------------------------------------

    /**
     * Enum for testing purposes.
     */
    @Ignore
    private enum WeatherForecast {
        SUNNY, WINDY, STORMY, CLOUDY, RAINY, MISTY, SNOWY;
    }

    /**
     * Tests the instantiation of a cell with a Enum cell value and cell name.
     */
    @Test
    public void instantiateValidEnumCell() {
        String cellName = "test";
        Enum<WeatherForecast> cellValue = WeatherForecast.SUNNY;

        Cell cell = new Cell(cellName, cellValue);

        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    /**
     * Tests the instantiation of a cell with a Enum cell value which is null.
     */
    @Test
    public void instantiateValidEnumCellWithNullValue() {
        String cellName = "test";
        Enum<WeatherForecast> cellValue = null; // must be null.

        Cell cell = new Cell(cellName, cellValue);

        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    /**
     * Tests the instantiation of a cell with a Enum cell value and cell name that is empty.
     */
    @Test(expected = IllegalArgumentException.class)
    public void instantiateInvalidEnumCellWithNameEmpty() {
        String cellName = ""; // must be empty.
        Enum<WeatherForecast> cellValue = WeatherForecast.RAINY;

        Cell cell = new Cell(cellName, cellValue);

        fail("Test should have thrown an IllegalArgumentException.");
        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    /**
     * Tests the instantiation of a cell with a Enum cell value and cell name that is null.
     */
    @Test(expected = NullPointerException.class)
    public void instantiateInvalidEnumCellWithNameNull() {
        String cellName = null; // must be null.
        Enum<WeatherForecast> cellValue = WeatherForecast.SNOWY;

        Cell cell = new Cell(cellName, cellValue);

        fail("Test should have thrown a NullPointerException.");
        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    // ------------------------------------------------------------------------
    // Cell value type: java.lang.Float
    // ------------------------------------------------------------------------

    /**
     * Tests the instantiation of a cell with a Float cell value and cell name.
     */
    @Test
    public void instantiateValidFloatCell() {
        String cellName = "test";
        Float cellValue = new Float(19.95);

        Cell cell = new Cell(cellName, cellValue);

        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    /**
     * Tests the instantiation of a cell with a Float cell value which is null.
     */
    @Test
    public void instantiateValidFloatCellWithNullValue() {
        String cellName = "test";
        Float cellValue = null; // must be null.

        Cell cell = new Cell(cellName, cellValue);

        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    /**
     * Tests the instantiation of a cell with a Float cell value and cell name that is empty.
     */
    @Test(expected = IllegalArgumentException.class)
    public void instantiateInvalidFloatCellWithNameEmpty() {
        String cellName = ""; // must be empty.
        Float cellValue = new Float(12.75);

        Cell cell = new Cell(cellName, cellValue);

        fail("Test should have thrown an IllegalArgumentException.");
        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    /**
     * Tests the instantiation of a cell with a Float cell value and cell name that is null.
     */
    @Test(expected = NullPointerException.class)
    public void instantiateInvalidFloatCellWithNameNull() {
        String cellName = null; // must be null.
        Float cellValue = new Float(0.045);

        Cell cell = new Cell(cellName, cellValue);

        fail("Test should have thrown a NullPointerException.");
        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    // ------------------------------------------------------------------------
    // Cell value type: java.lang.Integer
    // ------------------------------------------------------------------------

    /**
     * Tests the instantiation of a cell with a Integer cell value and cell name.
     */
    @Test
    public void instantiateValidIntegerCell() {
        String cellName = "test";
        Integer cellValue = new Integer(56345);

        Cell cell = new Cell(cellName, cellValue);

        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    /**
     * Tests the instantiation of a cell with a Integer cell value which is null.
     */
    @Test
    public void instantiateValidIntegerCellWithNullValue() {
        String cellName = "test";
        Integer cellValue = null; // must be null.

        Cell cell = new Cell(cellName, cellValue);

        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    /**
     * Tests the instantiation of a cell with a Integer cell value and cell name that is empty.
     */
    @Test(expected = IllegalArgumentException.class)
    public void instantiateInvalidIntegerCellWithNameEmpty() {
        String cellName = ""; // must be empty.
        Integer cellValue = new Integer(232);

        Cell cell = new Cell(cellName, cellValue);

        fail("Test should have thrown an IllegalArgumentException.");
        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    /**
     * Tests the instantiation of a cell with a Integer cell value and cell name that is null.
     */
    @Test(expected = NullPointerException.class)
    public void instantiateInvalidIntegerCellWithNameNull() {
        String cellName = null; // must be null.
        Integer cellValue = new Integer(9000);

        Cell cell = new Cell(cellName, cellValue);

        fail("Test should have thrown a NullPointerException.");
        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    // ------------------------------------------------------------------------
    // Cell value type: java.lang.Long
    // ------------------------------------------------------------------------

    /**
     * Tests the instantiation of a cell with a Long cell value and cell name.
     */
    @Test
    public void instantiateValidLongCell() {
        String cellName = "test";
        Long cellValue = new Long(456345L);

        Cell cell = new Cell(cellName, cellValue);

        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    /**
     * Tests the instantiation of a cell with a Long cell value which is null.
     */
    @Test
    public void instantiateValidLongCellWithNullValue() {
        String cellName = "test";
        Long cellValue = null; // must be null.

        Cell cell = new Cell(cellName, cellValue);

        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    /**
     * Tests the instantiation of a cell with a Long cell value and cell name that is empty.
     */
    @Test(expected = IllegalArgumentException.class)
    public void instantiateInvalidLongCellWithNameEmpty() {
        String cellName = ""; // must be empty.
        Long cellValue = new Long(5363453421L);

        Cell cell = new Cell(cellName, cellValue);

        fail("Test should have thrown an IllegalArgumentException.");
        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    /**
     * Tests the instantiation of a cell with a Long cell value and cell name that is null.
     */
    @Test(expected = NullPointerException.class)
    public void instantiateInvalidLongCellWithNameNull() {
        String cellName = null; // must be null.
        Long cellValue = new Long(543234L);

        Cell cell = new Cell(cellName, cellValue);

        fail("Test should have thrown a NullPointerException.");
        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    // ------------------------------------------------------------------------
    // Cell value type: java.lang.Short
    // ------------------------------------------------------------------------

    /**
     * Tests the instantiation of a cell with a Short cell value and cell name.
     */
    @Test
    public void instantiateValidShortCell() {
        String cellName = "test";
        Short cellValue = new Short((short) 25);

        Cell cell = new Cell(cellName, cellValue);

        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    /**
     * Tests the instantiation of a cell with a Short cell value which is null.
     */
    @Test
    public void instantiateValidShortCellWithNullValue() {
        String cellName = "test";
        Short cellValue = null; // must be null.

        Cell cell = new Cell(cellName, cellValue);

        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    /**
     * Tests the instantiation of a cell with a Short cell value and cell name that is empty.
     */
    @Test(expected = IllegalArgumentException.class)
    public void instantiateInvalidShortCellWithNameEmpty() {
        String cellName = ""; // must be empty.
        Short cellValue = new Short((short) 21);

        Cell cell = new Cell(cellName, cellValue);

        fail("Test should have thrown an IllegalArgumentException.");
        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    /**
     * Tests the instantiation of a cell with a Short cell value and cell name that is null.
     */
    @Test(expected = NullPointerException.class)
    public void instantiateInvalidShortCellWithNameNull() {
        String cellName = null; // must be null.
        Short cellValue = new Short((short) 5);

        Cell cell = new Cell(cellName, cellValue);

        fail("Test should have thrown a NullPointerException.");
        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    // ------------------------------------------------------------------------
    // Cell value type: java.lang.String
    // ------------------------------------------------------------------------

    /**
     * Tests the instantiation of a cell with a String cell value and cell name.
     */
    @Test
    public void instantiateValidStringCell() {
        String cellName = "test";
        String cellValue = new String("hello world!");

        Cell cell = new Cell(cellName, cellValue);

        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    /**
     * Tests the instantiation of a cell with a String cell value which is null.
     */
    @Test
    public void instantiateValidStringCellWithNullValue() {
        String cellName = "test";
        BigInteger cellValue = null; // must be null.

        Cell cell = new Cell(cellName, cellValue);

        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    /**
     * Tests the instantiation of a cell with a String cell value and cell name that is empty.
     */
    @Test(expected = IllegalArgumentException.class)
    public void instantiateInvalidStringCellWithNameEmpty() {
        String cellName = ""; // must be empty.
        String cellValue = new String("whatever");

        Cell cell = new Cell(cellName, cellValue);

        fail("Test should have thrown an IllegalArgumentException.");
        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    /**
     * Tests the instantiation of a cell with a String cell value and cell name that is null.
     */
    @Test(expected = NullPointerException.class)
    public void instantiateInvalidStringCellWithNameNull() {
        String cellName = null; // must be null.
        String cellValue = new String("did you see that movie?");

        Cell cell = new Cell(cellName, cellValue);

        fail("Test should have thrown a NullPointerException.");
        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    // ------------------------------------------------------------------------
    // Cell value type: java.math.BigDecimal
    // ------------------------------------------------------------------------

    /**
     * Tests the instantiation of a cell with a BigDecimal cell value and cell name.
     */
    @Test
    public void instantiateValidBigDecimalCell() {
        String cellName = "test";
        BigDecimal cellValue = new BigDecimal("42352344.5234");

        Cell cell = new Cell(cellName, cellValue);

        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    /**
     * Tests the instantiation of a cell with a BigDecimal cell value which is null.
     */
    @Test
    public void instantiateValidBigDecimalCellWithNullValue() {
        String cellName = "test";
        BigDecimal cellValue = null; // must be null.

        Cell cell = new Cell(cellName, cellValue);

        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    /**
     * Tests the instantiation of a cell with a BigDecimal cell value and cell name that is empty.
     */
    @Test(expected = IllegalArgumentException.class)
    public void instantiateInvalidBigDecimalCellWithNameEmpty() {
        String cellName = ""; // must be empty.
        BigDecimal cellValue = new BigDecimal("2134234");

        Cell cell = new Cell(cellName, cellValue);

        fail("Test should have thrown an IllegalArgumentException.");
        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    /**
     * Tests the instantiation of a cell with a BigDecimal cell value and cell name that is null.
     */
    @Test(expected = NullPointerException.class)
    public void instantiateInvalidBigDecimalCellWithNameNull() {
        String cellName = null; // must be null.
        BigDecimal cellValue = new BigDecimal("134234.45");

        Cell cell = new Cell(cellName, cellValue);

        fail("Test should have thrown a NullPointerException.");
        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    // ------------------------------------------------------------------------
    // Cell value type: java.math.BigInteger
    // ------------------------------------------------------------------------

    /**
     * Tests the instantiation of a cell with a BigInteger cell value and cell name.
     */
    @Test
    public void instantiateValidBigIntegerCell() {
        String cellName = "test";
        BigInteger cellValue = new BigInteger("423523445234");

        Cell cell = new Cell(cellName, cellValue);

        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    /**
     * Tests the instantiation of a cell with a BigInteger cell value which is null.
     */
    @Test
    public void instantiateValidBigIntegerCellWithNullValue() {
        String cellName = "test";
        BigInteger cellValue = null; // must be null.

        Cell cell = new Cell(cellName, cellValue);

        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    /**
     * Tests the instantiation of a cell with a BigInteger cell value and cell name that is empty.
     */
    @Test(expected = IllegalArgumentException.class)
    public void instantiateInvalidBigIntegerCellWithNameEmpty() {
        String cellName = ""; // must be empty.
        BigInteger cellValue = new BigInteger("2134234");

        Cell cell = new Cell(cellName, cellValue);

        fail("Test should have thrown an IllegalArgumentException.");
        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    /**
     * Tests the instantiation of a cell with a BigInteger cell value and cell name that is null.
     */
    @Test(expected = NullPointerException.class)
    public void instantiateInvalidBigIntegerCellWithNameNull() {
        String cellName = null; // must be null.
        BigInteger cellValue = new BigInteger("432134234");

        Cell cell = new Cell(cellName, cellValue);

        fail("Test should have thrown a NullPointerException.");
        assertEquals(cellName, cell.getName());
        assertEquals(cellValue, cell.getValue());
    }

    // ------------------------------------------------------------------------
}