package org.jsapar.parse.cell;

import org.jsapar.model.BooleanCell;
import org.jsapar.model.Cell;
import org.jsapar.text.BooleanFormat;

import java.text.Format;
import java.text.ParseException;
import java.util.Locale;

/**
 * Parses boolean values into {@link Cell} objects
 */
public class BooleanCellFactory implements CellFactory {
    private final static BooleanFormat defaultFormat = new BooleanFormat();

    @Override
    public Cell makeCell(String name, String value, Format format) throws ParseException {
        if (format == null)
            format = defaultFormat;
        return new BooleanCell(name, (Boolean) format.parseObject(value));
    }

    @Override
    public Format makeFormat(Locale locale) {
        return defaultFormat;
    }

    @Override
    public Format makeFormat(Locale locale, String pattern) {
        String[] aTrueFalse = pattern.trim().split("\\s*;\\s*");
        if (aTrueFalse.length < 1 || aTrueFalse.length > 2)
            throw new IllegalArgumentException(
                    "Boolean format pattern should only contain two fields separated with ; character");
        return new BooleanFormat(aTrueFalse[0].split("\\s*\\|\\s*"), aTrueFalse.length == 2 ? aTrueFalse[1].split("\\s*\\|\\s*") : new String[]{""});
    }
}
