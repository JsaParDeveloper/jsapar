package exp.jsapar.types2;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import exp.jsapar.filters.Filter;
import exp.jsapar.filters.Filterable;
import exp.jsapar.lists.LineList;
import exp.jsapar.utils.EqualsUtil;
import exp.jsapar.utils.HashCodeUtil;
import exp.jsapar.utils.ParamsUtil;
import exp.jsapar.utils.StringUtil;

/**
 * The paragraph type within the Document {@link exp.jsapar.types2.Document}
 * object. A paragraph contains a number of lines specified by a list of
 * {@link exp.jsapar.types2.Line} objects.
 * 
 * @author JsaPar Developer
 * 
 * @see exp.jsapar.types2.Document
 * @see exp.jsapar.types2.Line
 */
// TODO completely rewrite to use a LineList!
public class Paragraph implements Comparable<Paragraph>, Filterable<Paragraph>,
		Serializable, Cloneable {
	/**
	 * The Serial version ID for this class.
	 */
	private static final long serialVersionUID = 3631830254686023021L;
	/**
	 * The list of line objects in this paragraph.
	 */
	private LineList lines = null;

	// ------------------------------------------------------------------------

	/**
	 * Constructs an empty {@link exp.jsapar.types2.Paragraph} with zero
	 * {@link exp.jsapar.types2.Line} objects.
	 */
	public Paragraph() {
		lines = new LineList();
	}

	/**
	 * Gets the list of lines. This method always returns the <tt>full</tt> list
	 * of lines without any filtering of lines.
	 * 
	 * @return the list containing the lines.
	 */
	public List<Line> getLines() {
		return lines;
	}



	/**
	 * Adds a line to the list of lines.
	 * 
	 * @param line
	 *            the line object to be added to the list of lines.
	 */
	public void addLine(Line line) {
		ParamsUtil.checkForNullPointer(line);
		lines.add(line);
	}

	/**
	 * Inserts a line object in the list of lines at the given index.
	 * 
	 * @param line
	 *            the line object to be inserted into the list of lines.
	 * @param index
	 *            the index in the list where the line should be inserted.
	 */
	public void insertLine(Line line, int index) {
		ParamsUtil.checkForNullPointer(line);
		lines.add(index, line);
	}

	/**
	 * Gets the line with the given index.<br>
	 * <br>
	 * Note: IndexOutOfBoundsExceptions are swallowed, and <tt>null</tt> is
	 * returned instead.
	 * 
	 * @param index
	 *            the index of the line.
	 * @return the line at the given index or {@code null} when there is no line
	 *         at the given index.
	 */
	public Line getLine(int index) {
		Line retval = null;
		try {
			retval = lines.get(index);
		} catch (IndexOutOfBoundsException e) {
			// swallow exception and return null.
			retval = null;
		}
		return retval;
	}

	/**
	 * Checks if the paragraph is empty. If the list of lines is empty, then the
	 * paragraph is empty too.
	 * 
	 * @return {@code true} when paragraph is empty, {@code false} when
	 *         paragraph is not empty.
	 */
	public boolean isEmpty() {
		return (lines.isEmpty());
	}

	// ------------------------------------------------------------------------

	/**
	 * Compares this Paragraph object with the specified Paragraph object for order.
	 * Returns a negative integer, zero, or a positive integer as this Paragraph is
	 * less than, equal to, or greater than the specified Paragraph.
	 */
	@Override
	public int compareTo(Paragraph o) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Provides an iterator for iterating over the list of lines.
	 */
	@Override
	public Iterator<Paragraph> iterator() {
		// TODO
		return null;
	}

	/**
	 * Returns the textual representation of a exp.jsapar.types2.Paragraph,
	 * including all the elements within this exp.jsapar.types2.Paragraph as a
	 * textual representation.
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
				representation.append("Line "
						+ StringUtil.getLeadingZerosString(index, lines.size())
						+ index + ": ");
				representation.append(str);
				// TODO check if next line is displayed afterwards
			}
		}
		return representation.toString();
	}

	/**
	 * Compares this Paragraph with the specified Paragraph for equality.
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
		if (!(aThat instanceof Paragraph)) {
			return false;
		}
		// cast to native object is now safe
		Paragraph that = (Paragraph) aThat;

		// compare the content of the line lists.
		return EqualsUtil.areEqual(this.lines, that.lines);
	}

	/**
	 * Returns a hash code value for this Paragraph.
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
	public void removeFilter(Filter filter) {
		// TODO Auto-generated method stub
		
	}
}