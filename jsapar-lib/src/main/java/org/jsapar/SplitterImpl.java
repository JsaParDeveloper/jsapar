package org.jsapar;

import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsapar.exceptions.ComposeException;
import org.jsapar.exceptions.JsaParException;
import org.jsapar.exceptions.ParseException;
import org.jsapar.exceptions.SplitException;
import org.jsapar.types.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The implementation class for the Splitter tool of the JsaPar library.<br>
 * <br>
 * Note: This class is not part of the JsaPar API and should remain package-private.
 * 
 * @author JsaPar Developer
 * @version $Revision: 1.0 $
 */
class SplitterImpl extends Tool implements Splitter {
    /**
     * The logger for this class.
     */
    private static final Logger log = LoggerFactory.getLogger(SplitterImpl.class);
    /**
     * The parser that is used for parsing the source document.
     */
    private Parser parser = null;
    /**
     * The composer that is used for composing the destination document.
     */
    private Composer composer = null;
    /**
     * The names of the JsaPar schemata that are used for splitting up the source document into
     * several destination documents.
     */
    private List<String> destinationSchemaNames = null;

    /**
     * Private constructor prevents instantiation from other classes.
     */
    @SuppressWarnings("unused")
    private SplitterImpl() {
        // Intentionally left blank.
    }

    /**
     * Package-private constructor prevents instantiation from external classes.
     * 
     * @param configRef
     *            the reference to the JsaPar configuration object.
     */
    SplitterImpl(final Parser aParser, final Composer aComposer) {
        parser = aParser;
        composer = aComposer;
        log.trace("Instantiated a new Splitter with ID = " + id + ".");
    }

    @Override
    public void setup(String sourceSchemaName, String... destinationSchemataNames) throws JsaParException {
        log.debug("Schema setup for the parser used by this splitter (SplitterID = " + id
                + ") follows after this line.");
        parser.setup(sourceSchemaName);
        // The setup of the internal Composer is done in the split method because of the many
        // destination documents!
        destinationSchemaNames = Arrays.asList(destinationSchemataNames);
    }

    @Override
    public List<Writer> split(Reader sourceDoc) throws SplitException {
        Document documentToBeSplitted = null;
        Writer destinationWriter = null;
        List<Writer> destinationWriterList = new ArrayList<Writer>();

        // reset the error set.
        errorSet = null;

        // check if a proper setup was done
        if (!setupCompleted) {
            throw new IllegalStateException("Composer not properly setup.");
        }


        // TODO ---------------------------------------------------------------------------------

        // The destination schemas define the splitting process: every destination schema contains
        // the setup of the information in the source that is being discarded!

        try {
            documentToBeSplitted = parser.parse(sourceDoc);
        } catch (ParseException e1) {
            // TODO build a ErrorSet BEFORE an exception is thrown.
            // log
        }

        for (String aSchemaName : destinationSchemaNames) {
            try {
                log.debug("Schema setup for the composer used by this splitter (SplitterID = " + id
                        + ") follows after this line.");
                composer.setup(aSchemaName);
                destinationWriter = composer.compose(documentToBeSplitted);
                destinationWriterList.add(destinationWriter);
            } catch (ComposeException e) {
                // TODO build a ErrorSet BEFORE an exception is thrown.
                // log
            } catch (JsaParException e) {
                // TODO build a ErrorSet BEFORE an exception is thrown.
                // TODO JsaParSchema could not be loaded
                // Translate the JsaParException to a SplitException and throw this SplitException.
            }
        }

        return destinationWriterList;
    }

    @Override
    public String toString() {
        return "Splitter ID=" + super.toString();
    }
}