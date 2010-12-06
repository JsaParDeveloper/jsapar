package org.jsapar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import org.jsapar.exceptions.JsaParException;
import org.jsapar.exceptions.ParseException;
import org.jsapar.schema.JsaParSchema;
import org.jsapar.types.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Parser implementation class for parsing a source document according to the defined JsaPar
 * schema. The source document can be a file or a stream.<br>
 * <br>
 * Note: This class is not part of the JsaPar API and should remain package-private.
 * 
 * @version $Revision: 1.0 $
 * @author JsaPar Developer
 */
class ParserImpl extends Tool implements Parser {
    /**
     * The logger for this class.
     */
    private static final Logger log = LoggerFactory.getLogger(ParserImpl.class);
    /**
     * The reference to the JsaPar configuration object.
     */
    private ConfigRef cfgRef = null;
    /**
     * The current JsaPar schema that is used for composing the output document.
     */
    private JsaParSchema currentSchema = null;

    /**
     * Private constructor prevents instantiation from other classes.
     */
    @SuppressWarnings("unused")
    private ParserImpl() {
        // Intentionally left blank.
    }

    /**
     * Package-private constructor prevents instantiation from external classes.
     * 
     * @param configRef
     *            the reference to the JsaPar configuration object.
     */
    ParserImpl(ConfigRef configRef) {
        cfgRef = configRef;
        log.trace("Instantiated a new Parser with ID = " + id + ".");
    }

    @Override
    public void setup(final String sourceSchemaName) throws JsaParException {
        String msg = "Setup of schema is %s (ParserID = " + id + "; SchemaName = " + sourceSchemaName + ")";
        currentSchema = cfgRef.loadJsaParSchema(sourceSchemaName);
        if (currentSchema != null) {
            log.debug(String.format(msg, "successful."));
        }

        // before:
        // try {
        // currentSchema = cfgRef.loadJsaParSchema(sourceSchemaName);
        // if (currentSchema != null) {
        // log.debug(String.format(msg, "successful."));
        // }
        // } catch (SchemaNotLoadedException e) {
        // log.debug(String.format(msg, "NOT successful."));
        // throw new JsaParException(e.getMessage());
        // }
    }

    @Override
    public Document parse(final Reader sourceDoc) throws ParseException {
        String s = null;
        Document doc = null;
        BufferedReader br = null;

        // reset the error set.
        errorSet = null;

        // check if a proper setup was done
        if (!setupCompleted) {
            throw new IllegalStateException("Composer not properly setup.");
        }

        // checking reader not null
        if (sourceDoc == null) {
            throw new NullPointerException("Source document cannot be NULL.");
            // FIXME replace into utility class
        }

        // buffer the input
        br = new BufferedReader(sourceDoc);


        // for every error that occurs: keep track of these errors using an errorset!
        // after the number of errors exceeded the specified maximum number of errors
        // throw an ParseException(errorSet) <- which includes the errorset!

        // TODO --------------------------------------------------------------------------------

        // you can set the error set when the first error occurred. just create a new ErrorSet.
        // get the configuration to find out how many errors may occur before an exception must be
        // thrown.
        // add all the errors to the errorset when upper limit of errors occurred? -> create a
        // ParseException, then set the ErrorSet in this exception.

        // use a CharBuffer for the parseBuffer

        // get the line.separator from the system or from the schema: we are going to need it.

        // start reading line-by-line
        try {
            while ((s = br.readLine()) != null) {
                // log line to slf4j
                log.debug(s);
                // 1. Put the read line into a pipedbuffer and notify the other thread of it.
                // FIXME for now: just pretend we did that ;)
                // TODO parts 2 till 4 should actually be handled by the builder Thread.
                //
                // 2. Read the line from the pipedbuffer and hand it over to the DocumentBuilder
                // class.
                // 3. find out what kind of linetype this is and start constructing a Document
                // object
                // 4. repeat steps 2 & 3 until the EOF is reached for the source document

                // 5. Get the Document object from the builder
                // 6. clean up the mess and kill the builder thread.
                // 7. return the Document.
                // FIXME no threading for now
            }

        } catch (IOException e) {
            // TODO translate IOException
            e.printStackTrace();
        }

        // FIXME should we take into account the file.encoding and
        // file.separator as well?
        // See Thinking in Java 4th ed. pages: 950 - 953.
        // Buffer, CharBuffer
        // See pages: 962 - 965.

        // while (sourceDoc.) {
        //
        // }

        // when parse exception or document exception: build errorset first!

        return doc;
    }

    @Override
    public String toString() {
        return "Parser ID=" + super.toString();
    }
}