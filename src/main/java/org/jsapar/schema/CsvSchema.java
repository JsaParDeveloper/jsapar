package org.jsapar.schema;

import org.jsapar.parse.csv.CsvParser;
import org.jsapar.text.TextParseConfig;
import org.jsapar.parse.text.TextSchemaParser;

import java.io.Reader;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Defines a schema for a delimited input text. Each cell is delimited by a delimiter character sequence.
 * Lines are separated by the line separator defined by {@link Schema#getLineSeparator()}.
 * @see Schema
 * @see SchemaLine
 * @see CsvSchemaLine
 */
public class CsvSchema extends Schema implements Cloneable{

    /**
     * The schema lines
     */
    private LinkedHashMap<String, CsvSchemaLine> schemaLines = new LinkedHashMap<>();

    /**
     * Specifies the syntax while parsing and composing of quoted cells. Default is {@link QuoteSyntax#FIRST_LAST}
     */
    private QuoteSyntax quoteSyntax = QuoteSyntax.FIRST_LAST;

    /**
     * @param schemaLine the schemaLine to add
     */
    public void addSchemaLine(CsvSchemaLine schemaLine) {
        this.schemaLines.put(schemaLine.getLineType(), schemaLine);
    }

    @Override
    public boolean isEmpty() {
        return this.schemaLines.isEmpty();
    }

    @Override
    public CsvSchema clone() {
        CsvSchema schema;
        schema = (CsvSchema) super.clone();

        schema.schemaLines = new LinkedHashMap<>();
        this.stream().map(CsvSchemaLine::clone).forEach(schema::addSchemaLine);
        return schema;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jsapar.schema.Schema#toString()
     */
    @Override
    public String toString() {
        return "CsvSchema" + super.toString() +
                " schemaLines=" +
                this.schemaLines;
    }

    @Override
    public Collection<CsvSchemaLine> getSchemaLines() {
        return this.schemaLines.values();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jsapar.schema.Schema#getSchemaLine(java.lang.String)
     */
    @Override
    public Optional<CsvSchemaLine> getSchemaLine(String lineType) {
        return Optional.ofNullable(schemaLines.get(lineType));
    }

    @Override
    public int size() {
        return this.schemaLines.size();
    }

    @Override
    public Stream<CsvSchemaLine> stream() {
        return this.schemaLines.values().stream();
    }

    @Override
    public Iterator<CsvSchemaLine> iterator() {
        return schemaLines.values().iterator();
    }

    @Override
    public TextSchemaParser makeSchemaParser(Reader reader, TextParseConfig parseConfig) {
        return new CsvParser(reader, this, parseConfig);
    }

    public QuoteSyntax getQuoteSyntax() {
        return quoteSyntax;
    }

    public void setQuoteSyntax(QuoteSyntax quoteSyntax) {
        this.quoteSyntax = quoteSyntax;
    }
}
