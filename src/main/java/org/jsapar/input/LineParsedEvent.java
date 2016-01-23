/**
 * 
 */
package org.jsapar.input;

import java.util.EventObject;

import org.jsapar.model.Line;

/**
 * @author stejon0
 *
 */
public final class LineParsedEvent extends EventObject {

    /**
     * 
     */
    private static final long serialVersionUID = 9009392654758990079L;
    private final Line line;
    private final long lineNumber;

    /**
     * @param source
     * @param line
     * @param lineNumber
     */
    public LineParsedEvent(Object source, Line line, long lineNumber) {
	super(source);
	this.line = line;
	this.lineNumber = lineNumber;
	// TODO Auto-generated constructor stub
    }
    /**
     * @return the line
     */
    public Line getLine() {
        return line;
    }
    /**
     * @return the line number for this line. First line is 1. 
     */
    public long getLineNumber() {
        return lineNumber;
    }

}
