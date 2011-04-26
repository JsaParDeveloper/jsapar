package org.jsapar.schema;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.StringWriter;

import org.jsapar.JSaParException;
import org.jsapar.Line;
import org.jsapar.StringCell;
import org.jsapar.input.LineErrorEvent;
import org.jsapar.input.LineParsedEvent;
import org.jsapar.input.ParseException;
import org.jsapar.input.ParsingEventListener;
import org.junit.Test;

public class CSVSchemaLineTest  {

    boolean foundError=false;
    
    @Test
    public final void testCSVSchemaLine()  {
        CsvSchemaLine schemaLine = new CsvSchemaLine();
        assertEquals("", schemaLine.getLineType());
        assertEquals("", schemaLine.getLineTypeControlValue());
    }

    @Test
    public final void testCSVSchemaLine_String()  {
        CsvSchemaLine schemaLine = new CsvSchemaLine("LineType");
        assertEquals("LineType", schemaLine.getLineType());
        assertEquals("LineType", schemaLine.getLineTypeControlValue());
    }
    
    @Test
    public void testBuild() throws JSaParException {
	CsvSchemaLine schemaLine = new CsvSchemaLine(1);
	String sLine = "Jonas;Stenberg;Hemv�gen 19;111 22;Stockholm";
	boolean rc = schemaLine.parse(1, sLine, new ParsingEventListener() {

	    @Override
	    public void lineErrorEvent(LineErrorEvent event) throws ParseException {
	    }

	    @Override
	    public void lineParsedEvent(LineParsedEvent event) throws JSaParException {
		Line line = event.getLine();
		assertEquals("Jonas", line.getCell(0).getStringValue());
		assertEquals("Stenberg", line.getCell(1).getStringValue());
	    }
	});

	assertEquals(true, rc);
    }

    @Test
    public void testBuild_2byte_unicode() throws JSaParException {
        CsvSchemaLine schemaLine = new CsvSchemaLine(1);
        schemaLine.setCellSeparator("\uFFD0");
        String sLine = "Jonas\uFFD0Stenberg\uFFD0Hemv�gen 19\uFFD0111 22\uFFD0Stockholm";
        boolean rc = schemaLine.parse(1, sLine, new ParsingEventListener() {

            @Override
            public void lineErrorEvent(LineErrorEvent event) throws ParseException {
            }

            @Override
            public void lineParsedEvent(LineParsedEvent event) throws JSaParException {
                Line line = event.getLine();
                assertEquals("Jonas", line.getCell(0).getStringValue());
                assertEquals("Stenberg", line.getCell(1).getStringValue());
            }
        });

        assertEquals(true, rc);
    }
    
    @Test
    public void testBuild_quoted() throws JSaParException {
	CsvSchemaLine schemaLine = new CsvSchemaLine(1);
	schemaLine.setQuoteChar('\"');
	String sLine = "Jonas;Stenberg;\"Hemv�gen ;19\";\"111 22\";Stockholm";
	boolean rc = schemaLine.parse(1, sLine, new ParsingEventListener() {

	    @Override
	    public void lineErrorEvent(LineErrorEvent event) throws ParseException {
	    }

	    @Override
	    public void lineParsedEvent(LineParsedEvent event) throws JSaParException {
		Line line = event.getLine();
		assertEquals(5, line.getNumberOfCells());
		assertEquals("Jonas", line.getCell(0).getStringValue());
		assertEquals("Stenberg", line.getCell(1).getStringValue());
		assertEquals("Hemv�gen ;19", line.getCell(2).getStringValue());
		assertEquals("111 22", line.getCell(3).getStringValue());
		assertEquals("Stockholm", line.getCell(4).getStringValue());
	    }
	});

	assertEquals(true, rc);
    }

