package exp.jsapar;

import java.io.*;
import java.net.URL;

import exp.jsapar.builder.DocumentBuilder;
import exp.jsapar.schema.JsaParSchema;

/**
 * SimpleFileReader is an simple utility for testing the DocumentBuilder class. The moment the
 * DocumentBuilder works fine, this utility can be thrown away.
 */
public class TestFileReader {
    private static final String TEST_FILE_LOCATION = "/testfile-csv.txt";
    private static TestFileReader sfr = null;
    private BufferedReader breader = null;
    private FileReader freader = null;
    private BufferedReader bsreader = null;
    private FileReader fsreader = null;
    private DocumentBuilder docBuilder = null;

    public static void main(String[] args) {
        sfr = new TestFileReader();
        sfr.readFile();
    }

    // ------------------------------------------------------------------------

    private TestFileReader() {
        docBuilder = null;// FIXME new DocumentBuilder();
        URL url = this.getClass().getResource(TEST_FILE_LOCATION);
        System.out.println(url.getFile());
        try {
            // read csv source
            freader = new FileReader(url.getFile());
            breader = new BufferedReader(freader);
            
            // read jsapar schema
            // TODO use Configuration in other project for reading schema
            JsaParSchema schema = new JsaParSchema("flightschema");
            
            // act as if the schema was successfully read and 
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void readFile() {
        String fragment = null;
        int index = 0;
        try {
            while ((fragment = breader.readLine()) != null) {
                System.out.printf("Read line [%02d]: %s \n", index, fragment);
                docBuilder.processFragment(fragment);
                index++;
            }
            breader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}