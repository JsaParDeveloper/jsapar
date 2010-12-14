package exp.jsapar.lists;

import java.util.Collection;

import exp.jsapar.filters.Filter;

/**
 * TODO The filterable list should not have add/insert/remove options because the data is filtered
 * and doesn't contain all the elements of the complete list: the user doesn't have a complete
 * overview of the available data in the complete list through the filtered list. Adding elements to
 * the filtered list could cause the effect that it might look like the complete list doesn't have
 * an element with a certain name, but in fact - because the list is filtered - it only looks as if
 * the element is not in the list. That is a wrong assumption.
 * 
 * So this filteredList can only have those operations that do not influence the complete list in
 * any way! A filtered list should always return an unmodifiable list!
 * 
 * @author JsaPar Developer
 */
public interface FilterableList<E> extends Iterable<E> {

    /**
     * Adds the filter(s) to the list.
     */
    public void addFilter(Filter... filter);

    /**
     * Removes the filter from the list.
     * 
     * @param filterName
     *            the name of the filter.
     */
    public void removeFilter(String filterName);

    /**
     * Activates the filtering for this list.
     */
    public void activateFiltering();

    /**
     * Deactivates the filtering for this list.
     */
    public void deactivateFiltering();

    /**
     * Checks is filtering for this list is activated.
     * 
     * @return {@code true} when filtering is active, {@code false} when filtering is not active.
     */
    public boolean isFilteringActive();

    /**
     * Inverses the filtering for all the filters in the list.<br>
     * <br>
     * If a filter has a match based on the restrictions of that filter, the inverse causes it to
     * mismatch this filter.
     */
    public void inverseFiltering();

    /**
     * Returns the size of the <b>filtered</b> collection. The size can be the same size or less
     * than the total size of the collection depending on the filtering results.
     * 
     * @return the filtered collection size.
     */
    public int filteredSize();

    /**
     * Returns {@code true} if the <b>filtered</b> collection contains no elements.
     * 
     * @return {@code true} if the <b>filtered</b> collection contains no elements.
     */
    public boolean isFilteredEmpty();

    public boolean containsFiltered(Object o);

    public boolean containsAllFiltered(Collection<?> c);

    // public Iterator<E> iteratorFiltered();

    public Object[] toArrayFiltered();

    public <T> T[] toArrayFiltered(T[] a); // FIXME double check this!

    // clears the filtered list and also removed the elements in the
    // allcellslist
    public void clearFiltered(); // FIXME should we supply a clearFiltered?
    // No changing operations should be possible on the filtered list.
    // The user should get the information from the filtered list and use that information
    // for changing the complete list.
}
