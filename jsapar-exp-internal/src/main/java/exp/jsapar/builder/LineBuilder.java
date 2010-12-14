package exp.jsapar.builder;

import java.util.ArrayList;
import java.util.List;

import exp.jsapar.schema.JsaParSchema;
import exp.jsapar.types2.Line;

public class LineBuilder {

    JsaParSchema jsaparSchema = null;

    public LineBuilder(String schemaName) {
        this.jsaparSchema = new JsaParSchema(schemaName);
    }

    // LineBuilder detects according to the given JsaPar Schema (name), the Line that needs to be
    // constructed.
    // The LineBuilder tries to find a matching Line when a line is read from the file/stream.

    // the LineBuilder is used only for constructing Line objects from files/stream.
    // for the other way around, use a "OutputConstructor" (or whatever we want to call it) which
    // writes a Line object to a file/stream.

    // If a read line can have more than one Line definition, all possible Lines are stored in a
    // Line candidate list within the SectionBuilder.

    public List<Line> buildLine(String line) {
        // TODO process the received line
        List<Line> lineList = new ArrayList<Line>();
        lineList.add(new Line());
        return lineList;
    }

}
