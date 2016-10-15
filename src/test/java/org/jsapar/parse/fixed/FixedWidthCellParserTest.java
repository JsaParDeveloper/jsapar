package org.jsapar.parse.fixed;

import org.jsapar.JSaParException;
import org.jsapar.error.ExceptionErrorEventListener;
import org.jsapar.model.*;
import org.jsapar.error.ErrorEvent;
import org.jsapar.parse.LineEventListener;
import org.jsapar.parse.LineParsedEvent;
import org.jsapar.parse.ParseException;
import org.jsapar.schema.FixedWidthSchemaCell;
import org.jsapar.schema.SchemaCellFormat;
import org.jsapar.schema.SchemaException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Locale;

import static org.junit.Assert.*;

/**
 * Created by stejon0 on 2016-03-13.
 */
public class FixedWidthCellParserTest {


    private class TestParsingEventListener implements LineEventListener {

        @Override
        public void lineParsedEvent(LineParsedEvent event) throws JSaParException {
        }

    }

    @Test
    public final void testBuild() throws IOException, JSaParException {
        String toParse = "   Jonas   ";
        FixedWidthSchemaCell schemaCell = new FixedWidthSchemaCell("First name", 11);

        Reader reader = new StringReader(toParse);
        FixedWidthCellParser cellParser = new FixedWidthCellParser();
        Cell cell = cellParser.parse(schemaCell,reader, true, ' ', new ExceptionErrorEventListener());

        assertEquals("Jonas", cell.getStringValue());
    }

    @Test
    public final void testBuild_dont_trim() throws IOException, JSaParException {
        String toParse = "   Jonas   ";
        FixedWidthSchemaCell schemaCell = new FixedWidthSchemaCell("First name", 11);

        Reader reader = new StringReader(toParse);
        FixedWidthCellParser cellParser = new FixedWidthCellParser();
        Cell cell = cellParser.parse(schemaCell,reader, false, ' ', new ExceptionErrorEventListener());

        assertEquals("   Jonas   ", cell.getStringValue());
    }

    @Test
    public final void testBuildEmptyMandatory() throws IOException {
        String toParse = "           ";
        FixedWidthSchemaCell schemaCell = new FixedWidthSchemaCell("First name", 11);
        schemaCell.setMandatory(true);

        Reader reader = new StringReader(toParse);
        @SuppressWarnings("unused")
        Cell cell;
        try {
            FixedWidthCellParser cellParser = new FixedWidthCellParser();
            cell = cellParser.parse(schemaCell,reader, true, ' ', new ExceptionErrorEventListener());
            fail("Should throw exception");

        } catch (ParseException ex) {
            System.out.println(ex);
        }
    }

    @Test
    public final void testBuildEmptyOptional() throws IOException, ParseException {
        String toParse = "           ";
        FixedWidthSchemaCell schemaCell = new FixedWidthSchemaCell("First name", 11);

        Reader reader = new StringReader(toParse);
        Cell cell;
        FixedWidthCellParser cellParser = new FixedWidthCellParser();
        cell = cellParser.parse(schemaCell,reader, true, ' ', new ExceptionErrorEventListener());
        assertEquals(null, cell.getValue());
    }

    @Test
    public final void testBuildEmptyOptionalInteger() throws IOException, ParseException {
        String toParse = "           ";
        FixedWidthSchemaCell schemaCell = new FixedWidthSchemaCell("ShoeSize", 11);
        schemaCell.setCellFormat(new SchemaCellFormat(CellType.INTEGER));

        Reader reader = new StringReader(toParse);
        Cell cell;
        FixedWidthCellParser cellParser = new FixedWidthCellParser();
        cell = cellParser.parse(schemaCell,reader, true, ' ', new ExceptionErrorEventListener());
        assertEquals(null, cell.getValue());
    }

