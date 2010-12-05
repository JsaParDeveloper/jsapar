/**
 * The JSaPar library works with a document object structure (see {@see org.jsapar.types.Document} that holds
 * the source or destination data contained within the files that need to be parsed, composed, converted or 
 * splitted. 
 * A jsapar schema describes the structure of the source or destination document, supplying the JsaPar library
 * with the meta-data of the data found in these files/streams. The org.jsapar.types.Document object only holds
 * the data of the source or destination document(s).  
 *  
 * The org.jsapar.types.Document object contains a list of org.jsapar.types.Section objects. 
 * The org.jsapar.types.Section object contains a list of org.jsapar.types.Line objects.
 * The org.jsapar.types.Line object contains a list of org.jsapar.types.Cell objects.
 * The org.jsapar.types.Cell objects hold the actual information of the data: types and values.
 * 
 * The org.jsapar.types.Document has no knowledge of the meta-data contained within the source and destination
 * documents. For example: the information whether this Document was constructed out of a flat file or delimited
 * file is NOT stored in the Document. Only the library itself knows what the type of document the source and 
 * destination documents are, because the jsapar schema defines this for the particular file(s)/stream(s).<br> 
 *
 * <h2>Adding data into a Document</h2>
 * TODO
 * 
 * <h2>Retrieving data from a Document</h2>
 * TODO
 * 
 * <h2>Inserting data into a Document</h2>
 * TODO
 * 
 * <h2>Replacing data in a Document</h2>
 * TODO
 *
 * <h2>Removing data from a Document</h2>
 * TODO
 * 
 * @see org.jsapar.types.Document
 * @see org.jsapar.types.Section
 * @see org.jsapar.types.Line
 * @see org.jsapar.types.Cell
 * 
 * @author JsaPar Developer
 */
package org.jsapar.types;