package org.jsapar.schema;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.List;
import java.util.Locale;

import org.jsapar.Document;
import org.jsapar.JSaParException;
import org.jsapar.input.CellParseError;
import org.jsapar.input.Parser;


/**
 * @author Jonas
 * 
 */
public abstract class Schema implements Cloneable, Parser {

	public enum LineTypeByTypes {
		OCCURS, CONTROL_CELL
	};

	private String lineSeparator = System.getProperty("line.separator");

	private LineTypeByTypes lineTypeBy = LineTypeByTypes.OCCURS;

	/**
	 * This method should only be called by a Outputter class. Don't use this
	 * directly in your code. Use a Outputter instead.
	 * 
	 * @param document
	 * @param writer
	 * @throws IOException
	 * @throws JSaParException
	 */
	public abstract void output(Document document, Writer writer)
			throws IOException, JSaParException;

	private java.util.Locale locale=Locale.getDefault();

	/**
	 * @return the lineSeparator
	 */
	public String getLineSeparator() {
		return lineSeparator;
	}

	/**
	 * Sets the line separator string. Default value is the system default
	 * (Retrieved by System.getProperty("line.separator")).
	 * 
	 * @param lineSeparator
	 *            the lineSeparator to set.
	 */
	public void setLineSeparator(String lineSeparator) {
		this.lineSeparator = lineSeparator;
	}

	/**
	 * @return the locale
	 */
	public java.util.Locale getLocale() {
		return locale;
	}

	/**
	 * @param locale
	 *            the locale to set
	 */
	public void setLocale(java.util.Locale locale) {
		this.locale = locale;
	}

	/**
	 * @return the lineTypeBy
	 */
	public LineTypeByTypes getLineTypeBy() {
		return lineTypeBy;
	}

	/**
	 * @param lineTypeBy
	 *            the lineTypeBy to set
	 */
	public void setLineTypeBy(LineTypeByTypes lineTypeBy) {
		this.lineTypeBy = lineTypeBy;
	}

	public Document build(java.io.Reader reader,
			List<CellParseError> parseErrors) throws JSaParException,
			IOException {
		if (this.getLineTypeBy() == LineTypeByTypes.OCCURS) {
			return buildByOccurs(reader, parseErrors);
		} else if (this.getLineTypeBy() == LineTypeByTypes.CONTROL_CELL) {
			return buildByControlCell(reader, parseErrors);
		} else
			throw new SchemaException("Unimplemented lineTypeBy within schema.");
	}

	protected abstract Document buildByControlCell(Reader reader,
			List<CellParseError> parseErrors) throws JSaParException;

	protected abstract Document buildByOccurs(Reader reader,
			List<CellParseError> parseErrors) throws JSaParException,
			IOException;

	/**
	 * Reads a line from the reader.
	 * 
	 * @param reader
	 * @return The line as a string or an empty string if no line was found.
	 * @throws IOException
	 */
	protected String parseLine(java.io.Reader reader) throws IOException {
		char chLineSeparatorNext = getLineSeparator().charAt(0);
		StringBuilder lineBuilder = new StringBuilder();
		StringBuilder pending = new StringBuilder();
		while (true) {
			int nRead = reader.read();
			if (nRead == -1)
				return lineBuilder.toString(); // End of input buffer.
			char chRead = (char) nRead;
			if (chRead == chLineSeparatorNext) {
				pending.append(chRead);
				if (getLineSeparator().length() > pending.length())
					chLineSeparatorNext = getLineSeparator().charAt(
							pending.length());
				else
					break; // End of line found.
			}
			// It was not a complete line separator.
			else if (pending.length() > 0) {
				// Move pending characters to lineBuilder.
				lineBuilder.append(pending);
				pending.setLength(0);
				lineBuilder.append(chRead);
			} else
				lineBuilder.append(chRead);
		}
		return lineBuilder.toString();
	}

	public Schema clone() throws CloneNotSupportedException {
		return (Schema) super.clone();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(" lineSeparator=");
		String ls = this.lineSeparator;
//		ls.replaceAll("\\n", "/n");
//		ls.replaceAll("\\r", "/r");
		sb.append(ls);
		sb.append(" lineTypeBy=");
		sb.append(this.lineTypeBy);
		sb.append(" locale=");
		sb.append(this.locale);
		return sb.toString();  
	}
}