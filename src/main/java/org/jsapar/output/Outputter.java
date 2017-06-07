/** 
 * Copyright: Jonas Stenberg
 */
package org.jsapar.output;

import java.io.IOException;
import java.util.Iterator;

import org.jsapar.Document;
import org.jsapar.JSaParException;
import org.jsapar.Line;
import org.jsapar.schema.CsvSchemaLine;
import org.jsapar.schema.Schema;

/**
 * This class contains methods for transforming a Document into an output. E.g. if you want to write
 * the Document to a file you should first set the schema, then use a {@link java.io.FileWriter} and
 * call the {@link #output(Document, java.io.Writer)} method.
 * 
 * @author Jonas Stenberg
 * 
 */
public class Outputter {
    private Schema schema;

    /**
     * Creates an Outputter with a schema.
     * 
     * @param schema
     */
    public Outputter(Schema schema) {
        this.schema = schema;
    }

    /**
     * Writes the document to a {@link java.io.Writer} according to the schemas of this outputter.
     * Note that you have to add at least one schema to the instance of Outputter before calling
     * this method.
     * 
     * @param document
     * @param writer
     * @throws JSaParException
     */
    public void output(Document document, java.io.Writer writer) throws JSaParException {
        output(document.getLineIterator(), writer);
    }

    /**
     * Writes the document to a {@link java.io.Writer} according to the schemas of this outputter. Note that you have to
     * add at least one schema to the instance of Outputter before calling this method.
     * 
     * @param lineIterator
     *            An iterator that iterates over a collection of lines. Can be used to build lines on-the-fly if you
     *            don't want to store them all in memory.
     * @param writer
     * @throws JSaParException
     */
    public void output(Iterator<Line> lineIterator, java.io.Writer writer) throws JSaParException {
        try {
            schema.outputBefore(writer);
            schema.output(lineIterator, writer);
            schema.outputAfter(writer);
            if (!lineIterator.hasNext())
                return;
        } catch (IOException e) {
            throw new OutputException("Failed to write to buffert.", e);
        }
    }    
    
    /**
     * Writes the single line to a {@link java.io.Writer} according to the line type of the line.
     * The line is terminated by the line separator.
     * 
     * @param line
     * @param writer
     * @throws JSaParException
     */
    public void outputLineLn(Line line, java.io.Writer writer) throws JSaParException {
        try {
            if(!schema.outputLineLn(line, writer))
                throw new OutputException("Line type [" + line.getLineType() + "] not found within schema.");
        } catch (IOException e) {
            throw new OutputException("Failed to write line.", e);
        }
    }

    /**
     * Writes the single line to a {@link java.io.Writer} according to the line type of the supplied
     * line. Note that the line separator is not written by this method.
     * 
     * @param line
     * @param writer
     * @throws JSaParException
     */
    public void outputLine(Line line, java.io.Writer writer) throws JSaParException {
        try {
            if(!schema.outputLine(line, writer))
                throw new OutputException("Line type [" + line.getLineType() + "] not found within schema.");
        } catch (IOException e) {
            throw new OutputException("Failed to write line.", e);
        }
    }


    /**
     * Writes the single line to a {@link java.io.Writer} according to the supplied schema for the
     * supplied line. Note that the this is a static method and does not use the member schema of an
     * instance of Outputter.
     * 
     * @param line
     * @param schema
     * @param lineNumberForSchema
     *            The line number for this schema. If you use multiple schemas in sequence, the
     *            number should only count the number of lines written with the supplied schema.
     * @param writer
     * @throws JSaParException
     */
    public static void outputLine(Line line, Schema schema, long lineNumberForSchema, java.io.Writer writer)
            throws JSaParException {
        try {
            schema.outputLine(line, lineNumberForSchema, writer);
        } catch (IOException e) {
            throw new OutputException("Failed to write to buffert.", e);
        }
    }

    /**
     * Writes the single line to a {@link java.io.Writer} according to the member schema of this
     * instance.
     * 
     * @param line
     * @param lineNumber
     *            The line number for this schema. 
     * @param writer
     * @throws JSaParException
     */
    public void outputLine(Line line, long lineNumber, java.io.Writer writer) throws JSaParException {
        try {
            this.schema.outputLine(line, lineNumber, writer);
        } catch (IOException e) {
            throw new OutputException("Failed to write to buffert.", e);
        }
    }

    /**
     * Writes the header line if the first line is schema.
     * 
     * @param schemaLine
     * @param writer
     * @throws JSaParException
     */
    public void outputCsvHeaderLine(String lineType, java.io.Writer writer) throws JSaParException {
        outputCsvHeaderLine((CsvSchemaLine)this.schema.getSchemaLine(lineType), writer);
    }
    
    
    /**
     * Writes the header line if the first line is schema.
     * 
     * @param schemaLine
     * @param writer
     * @throws JSaParException
     */
    public void outputCsvHeaderLine(CsvSchemaLine schemaLine, java.io.Writer writer) throws JSaParException {
        if (!schemaLine.isFirstLineAsSchema())
            throw new JSaParException("The schema line is not of type where first line is schema.");

        try {
            schemaLine.outputHeaderLine(writer);
        } catch (IOException e) {
            throw new OutputException("Failed to write to buffert.", e);
        }
    }

    /**
     * @return the schema
     */
    public Schema getSchema() {
        return schema;
    }

    /**
     * @param schema the schema to set
     */
    public void setSchema(Schema schema) {
        this.schema = schema;
    }

}