    @Test
    public void testBuild_quoted_last() throws JSaParException {
        CsvSchemaLine schemaLine = new CsvSchemaLine(1);
        schemaLine.setQuoteChar('\"');
        String sLine = "Jonas;Stenberg;\"Hemv�gen ;19\"";
        boolean rc = schemaLine.parse(1, sLine, new ParsingEventListener() {

            @Override
            public void lineErrorEvent(LineErrorEvent event) throws ParseException {
            }

            @Override
            public void lineParsedEvent(LineParsedEvent event) throws JSaParException {
                Line line = event.getLine();
                assertEquals(3, line.getNumberOfCells());
                assertEquals("Jonas", line.getCell(0).getStringValue());
                assertEquals("Stenberg", line.getCell(1).getStringValue());
                assertEquals("Hemv�gen ;19", line.getCell(2).getStringValue());
            }
        });

        assertEquals(true, rc);
    }

    @Test
    public void testBuild_quoted_last_cellsep() throws JSaParException {
        CsvSchemaLine schemaLine = new CsvSchemaLine(1);
        schemaLine.setQuoteChar('\"');
        String sLine = "Jonas;Stenberg;\"Hemv�gen ;19\";";
        boolean rc = schemaLine.parse(1, sLine, new ParsingEventListener() {

            @Override
            public void lineErrorEvent(LineErrorEvent event) throws ParseException {
            }

            @Override
            public void lineParsedEvent(LineParsedEvent event) throws JSaParException {
                Line line = event.getLine();
                assertEquals(3, line.getNumberOfCells());
                assertEquals("Jonas", line.getCell(0).getStringValue());
                assertEquals("Stenberg", line.getCell(1).getStringValue());
                assertEquals("Hemv�gen ;19", line.getCell(2).getStringValue());
            }
        });

        assertEquals(true, rc);
    }
    
    @Test
    public void testBuild_quoted_missing_end() throws JSaParException {
	CsvSchemaLine schemaLine = new CsvSchemaLine(1);
	schemaLine.setQuoteChar('\"');
	String sLine = "Jonas;Stenberg;\"Hemv�gen ;19;111 22;Stockholm";
        boolean rc = schemaLine.parse(1, sLine, new ParsingEventListener() {

            @Override
            public void lineErrorEvent(LineErrorEvent event) throws ParseException {
            }

            @Override
            public void lineParsedEvent(LineParsedEvent event) throws JSaParException {
                Line line = event.getLine();
                assertEquals(6, line.getNumberOfCells());
                assertEquals("Jonas", line.getCell(0).getStringValue());
                assertEquals("Stenberg", line.getCell(1).getStringValue());
                assertEquals("\"Hemv�gen ", line.getCell(2).getStringValue());
                assertEquals("19", line.getCell(3).getStringValue());
            }
        });

        assertEquals(true, rc);
    }

    @Test
    public void testBuild_quoted_miss_placed_start() throws JSaParException {
	CsvSchemaLine schemaLine = new CsvSchemaLine(1);
	schemaLine.setQuoteChar('\"');
	String sLine = "Jonas;Stenberg;H\"emv�gen ;19;111 \"22\";\"Stoc\"kholm\"";
        boolean rc = schemaLine.parse(1, sLine, new ParsingEventListener() {

            @Override
            public void lineErrorEvent(LineErrorEvent event) throws ParseException {
            }

            @Override
            public void lineParsedEvent(LineParsedEvent event) throws JSaParException {
                Line line = event.getLine();
                assertEquals(6, line.getNumberOfCells());
                assertEquals("Jonas", line.getCell(0).getStringValue());
                assertEquals("Stenberg", line.getCell(1).getStringValue());
                assertEquals("H\"emv�gen ", line.getCell(2).getStringValue());
                assertEquals("19", line.getCell(3).getStringValue());
                assertEquals("111 \"22\"", line.getCell(4).getStringValue());
                assertEquals("Stoc\"kholm", line.getCell(5).getStringValue());
            }
        });

        assertEquals(true, rc);
    }

    @Test
    public void testBuild_quoted_miss_placed_end() throws JSaParException {
	CsvSchemaLine schemaLine = new CsvSchemaLine(1);
	schemaLine.setQuoteChar('\"');
	String sLine = "Jonas;Stenberg;\"Hemv�gen ;1\"9;111 22;Stockholm";
        boolean rc = schemaLine.parse(1, sLine, new ParsingEventListener() {

            @Override
            public void lineErrorEvent(LineErrorEvent event) throws ParseException {
            }

            @Override
            public void lineParsedEvent(LineParsedEvent event) throws JSaParException {
                Line line = event.getLine();
                assertEquals(6, line.getNumberOfCells());
                assertEquals("Jonas", line.getCell(0).getStringValue());
                assertEquals("Stenberg", line.getCell(1).getStringValue());
                assertEquals("\"Hemv�gen ", line.getCell(2).getStringValue());
                assertEquals("1\"9", line.getCell(3).getStringValue());
                assertEquals("111 22", line.getCell(4).getStringValue());
            }
        });

        assertEquals(true, rc);
    }

