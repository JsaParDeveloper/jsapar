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
    
    public void processFragment(String fragment) {
        List<Line> lineCandidates = lb.progessFragment(fragment);
        if (lineCandidates.size() == 1) {
            // TODO
        }
        
    }
}