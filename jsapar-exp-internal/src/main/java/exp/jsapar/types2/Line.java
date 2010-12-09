package exp.jsapar.types2;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import exp.jsapar.exception.AnnotationException;
import exp.jsapar.filters.Filter;
import exp.jsapar.filters.Filterable;
import exp.jsapar.lists.CellList;
import exp.jsapar.utils.AnnotationUtil;
import exp.jsapar.utils.EqualsUtil;
import exp.jsapar.utils.HashCodeUtil;
import exp.jsapar.utils.ParamsUtil;
import exp.jsapar.utils.StringUtil;

/**
 * The line type within the Section {@link exp.jsapar.types2.Section} object. A line contains a
 * number of cells specified by a list of {@link exp.jsapar.types2.Cell} objects.<br>
 * 
 * The line object can be iterated and filtered.
 * 
 * @author JsaPar Developer
 * 
 * @see exp.jsapar.types2.Section
 * @see exp.jsapar.types2.Cell
 */
public class Line implements Filterable<Cell>, Comparable<Line>, Serializable, Cloneable {
    /**
     * The Serial version ID for this class.
     */
    private static final long serialVersionUID = -7016620767748496214L;
    /**
     * The list of cell objects in this line.
     */
    private CellList cells = null;

    // TODO completely rewrite to use a CellList!

    // ------------------------------------------------------------------------

    /**
     * Constructs an empty {@link exp.jsapar.types2.Line} with zero {@link exp.jsapar.types2.Cell}
     * objects.
     */
    public Line() {
        cells = new CellList();
    }

    /**
     * Constructs a {@link exp.jsapar.types2.Line} with the given {@link exp.jsapar.types2.Cell}
     * object(s).
     * 
     * @param cells
     *            the given Cell object(s).
     */
    public Line(Cell... cells) {
        this();
        for (Cell cell : cells) {
            this.cells.add(cell);
        }
    }

    public Line(Collection<Cell> aCollection) {
        this();
        this.cells.addAll(aCollection);
    }

    /**
     * Constructs a {@link exp.jsapar.types2.Line} object with the {@link exp.jsapar.types2.Cell}
     * objects that are defined within the specified user objects. These user objects must contain
     * JsaPar annotations: @Line for the class and @Cell for the members of that class.
     * 
     * @param objects
     *            the user objects which are annotated with JsaPar annotations.
     * 
     * @throws AnnotationException
     *             thrown when a {@code @Line} annotation was not found in the specified class(es),
     *             or when no {@code @Cell} annotation was found in the specified class(es) for it's
     *             members.
     */
    public Line(Object... objects) {
        this();
        for (Object obj : objects) {
            ParamsUtil.checkForNullPointer(obj);
            addAnnotatedObject(obj);
        }
    }

    // move private method to end of file when fully implemented
    private void addAnnotatedObject(final Object obj) {
        // check objects that are specified for valid JsaPar annotations:
        // @Line and @Cell.

        // TODO throw exception when annotation missing for user class
        // (@Line), or when no @Cell annotations were discovered in the
        // user class.
        Object cellValue = null;
        Cell cell = null;

        // TODO read the @Line annotation attributes.
        // TODO check for duplicate cell names.
        // TODO check cell names in JsaPar Schema.
        // TODO what to do with the cell names that are not listed in a line
        // definition of the schema?
        // ignore them? throw exception?
        // TODO how to deal with arrays of primitives?
        Class<?> cls = obj.getClass();
        List<Field> annotatedCellFields = AnnotationUtil.getCellAnnotatedFields(cls);

        if (annotatedCellFields.isEmpty()) {
            throw new AnnotationException("No JsaPar specific annotations found in given class.");
        }

        for (Field field : annotatedCellFields) {
            // suppress Java language access checking for the current field.
            field.setAccessible(true);

            String cellName = AnnotationUtil.getCellNameFromAnnotatedField(field);
            try {
                cellValue = field.get(obj);
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
            } catch (IllegalAccessException e) {
                // Intentionally swallowed because the accessibility is actively
                // forced by setting the accessibility of the field to true,
                // before it is accessed.
            }

            if (cellValue != null) {
                cell = new Cell(cellName, cellValue);
                this.cells.add(cell);
            }
            // restore Java language access checking for the current field.
            field.setAccessible(false);
        }
    }

    // ------------------------------------------------------------------------

    /**
     * Adds a cell to the list of cells. This method is always applied to the <tt>full</tt> list of
     * cells.
     * 
     * @param cell
     *            the cell object to be added to the list of cells.
     * @throws IllegalArgumentException
     *             thrown when duplicate cell detected.
     * @throws NullPointerException
     *             thrown when cell is {@code null}.
     */
    public void addCell(Cell cell) {
        ParamsUtil.checkForNullPointer(cell);
        cells.add(cell);
    }

