package org.jsapar;

import java.io.Writer;

import org.jsapar.exceptions.ComposeException;
import org.jsapar.exceptions.JsaParException;
import org.jsapar.schema.JsaParSchema;
import org.jsapar.types.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The implementation class for the Composer tool of the JsaPar library.<br>
 * <br>
 * Note: This class is not part of the JsaPar API and should remain package-private.
 * 
 * @version $Revision: 1.0 $
 * @author JsaPar Developer
 */
class ComposerImpl extends Tool implements Composer {
    /**
     * The logger for this class.
     */
    private static final Logger log = LoggerFactory.getLogger(ComposerImpl.class);
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
    private ComposerImpl() {
        // Intentionally left blank.
    }

    /**
     * Package-private constructor prevents instantiation from external classes.
     * 
     * @param configRef
     *            the reference to the JsaPar configuration object.
     */
    ComposerImpl(ConfigRef configRef) {
        cfgRef = configRef;
        log.trace("Instantiated a new Composer with ID = " + id + ".");
    }

    @Override
    public void setup(String destinationSchemaName) throws JsaParException {
        String msg = "Setup of schema is %s (ComposerID = " + id + "; SchemaName = " + destinationSchemaName + ")";
        currentSchema = cfgRef.loadJsaParSchema(destinationSchemaName);
        if (currentSchema != null) {
            log.debug(String.format(msg, "successful."));
        }
        
        // before:
//        try {
//            currentSchema = cfgRef.loadJsaParSchema(destinationSchemaName);
//            if (currentSchema != null) {
//                log.debug(String.format(msg, "successful."));
//            }
//        } catch (SchemaNotLoadedException e) {
//            // TODO build a ErrorSet BEFORE an exception is thrown.
//            log.debug(String.format(msg, "NOT successful."));
//            throw new JsaParException(e.getMessage());
//        }
    }

    @Override
    public Writer compose(Document sourceDoc) throws ComposeException {
        Writer writer = null;

        // check if a proper setup was done
        if (!setupCompleted) {
            throw new IllegalStateException("Composer not properly setup.");
        }

        // reset the error set.
        errorSet = null;
        
        // TODO -------------------------------------------------------------------------------------
        
        // TODO build a ErrorSet BEFORE an exception is thrown.
        return writer;
    }
    
    @Override
    public String toString() {
        return "Composer ID=" + super.toString();
    }
}