    @Test
    public void testBuild_withNames() throws JSaParException {
        CsvSchemaLine schemaLine = new CsvSchemaLine(1);
        schemaLine.setCellSeparator(";-)");
        schemaLine.addSchemaCell(new CsvSchemaCell("First Name"));
        schemaLine.addSchemaCell(new CsvSchemaCell("Last Name"));

        String sLine = "Jonas;-)Stenberg";
        boolean rc = schemaLine.parse(1, sLine, new ParsingEventListener() {

            @Override
            public void lineErrorEvent(LineErrorEvent event) throws ParseException {
                // TODO Auto-generated method stub

            }

            @Override
            public void lineParsedEvent(LineParsedEvent event) throws JSaParException {
                Line line = event.getLine();
                assertEquals("Jonas", line.getCell(0).getStringValue());
                assertEquals("Stenberg", line.getCell(1).getStringValue());

                assertEquals("First Name", line.getCell(0).getName());
                assertEquals("Last Name", line.getCell(1).getName());
            }
        });
        assertEquals(true, rc);

    }

    @Test
    public void testBuild_withDefault() throws JSaParException {
        CsvSchemaLine schemaLine = new CsvSchemaLine(1);
        schemaLine.setCellSeparator(";-)");
        schemaLine.addSchemaCell(new CsvSchemaCell("First Name"));
        CsvSchemaCell happyCell = new CsvSchemaCell("Happy");
        happyCell.setDefaultValue("yes");
        schemaLine.addSchemaCell(happyCell);
        schemaLine.addSchemaCell(new CsvSchemaCell("Last Name"));

        String sLine = "Jonas;-);-)Stenberg";
        boolean rc = schemaLine.parse(1, sLine, new ParsingEventListener() {

            @Override
            public void lineErrorEvent(LineErrorEvent event) throws ParseException {
                // TODO Auto-generated method stub

            }

            @Override
            public void lineParsedEvent(LineParsedEvent event) throws JSaParException {
                Line line = event.getLine();
                assertEquals("Jonas", line.getCell(0).getStringValue());
                assertEquals("Jonas", line.getStringCellValue("First Name"));
                assertEquals("Stenberg", line.getCell(2).getStringValue());
                assertEquals("yes", line.getStringCellValue("Happy"));

                assertEquals("First Name", line.getCell(0).getName());
                assertEquals("Last Name", line.getCell(2).getName());
            }
        });
        assertEquals(true, rc);

    }

    @Test
    public void testBuild_default_and_mandatory() throws JSaParException {
        CsvSchemaLine schemaLine = new CsvSchemaLine(1);
        schemaLine.setCellSeparator(";-)");
        schemaLine.addSchemaCell(new CsvSchemaCell("First Name"));
        CsvSchemaCell happyCell = new CsvSchemaCell("Happy");
        happyCell.setDefaultValue("yes");
        happyCell.setMandatory(true);
        schemaLine.addSchemaCell(happyCell);
        schemaLine.addSchemaCell(new CsvSchemaCell("Last Name"));

        String sLine = "Jonas;-);-)Stenberg";
        boolean rc = schemaLine.parse(1, sLine, new ParsingEventListener() {

            @Override
            public void lineErrorEvent(LineErrorEvent event) throws ParseException {
                assertEquals("Happy", event.getCellParseError().getCellName());
                foundError = true;
            }

            @Override
            public void lineParsedEvent(LineParsedEvent event) throws JSaParException {
                Line line = event.getLine();
                assertEquals("Jonas", line.getCell(0).getStringValue());
                assertEquals("Jonas", line.getStringCellValue("First Name"));
                assertEquals("Stenberg", line.getCell(2).getStringValue());
                assertEquals("yes", line.getStringCellValue("Happy"));

                assertEquals("First Name", line.getCell(0).getName());
                assertEquals("Last Name", line.getCell(2).getName());
            }
        });
        assertEquals(true, rc);
        assertEquals(true, foundError);
    }
    
