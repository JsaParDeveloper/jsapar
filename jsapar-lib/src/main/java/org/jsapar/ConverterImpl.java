package org.jsapar;

import java.io.Reader;
import java.io.Writer;

import org.jsapar.exceptions.ComposeException;
import org.jsapar.exceptions.ConvertException;
import org.jsapar.exceptions.JsaParException;
import org.jsapar.exceptions.ParseException;
import org.jsapar.types.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The implementation class for the Converter tool of the JsaPar library.<br>
 * <br>
 * Note: This class is not part of the JsaPar API and should remain package-private.
 * 
 * @version $Revision: 1.0 $
 * @author JsaPar Developer
 */
class ConverterImpl extends Tool implements Converter {
    /**
     * The logger for this class.
     */
    private static final Logger log = LoggerFactory.getLogger(ConverterImpl.class);
    /**
     * The parser that is used for parsing the source document.
     */
    private Parser parser = null;
    /**
     * The composer that is used for composing the destination document.
     */
    private Composer composer = null;
    
    /**
     * Private constructor prevents instantiation from other classes.
     */
    @SuppressWarnings("unused")
    private ConverterImpl() {
        // Intentionally left blank.
    }
    
    /**
     * Package-private constructor prevents instantiation from external classes.
     * 
     * @param aParser
     *            the parser that is used for reading the input document.
     * @param aComposer
     *            the composer that is used for writing the output document.
     */
    ConverterImpl(final Parser aParser, final Composer aComposer) {
        parser = aParser;
        composer = aComposer;
        log.trace("Instantiated a new Converter with ID = " + id + ".");
    }

    @Override
    public void setup(final String sourceSchemaName, final String destinationSchemaName) throws JsaParException {
        log.debug("Schema setup for the converter (ConverterID = " + id + ") follows after this line.");
        parser.setup(sourceSchemaName);
        composer.setup(destinationSchemaName);
        setupCompleted = true;
    }

    @Override
    public Writer convert(final Reader sourceDoc) throws ConvertException {
        Document doc = null;
        Writer writer = null;

        // check if a proper setup was done
        if (!setupCompleted) {
            throw new IllegalStateException("Composer not properly setup.");
        }
 
        // reset the error set.
        errorSet = null;
        
        // TODO ----------------------------------------------------------------
        
        // first: parse the source document into a Document object.
        try {
            doc = parser.parse(sourceDoc);
        } catch (ParseException e) {
            // TODO build a ErrorSet BEFORE an exception is thrown.
            // TODO Translate ParseException into a ConvertException and rethrow
            // the ConvertException
            e.printStackTrace();
        }
        // second: compose the Document object into a destination document.
        try {
            writer = composer.compose(doc);
        } catch (ComposeException e) {
            // TODO build a ErrorSet BEFORE an exception is thrown.
            // TODO Translate ComposeException into a ConvertException and
            // rethrow the ConvertException
            e.printStackTrace();
        }

        return writer;
    }
    
    @Override
    public String toString() {
        return "Converter ID=" + super.toString();
    }
}