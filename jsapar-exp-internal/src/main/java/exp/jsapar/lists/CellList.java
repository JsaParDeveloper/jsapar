package exp.jsapar.lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import exp.jsapar.filters.CellFilter;
import exp.jsapar.filters.Filter;
import exp.jsapar.filters.FilterChain;
import exp.jsapar.types2.Cell;
import exp.jsapar.utils.ParamsUtil;

/**
 * An ordered collection (also known as a <i>sequence</i>) of {@code Cell} elements with filtering
 * capabilities. The user of this list has precise control over where in the list each element is
 * inserted. The user can access elements by their integer index (position in the list), search for
 * elements in the list, and filter the collection according to the needs of the user.
 * <p>
 * Filtering of cells is possible by adding a {@code CellFilter} to the collection and setting the
 * filtering of cells in this collection to active by invoking the {@code activateFiltering()}
 * method.
 * 
 * @author JsaPar Developer
 * 
 * @see {@link exp.jsapar.types2.Cell}
 * @see {@link exp.jsapar.filters.CellFilter}
 * @see {@link exp.jsapar.lists.FilterableList}
 * @see {@link java.util.List}
 * @see {@link java.util.ArrayList}
 */
public class CellList implements List<Cell>, FilterableList<Cell> {
    /**
     * The list containing the cells.
     */
    private List<Cell> allCells = null;
    /**
     * The list containing the filtered cells. This is the filtered version of the allCells list.
     */
    private List<Cell> filteredCells = null;
    /**
     * The map of cell elements available in the list.<br>
     * The map is used for performing a quick look up on the cell name, like: getCell(name); The map
     * is also used to prevent that another cell with the same name is added to the collection of
     * cell elements.
     */
    private Map<String, Cell> allCellsByName = null;
    /**
     * The list of filters applicable for the cell list.
     */
    private FilterChain<CellFilter> filterChain = new FilterChain<CellFilter>();
    /**
     * Indicates whether filtering is applied or not.
     */
    private boolean filteringActive = false;

    // ------------------------------------------------------------------------

    // TODO add more definitions to FilterableList and implement
    // TODO allCellsByName implementing

    /**
     * Constructs a CellList.
     */
    public CellList() {
        allCells = new ArrayList<Cell>();
        filteredCells = new ArrayList<Cell>();
        allCellsByName = new HashMap<String, Cell>();
    }

    // ------------------------------------------------------------------------
    // List<Cell> interface method implementations
    // ------------------------------------------------------------------------

    /**
     * Adds the given cell element to the complete list. When the filtering is active, the
     * side-effect of this operation is that the filtered list will be completely refreshed. So,
     * when adding more than one cell element, it is wisely to deactivate the filtering of this list
     * to increase performance.
     * 
     * @return {@code true} if the complete list is changed as a result of the call.
     * 
     * @see java.util.List#add(java.lang.Object)
     */
    @Override
    public boolean add(Cell cell) {
        if (!contains(cell)) {
            allCells.add(cell);
            allCellsByName.put(cell.getName(), cell);
            buildfilteredCellList();
            return true;
        }
        return false;
    }

    /**
     * Inserts the specified cell element at the specified position in this list (optional
     * operation). Shifts the cell element currently at that position (if any) and any subsequent
     * cell elements to the right (adds one to their indices).
     * 
     * @see java.util.List#add(int, java.lang.Object)
     */
    @Override
    public void add(int index, Cell cell) {
        if (!contains(cell)) {
            allCells.add(index, cell);
            buildfilteredCellList();
        }
    }

    /**
     * Appends all of the cell elements in the specified collection to the end of this list, in the
     * order that they are returned by the specified collection's iterator (optional operation).<br>
     * 
     * If filtering is active, then filtering is temporarily deactivated to guarantee performance.
     * 
     * @return {@code true} if this list changed as a result of the call.
     * 
     * @see java.util.List#addAll(java.util.Collection)
     */
    @Override
    public boolean addAll(Collection<? extends Cell> cells) {
        // remember current filtering setting.
        boolean currentFilteringSetting = filteringActive;

        // first check if all elements in the specified collection can be added.
        for (Cell cell : cells) {
            if (contains(cell)) {
                // there is an element that is already in the complete list, so
                // don't add ANY element to the complete list.
                return false;
            }
        }

        // turn off filtering, so performance is guaranteed.
        deactivateFiltering();

        // safe to add ALL the elements.
        for (Cell cell : cells) {
            add(cell);
        }

        // restore previous filtering setting.
        if (currentFilteringSetting) {
            activateFiltering();
        }
        return true;
    }

