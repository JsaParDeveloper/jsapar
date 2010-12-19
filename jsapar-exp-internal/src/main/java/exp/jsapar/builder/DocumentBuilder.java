package exp.jsapar.builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exp.jsapar.schema.JsaParSchema;

public class DocumentBuilder extends Builder {
    /**
     * The logger for this class.
     */
    private static final Logger log = LoggerFactory.getLogger(DocumentBuilder.class);
    /**
     * The builder for building sections.
     */
    private SectionBuilder sb = null;
    
    protected DocumentBuilder(JsaParSchema schema) {
        super(schema);
        this.sb = new SectionBuilder(schema);
    }

    // TODO How to get the Configuration object?

    // Builds the whole document
    // This class is the entry point of the building mechanism which is called by the Parser class
    // The parser class doesn't know anything about a Section- or LineBuilder class, only a
    // DocumentBuilder!
    // All lines that are read by the parser gets handed over to the DocumentBuilder! This is done
    // because the parser gets in return a Document object. Read lines are handled by the
    // DocumentBuilder and detailed builders (Section and Line builders).

    public void processFragment(String fragment) {
        sb.processFragment(fragment);
    }
}