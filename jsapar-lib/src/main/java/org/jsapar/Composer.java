package org.jsapar;

import java.io.Writer;

import org.jsapar.exceptions.ComposeException;
import org.jsapar.exceptions.JsaParException;
import org.jsapar.types.Document;

/**
 * The Composer interface for composing a destination document from a org.jsapar.types.Document
 * according to the defined JsaPar schema. The destination document can be a file or a stream.
 * 
 * @version $Revision: 1.0 $
 * @author JsaPar Developer
 */
public interface Composer {

    /**
     * Sets up the composer with the selected jsapar schemata configuration.
     * 
     * @param destinationSchemaName
     *            the name of the schema that specifies which schema from the configuration should
     *            be used by the composer for composing the destination document.
     * @throws JsaParException
     *             TODO
     */
    public void setup(final String destinationSchemaName) throws JsaParException;

    /**
     * Composes a <code>Document</code> object into a destination document according to a JsaPar
     * schema.
     * 
     * @param sourceDocument
     *            the source document as a {@link org.jsapar.types.Document}
     * @return the destination document as a {@link java.io.Writer}
     * @throws ComposeException
     *             the exception when the destination document could not be written correctly.
     * @throws IllegalStateException
     *             thrown when the {@code compose} method was called before the {@code setup} method
     *             was called.
     */
    public Writer compose(final Document sourceDoc) throws ComposeException;
}