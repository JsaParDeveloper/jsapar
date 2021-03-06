package org.jsapar.parse.fixed;

import org.jsapar.error.ExceptionErrorEventListener;
import org.jsapar.model.Document;
import org.jsapar.model.LineUtils;
import org.jsapar.parse.DocumentBuilderLineEventListener;
import org.jsapar.text.TextParseConfig;
import org.jsapar.schema.FixedWidthSchema;
import org.jsapar.schema.FixedWidthSchemaCell;
import org.jsapar.schema.FixedWidthSchemaLine;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;

public class FixedWidthParserTest {

    @Test
    public final void testParse_Flat() throws IOException {
        String toParse = "JonasStenbergFridaStenberg";
        org.jsapar.schema.FixedWidthSchema schema = new org.jsapar.schema.FixedWidthSchema();
        FixedWidthSchemaLine schemaLine = new FixedWidthSchemaLine(2);
        schema.setLineSeparator("");

        schemaLine.addSchemaCell(new FixedWidthSchemaCell("First name", 5));
        schemaLine.addSchemaCell(new FixedWidthSchemaCell("Last name", 8));
        schema.addSchemaLine(schemaLine);

        Reader reader = new StringReader(toParse);

        Document doc = build(reader, schema);

        assertEquals("Jonas", LineUtils.getStringCellValue(doc.getLine(0), "First name"));
        assertEquals("Stenberg", LineUtils.getStringCellValue(doc.getLine(0), "Last name"));

        assertEquals("Frida", LineUtils.getStringCellValue(doc.getLine(1), "First name"));
        assertEquals("Stenberg", LineUtils.getStringCellValue(doc.getLine(1), "Last name"));
    }

    private Document build(Reader reader, FixedWidthSchema schema) throws IOException {
        FixedWidthParser parser = new FixedWidthParser(reader, schema, new TextParseConfig());
        DocumentBuilderLineEventListener builder = new DocumentBuilderLineEventListener();
        parser.parse(builder, new ExceptionErrorEventListener());
        return builder.getDocument();
    }


}
