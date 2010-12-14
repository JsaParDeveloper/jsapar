package exp.jsapar.types2;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import exp.jsapar.exception.AnnotationException;
import exp.jsapar.filters.Filter;
import exp.jsapar.filters.Filterable;
import exp.jsapar.lists.LineList;
import exp.jsapar.utils.EqualsUtil;
import exp.jsapar.utils.HashCodeUtil;
import exp.jsapar.utils.ParamsUtil;
import exp.jsapar.utils.StringUtil;

/**
 * The section type within the Document {@link exp.jsapar.types2.Document} object. A section
 * contains a number of lines specified by a list of {@link exp.jsapar.types2.Line} objects.
 * 
 * @author JsaPar Developer
 * 
 * @see exp.jsapar.types2.Document
 * @see exp.jsapar.types2.Line
 */
public class Section implements Filterable<Line>, Comparable<Section>, Serializable, Cloneable {
    /**
     * The Serial version ID for this class.
     */
    private static final long serialVersionUID = 3631830254686023021L;
    /**
     * The list of line objects in this section.
     */
    private LineList lines = null;

    // ------------------------------------------------------------------------

    /**
     * Constructs an empty {@link exp.jsapar.types2.Section} with zero
     * {@link exp.jsapar.types2.Line} objects.
     */
    public Section() {
        lines = new LineList();
    }

    /**
     * Constructs a {@link exp.jsapar.types2.Section} object with the {@link exp.jsapar.types2.Line}
     * objects that are defined within the specified user objects. These user objects must contain
     * JsaPar annotations: @Line for the class and @Cell for the members of that class.
     * 
     * @param objects
     *            the user objects which are annotated with JsaPar annotations.
     * 
     * @throws AnnotationException
     *             thrown when a {@code @Line} annotation was not found in the specified class(es),
     *             or when no {@code @Cell} annotation was found in the specified class(es) for it's
     *             members.
     */
    public Section(Object... objects) {
        this();
        for (Object obj : objects) {
            ParamsUtil.checkForNullPointer(obj);
            addAnnotatedObject(obj);
        }
    }

    /**
     * Constructs a {@link exp.jsapar.types2.Section} with the given {@link exp.jsapar.types2.Line}
     * object(s).
     * 
     * @param lines
     *            the given Line object(s).
     */
    public Section(Line... lines) {
        this();
        for (Line line : lines) {
            ParamsUtil.checkForNullPointer(line);
            this.lines.add(line);
        }
    }

    // ------------------------------------------------------------------------

    private void addAnnotatedObject(Object obj) {
        // TODO Auto-generated method stub
        // create new Line object from the given obj.
    }

    /**
     * Gets the list of lines. This method always returns the <tt>full</tt> list of lines without
     * any filtering of lines.
     * 
     * @return the list containing the lines.
     */
    public List<Line> getLines() {
        return lines;
    }

    /**
     * Adds line object(s) to the list of lines.
     * 
     * @param lines
     *            the line object(s) to be added to the list of lines.
     */
    public void addLines(Line... lines) {
        for (Line line : lines) {
            ParamsUtil.checkForNullPointer(line);
            this.lines.add(line);
        }
    }

    // ------------------------------------------------------------------------

    /**
     * Inserts a line object in the list of lines at the given index.
     * 
     * @param line
     *            the line object to be inserted into the list of lines.
     * @param index
     *            the index in the list where the line should be inserted.
     */
    public void insertLine(int index, Line line) {
        ParamsUtil.checkForNullPointer(line);
        lines.add(index, line);
    }

    public void insertLine(Line line) {
        ParamsUtil.checkForNullPointer(line);
        lines.add(0, line);
    }

    // ------------------------------------------------------------------------

    /**
     * Gets the line with the given index.
     * 
     * @param index
     *            the index of the line.
     * 
     * @return the line at the given index.
     */
    public Line getLine(int index) {
        return lines.get(index);
    }

    /**
     * Checks if the Section is empty. If the list of lines is empty, then the Section is empty too.
     * 
     * @return {@code true} when Section is empty, {@code false} when Section is not empty.
     */
    public boolean isEmpty() {
        return (lines.isEmpty());
    }

    // ------------------------------------------------------------------------

    public Line removeLine(Line line) {
        Line removedLine = null;
        // TODO
        return removedLine;
    }

    public Line removeLine(int index) {
        Line removedLine = null;
        // TODO
        return removedLine;
    }

    // ------------------------------------------------------------------------

    public Line replaceLine(Line line) {
        Line replacedLine = null;
        // TODO
        return replacedLine;
    }

    public Line replaceLine(int index, Line line) {
        Line replacedLine = null;
        // TODO
        return replacedLine;
    }

    public Line replaceLine(Line currentline, Line newLine) {
        Line replacedLine = null;
        // TODO
        return replacedLine;
    }

    // ------------------------------------------------------------------------

    /**
     * Compares this Section object with the specified Section object for order. Returns a negative
     * integer, zero, or a positive integer as this Section is less than, equal to, or greater than
     * the specified Section.
     */
    @Override
    public int compareTo(Section o) {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * Provides an iterator for iterating over the list of lines.
     */
    @Override
    public Iterator<Line> iterator() {
        // TODO
        return null;
    }

    // ------------------------------------------------------------------------

    /**
     * Returns the textual representation of a exp.jsapar.types2.Section, including all the elements
     * within this exp.jsapar.types2.Section as a textual representation.
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        Line line = null;
        String str = null;
        StringBuilder representation = new StringBuilder();

        for (int index = 0; index < lines.size(); index++) {
            line = lines.get(index);
            str = line.toString();
            // check for null otherwise 'null' gets added as a string.
            if (str != null) {
                representation.append("Line " + StringUtil.getLeadingZerosString(index, lines.size()) + index + ": ");
                representation.append(str);
                // TODO check if next line is displayed afterwards
            }
        }
        return representation.toString();
    }

    /**
     * Compares this Section with the specified Section for equality.
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object aThat) {
        // check for self-comparison
        if (this == aThat) {
            return true;
        }
        // check for same type
        if (!(aThat instanceof Section)) {
            return false;
        }
        // cast to native object is now safe
        Section that = (Section) aThat;

        // compare the content of the line lists.
        return EqualsUtil.areEqual(this.lines, that.lines);
    }

    /**
     * Returns a hash code value for this Section.
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int result = HashCodeUtil.SEED;
        for (Line line : lines) {
            result = HashCodeUtil.hash(result, line);
        }
        return result;
    }

    // ------------------------------------------------------------------------

    @Override
    public void addFilters(Filter... filters) {
        // TODO Auto-generated method stub
    }

    @Override
    public Filter getFilter(String filterName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<? extends Filter> getFilters() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isFilterPresent(String filterName) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasFilters() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void removeAllFilters() {
        // TODO Auto-generated method stub
    }

    @Override
    public void removeFilter(String filterName) {
        // TODO Auto-generated method stub
    }
}