/**
 * 
 */
package org.jsapar.schema;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringWriter;

import org.jsapar.Document;
import org.jsapar.JSaParException;
import org.jsapar.Line;
import org.jsapar.StringCell;
import org.jsapar.input.LineErrorEvent;
import org.jsapar.input.LineParsedEvent;
import org.jsapar.input.ParseException;
import org.jsapar.input.ParseSchema;
import org.jsapar.input.ParsingEventListener;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author stejon0
 * 
 */
public class CsvControlCellSchemaTest {

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for
     * {@link org.jsapar.schema.CsvControlCellSchema#output(org.jsapar.Document, java.io.Writer)} .
     * 
     * @throws IOException
     * @throws JSaParException
     */
    @Test
    public void testOutput() throws IOException, JSaParException {
        CsvControlCellSchema schema = new CsvControlCellSchema();
        schema.setControlCellSeparator(":->");
        CsvSchemaLine schemaLine = new CsvSchemaLine("Address");
        schemaLine.addSchemaCell(new CsvSchemaCell("Street"));
        schemaLine.addSchemaCell(new CsvSchemaCell("ZipCode"));
        schemaLine.addSchemaCell(new CsvSchemaCell("City"));
        schemaLine.setCellSeparator(":");
        schema.addSchemaLine(schemaLine);

        schemaLine = new CsvSchemaLine("Name");
        schemaLine.addSchemaCell(new CsvSchemaCell("First name"));
        schemaLine.addSchemaCell(new CsvSchemaCell("Last name"));
        schema.addSchemaLine(schemaLine);

        Document doc = new Document();

        Line line = new Line("Name");
        line.addCell(new StringCell("Jonas"));
        line.addCell(new StringCell("Stenberg"));
        doc.addLine(line);

        line = new Line("Address");
        line.addCell(new StringCell("Storgatan 4"));
        line.addCell(new StringCell("12345"));
        line.addCell(new StringCell("Bortastaden"));
        doc.addLine(line);

        line = new Line("Name");
        line.addCell(new StringCell("Nils"));
        line.addCell(new StringCell("Nilsson"));
        doc.addLine(line);

        StringWriter writer = new StringWriter();
        schema.output(doc.getLineIterator(), writer);

        String sLineSep = System.getProperty("line.separator");
        String sExpected = "Name:->Jonas;Stenberg" + sLineSep + "Address:->Storgatan 4:12345:Bortastaden" + sLineSep
                + "Name:->Nils;Nilsson";

        assertEquals(sExpected, writer.toString());
    }

    /**
     * Test method for
     * {@link org.jsapar.schema.CsvControlCellSchema#output(org.jsapar.Document, java.io.Writer)} .
     * 
     * @throws IOException
     * @throws JSaParException
     */
    @Test
    public void testOutputLine() throws IOException, JSaParException {
        CsvControlCellSchema schema = new CsvControlCellSchema();
        schema.setControlCellSeparator(":->");

        CsvSchemaLine schemaLine = new CsvSchemaLine("Name");
        schemaLine.addSchemaCell(new CsvSchemaCell("First name"));
        schemaLine.addSchemaCell(new CsvSchemaCell("Last name"));
        schema.addSchemaLine(schemaLine);
        schema.setLineSeparator("*");


        Line line = new Line("Name");
        line.addCell(new StringCell("Jonas"));
        line.addCell(new StringCell("Stenberg"));


        StringWriter writer = new StringWriter();
        schema.outputLineLn(line, writer);

        String sExpected = "Name:->Jonas;Stenberg*";

        assertEquals(sExpected, writer.toString());
    }    
    /**
     * Test method for
     * {@link org.jsapar.schema.CsvControlCellSchema#output(org.jsapar.Document, java.io.Writer)} .
     * 
     * @throws IOException
     * @throws JSaParException
     */
    @Test
    public void testOutput_ControlValueNotType() throws IOException, JSaParException {
        CsvControlCellSchema schema = new CsvControlCellSchema();
        schema.setControlCellSeparator(":->");
        CsvSchemaLine schemaLine = new CsvSchemaLine("Address", "A");
        schemaLine.addSchemaCell(new CsvSchemaCell("Street"));
        schemaLine.addSchemaCell(new CsvSchemaCell("ZipCode"));
        schemaLine.addSchemaCell(new CsvSchemaCell("City"));
        schemaLine.setCellSeparator(":");
        schema.addSchemaLine(schemaLine);

        schemaLine = new CsvSchemaLine("Name", "N");
        schemaLine.addSchemaCell(new CsvSchemaCell("First name"));
        schemaLine.addSchemaCell(new CsvSchemaCell("Last name"));
        schema.addSchemaLine(schemaLine);

        Document doc = new Document();

        Line line = new Line("Name");
        line.addCell(new StringCell("Jonas"));
        line.addCell(new StringCell("Stenberg"));
        doc.addLine(line);

        line = new Line("Address");
        line.addCell(new StringCell("Storgatan 4"));
        line.addCell(new StringCell("12345"));
        line.addCell(new StringCell("Bortastaden"));
        doc.addLine(line);

        line = new Line("Name");
        line.addCell(new StringCell("Nils"));
        line.addCell(new StringCell("Nilsson"));
        doc.addLine(line);

        StringWriter writer = new StringWriter();
        schema.output(doc.getLineIterator(), writer);

        String sLineSep = System.getProperty("line.separator");
        String sExpected = "N:->Jonas;Stenberg" + sLineSep + "A:->Storgatan 4:12345:Bortastaden" + sLineSep
                + "N:->Nils;Nilsson";

        assertEquals(sExpected, writer.toString());
    }
    
    @Test
    public void testOutput_DontWriteControlCell() throws IOException, JSaParException {
        CsvControlCellSchema schema = new CsvControlCellSchema();
        schema.setWriteControlCell(false);

        CsvSchemaLine schemaLine = new CsvSchemaLine("Address", "A");
        schemaLine.addSchemaCell(new CsvSchemaCell("Street"));
        schemaLine.addSchemaCell(new CsvSchemaCell("ZipCode"));
        schemaLine.addSchemaCell(new CsvSchemaCell("City"));
        schemaLine.setCellSeparator(":");
        schema.addSchemaLine(schemaLine);

        schemaLine = new CsvSchemaLine("Name", "N");
        schemaLine.addSchemaCell(new CsvSchemaCell("First name"));
        schemaLine.addSchemaCell(new CsvSchemaCell("Last name"));
        schema.addSchemaLine(schemaLine);

        Document doc = new Document();

        Line line = new Line("Name");
        line.addCell(new StringCell("Jonas"));
        line.addCell(new StringCell("Stenberg"));
        doc.addLine(line);

        line = new Line("Address");
        line.addCell(new StringCell("Storgatan 4"));
        line.addCell(new StringCell("12345"));
        line.addCell(new StringCell("Bortastaden"));
        doc.addLine(line);

        line = new Line("Name");
        line.addCell(new StringCell("Nils"));
        line.addCell(new StringCell("Nilsson"));
        doc.addLine(line);

        StringWriter writer = new StringWriter();
        schema.output(doc.getLineIterator(), writer);

        String sLineSep = System.getProperty("line.separator");
        String sExpected = "Jonas;Stenberg" + sLineSep + "Storgatan 4:12345:Bortastaden" + sLineSep
                + "Nils;Nilsson";

        assertEquals(sExpected, writer.toString());
    }

    /**
     * Test method for {@link org.jsapar.schema.CsvControlCellSchema#getControlCellSeparator()}.
     */
    @Test
    public void testGetSetControlCellSeparator() {
        CsvControlCellSchema schema = new CsvControlCellSchema();
        assertEquals(";", schema.getControlCellSeparator());
        schema.setControlCellSeparator("$$-$$");
        assertEquals("$$-$$", schema.getControlCellSeparator());
    }

    /**
     * Test method for
     * {@link org.jsapar.schema.CsvControlCellSchema#getSchemaLineByControlValue(java.lang.String)}
     * .
     */
    @Test
    public void testGetSchemaLine() {
        CsvControlCellSchema schema = new CsvControlCellSchema();
        schema.setControlCellSeparator(":->");
        CsvSchemaLine schemaLine = new CsvSchemaLine("Address");
        schemaLine.setLineTypeControlValue("A");
        schema.addSchemaLine(schemaLine);
        assertEquals("Address", schema.getSchemaLineByControlValue("A").getLineType());
    }


    /**
     * Test method for {@link org.jsapar.schema.CsvControlCellSchema#clone()}.
     * 
     * @throws CloneNotSupportedException
     */
    @Test
    public void testClone() throws CloneNotSupportedException {
        CsvControlCellSchema schema = new CsvControlCellSchema();
        schema.setControlCellSeparator(":->");
        CsvSchemaLine schemaLine = new CsvSchemaLine("Address");
        schemaLine.setCellSeparator(":");
        schema.addSchemaLine(schemaLine);

        schemaLine = new CsvSchemaLine("Name");
        schema.addSchemaLine(schemaLine);

        CsvControlCellSchema clone = schema.clone();
        assertEquals(schema.getControlCellSeparator(), clone.getControlCellSeparator());
    }


}