package exp.jsapar.builder;

public class DocumentBuilder {
    // Builds the whole document
    // This class is the entry point of the building mechanism which is called by the Parser class
    // The parser class doesn't know anything about a Section- or LineBuilder class, only a
    // DocumentBuilder!
    // All lines that are read by the parser gets handed over to the DocumentBuilder! This is done
    // because the parser gets in return a Document object. Read lines are handled by the
    // DocumentBuilder and detailed builders (Section and Line builders).
}