    public void addCells(Object... annotatedObjects) {
        // TODO add cells coming from the annotated user classes

        // duplicate code: see ctor!
        for (Object obj : annotatedObjects) {
            if (obj != null) {
                // change to util static class
            }
            // check objects that are specified for valid JsaPar annotations:
            // @Line and @Cell.
            // TODO throw exception when annotation missing for user class
            // (@Line), or when no @Cell annotations were discovered in the
            // user class.
        }

        throw new AnnotationException(""); // TODO
    }

    /**
     * Gets the cell with the given index.<br>
     * <br>
     * Note: IndexOutOfBoundsExceptions are swallowed, and {@code null} is returned instead.
     * 
     * @param index
     *            the index of the cell.
     * @return the cell at the given index or {@code null} when there is no cell at the given index.
     */
    public Cell getCell(int index) {
        Cell retval = null;
        try {
            retval = cells.get(index);
        } catch (IndexOutOfBoundsException e) {
            retval = null;
        }
        return retval;
    }

    /**
     * Gets the cell with the given name.
     * 
     * @param name
     *            the name of the cell.
     * @return the cell with the given name or {@code null} when there is no cell with that name.
     */
    public Cell getCell(String name) {
        ParamsUtil.checkForNullPointer(name);
        return cells.get(name);
    }

    /**
     * Gets the list of cells. This method always returns the <tt>full</tt> list of cells without
     * any filtering of cells.
     * 
     * @return the list containing the cells.
     */
    public List<Cell> getCells() {
        return cells;
    }

    /**
     * Returns the number of cells available in this Line object.
     * 
     * @return the number of cells.
     */
    public int getNumberOfCells() {
        return cells.size();
    }

    /**
     * Inserts a cell object in the list of cells at the given index. This method is always applied
     * to the <tt>full</tt> list of cells.
     * 
     * @param cell
     *            the cell object to be inserted into the list of cells.
     * @param index
     *            the index in the list where the cell should be inserted.
     * @throws IllegalArgumentException
     *             thrown when duplicate cell detected.
     * @throws NullPointerException
     *             thrown when cell is {@code null}.
     */
    public void insertCell(Cell cell, int index) {
        ParamsUtil.checkForNullPointer(cell);
        // if (cellsByName.containsKey(cell.getName())) {
        // // name already exists in this Line object!
        // throw new IllegalArgumentException("Duplicate cell name: "
        // + cell.getName());
        // }
        cells.add(index, cell);
        // cellsByName.put(cell.getName(), cell);
    }

    /**
     * TODO
     * 
     * @return
     */
    public boolean containsCell(String name) {
        return cells.contains(name);
    }

    /**
     * Checks if the line is empty. If the list of cells is empty, then the line is empty too.
     * 
     * @return {@code true} when line is empty, {@code false} when line is not empty.
     */
    public boolean isEmpty() {
        return (cells.isEmpty());
    }

    /**
     * Removes a cell object from the list of cells at the given index.
     * 
     * Note: IndexOutOfBoundsExceptions are swallowed, and {@code null} is returned instead.
     * 
     * @param index
     *            the index in the list where the cell must be removed.
     * 
     * @return the cell object that is removed from the list of cells.
     */
    public Cell removeCell(int index) {
        Cell removedCell = null;
        try {
            removedCell = cells.remove(index);
            // cellsByName.remove(removedCell.getName());
        } catch (IndexOutOfBoundsException e) {
            // swallow exception and return null.
            removedCell = null;
        }
        return removedCell;
    }

    /**
     * Removes a cell object from the list of cells at the given index.
     * 
     * @param name
     *            the name of the cell to be removed.
     * 
     * @return the cell object that is removed from the list of cells, or {@code null} when there is
     *         no cell with that name.
     */
    public Cell removeCell(String name) {
        // TODO
        return null;
    }

    /**
     * Replaces a cell object from the list with the given cell object.<br>
     * The cell that is being replaced has the same cell name as the new cell. If you want to
     * replace a cell in the list that has a different cell name than the one in the list, use the
     * {@link #replaceCell(Cell, String)} method.
     * 
     * @param cell
     *            the cell object to be replaced with the old cell in the list of cells.
     * 
     * @return the cell object from the list that is replaced by the new cell.
     */
    public Cell replaceCell(Cell cell) {
        ParamsUtil.checkForNullPointer(cell);
        // TODO
        return cell;
    }

    /**
     * Replaces a cell object from the list at the given index with the given cell object.
     * 
     * @param cell
     *            the cell object to be replaced with the old cell in the list of cells.
     * @param index
     *            the index in the list where the cell must be removed.
     * @return the cell object from the list that is replaced by the new cell.
     */
    public Cell replaceCell(Cell cell, int index) {
        Cell currentCell = null;
        ParamsUtil.checkForNullPointer(cell);

        // first check if new cell is not already present in list of cells.
        currentCell = cells.get(index);
        // if (!cellsByName.containsKey(cell.getName())
        // && !cell.getName().equals(currentCell.getName())) {
        // // name of new cell not in map: remove cell from list and map.
        // currentCell = cells.remove(index);
        // cellsByName.remove(currentCell.getName());
        // cells.add(index, cell);
        // cellsByName.put(cell.getName(), cell);
        // }
        return currentCell;
    }