    /**
     * Inserts all of the cell elements in the specified collection into this list at the specified
     * position (optional operation). Shifts the cell element currently at that position (if any)
     * and any subsequent cell elements to the right (increases their indices). The new cell
     * elements will appear in this list in the order that they are returned by the specified
     * collection's iterator.
     * 
     * @see java.util.List#addAll(int, java.util.Collection)
     */
    @Override
    public boolean addAll(int index, Collection<? extends Cell> cells) {
        // remember current filtering setting.
        boolean currentFilteringSetting = filteringActive;

        // first check if all elements in the specified collection can be added.
        for (Cell cell : cells) {
            if (contains(cell)) {
                // there is an element that is already in the complete list, so
                // don't add ANY element to the complete list.
                return false;
            }
        }

        // turn off filtering, so performance is guaranteed.
        deactivateFiltering();

        // safe to add ALL the elements at the initial specified index.
        int internalIndex = index;
        for (Cell cell : cells) {
            add(internalIndex++, cell);
        }

        // restore previous filtering setting.
        if (currentFilteringSetting) {
            activateFiltering();
        }
        return true;
    }

    /**
     * Removes all of the cell elements from this list (optional operation). <br>
     * Filtering will be deactivated and the list will be empty after this call returns.
     * 
     * @see java.util.List#clear()
     */
    @Override
    public void clear() {
        deactivateFiltering();
        allCells.clear();
        filteredCells.clear();
    }

