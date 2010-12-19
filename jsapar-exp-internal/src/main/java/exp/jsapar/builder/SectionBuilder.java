package exp.jsapar.builder;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exp.jsapar.schema.JsaParSchema;
import exp.jsapar.types2.Line;

public class SectionBuilder extends Builder {
    /**
     * The logger for this class.
     */
    private static final Logger log = LoggerFactory.getLogger(SectionBuilder.class);
    /**
     * The builder for building lines.
     */
    private LineBuilder lb = null;

    protected SectionBuilder(JsaParSchema schema) {
        super(schema);
        this.lb = new LineBuilder(schema);
    }

    // Theory only:
    // a SectionBuilder looks for Line candidates.
    // a Line candidate is a Line within a file/stream that COULD BE the correct type, but depends
    // on other lines to be sure it really is the correct type!
    // This could happen when a master-detail-detail record is read: we know when the master line is
    // read, but we don't know when the last detail is read of the mdd-record.
    // After the last line is read of the record, we know that the Section is complete.
    // It could also be that the next line of a new mdd-record is read before the previous section
    // is finished. This can happen when the last detail record contains no information to detect
    // that this is the last line of the mdd-record.

    // Many Line candidates can pass by (i.e. lines are read) before a Section can be constructed.
    // A Line candidate can also be "reconsidered", meaning that the line that was read, actually
    // was not a Line of type X, but a type of Y.
    // After a complete Section was created and validated against the JsaPar Schema, the Section is
    // handed over to the DocumentBuilder.

    // A Section can have delimited or fixed width content, but not both at the same time. The
    // content type of a Section is a logical disjunction: one or the other but not both!
    // A SectionBuilder needs to detect which kind of contentType is present when construction a
    // Section.
    // This can be done by successfully detecting a Line that is defined within a Section.
    // At first all lines definitions are read from the document definition xml file, next the lines
    // are put in an array so the matching lines within the file can be traced back to the section
    // of the xml file. The line-candidates are tried out on the current read line to find a
    // matching
    // line definition for the current line. If a line-candidate matches a line, we know two things:
    // how the line should be parsed into Cells AND in what Section the line belongs: a delimited
    // section or a fixed width section.
    // Once a Line is detected within the Section of type "delimited", then that Section cannot hold
    // lines of type "fixed width".

    public void processFragment(String fragment) {
        List<Line> lineCandidates = lb.progessFragment(fragment);
        if (lineCandidates.size() == 1) {
            // TODO
        }

    }
}