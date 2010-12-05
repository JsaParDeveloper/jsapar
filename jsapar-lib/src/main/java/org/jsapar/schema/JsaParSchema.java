package org.jsapar.schema;

import org.jsapar.schema.cfg.SchemaSource;
import org.jsapar.schema.doc.Schema;

/**
 * Wrapper class for a JsaPar schema. Combines the generated SchemaSource class from the
 * configuration XML schema with the generated Schema from the document XML schema.
 * 
 * @author JsaPar Developer
 * 
 * @see org.jsapar.schema.cfg.SchemaSource
 * @see org.jsapar.schema.doc.Schema
 */
public class JsaParSchema {
    /**
     * The jsapar schema source which indicates the name and location of the jsapar schema file.
     */
    private SchemaSource schemaSource;
    /**
     * The object representation of the jsapar schema source.
     */
    private Schema schema;

    /**
     * Constructs a JsaParSchema object with the name and location of the jsapar XML source.<br>
     * <br>
     * This constructor is used when the client code wants to add an extra jsapar XML source that
     * was not defined in the JsaPar configuration file (jsapar.cfg.xml).
     * 
     * @param name
     *            the name of the jsapar XML source.
     * @param location
     *            the location of the jsapar XML source.
     */
    public JsaParSchema(final String name, final String location) {
        schemaSource = new SchemaSource();
        this.schemaSource.setName(name);
        this.schemaSource.setLocation(location);
        /*
         * The Schema is not created at THIS point, because at this moment it is not clear if the
         * JsaPar schema is going to be used anyway.
         */
    }

    /**
     * Loads and returns the object representation of the jsapar schema source.<br>
     * <br>
     * When this method is called for the first time, the object representation is constructed from
     * the source location specified in the schema source object which is also present in this
     * class.
     * 
     * @return the constructed Schema object representing the loaded jsapar schema source, or
     *         <code>null</code> when Schema could not be constructed.
     */
    public Schema getSchemaTypeInstance() {
        if (schema == null) {
            schema = loadSchemaTypeFromLocation();
            // TODO
            // read the location and make an object representation of the jsapar
            // schema source.
        }
        return schema;
    }

    /**
     * Loads the source. TODO
     * 
     * @return
     */
    private Schema loadSchemaTypeFromLocation() {

        // TODO
        // 1. load the source from the location
        // loadSourceAsStream(schemaSource.getLocation());
        // 2. unmarshal the document into an object
        // 3. return the object.
        return new Schema();
    }
}