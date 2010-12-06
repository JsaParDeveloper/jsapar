package org.jsapar.examples.ch01;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import org.jsapar.Configuration;
import org.jsapar.Parser;
import org.jsapar.Toolbox;
import org.jsapar.exceptions.JsaParException;
import org.jsapar.exceptions.ParseException;
import org.jsapar.types.Document;

/**
 * Example for setting up the library.
 * 
 * @author JsaPar Developer
 */
public class SetupLibraryExample {

    // TODO fix this example

    /**
     * @param args
     *            the command line arguments (not used in this example).
     */
    public static void main(String[] args) {
        // read file to be parsed
        Reader sourceDoc = null;
        Reader jsaparCfg = null;
        Document document = null;
        
        try {
            sourceDoc = new FileReader("delimited01.txt");
            jsaparCfg = new FileReader("jsapar.cfg.xml");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        // configure the JsaPar library, see jsapar.cfg.xml for details
        Configuration cfg = Configuration.getConfiguration();
        try {
            cfg.configure(jsaparCfg);
        } catch (JsaParException e) {
            e.printStackTrace();
        }
        // get the toolbox from the configuration
        Toolbox toolbox = cfg.getToolbox();

        // get a parser tool from the toolbox
        Parser p = toolbox.getParser();
        
        
        try {
            p.setup("delimited01");
        } catch (JsaParException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        try {
            document = p.parse(sourceDoc);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        
        System.out.println(document); // FIXME get real Document from experimental playground
    }
}
