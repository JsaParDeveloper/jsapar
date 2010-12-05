/**
 * TODO 
 * 
 * A jsapar schema describes the structure of the source or destination document, supplying the JsaPar library
 * with the meta-data of the data found in these files/streams. The org.jsapar.types.Document object only holds
 * the data of the source or destination document(s).  
 * 
 * TODO
 * Unmarchalling of the JsaPar schema will be executed/processed when the file/schema is being setup() within a
 * tool. Otherwise it will become a heavy load on the Configuration class when a lot of files/schemas are defined
 * in the config file. Validation must be done in the configuration class before the user starts using the 
 * library.
 * 
 * @author JsaPar Developer
 */
package org.jsapar.schema;