    /**
     * Returns {@code true} if this list contains the specified cell element.
     * 
     * @return {@code true} if this list contains the specified cell element.
     * 
     * @see java.util.List#contains(java.lang.Object)
     */
    @Override
    public boolean contains(Object o) {
        ParamsUtil.checkForNullPointer(o);
        Cell co = null;
        if (!(o instanceof Cell)) {
            throw new IllegalArgumentException("The specified object is not a Cell object.");
        }
        // safe to cast
        co = (Cell) o;
        // find the cell that has the same name and type
        for (Cell cell : allCells) {
            // FIXME what should be 'seen' as equal: the name or the name/type
            // combination of the cell? If we define equal as name/type, then
            // there is a change that the wrong value is returned when
            // the name is present in the list but the value type is different.
            if (cell.equals(co)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns {@code true} if the cell list contains a cell with the specified cell name.
     * 
     * @return {@code true} if the cell list contains a cell with the specified cell name.
     */
    public boolean contains(String cellName) {
        ParamsUtil.checkForNullPointer(cellName);

        // check if a cell exists with the specified name
        for (Cell cell : allCells) {
            if (cell.getName().equals(cellName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns {@code true} if this list contains all of the cell elements of the specified
     * collection.
     * 
     * @see java.util.List#containsAll(java.util.Collection)
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        ParamsUtil.checkForNullPointer(c);

        try {
            for (Object obj : c) {
                if (!contains(obj)) {
                    return false;
                }
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("One or more objects in the specified collection is not a Cell object.");
        }
        return true;
    }

    /**
     * Gets the cell element at the specified index.
     * 
     * @see java.util.List#get(int)
     */
    @Override
    public Cell get(int index) {
        return allCells.get(index);
    }

    /**
     * Gets the cell element with the specified name.
     * 
     * @param name
     *            the name of the cell element.
     * @return the cell element that matched the specified name, or {@code null} when no element
     *         could be found.
     */
    public Cell get(String name) {
        if (allCellsByName.containsKey(name)) {
            return allCellsByName.get(name);
        }
        return null;
    }

    /**
     * Returns the index of the first occurrence of the specified element in the complete
     * collection, or -1 if the complete collection does not contain the element.
     * 
     * @see java.util.List#indexOf(java.lang.Object)
     */
    @Override
    public int indexOf(Object o) {
        if (o instanceof Cell) {
            return allCells.indexOf(o);
        }
        return -1;
    }

    /**
     * Returns {@code true} if the complete collection contains no elements.
     * 
     * @return {@code true} if the complete collection contains no elements.
     */
    @Override
    public boolean isEmpty() {
        return allCells.isEmpty();
    }

    /**
     * Returns the a cell iterator for the complete collection.
     * 
     * @return the cell iterator for the complete collection.
     */
    @Override
    public Iterator<Cell> iterator() {
        // FIXME should be a singleton?
        return new CellIterator();
    }

    /**
     * Returns the index of the last occurrence of the specified cell element in this list, or -1 if
     * this list does not contain the cell element.
     * 
     * @return TODO
     * 
     * @see java.util.List#lastIndexOf(java.lang.Object)
     */
    @Override
    public int lastIndexOf(Object o) {
        if (o instanceof Cell) {
            return allCells.indexOf(o);
        }
        return -1;
    }

    @Override
    public ListIterator<Cell> listIterator() {
        // TODO
        // own list iterator implementation must be created to insure that the
        // allCells and filteredCells lists are kept in sync!
        // FIXME should be a singleton?
        return new CellListIterator();
    }

    @Override
    public ListIterator<Cell> listIterator(int index) {
        // TODO implement
        throw new UnsupportedOperationException("Not yet implemented!");
        // return null;
    }

    /**
     * Removes the cell element at the specified index.
     * 
     * @see java.util.List#remove(int)
     */
    @Override
    public Cell remove(int index) {
        Cell retval = allCells.remove(index);
        buildfilteredCellList();
        return retval;
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present
     * (optional operation). If this list does not contain the element, it is unchanged.
     * 
     * @return TODO
     * 
     * @see java.util.List#remove(java.lang.Object)
     */
    @Override
    public boolean remove(Object o) {
        if (!(o instanceof Cell)) {
            return false;
        }

        if (allCells.contains(o)) {
            allCells.remove(o);
            buildfilteredCellList();
            return true;
        }

        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        // how to deal with objects that are missing in the allCells list
        // but are present in the given collection?

        // FIXME this implementation is not 100% correct!
        // turn of filtering when removing a lot of objects -> performance!
        for (Object obj : c) {
            remove(obj);
        }

        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        // TODO implement
        // TODO also rebuild the filtered list!
        return false;
    }

    /**
     * Replaces the element at the specified position in this list with the specified element
     * (optional operation).
     * 
     * @return the cell element that was replaced in the collection.
     * 
     * @see java.util.List#set(int, java.lang.Object)
     */
    @Override
    public Cell set(int index, Cell cell) {
        Cell retval = allCells.set(index, cell);
        buildfilteredCellList();
        return retval;
    }

    /**
     * Returns the total size of the collection.
     * 
     * @return the total size of the collection.
     */
    @Override
    public int size() {
        return allCells.size();
    }

    @Override
    public List<Cell> subList(int fromIndex, int toIndex) {
        // TODO implement
        return null;
    }

    // TODO should we also supply a subList(String cellName1, String cellName2)
    // which supplies a sub list of cells that is within "range" of cell with
    // cellname1 and cell with cellname2.

    /**
     * Returns an array containing all the cell elements from the complete collection in proper
     * sequence (from first to last cell element).
     * 
     * @return the array containing all of the cell elements.
     * 
     * @see java.util.List#toArray()
     */
    @Override
    public Object[] toArray() {
        return allCells.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        // TODO implement
        throw new UnsupportedOperationException("Not yet implemented!");
        // return null;
    }

    // ------------------------------------------------------------------------
    // Filter interface method implementations
    // ------------------------------------------------------------------------

    /**
     * // okay
     */
    @Override
    public void addFilter(Filter... filters) {
        if (filters.length == 0) {
            return;
        }
        for (Filter filter : filters) {
            filterChain.addFilter(filter);
        }
        buildfilteredCellList();
    }

    /**
     * Clears the filtered collection AND deactivates the filtering. If the filtering wouldn't be
     * deactivated, then the filtered collection would be rebuild from scratch using the complete
     * collection again.
     */
    @Override
    // TODO should we really have this operation?
    public void clearFiltered() {
        deactivateFiltering();
        filteredCells.clear();
    }

    /**
     * // okay
     */
    @Override
    public void removeFilter(String filterName) {
        filterChain.removeFilter(filterName);
        buildfilteredCellList();
    }

    /**
     * // okay
     */
    @Override
    public void activateFiltering() {
        filteringActive = true;
        buildfilteredCellList();
    }

    /**
     * // okay
     */
    @Override
    public void deactivateFiltering() {
        filteringActive = false;
    }

    /**
     * // okay
     */
    @Override
    public boolean isFilteringActive() {
        return filteringActive;
    }

    /**
     * // okay
     */
    @Override
    public void inverseFiltering() {
        filterChain.invertFilters();
        buildfilteredCellList();
    }

    /**
     * Returns the size of the <b>filtered</b> collection. The size can be the same size or less
     * than the total size of the collection depending on the filtering results.
     * 
     * @return the filtered collection size.
     */
    @Override
    public int filteredSize() {
        return filteredCells.size();
    }

    /**
     * Returns {@code true} if the <b>filtered</b> collection contains no elements.
     * 
     * @return {@code true} if the <b>filtered</b> collection contains no elements.
     */
    @Override
    public boolean isFilteredEmpty() {
        return filteredCells.isEmpty();
    }

    /**
     * Checks if the <b>filtered</b> collection contains the given object.<br>
     * 
     * @return TODO
     */
    @Override
    public boolean containsFiltered(Object o) {
        return filteredCells.contains(o);
    }

    // ------------------------------------------------------------------------

    /**
     * TODO
     */
    private void buildfilteredCellList() {
        // only build filtered list when filtering is active.
        if (filteringActive) {
            // TODO first clear the list if something has changed within the
            // filterChain how to find out if something has changed?
            // if (filtersChanged && !allCells.isEmpty() &&
            // !filteredCells.isEmpty())
            filteredCells.clear();
            // endif
        }
    }

    // ------------------------------------------------------------------------
    // Private Iterator and ListIterator implementations
    // ------------------------------------------------------------------------

    /**
     * TODO <br>
     * Needed to insure that when iterating over the allCells list, the allCells list AND
     * filteredCells list stay synchronized when removing cell elements.
     * 
     * @author JsaPar Developer
     */
    private class CellIterator implements Iterator<Cell> {

        private Iterator<Cell> cellIterator = null;

        CellIterator() {
            cellIterator = allCells.iterator();
        }

        @Override
        public boolean hasNext() {
            return cellIterator.hasNext();
        }

        @Override
        public Cell next() {
            return cellIterator.next();
        }

        @Override
        public void remove() {
            // TODO
            // 1. find out which element this is
            // 2. find the cell element in the filteredCells list
            // 3. remove the cell in the filteredCells list and in allCells list
            cellIterator.remove();
            // FIXME implement this correct!
            // filteredCells.remove(o);
        }
    }

    /**
     * TODO <br>
     * Needed to insure that when iterating over the allCells list, the allCells list AND
     * filteredCells list stay synchronized when removing cell elements.
     * 
     * @author JsaPar Developer
     */
    private class CellListIterator implements ListIterator<Cell> {

        private ListIterator<Cell> cellListIterator = null;

        CellListIterator() {
            cellListIterator = allCells.listIterator();
        }

        @Override
        public boolean hasNext() {
            return cellListIterator.hasNext();
        }

        @Override
        public Cell next() {
            return cellListIterator.next();
        }

        @Override
        public boolean hasPrevious() {
            return cellListIterator.hasPrevious();
        }

        @Override
        public Cell previous() {
            return cellListIterator.previous();
        }

        @Override
        public int nextIndex() {
            return cellListIterator.nextIndex();
        }

        @Override
        public int previousIndex() {
            return cellListIterator.previousIndex();
        }

        @Override
        public void remove() {
            // TODO rebuild filtered cell list
            cellListIterator.remove();
        }

        @Override
        public void set(Cell cell) {
            // TODO rebuild filtered cell list
            cellListIterator.set(cell);
        }

        @Override
        public void add(Cell cell) {
            // TODO rebuild filtered cell list
            cellListIterator.add(cell);
        }
    }

    @Override
    public boolean containsAllFiltered(Collection<?> c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Object[] toArrayFiltered() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T[] toArrayFiltered(T[] a) {
        // TODO Auto-generated method stub
        return null;
    }
}