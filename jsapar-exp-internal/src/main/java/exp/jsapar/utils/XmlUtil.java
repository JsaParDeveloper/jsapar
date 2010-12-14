package exp.jsapar.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import exp.jsapar.exception.internal.InvalidXmlException;

import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

/**
 * Provides simple static utility methods for working with XML, like validation and mapping
 * utilities.
 * 
 * @author JsaPar Developer
 */
public final class XmlUtil {

    /**
     * Check style rule: utility classes should not have a public constructor.
     */
    private XmlUtil() {
        // Intentionally left blank.
    }

    // TODO add mapping utility when TWO jsapar documents must be mapped in
    // international situations. For example: map Swedish jsapar schema to a
    // Dutch jsapar schema. The cellnames have to be mapped in that situation!

    /**
     * TODO rewrite! Find best and quickest validation mechanism using classes available in 1.6 jvm
     * 
     * @param aLocation
     */
    public static void validateXML(final InputStream xmlDocument, final InputStream xmlSchema)
            throws InvalidXmlException {

        // TODO REALLY BIG TO DO HERE -> find a good and quick validation
        // implementation.
        // Use JAXP 1.4.3 validation in combination with Stax?

        // -------------- UNDER THIS LINE IS CRAPPY CODE -------------------

        ErrorHandler myErrorHandler = null;

        // build an XSD-aware SchemaFactory
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        // hook up org.xml.sax.ErrorHandler implementation.
        schemaFactory.setErrorHandler(myErrorHandler);

        // get the custom xsd schema describing the required format for my XML
        // files.
        Schema schemaXSD = null;
        try {
            schemaXSD = schemaFactory.newSchema(new File("myschema.xsd"));
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Create a validator capable of validating XML files according to my
        // custom schema.
        Validator validator = schemaXSD.newValidator();

        // Get a parser capable of parsing vanilla XML into a DOM tree
        DocumentBuilder parser = null;
        try {
            parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // parse the XML purely as XML and get a DOM tree representation.
        Document document = null;
        try {
            document = parser.parse(new File("myxml.xml"));
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // parse the XML DOM tree against the stricter XSD schema
        try {
            validator.validate(new DOMSource(document));
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}