    @Test
    public void testBuild_withDefaultLast() throws JSaParException {
        CsvSchemaLine schemaLine = new CsvSchemaLine(1);
        schemaLine.setCellSeparator(";-)");
        schemaLine.addSchemaCell(new CsvSchemaCell("First Name"));
        schemaLine.addSchemaCell(new CsvSchemaCell("Last Name"));
        CsvSchemaCell happyCell = new CsvSchemaCell("Happy");
        happyCell.setDefaultValue("yes");
        schemaLine.addSchemaCell(happyCell);

        String sLine = "Jonas;-)Stenberg";
        boolean rc = schemaLine.parse(1, sLine, new ParsingEventListener() {

            @Override
            public void lineErrorEvent(LineErrorEvent event) throws ParseException {
                // TODO Auto-generated method stub

            }

            @Override
            public void lineParsedEvent(LineParsedEvent event) throws JSaParException {
                Line line = event.getLine();
                assertEquals("Jonas", line.getCell(0).getStringValue());
                assertEquals("Jonas", line.getStringCellValue("First Name"));
                assertEquals("Stenberg", line.getCell(1).getStringValue());
                assertEquals("yes", line.getStringCellValue("Happy"));

                assertEquals("First Name", line.getCell(0).getName());
                assertEquals("Last Name", line.getCell(1).getName());
            }
        });
        assertEquals(true, rc);

    }
    
    @Test(expected=ParseException.class)
    public void testBuild_withMandatoryLast() throws JSaParException {
        CsvSchemaLine schemaLine = new CsvSchemaLine(1);
        schemaLine.setCellSeparator(";-)");
        schemaLine.addSchemaCell(new CsvSchemaCell("First Name"));
        schemaLine.addSchemaCell(new CsvSchemaCell("Last Name"));
        CsvSchemaCell happyCell = new CsvSchemaCell("Happy");
        happyCell.setMandatory(true);
        schemaLine.addSchemaCell(happyCell);

        String sLine = "Jonas;-)Stenberg";
        schemaLine.parse(1, sLine, new ParsingEventListener() {

            @Override
            public void lineErrorEvent(LineErrorEvent event) throws ParseException {
                throw new ParseException(event.getCellParseError());
            }

            @Override
            public void lineParsedEvent(LineParsedEvent event) throws JSaParException {
                fail("Expects an error");
            }
        });
        fail("Expects an error");

    }
    
    
    @Test
    public void testOutput() throws IOException, JSaParException {

	CsvSchemaLine schemaLine = new CsvSchemaLine(1);
	schemaLine.setCellSeparator(";-)");
	schemaLine.addSchemaCell(new CsvSchemaCell("First Name"));
	schemaLine.addSchemaCell(new CsvSchemaCell("Last Name"));

	Line line = new Line();
	line.addCell(new StringCell("First Name", "Jonas"));
	line.addCell(new StringCell("Last Name", "Stenberg"));
	StringWriter writer = new StringWriter();

	schemaLine.output(line, writer);

	assertEquals("Jonas;-)Stenberg", writer.toString());

    }
    
    @Test
    public void testOutput_ignoreWrite() throws IOException, JSaParException {

        CsvSchemaLine schemaLine = new CsvSchemaLine(1);
        schemaLine.setCellSeparator(";-)");
        CsvSchemaCell firstNameSchema = new CsvSchemaCell("First Name");
        firstNameSchema.setIgnoreWrite(true);
        schemaLine.addSchemaCell(firstNameSchema);
        schemaLine.addSchemaCell(new CsvSchemaCell("Last Name"));

        Line line = new Line();
        line.addCell(new StringCell("First Name", "Jonas"));
        line.addCell(new StringCell("Last Name", "Stenberg"));
        StringWriter writer = new StringWriter();

        schemaLine.output(line, writer);

        assertEquals(";-)Stenberg", writer.toString());

    }    

