package exp.jsapar.builder;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exp.jsapar.schema.JsaParSchema;
import exp.jsapar.types2.Line;

public class LineBuilder extends Builder {
    /**
     * The logger for this class.
     */
    private static final Logger log = LoggerFactory.getLogger(LineBuilder.class);

    protected LineBuilder(JsaParSchema schema) {
        super(schema);
    }

    // LineBuilder detects according to the given JsaPar Schema (name), the Line that needs to be
    // constructed.
    // The LineBuilder tries to find a matching Line when a line is read from the file/stream.

    // the LineBuilder is used only for constructing Line objects from files/stream.
    // for the other way around, use a "OutputConstructor" (or whatever we want to call it) which
    // writes a Line object to a file/stream.

    // If a read line can have more than one Line definition, all possible Lines are stored in a
    // Line candidate list within the SectionBuilder.

    // Fixed Width: First try to match all possible Line combinations. Do this by reading the line
    // character by character. If a line matches a control character, that doesn't mean
    // automatically that the line is determined correctly yet. It is possible that a control
    // character is actually not a control character because the line doesn't match the definition
    // completely.


    public List<Line> buildLine(String line) {
        // TODO process the received line
        List<Line> lineList = new ArrayList<Line>();
        Line currentLine = new Line();

        lineList.add(currentLine);
        return lineList;
    }

    public List<Line> progessFragment(String fragment) {
        return buildLine(fragment);
    }
}