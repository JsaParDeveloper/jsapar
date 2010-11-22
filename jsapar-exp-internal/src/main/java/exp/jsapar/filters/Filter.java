package exp.jsapar.filters;


/**
 * A filter constrains data with a filtering pattern.
 * 
 * @author JsaPar Developer
 */
public class Filter {

	/**
	 * Holds the results of a filtering operation. The results are the values
	 * computed by the filtering operation and the number of these values.
	 * 
	 * @author JsaPar Developer
	 * 
	 */
	private class FilterResults {

	}

	// filter on the object type, like Integer, String
	// when filtered on String then only Cells in the Line list with type String
	// are returned.
	private Object filterType;
	// invert the filter. So if filtered on String, the Cells containing String
	// are NOT returned, BUT all other Cells are returned instead.
	private boolean invertFilter;

	// if filterType is String, you could also filter on the subCells
	// how to filter on the subcells is not clear to me yet ;)
	private boolean filterOnSubCells;

	@SuppressWarnings("unchecked")
	public <T> T getFilterType() {
		return (T) filterType;
	}

	// you can filter a Line or Cell to only give the results that contain as
	// specific type, and by doing so
	// limit the list of cells (in a Line) or limit the list of subcells (in a
	// Cell).
	public <T> void setFilterType(T filterType) throws FilterTypeException {
		// check if the filterType is within the collection of types available
		// in jsapar!
		// if not: throw an FilterType Exception!
		this.filterType = filterType;
	}

	public boolean isInvertFilter() {
		return invertFilter;
	}

	public void setInvertFilter(boolean invertFilter) {
		this.invertFilter = invertFilter;
	}
}