    @Test
    public void testOutput_2byte_unicode() throws IOException, JSaParException {

        CsvSchemaLine schemaLine = new CsvSchemaLine(1);
        schemaLine.setCellSeparator("\uFFD0");
        schemaLine.addSchemaCell(new CsvSchemaCell("First Name"));
        schemaLine.addSchemaCell(new CsvSchemaCell("Last Name"));

        Line line = new Line();
        line.addCell(new StringCell("First Name", "Jonas"));
        line.addCell(new StringCell("Last Name", "Stenberg"));
        StringWriter writer = new StringWriter();

        schemaLine.output(line, writer);

        assertEquals("Jonas\uFFD0Stenberg", writer.toString());
    }

    
    @Test
    public void testOutput_not_found_in_line() throws IOException, JSaParException {

        CsvSchemaLine schemaLine = new CsvSchemaLine(1);
        schemaLine.setCellSeparator(";-)");
        schemaLine.addSchemaCell(new CsvSchemaCell("First Name"));
        schemaLine.addSchemaCell(new CsvSchemaCell("Last Name"));
        schemaLine.addSchemaCell(new CsvSchemaCell("Shoe size"));

        Line line = new Line();
        line.addCell(new StringCell("First Name", "Jonas"));
        line.addCell(new StringCell("Last Name", "Stenberg"));
        StringWriter writer = new StringWriter();

        schemaLine.output(line, writer);

        assertEquals("Jonas;-)Stenberg;-)", writer.toString());

    }

    @Test
    public void testOutput_null_value() throws IOException, JSaParException {

        CsvSchemaLine schemaLine = new CsvSchemaLine(1);
        schemaLine.setCellSeparator(";-)");
        schemaLine.addSchemaCell(new CsvSchemaCell("First Name"));
        schemaLine.addSchemaCell(new CsvSchemaCell("Last Name"));
        schemaLine.addSchemaCell(new CsvSchemaCell("Shoe size"));

        Line line = new Line();
        line.addCell(new StringCell("First Name", "Jonas"));
        line.addCell(new StringCell("Last Name", "Stenberg"));
        line.addCell(new StringCell("Shoe size", null));
        StringWriter writer = new StringWriter();

        schemaLine.output(line, writer);

        assertEquals("Jonas;-)Stenberg;-)", writer.toString());

    }
    
    @Test
    public void testOutput_reorder() throws IOException, JSaParException {
	CsvSchemaLine schemaLine = new CsvSchemaLine(1);
	schemaLine.addSchemaCell(new CsvSchemaCell("First Name"));
	schemaLine.addSchemaCell(new CsvSchemaCell("Last Name"));
	schemaLine.setCellSeparator(";");

	Line line = new Line();
	line.addCell(new StringCell("Last Name", "Stenberg"));
	line.addCell(new StringCell("First Name", "Jonas"));
	StringWriter writer = new StringWriter();

	schemaLine.output(line, writer);

	assertEquals("Jonas;Stenberg", writer.toString());

    }

    @Test
    public void testOutput_default() throws IOException, JSaParException {

        CsvSchemaLine schemaLine = new CsvSchemaLine(1);
        schemaLine.setCellSeparator(";-)");
        schemaLine.addSchemaCell(new CsvSchemaCell("First Name"));
        CsvSchemaCell lastNameSchema = new CsvSchemaCell("Last Name");
        lastNameSchema.setDefaultValue("Svensson");
        schemaLine.addSchemaCell(lastNameSchema);

        Line line = new Line();
        line.addCell(new StringCell("First Name", "Jonas"));
        StringWriter writer = new StringWriter();

        schemaLine.output(line, writer);

        assertEquals("Jonas;-)Svensson", writer.toString());

    }
    
    @Test
    public void testGetSchemaCell(){
        CsvSchemaLine schemaLine = new CsvSchemaLine(1);
        schemaLine.setCellSeparator(";-)");
        CsvSchemaCell cell1 = new CsvSchemaCell("First Name");
        schemaLine.addSchemaCell(cell1);
        schemaLine.addSchemaCell(new CsvSchemaCell("Last Name"));
        
        assertNull(schemaLine.getSchemaCell("Does not exist"));
        assertSame(cell1, schemaLine.getSchemaCell("First Name"));
        
    }
}