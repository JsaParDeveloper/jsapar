package org.jsapar;

import java.io.Reader;
import java.io.Writer;

import org.jsapar.exceptions.ConvertException;
import org.jsapar.exceptions.JsaParException;

/**
 * The Converter interface for converting a source document into a destination document according to
 * the defined JsaPar input and output schemas. The source and destination documents can be a file
 * or a stream.
 * 
 * @version $Revision: 1.0 $
 * @author JsaPar Developer
 */
public interface Converter {

    /**
     * Sets up the converter with the selected jsapar schemata configuration.
     * 
     * @param sourceSchemaName
     *            the name of the source schema that specifies which schema from the configuration
     *            should be used by the converter for parsing the source.
     * 
     * @param destinationSchemaName
     *            the name of the destination schema that specifies which schema from the
     *            configuration should be used by the converter for composing the destination
     *            document.
     * @throws JsaParException
     *             TODO
     */
    public void setup(final String sourceSchemaName, final String destinationSchemaName) throws JsaParException;

    /**
     * Converts a source document according to a source schema into a destination document according
     * to a destination schema.
     * 
     * @param sourceDoc
     *            the source document as a {@link java.io.Reader}
     * @return the destination document as a {@link java.io.Writer}
     * @throws ConvertException
     *             the exception when the source document could not be parsed correctly, or when the
     *             destination document could not be written correctly.
     * @throws IllegalStateException
     *             thrown when the {@code convert} method was called before the {@code setup} method
     *             was called.
     */
    public Writer convert(final Reader sourceDoc) throws ConvertException;
}