    /**
     * A cell should not be considerede empty if blanks are not removed by trimming.
     */
    @Test
    public final void testBuildEmptyMandatoryNoTrim() throws IOException, Exception {
        String toParse = "           ";
        FixedWidthSchemaCell schemaCell = new FixedWidthSchemaCell("First name", 11);
        schemaCell.setMandatory(true);

        Reader reader = new StringReader(toParse);
        Cell cell;
        FixedWidthCellParser cellParser = new FixedWidthCellParser();
        cell = cellParser.parse(schemaCell,reader, false, ' ', new ExceptionErrorEventListener());
        assertEquals("           ", cell.getValue());
    }

    @Test
    public final void testBuild_empty() throws IOException, JSaParException {
        String toParse = "           ";
        FixedWidthSchemaCell schemaCell = new FixedWidthSchemaCell("First name", 11);

        Reader reader = new StringReader(toParse);
        FixedWidthCellParser cellParser = new FixedWidthCellParser();
        Cell cell = cellParser.parse(schemaCell,reader, true, ' ', new ExceptionErrorEventListener());

        assertEquals(null, cell.getValue());
        assertEquals("", cell.getStringValue());
    }

    @Test
    public final void testBuild_date() throws IOException, JSaParException, SchemaException {
        String toParse = "2007-04-10 16:15";
        FixedWidthSchemaCell schemaCell = new FixedWidthSchemaCell("Date", 16);
        schemaCell.setCellFormat(new SchemaCellFormat(CellType.DATE, "yyyy-MM-dd HH:mm"));

        Reader reader = new StringReader(toParse);
        FixedWidthCellParser cellParser = new FixedWidthCellParser();
        DateCell cell = (DateCell) cellParser.parse(schemaCell,reader, true, ' ', new ExceptionErrorEventListener());
        java.util.Date date = cell.getDateValue();
        Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(date);

        assertEquals(2007, calendar.get(Calendar.YEAR));
        assertEquals(3, calendar.get(Calendar.MONTH));
        assertEquals(10, calendar.get(Calendar.DAY_OF_MONTH));
        assertEquals(16, calendar.get(Calendar.HOUR_OF_DAY));
        assertEquals(15, calendar.get(Calendar.MINUTE));
    }

    @Test
    public final void testBuild_decimal_sv() throws IOException, JSaParException, SchemaException {
        String toParse = "-123 456,78  ";
        FixedWidthSchemaCell schemaCell = new FixedWidthSchemaCell("Decimal", 11);
        schemaCell.setCellFormat(new SchemaCellFormat(CellType.DECIMAL, "#,###.#", new Locale("sv", "SE")));
        // schemaCell.setCellFormat(new SchemaCellFormat(CellType.DECIMAL));

        Reader reader = new StringReader(toParse);
        FixedWidthCellParser cellParser = new FixedWidthCellParser();
        BigDecimalCell cell = (BigDecimalCell) cellParser.parse(schemaCell,reader, true, ' ', new ExceptionErrorEventListener());
        BigDecimal value = cell.getBigDecimalValue();

        assertEquals(new BigDecimal("-123456.78"), value);
    }

    @Test
    public final void testBuild_decimal_sv_dont_trim() throws IOException, JSaParException, SchemaException {
        String toParse = "-123 456,78  ";
        FixedWidthSchemaCell schemaCell = new FixedWidthSchemaCell("Decimal", 11);
        schemaCell.setCellFormat(new SchemaCellFormat(CellType.DECIMAL, "#,###.#", new Locale("sv", "SE")));

        Reader reader = new StringReader(toParse);
        FixedWidthCellParser cellParser = new FixedWidthCellParser();
        BigDecimalCell cell = (BigDecimalCell) cellParser.parse(schemaCell,reader, false, ' ', new ExceptionErrorEventListener());
        BigDecimal value = cell.getBigDecimalValue();

        assertEquals(new BigDecimal("-123456.78"), value);
    }

