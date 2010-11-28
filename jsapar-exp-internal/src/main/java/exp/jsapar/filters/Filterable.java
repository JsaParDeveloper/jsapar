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
 * @param <T> TODO
 */
public interface Filterable<T> extends Iterable<T> {

	/**
	 * Adds one or more filters to this elements chain of filters.
	 * 
	 * @param filters
	 *            the filters to be added.
	 * @throws FilterNotApplicableForThisElementException
	 *             TODO
	 */
	public void addFilters(Filter... filters);

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
	public List<? extends Filter> getFilters();

	/**
	 * Checks if a certain filter is present in this elements chain of filters.
	 * 
	 * @return {@code true} when this filter is present, {@code false} when this
	 *         filter is not present.
	 */
	public boolean isFilterPresent(String filterName);

	/**
	 * Checks if this element has filters present.
	 * 
	 * @return {@code true} when filters are present in this element,
	 *         {@code false} when no filters are present in this element.
	 */
	public boolean hasFilters();

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