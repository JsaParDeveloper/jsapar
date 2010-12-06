package org.jsapar;

import org.jsapar.exceptions.JsaParException;
import org.jsapar.schema.JsaParSchema;

/**
 * Restricted reference interface for the Configuration class. This reference interface is used
 * within the Parser, Converter, Composer and Splitter classes to prohibit these classes from
 * accessing configuration methods that should only be called by the Configuration class itself.<br>
 * 
 * The Parser, Converter, Composer and Splitter object DO need access to the Configuration class,
 * but with more restrictions.
 * 
 * @author JsaPar Developer
 * 
 * @see org.jsapar.Configuration
 */
public interface ConfigRef {

    /**
     * Loads the SchemaSource with the given 'name' and instantiates a corresponding JsaParSchema
     * object.<br>
     * 
     * The JsaParSchema object wraps the SchemaSource's name and location with the instantiated
     * Schema object, which represents the XML file located at the schemaSource's location.
     * 
     * @param name
     *            the name of the schema source.
     * @throws JsaParException
     *             TODO
     * 
     * @see org.jsapar.schema.cfg.SchemaSource
     * @see org.jsapar.schema.doc.Schema
     * @see org.jsapar.schema.JsaParSchema
     */
    public JsaParSchema loadJsaParSchema(final String name) throws JsaParException;
}