    @Test
    public final void testBuild_decimal_uk() throws IOException, JSaParException, SchemaException {
        String toParse = "-123,456.78  ";
        FixedWidthSchemaCell schemaCell = new FixedWidthSchemaCell("Decimal", 11);
        schemaCell.setCellFormat(new SchemaCellFormat(CellType.DECIMAL, "#,###.#", new Locale("en", "UK")));
        // schemaCell.setCellFormat(new SchemaCellFormat(CellType.DECIMAL));

        Reader reader = new StringReader(toParse);
        FixedWidthCellParser cellParser = new FixedWidthCellParser();
        BigDecimalCell cell = (BigDecimalCell) cellParser.parse(schemaCell,reader, true, ' ', new ExceptionErrorEventListener());
        BigDecimal value = cell.getBigDecimalValue();

        assertEquals(new BigDecimal("-123456.78"), value);
    }

    @Test
    public final void testBuild_int() throws IOException, JSaParException, SchemaException {
        String toParse = "123456";
        FixedWidthSchemaCell schemaCell = new FixedWidthSchemaCell("Integer", 6);
        schemaCell.setCellFormat(new SchemaCellFormat(CellType.INTEGER));

        Reader reader = new StringReader(toParse);
        FixedWidthCellParser cellParser = new FixedWidthCellParser();
        NumberCell cell = (NumberCell) cellParser.parse(schemaCell,reader, true, ' ', new ExceptionErrorEventListener());
        int value = cell.getNumberValue().intValue();

        assertEquals(123456, value);
    }

    @Test
    public final void testBuild_float() throws IOException, JSaParException, SchemaException {
        String toParse = "1123,234";
        FixedWidthSchemaCell schemaCell = new FixedWidthSchemaCell("Float", 6);
        schemaCell.setCellFormat(new SchemaCellFormat(CellType.FLOAT));

        Reader reader = new StringReader(toParse);
        FixedWidthCellParser cellParser = new FixedWidthCellParser();
        NumberCell cell = (NumberCell) cellParser.parse(schemaCell,reader, true, ' ', new ExceptionErrorEventListener());
        double value = cell.getNumberValue().doubleValue();

        assertEquals(1123, 234, value);
    }

    @Test
    public final void testBuild_floatExp() throws IOException, JSaParException, SchemaException {
        String toParse = "1,234E6 ";
        FixedWidthSchemaCell schemaCell = new FixedWidthSchemaCell("Float", 8);
        schemaCell.setLocale(new Locale("sv", "SE"));
        schemaCell.setCellFormat(new SchemaCellFormat(CellType.FLOAT, "#.###E0", schemaCell.getLocale()));

        Reader reader = new StringReader(toParse);
        FixedWidthCellParser cellParser = new FixedWidthCellParser();
        NumberCell cell = (NumberCell) cellParser.parse(schemaCell,reader, true, ' ', new ExceptionErrorEventListener());
        double value = cell.getNumberValue().doubleValue();

        assertEquals(1.234e6, value, 0.001);
    }

    @Test
    public final void testBuild_boolean() throws IOException, JSaParException, SchemaException {
        String toParse = "true ";
        FixedWidthSchemaCell schemaCell = new FixedWidthSchemaCell("True", 5);
        schemaCell.setCellFormat(new SchemaCellFormat(CellType.BOOLEAN));

        Reader reader = new StringReader(toParse);
        FixedWidthCellParser cellParser = new FixedWidthCellParser();
        BooleanCell cell = (BooleanCell) cellParser.parse(schemaCell,reader, true, ' ', new ExceptionErrorEventListener());
        boolean value = cell.getBooleanValue();

        assertEquals(true, value);
    }

    @Test
    public final void testBuildZeroLength() throws IOException, ParseException {
        String toParse = "Next";
        FixedWidthSchemaCell schemaCell = new FixedWidthSchemaCell("DontRead", 0);

        Reader reader = new StringReader(toParse);
        Cell cell;
        FixedWidthCellParser cellParser = new FixedWidthCellParser();
        cell = cellParser.parse(schemaCell,reader, true, ' ', new ExceptionErrorEventListener());
        Assert.assertNotNull(cell);
        Assert.assertNull(cell.getValue());
        Assert.assertEquals("", cell.getStringValue());
    }
}