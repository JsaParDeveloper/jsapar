package org.jsapar.examples.ch01;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.jsapar.Configuration;
import org.jsapar.Parser;
import org.jsapar.Toolbox;
import org.jsapar.error.ErrorSet;
import org.jsapar.exceptions.JsaParException;
import org.jsapar.exceptions.ParseException;
import org.jsapar.types.Document;

/**
 * Example for setting up the parser tool. This example shows how to setup a parser tool and use it
 * to parse a simple delimited file into a Document object.
 * 
 * @author JsaPar Developer
 */
public class SetupParserExample {

    /**
     * @param args
     *            the command line arguments (not used in this example).
     */
    public static void main(String[] args) {
        // FIXME add input/output files (use in-memory files?)
        Parser parser = null;
        Reader srcDoc = null;
        Document doc = null;
        ErrorSet errorSet = null;

        // define schemata for configuration. Can also be defined in jsapar.cfg.xml file.
        Map<String, String> map = new HashMap<String, String>();
        map.put("csv", "/schemata/bank-schema-csv.xml");
        map.put("asc", "/schemata/bank-schema-asc.xml");
        map.put("bri", "/schemata/bank-schema-bri.xml");

        Configuration cfg = Configuration.getConfiguration();
        Toolbox toolbox = cfg.getToolbox();

        try {
            cfg.configure();
            cfg.addSchemaSources(map);
        } catch (JsaParException e1) {
            e1.printStackTrace();
        }

        parser = toolbox.getParser();

        try {
            parser.setup("csv");
            doc = parser.parse(srcDoc);
        } catch (ParseException e) {
            errorSet = e.getErrorSet();
        } catch (JsaParException e) {
            e.printStackTrace();
        }

        System.out.println(errorSet.explain());
        System.out.println(doc.toString());
    }
}