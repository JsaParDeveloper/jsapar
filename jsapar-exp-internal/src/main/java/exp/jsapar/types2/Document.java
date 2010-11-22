package exp.jsapar.types2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import exp.jsapar.utils.EqualsUtil;
import exp.jsapar.utils.HashCodeUtil;
import exp.jsapar.utils.ParamsUtil;
import exp.jsapar.utils.StringUtil;

/**
 * The document type. A document contains a number of paragraphs specified by a
 * list of {@link exp.jsapar.types2.Paragraph} objects.
 * 
 * @author JsaPar Developer
 * 
 * @see exp.jsapar.types2.Paragraph
 */
public class Document implements Iterable<Paragraph>, Serializable, Cloneable {
	// TODO Filterable
	/**
	 * The Serial version ID for this class.
	 */
	private static final long serialVersionUID = -8200923869340725883L;

	/**
	 * The list of paragraph objects in this document.
	 */
	private List<Paragraph> paragraphs = null;

	// ------------------------------------------------------------------------
	
	/**
	 * Constructs an empty exp.jsapar.types2.Document with zero
	 * exp.jsapar.types2.Paragraph objects.
	 */
	public Document() {
		paragraphs = new ArrayList<Paragraph>();
	}

	/**
	 * Gets the list of paragraphs.
	 * 
	 * @return the list containing the paragraphs.
	 */
	public List<Paragraph> getParagraphs() {
		return paragraphs;
	}

	/**
	 * Sets the list of paragraphs.
	 * 
	 * @param paragraphs
	 *            the list containing the paragraphs.
	 */
	public void setParagraphs(List<Paragraph> paragraphs) {
		ParamsUtil.checkForNullPointer(paragraphs);
		this.paragraphs = paragraphs;
	}

	/**
	 * Adds a paragraph to the list of paragraphs.
	 * 
	 * @param paragraph
	 *            the paragraph object to be added to the list of paragraphs.
	 */
	public void addParagraph(Paragraph paragraph) {
		ParamsUtil.checkForNullPointer(paragraph);
		paragraphs.add(paragraph);
	}

	/**
	 * Inserts a paragraph object in the list of paragraphs at the given index.
	 * 
	 * @param paragraph
	 *            the paragraph object to be inserted into the list of
	 *            paragraphs.
	 * @param index
	 *            the index in the list where the paragraph should be inserted.
	 */
	public void insertParagraph(Paragraph paragraph, int index) {
		ParamsUtil.checkForNullPointer(paragraph);
		paragraphs.add(index, paragraph);
	}

	/**
	 * Gets the paragraph with the given index.<br>
	 * <br>
	 * Note: IndexOutOfBoundsExceptions are swallowed, and <tt>null</tt> is
	 * returned instead.
	 * 
	 * @param index
	 *            the index of the paragraph.
	 * @return the paragraph at the given index or <tt>null</tt> when there is
	 *         no paragraph at the given index.
	 */
	public Paragraph getParagraph(int index) {
		Paragraph paragraph = null;
		try {
			paragraph = paragraphs.get(index);
		} catch (IndexOutOfBoundsException e) {
			// swallow exception and return null.
			paragraph = null;
		}
		return paragraph;
	}

	/**
	 * Checks if the document is empty. If the list of paragraphs is empty, then
	 * the document is empty too.
	 * 
	 * @return <tt>true</tt> when document is empty, <tt>false</tt> when
	 *         document is not empty.
	 */
	public boolean isEmpty() {
		return (paragraphs.isEmpty());
	}

	// ------------------------------------------------------------------------
	
	/**
	 * Provides an iterator for iterating over the paragraph list.
	 */
	@Override
	public Iterator<Paragraph> iterator() {
		return new DocumentIterator();
	}
	
	/**
	 * Returns the textual representation of a exp.jsapar.types2.Document,
	 * including all the elements within this exp.jsapar.types2.Document as a
	 * textual representation.
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		Paragraph par = null;
		String str = null;
		StringBuilder representation = new StringBuilder();

		for (int index = 0; index < paragraphs.size(); index++) {
			par = paragraphs.get(index);
			str = par.toString();
			// check for null otherwise 'null' gets added as a string.
			if (str != null) {
				representation.append("Paragraph "
						+ StringUtil.getLeadingZerosString(index, paragraphs.size())
						+ index + ": ");
				representation.append(str);
				// TODO check if next paragraph is displayed afterwards
			}
		}
		return representation.toString();
	}

	/**
	 * Compares this Document with the specified Document for equality.
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
		if (!(aThat instanceof Document)) {
			return false;
		}
		// cast to native object is now safe
		Document that = (Document) aThat;

		// compare the content of the paragraph lists.
		return EqualsUtil.areEqual(this.paragraphs, that.paragraphs);
	}

	/**
	 * Returns a hash code value for this Document.
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int result = HashCodeUtil.SEED;
		for (Paragraph paragraph : paragraphs) {
			result = HashCodeUtil.hash(result, paragraph);
		}
		return result;
	}
}