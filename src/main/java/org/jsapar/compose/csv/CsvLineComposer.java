package org.jsapar.compose.csv;

import org.jsapar.JSaParException;
import org.jsapar.compose.LineComposer;
import org.jsapar.model.Cell;
import org.jsapar.model.CellType;
import org.jsapar.model.Line;
import org.jsapar.model.StringCell;
import org.jsapar.schema.CsvSchemaCell;
import org.jsapar.schema.CsvSchemaLine;
import org.jsapar.schema.SchemaCellFormat;
import org.jsapar.schema.SchemaLine;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;

/**
 * Created by stejon0 on 2016-01-30.
 */
public class CsvLineComposer implements LineComposer {

    Writer        writer;
    CsvSchemaLine schemaLine;
    private String lineSeparator;
    CsvCellComposer cellComposer;
    boolean firstRow=true;

    public CsvLineComposer(Writer writer, CsvSchemaLine schemaLine, String lineSeparator) {
        this.writer = writer;
        this.schemaLine = schemaLine;
        this.lineSeparator = lineSeparator;
        this.cellComposer = new CsvCellComposer(writer);
    }

    @Override
    public void compose(Line line) throws IOException {
        if(firstRow && schemaLine.isFirstLineAsSchema()){
            composeHeaderLine();
            writer.write(lineSeparator);
        }
        firstRow = false;
        String sCellSeparator = schemaLine.getCellSeparator();

        Iterator<CsvSchemaCell> iter = schemaLine.getSchemaCells().iterator();
        for (int i = 0; iter.hasNext(); i++) {
            CsvSchemaCell schemaCell = iter.next();
            Cell cell = line.getCell(schemaCell.getName());
            char quoteChar = schemaLine.getQuoteChar();

            cellComposer.compose(cell, schemaCell, sCellSeparator, quoteChar);

            if (iter.hasNext())
                writer.write(sCellSeparator);
        }
    }

    /**
     * Writes header line if first line is schema.
     *
     * @throws IOException
     * @throws JSaParException
     */
    public void composeHeaderLine() throws IOException {
        CsvSchemaLine unformattedSchemaLine = schemaLine.clone();
        unformattedSchemaLine.setFirstLineAsSchema(false);
        for (CsvSchemaCell schemaCell : unformattedSchemaLine.getSchemaCells()) {
            schemaCell.setCellFormat(new SchemaCellFormat(CellType.STRING));
        }
        CsvLineComposer headerLineComposer = new CsvLineComposer(writer, unformattedSchemaLine, lineSeparator);
        headerLineComposer.compose(this.buildHeaderLineFromSchema(unformattedSchemaLine));
    }

    /**
     * @return
     * @throws JSaParException
     */
    private Line buildHeaderLineFromSchema(CsvSchemaLine headerSchemaLine)  {
        Line line = new Line();

        for (CsvSchemaCell schemaCell : headerSchemaLine.getSchemaCells()) {
            line.addCell(new StringCell(schemaCell.getName(), schemaCell.getName()));
        }

        return line;
    }

}