package org.jsapar;

import java.io.Reader;

import org.jsapar.exceptions.JsaParException;
import org.jsapar.exceptions.ParseException;
import org.jsapar.types.Document;

/**
 * The Parser interface for parsing a source document according to the defined JsaPar schema. The
 * source document can be a file or a stream.
 * 
 * @version $Revision: 1.0 $
 * @author JsaPar Developer
 */
public interface Parser {

    /**
     * Sets up the parser with the selected jsapar schema configuration.
     * 
     * @param sourceSchemaName
     *            the name of the schema that specifies which schema from the configuration should
     *            be used by the parser when parsing the source.
     * @throws JsaParException
     *             TODO
     */
    public void setup(final String sourceSchemaName) throws JsaParException;

    /**
     * Parses a given source document (e.g. a File or a Stream) and returns a JsaPar
     * <code>Document</code> object.
     * 
     * @param sourceDoc
     *            the source document that is parsed into a <code>Document</code> object.
     * 
     * @return a JsaPar <code>Document</code> object containing the content of the source document.
     * @throws ParseException
     *             thrown when the source document could not be parsed correctly.
     * @throws IllegalStateException
     *             thrown when the {@code parse} method was called before the {@code setup} method
     *             was called.
     */
    public Document parse(final Reader sourceDoc) throws ParseException;
}