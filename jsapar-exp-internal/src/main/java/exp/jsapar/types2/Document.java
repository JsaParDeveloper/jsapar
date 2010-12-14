package exp.jsapar.types2;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import exp.jsapar.filters.Filter;
import exp.jsapar.filters.Filterable;
import exp.jsapar.lists.SectionList;
import exp.jsapar.utils.EqualsUtil;
import exp.jsapar.utils.HashCodeUtil;
import exp.jsapar.utils.ParamsUtil;
import exp.jsapar.utils.StringUtil;

/**
 * The document type. A document contains a number of sections specified by a list of
 * {@link exp.jsapar.types2.Section} objects.
 * 
 * @author JsaPar Developer
 * 
 * @see exp.jsapar.types2.Section
 */
public class Document implements Filterable<Section>, Serializable, Cloneable {
    /**
     * The Serial version ID for this class.
     */
    private static final long serialVersionUID = -8200923869340725883L;

    /**
     * The list of section objects in this document.
     */
    private SectionList sections = null;

    // ------------------------------------------------------------------------

    /**
     * Constructs an empty exp.jsapar.types2.Document with zero exp.jsapar.types2.Section objects.
     */
    public Document() {
        sections = new SectionList();
    }

    /**
     * Constructs a {@link exp.jsapar.types2.Document} with the given
     * {@link exp.jsapar.types2.Section} object(s).
     * 
     * @param lines
     *            the given Section object(s).
     */
    public Document(Section... sections) {
        this();
        for (Section section : sections) {
            ParamsUtil.checkForNullPointer(section);
            this.sections.add(section);
        }
    }

    // ------------------------------------------------------------------------

    /**
     * Gets the list of sections.
     * 
     * @return the list containing the sections.
     */
    public List<Section> getSections() {
        return sections;
    }

    /**
     * Sets the list of sections.
     * 
     * @param sections
     *            the list containing the sections.
     */
    public void setSections(SectionList sections) {
        ParamsUtil.checkForNullPointer(sections);
        this.sections = sections;
    }

    /**
     * Adds a section to the list of sections.
     * 
     * @param section
     *            the section object to be added to the list of sections.
     */
    public void addSection(Section section) {
        ParamsUtil.checkForNullPointer(section);
        sections.add(section);
    }

    /**
     * Inserts a section object in the list of sections at the given index.
     * 
     * @param section
     *            the section object to be inserted into the list of sections.
     * @param index
     *            the index in the list where the section should be inserted.
     */
    public void insertSection(Section section, int index) {
        ParamsUtil.checkForNullPointer(section);
        sections.add(index, section);
    }

    /**
     * Gets the section with the given index.
     * 
     * @param index
     *            the index of the section.
     * 
     * @return the section at the given index.
     */
    public Section getSection(int index) {
        return sections.get(index);
    }

    // public int getNumberOfsections() {}

    /**
     * Checks if the document is empty. If the list of sections is empty, then the document is empty
     * too.
     * 
     * @return <tt>true</tt> when document is empty, <tt>false</tt> when document is not empty.
     */
    public boolean isEmpty() {
        return (sections.isEmpty());
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

    // ------------------------------------------------------------------------

    /**
     * Provides an iterator for iterating over the section list.
     */
    @Override
    public Iterator<Section> iterator() {
        // TODO
        return null;
    }

    // ------------------------------------------------------------------------

    /**
     * Returns the textual representation of a exp.jsapar.types2.Document, including all the
     * elements within this exp.jsapar.types2.Document as a textual representation.
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        Section par = null;
        String str = null;
        StringBuilder representation = new StringBuilder();

        for (int index = 0; index < sections.size(); index++) {
            par = sections.get(index);
            str = par.toString();
            // check for null otherwise 'null' gets added as a string.
            if (str != null) {
                representation.append("Section " + StringUtil.getLeadingZerosString(index, sections.size()) + index
                        + ": ");
                representation.append(str);
                // TODO check if next section is displayed afterwards
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

        // compare the content of the section lists.
        return EqualsUtil.areEqual(this.sections, that.sections);
    }

    /**
     * Returns a hash code value for this Document.
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int result = HashCodeUtil.SEED;
        for (Section section : sections) {
            result = HashCodeUtil.hash(result, section);
        }
        return result;
    }
}