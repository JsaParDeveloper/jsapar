package org.jsapar;

import java.io.Reader;
import java.io.Writer;
import java.util.List;

import org.jsapar.exceptions.JsaParException;
import org.jsapar.exceptions.SplitException;

/**
 * The Splitter interface for splitting a source document into several destination documents
 * according to the defined source and destination JsaPar schemata. The destination documents can be
 * a file or a stream.
 * 
 * @version $Revision: 1.0 $
 * @author JsaPar Developer
 */
public interface Splitter {

    /**
     * Sets up the splitter with the selected jsapar schemata configurations.
     * 
     * @param sourceSchemaName
     *            the name of the schema that specifies which schema from the configuration should
     *            be used by the splitter for parsing the source.
     * @param destinationSchemataNames
     *            the names of the schemata that specifies which schemata from the configuration
     *            should be used by the splitter for constructing the destination documents.
     * @throws JsaParException
     *             TODO
     */
    public void setup(final String sourceSchemaName, final String... destinationSchemataNames) throws JsaParException;

    /**
     * Splits a given source document (e.g. a File or a Stream) into several destination documents
     * according to the JsaPar schemata. Returns the splitted documents in a list of Writer objects.
     * 
     * @param sourceDoc
     *            the source document that is parsed into several destination documents.
     * @return a list of Writer objects containing the destination documents.
     * @throws SplitException
     *             the exception when the source document could not be splitted correctly into the
     *             destination documents.
     * @throws IllegalStateException
     *             thrown when the {@code split} method was called before the {@code setup} method
     *             was called.
     */
    public List<Writer> split(final Reader sourceDoc) throws SplitException;
}