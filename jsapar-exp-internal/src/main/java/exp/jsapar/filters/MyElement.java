package exp.jsapar.filters;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// MyElement == Line. Line implements Filterable<Cell> 
public class MyElement implements Filterable<Object> {

	private List<Object> objList = null;
// TODO remove:	private FilterChain<CellFilter> filterChain = new FilterChain<CellFilter>();

	public MyElement() {
		objList = new ArrayList<Object>();
		// init the list with default values
		objList.add("Hello");
		objList.add("World!");
		objList.add(new Integer(10));
		objList.add(new Double(15.5));
		objList.add("G'day mate!");
	}

	public void printList() {
		for (Object obj : objList) {
			System.out.println(obj.toString());
		}
	}

	// -----------------------------------------------------------------------------------------

	@Override
	public void addFilters(Filter... filters) {
		// ignore adding when there is nothing to add
		if (filters.length != 0) {
			return;
		}

		// add the filters
		for (Filter filter : filters) {
	//		filterChain.addFilter(filter);
		}
	}

	@Override
	public Filter getFilter(String filterName) {
		if (hasFilters()) {
		//	return filterChain.getFilter(filterName);
		}
		return null;
	}

	@Override
	public List<CellFilter> getFilters() {
		if (hasFilters()) {
		//	return filterChain.getFilters();
		}
		return null;
	}

	@Override
	public boolean isFilterPresent(String filterName) {
		boolean retval = false;
		if (hasFilters()) {
	//		retval = filterChain.getFilters().contains(filterName);
		}
		return retval;
	}

	@Override
	public boolean hasFilters() {
	//	return !filterChain.getFilters().isEmpty();
		return false;
	}

	@Override
	public void removeAllFilters() {
		if (hasFilters()) {
		//	filterChain.getFilters().clear();
		}
	}

	@Override
	public void removeFilter(Filter filter) {
		if (hasFilters()) {
		//	filterChain.getFilters().remove(filter);
		}
	}

	@Override
	public Iterator<Object> iterator() {
		return new InnerElementIterator();
	}

	
	// FIXME DO NOT create a inner class -> MAKE an CellList, LineList and ParagraphList that contain the 
	// inner iterator class with the filter possibilities. Also add extra methods to be used by user for adding
	// filters to the CellList etc. Make an skeletal implementation of the List!
	// --
	// CellList implements Filterable<Cell>
	// LineList implements Filterable<Line>
	// ParagraphList implements Filterable<Paragraph>
	// --
	// Line: contains CellList; 
	// Paragraph: contains LineList;
	// Document: contains ParagraphList;
	
	
	// inner iterator is needed because otherwise we cannot reach the objList.
	private class InnerElementIterator implements Iterator<Object> {

		private Object currentObject = null;
		
		@Override
		public boolean hasNext() {
			// TODO check if this works

			// TODO most of these steps can be catched in a private method!
			// we have to go through all the filters EACH TIME for the current
			// element
			// 1. what is the current object (index)?
			// 2. what is the type of the current object (instanceof)?
			// 3. which filters should be applied on this current object? (loop
			// over the filterlist)
			// 3.a. does this filter match the current object type? yes: 3.b.,
			// no: skip to
			// 3.b. is this filter active? yes: 3.c., no: skip to
			// 3.c. is this filter set to reveal (or conceal)? reveal: 3.d.,
			// conceal: skip to
			// 3.d. what are the restrictions on the filter for this type?
			// - is there a restriction? yes/no
			// - is it within range (numbers)?
			// - is it within the collection (enums/strings/characters)?
			// - does it validate the restriction? yes: 3.e., no: skip to
			// 3.e. we just found our element! store this object in current.
			// skip to 5.
			// 4. move internal listpointer to next element in the list, rerun
			// the loop. goto 1!
			// 5. return true when item found, return false when internal list
			// completely run and no items
			// could be found.

			return false;
		}

		@Override
		public Object next() {
			// TODO somewhat same as above: when the hasNext method has run, and
			// it could found an item, it returned true. It also has putted the
			// object into the currentObject. Just return the currentObject.
			return currentObject;
		}

		@Override
		public void remove() {
			// this is a filter iterator! it doesn't remove items in the list!
			throw new UnsupportedOperationException("This filter iterator doesn't remove items in a filtered list!");
		}
	}
}