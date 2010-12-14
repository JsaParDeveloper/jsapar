package exp.jsapar.types2;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

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
            ParamsUtil.checkForNullPointer(cell);
            this.cells.add(cell);
        }
    }

    /**
     * Constructs a {@link exp.jsapar.types2.Line} with the given collection of
     * {@link exp.jsapar.types2.Cell} object(s).
     * 
     * @param cellCollection
     *            the collection of Cell objects to be added to the Line.
     */
    public Line(Collection<Cell> cellCollection) {
        this();
        ParamsUtil.checkForNullPointer(cellCollection);
        this.cells.addAll(cellCollection);
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

    // ------------------------------------------------------------------------

    /**
     * Adds the cell object(s) to the list of cells.
     * 
     * @param cells
     *            the cell object(s) to be added to the list of cells.
     * 
     * @throws IllegalArgumentException
     *             thrown when duplicate cell detected.
     * @throws NullPointerException
     *             thrown when cell name is {@code null}.
     */
    public void addCells(Cell... cells) {
        for (Cell cell : cells) {
            ParamsUtil.checkForNullPointer(cell);
            this.cells.add(cell);
        }
    }

    /**
     * Adds the given collection of {@link exp.jsapar.types2.Cell} object(s) to the list of cells.
     * 
     * @param cellCollection
     *            the collection of Cell objects to be added to the Line.
     */
    public void addCells(Collection<Cell> cellCollection) {
        ParamsUtil.checkForNullPointer(cellCollection);
        this.cells.addAll(cellCollection);
    }

    /**
     * Adds the @Cell annotated fields of the user object(s) to the list of
     * {@link exp.jsapar.types2.Cell} object(s). These user objects must contain JsaPar annotations: @Line
     * for the class and @Cell for the members of that class.
     * 
     * @param objects
     *            the user objects which are annotated with JsaPar annotations.
     * 
     * @throws IllegalArgumentException
     *             thrown when duplicate cell detected.
     * @throws NullPointerException
     *             thrown when cell name is {@code null}.
     */
    public void addCellsOfAnnotatedObjects(Object... objects) {
        for (Object obj : objects) {
            ParamsUtil.checkForNullPointer(obj);
            addAnnotatedObject(obj);
        }
    }

    /**
     * Removes all of the cell objects within this Line object.
     */
    public void clear() {
        this.cells.clear();
    }

    /**
     * Gets the cell with the given index.
     * 
     * @param index
     *            the index of the cell.
     * 
     * @return the cell at the given index.
     */
    public Cell getCell(int index) {
        return cells.get(index);
    }

    /**
     * Gets the cell with the given name.
     * 
     * @param name
     *            the name of the cell.
     * 
     * @return the cell with the given name or {@code null} when there is no cell with that name.
     */
    public Cell getCell(String name) {
        ParamsUtil.checkForNullPointer(name);
        ParamsUtil.checkForEmptyString(name);
        return cells.get(name);
    }

    /**
     * Gets the list of cells. This method always returns the <tt>full</tt> list of cells without
     * any filtering of cells.
     * 
     * @return the list containing the cells.
     */
    public List<Cell> getCells() {
        return Collections.unmodifiableList(cells);
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
     * Inserts a cell object in the list of cells at the given index.
     * 
     * @param index
     *            the index in the list where the cell should be inserted.
     * @param cell
     *            the cell object to be inserted into the list of cells.
     * 
     * @throws IllegalArgumentException
     *             thrown when duplicate cell detected.
     * @throws NullPointerException
     *             thrown when cell is {@code null}.
     */
    public void insertCell(int index, Cell cell) {
        ParamsUtil.checkForNullPointer(cell);
        cells.add(index, cell);
    }

    /**
     * Inserts a cell object in the list of cells at the <u>beginning</u> of the list.
     * 
     * @param cell
     *            the cell object to be inserted into the list of cells.
     * 
     * @throws IllegalArgumentException
     *             thrown when duplicate cell detected.
     * @throws NullPointerException
     *             thrown when cell is {@code null}.
     */
    public void insertCell(Cell cell) {
        ParamsUtil.checkForNullPointer(cell);
        cells.insertCell(cell);
    }

    /**
     * Inserts a cell object in the list of cells <u>after</u> the given cell name.
     * 
     * @param cellName
     *            the cell name of the cell that is already in the list.
     * @param cell
     *            the cell object to be inserted into the list of cells.
     * 
     * @throws IllegalArgumentException
     *             thrown when duplicate cell detected.
     * @throws NullPointerException
     *             thrown when cell is {@code null}.
     */
    // FIXME should this really be working like this?
    public void insertCell(String cellName, Cell cell) {
        ParamsUtil.checkForNullPointer(cell);
        ParamsUtil.checkForNullPointer(cellName);
        ParamsUtil.checkForEmptyString(cellName);
        cells.insertCell(cellName, cell);
    }

    /**
     * Checks if the cell list contains a cell with the specified cell name.
     * 
     * @return {@code true} if a cell with the specified cell name exists in the cell list,
     *         {@code false} otherwise.
     */
    public boolean containsCell(String cellName) {
        ParamsUtil.checkForNullPointer(cellName);
        ParamsUtil.checkForEmptyString(cellName);
        return cells.contains(cellName);
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
     * Removes a cell object from the list of cells at the specified index.
     * 
     * @param index
     *            the index in the list where the cell must be removed.
     * 
     * @return the cell object that is removed from the list of cells.
     */
    public Cell removeCell(int index) {
        return cells.remove(index);
    }

    /**
     * Removes the cell object from the list of cells with the specified cell name.
     * 
     * @param name
     *            the name of the cell to be removed.
     * 
     * @return the cell object that is removed from the list of cells, or {@code null} when there is
     *         no cell with that name.
     */
    public Cell removeCell(String name) {
        ParamsUtil.checkForNullPointer(name);
        ParamsUtil.checkForEmptyString(name);
        Cell removedCell = cells.remove(name);
        return removedCell;
    }

    /**
     * Replaces a cell object from the list with the given cell object.<br>
     * The cell that is being replaced has the same cell name as the new cell. If you want to
     * replace a cell in the list that has a different cell name than the one in the list, use the
     * {@link #replaceCell(String, Cell)} method.
     * 
     * @param cell
     *            the cell object to be replaced with the old cell in the list of cells.
     * 
     * @return the cell object from the list that is replaced by the new cell, or
     *         {@code null) when the cell was absent in the list. 
     */
    public Cell replaceCell(Cell cell) {
        ParamsUtil.checkForNullPointer(cell);
        return cells.replace(cell);
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
    public Cell replaceCell(int index, Cell cell) {
        ParamsUtil.checkForNullPointer(cell);
        return cells.replace(index, cell);
    }

    /**
     * Replaces the cell object from the list that has the specified cell name with the given cell
     * object. Use this method when the cell name of the new cell differs from the old cell. If cell
     * names are equal, use the {@link #replaceCell(Cell)} method instead.
     * 
     * @param cellName
     *            the name of the old cell that is being replaced.
     * @param cell
     *            the cell object to be replaced with the old cell in the list of cells.
     * 
     * @return the cell object from the list that is replaced by the new cell.
     */
    public Cell replaceCell(String cellName, Cell cell) {
        ParamsUtil.checkForNullPointer(cell);
        ParamsUtil.checkForNullPointer(cellName);
        ParamsUtil.checkForEmptyString(cellName);
        return cells.replace(cellName, cell);
    }

    /**
     * Sets the list of cells. This operation overrides the current list of cells.
     * 
     * @param cells
     *            the list containing the cells.
     * 
     * @throws IllegalArgumentException
     *             thrown when duplicate cell detected.
     * @throws NullPointerException
     *             thrown when cell name is {@code null}.
     */
    public void setCells(List<Cell> cells) {
        ParamsUtil.checkForNullPointer(cells);
        for (Cell cell : cells) {
            ParamsUtil.checkForNullPointer(cell);
        }
        this.cells.setCells(cells);
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
        for (Filter filter : filters) {
            ParamsUtil.checkForNullPointer(filter);
            cells.addFilter(filter);
        }
    }

    @Override
    public Filter getFilter(String filterName) {
        ParamsUtil.checkForNullPointer(filterName);
        ParamsUtil.checkForEmptyString(filterName);
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
        ParamsUtil.checkForNullPointer(filterName);
        ParamsUtil.checkForEmptyString(filterName);
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
        ParamsUtil.checkForNullPointer(filterName);
        ParamsUtil.checkForEmptyString(filterName);
        cells.removeFilter(filterName);
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
                representation.append("Cell ");
                representation.append(StringUtil.getLeadingZerosString(index, cells.size()));
                representation.append(index);
                representation.append(": ");
                representation.append(str);
                representation.append("\n");
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
        ParamsUtil.checkForNullPointer(aThat);
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

    /**
     * Adds the @Cell annotated fields of this object to the list of cells.
     * 
     * @param obj
     *            the annotated object that holds the @Cell annotated fields.
     */
    private void addAnnotatedObject(final Object obj) {
        // check objects that are specified for valid JsaPar annotations:
        // @Line and @Cell.

        // TODO throw exception when annotation missing for user class
        // (@Line), or when no @Cell annotations were discovered in the
        // user class.

        Object cellValue = null;
        Cell cell = null;

        // TODO read the @Line annotation attributes. how to deal with arrays of primitives in user
        // class as field?
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
                // TODO what to do with this exception?
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
}