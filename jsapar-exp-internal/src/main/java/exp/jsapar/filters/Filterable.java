package exp.jsapar.filters;

import java.util.List;

/**
 * Defines methods to add / remove filters on a specific object.<br>
 * <br>
 * Filters can be applied to the object that implements this interface so that
 * objects in the internal collection are skipped or returned when using an
 * iterator.
 * 
 * @author JsaPar Developer
 */
public interface Filterable {

	/**
	 * Adds a filter to this elements chain of filters.
	 * 
	 * @param filter
	 *            the filter to be added.
	 */
	public void addFilter(Filter filter);

	/**
	 * Gets the filter from this elements chain of filters.
	 * 
	 * @param filter
	 *            the filter to be added.
	 */
	public Filter getFilter(String filterName);

	/**
	 * Gets all the filters from this elements chain of filters.
	 */
	public List<Filter> getFilter();

	/**
	 * Checks if a certain filter is present in this elements chain of filters.
	 * 
	 * @return {@code true} when this filter is present, {@code false} when this
	 *         filter is not present.
	 */
	public boolean isFilterPresent(Filter filter);

	/**
	 * Checks if this element has filters present.
	 * 
	 * @return {@code true} when filters are present in this element,
	 *         {@code false} when no filters are present in this element.
	 */
	public boolean hasFilters();

	/**
	 * Enables the filters for this element.
	 */
	public void enableFilters();

	/**
	 * Disables the filters for this element.
	 */
	public void disableFilters();

	/**
	 * Removes all filters from the chain of filters for this element.
	 */
	public void removeAllFilters();

	/**
	 * Removes the specified filter from the chain of filters for this element.
	 * 
	 * @param filter
	 *            the filter to be removed from the chain.
	 */
	public void removeFilter(Filter filter);
}