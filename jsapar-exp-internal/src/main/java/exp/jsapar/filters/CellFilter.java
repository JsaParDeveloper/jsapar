package exp.jsapar.filters;

import exp.jsapar.utils.TypesUtil;

// how to restrict the filter from being used in the wrong context
// like: a linefilter used in a section!
public class CellFilter extends Filter {

	private Class theClass = null;

	public CellFilter(Class clazz) {
		TypesUtil.isValidDataType(clazz);
		theClass = clazz; 
	}
	
	public void lowerLimit() {
		// to be used by number filters only! Not for Enum or Boolean!
		// Check the class type and find out if the lower limit fits in the range of the class type.
		// also check if the lowerLimit <= upperLimit
	}

	public void upperLimit() {
		// to be used by number filters only! Not for Enum or Boolean!
	}

	public void range() {
		// to be used by number filters only! Not for Enum or Boolean!
	}

	public void nullable(boolean yesOrNo) {
		// to be used by number filters only! Not for Enum or Boolean!

	}
	
	// filter on the object type, like Integer, String
	// when filtered on String then only Cells in the Line list with type String
	// are returned.
	private Object filterType;

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
	
	
}
