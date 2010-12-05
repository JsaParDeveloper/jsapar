/**
 * JsaPar 2.0: A breeze of ease.
 * 
 * The JSaPar library, which stands for: Java SchemA based PArser library, is a java library that provides
 * several tools (Parser, Converter, Composer, Splitter) for flat and delimited files.<br>
 * 
 * Flat files are also known as fixed width files. Delimited files are also known as comma separated value files.
 * Because these names do not cover the whole spectrum of flat and delimited files, these names are not used
 * in this documentation.<br>
 * 
 * The concept of the JsaPar library is that a jsapar schema denotes the way a file should be parsed or written.
 * This schema is used for constructing: 
 * 1. a org.jsapar.types.Document object from a source document.
 * 2. a destination document from a org.jsapar.types.Document object.
 *    
 * The jsapar schema can be built by specifying the schema setup using:<br>
 * <ul>
 * <li>a XML document, or
 * <li>Java annotations
 * </ul>
 * The preferred way is by specifying the jsapar schema in a XML document because this is the most flexible 
 * solution available and can be modified without changing the source code files.<br>
 *  
 * <h2>Successful situations</h2> 
 * The input of the parser is a java.io.Reader object that represents the source document, which can be any of 
 * the derived classes of the java.io.Reader class. 
 * The output of the parser is in successful situations a org.jsapar.types.Document object that contains a list
 * of org.jsapar.types.Line objects which contains a list of org.jsapar.types.Cell objects.<br>
 * <br> 
 * The input of the converter is the same as for a parser. The output of the converter is in successful 
 * situations a java.io.Writer object, that represents the destination document. First the converter parses the
 * source document into an org.jsapar.types.Document object, then it composes the org.jsapar.types.Document
 * object into a Writer object, representing the destination document that can be saved to whatever 
 * destination is desirable.<br>
 * <br>
 * The input of the composer is a org.jsapar.types.Document object. The output of the composer is in successful
 * situations a java.io.Writer object, that represents the destination document. Just like the output of a 
 * converter.<br>
 * <br>
 * The input of the splitter is the same as for a parser. The output of the splitter is in successful 
 * situations a list of java.io.Writer objects, that represents the destination documents.
 * 
 * <h2>Non successful situations</h2>
 * When the parser, converter, composer or splitter cannot be successful, for whatever reason, the cause or causes
 * of the failure is stored in a ErrorSet object that can be retrieved from the parser/converter/composer/splitter
 * after an exception was received from the library. A org.jsapar.error.ErrorSet is only constructed when errors
 * or other failures arise within the parser/converter/composer/splitter.<br>
 * 
 * <h2>Document structure</h2>
 * For more information about the org.jsapar.types.Document structure and how to compose a new <tt>Document</tt>
 * object or retrieve information from a <tt>Document</tt> object, see the information provided in the 
 * org.jsapar.types package.
 * 
 *   
 * @author JsaPar Developer
 * @since 2.0 
 *
 * @see java.io.Reader
 * @see java.io.Writer
 * 
 * @see org.jsapar.Parser
 * @see org.jsapar.Converter
 * @see org.jsapar.Composer
 * @see org.jsapar.Splitter
 * 
 * @see org.jsapar.types.Document
 * @see org.jsapar.types.Line
 * @see org.jsapar.types.Cell
 * 
 * @see org.jsapar.error.ErrorSet
 */
package org.jsapar;