    /**
     * Replaces a cell object from the list with the specified cell name with the given cell object.
     * Use this method when the cell name of the new cell differs from the old cell. If cell names
     * are equal, use the {@link #replaceCell(Cell)} method instead.
     * 
     * @param cell
     *            the cell object to be replaced with the old cell in the list of cells.
     * @param cellName
     *            the name of the old cell that is being replaced.
     * 
     * @return the cell object from the list that is replaced by the new cell.
     */
    public Cell replaceCell(Cell cell, String cellName) {
        // TODO
        return null;
    }

    /**
     * Sets the list of cells. This method is always applied to the <tt>full</tt> list of cells.
     * 
     * @param cells
     *            the list containing the cells.
     * @throws IllegalArgumentException
     *             thrown when duplicate cell detected.
     * @throws NullPointerException
     *             thrown when cell name is {@code null}.
     */
    public void setCells(List<Cell> cells) {
        for (Cell cell : cells) {
            ParamsUtil.checkForNullPointer(cell);
        }
        setCellListAndCellMap(cells); // TODO rewrite to CellList
    }

    // ------------------------------------------------------------------------

    /**
     * Compares this Line object with the specified Line object for order. Returns a negative
     * integer, zero, or a positive integer as this Line is less than, equal to, or greater than the
     * specified Line.
     */
    @Override
    public int compareTo(Line line) {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * Provides an iterator for iterating over the cell list.
     */
    @Override
    public Iterator<Cell> iterator() {
        return null; // TODO
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
    public void removeFilter(Filter filter) {
        // TODO Auto-generated method stub

    }

    // ------------------------------------------------------------------------

    /**
     * Returns the textual representation of a exp.jsapar.types2.Line, including all the elements
     * within this exp.jsapar.types2.Line as a textual representation.
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        Cell cell = null;
        String str = null;
        StringBuilder representation = new StringBuilder();

        for (int index = 0; index < cells.size(); index++) {
            cell = cells.get(index);
            str = cell.toString();
            // check for null otherwise 'null' gets added as a string.
            if (str != null) {
                representation.append("Cell " + StringUtil.getLeadingZerosString(index, cells.size()) + index + ": ");
                representation.append(str);
                // TODO check if next cell is displayed afterwards
            }
        }
        return representation.toString();
    }

    /**
     * Compares this Line with the specified Line for equality.
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
        if (!(aThat instanceof Line)) {
            return false;
        }
        // cast to native object is now safe
        Line that = (Line) aThat;

        // compare the content of the cell lists.
        return EqualsUtil.areEqual(this.cells, that.cells);
    }

    /**
     * Returns a hash code value for this Line.
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int result = HashCodeUtil.SEED;
        for (Cell cell : cells) {
            result = HashCodeUtil.hash(result, cell);
        }
        return result;
    }

    // ------------------------------------------------------------------------

    // TO BE REMOVED
    private void setCellListItemAndCellMapItem(final Cell cell) {
        // if (allCellsByName.containsKey(cell.getName())) {
        // throw new IllegalArgumentException("Duplicate cell name: " + cell.getName());
        // }
        // this.cells.add(cell);
        // this.cellsByName.put(cell.getName(), cell);
    }

    /**
     * Sets the list of cells and the map of cells. The cell name is used as key in the map, the
     * cell itself is used as the value. This results in a <cell name, cell object> pair that is
     * used for quick lookup of the cell object matching the cell name.
     * 
     * @param cells
     *            the list of cells that needs to be added to the map of cells.
     * @throws IllegalArgumentException
     *             thrown when duplicate cell detected
     * @throws NullPointerException
     *             thrown when cell name is {@code null}.
     */
    private void setCellListAndCellMap(final List<Cell> cells) {
        // set collection used for duplication checking.
        Set<String> cellSet = new HashSet<String>();
        // internal map collection used as buffer before Line map gets
        // overridden.
        Map<String, Cell> cellMap = new HashMap<String, Cell>();

        // check if the list doesn't contain duplicate cell names.
        for (Cell cell : cells) {
            try {
                if (!cellSet.add(cell.getName())) {
                    throw new IllegalArgumentException("Duplicate cell name: " + cell.getName());
                }
                cellMap.put(cell.getName(), cell);
            } catch (NullPointerException e) {
                throw new NullPointerException("Cell name is NULL.");
            }
        }
        // this.cells = cells;
        // this.cellsByName = cellMap;
    }
}