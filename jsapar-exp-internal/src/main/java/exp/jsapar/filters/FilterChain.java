package exp.jsapar.filters;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import exp.jsapar.utils.ParamsUtil;

/**
 * Holds the filters of a filterable element.
 * 
 * @author JsaPar Developer
 */
public class FilterChain<T extends Filter> {

	private List<T> filterList = null;

	public FilterChain() {
		filterList = new ArrayList<T>();
	}

	public <T> void addFilter(T filter) {
		ParamsUtil.checkForNullPointer(filter);

		// only add the filter when not already in list.
		if (!filterList.contains(filter)) {
			addFilter(filter);
		}
	}

	public Iterator<T> iterate() {
		return filterList.iterator();
	}

	public T getFilter(String name) {
		// useful ?
		return null;
	}

	// invert the filter. So if filtered on String, the Cells containing String
	// are NOT returned, BUT all other Cells are returned instead.
	private boolean invertFilters;

	public boolean invertFilters() {
		return invertFilters;
	}

	public boolean isInvertFilters() {
		return invertFilters;
	}

	public List<T> getFilters() {
		return filterList;
	}

	// okay
	public void removeFilter(String filterName) {
		ParamsUtil.checkForNullPointer(filterName);
		ParamsUtil.checkForEmptyString(filterName);
		for (Filter filter : filterList) {
			if (filter.getName().equals(filterName)) {
				filterList.remove(filter);
				return;
			}
		}
	}
}