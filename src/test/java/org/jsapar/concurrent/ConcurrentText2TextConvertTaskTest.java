package org.jsapar.concurrent;

import org.jsapar.TextComposer;
import org.jsapar.convert.ConvertTask;
import org.jsapar.error.JSaParException;
import org.jsapar.parse.text.TextParseTask;
import org.jsapar.schema.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.Assert.assertSame;

/**
 */
public class ConcurrentText2TextConvertTaskTest {
    @Test
    public void testConcurrentText2TextConverter() throws IOException, JSaParException {
        TextParseTask p = new TextParseTask(new CsvSchema(), new StringReader(""));
        TextComposer c = new TextComposer(new FixedWidthSchema(), new StringWriter());
        ConvertTask instance = new ConcurrentText2TextConverter(p, c);
        assertSame(p, instance.getParseTask());
        assertSame(c, instance.getComposer());
    }

    @Test
    public void testConvert() throws IOException, JSaParException {
        String toParse = "Jonas Stenberg \nFrida Bergsten ";
        org.jsapar.schema.FixedWidthSchema inputSchema = new org.jsapar.schema.FixedWidthSchema();
        FixedWidthSchemaLine inputSchemaLine = new FixedWidthSchemaLine("Person");
        inputSchemaLine.addSchemaCell(new FixedWidthSchemaCell("First name", 6));
        inputSchemaLine.addSchemaCell(new FixedWidthSchemaCell("Last name", 9));
        inputSchema.addSchemaLine(inputSchemaLine);
        inputSchema.setLineSeparator("\n");

        org.jsapar.schema.CsvSchema outputSchema = new org.jsapar.schema.CsvSchema();
        CsvSchemaLine outputSchemaLine = new CsvSchemaLine("Person");
        outputSchemaLine.addSchemaCell(new CsvSchemaCell("First name"));
        outputSchemaLine.addSchemaCell(new CsvSchemaCell("Last name"));
        outputSchemaLine.setCellSeparator(";");
        outputSchema.addSchemaLine(outputSchemaLine);
        outputSchema.setLineSeparator("|");

        StringWriter writer = new StringWriter();
        StringReader reader = new StringReader(toParse);
        ConvertTask convertTask = new ConcurrentText2TextConverter(inputSchema, reader, outputSchema, writer);
        convertTask.execute();
        reader.close();
        writer.close();
        String sResult = writer.getBuffer().toString();
        String sExpected = "Jonas;Stenberg|Frida;Bergsten|";

        Assert.assertEquals(sExpected, sResult);

    }

}