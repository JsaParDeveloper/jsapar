package org.jsapar;

import java.io.Serializable;
import java.util.Iterator;

import org.jsapar.Cell.CellType;

/**
 * A line is one row of the input buffer. Each line contains a list of cells. Cells can be retrieved
 * either by index O(1) or by name O(n). Note that the class is not synchronized internally. If
 * multiple threads access the same instance, external synchronization is required.
 * 
 * @author Jonas Stenberg
 * 
 */
public class Line implements Serializable {

    /**
     * 
     */
    private static final long               serialVersionUID = 6026541900371948402L;
    public static final String             EMPTY            = "";

    private java.util.ArrayList<Cell>       cellsByIndex     = null;

    private java.util.HashMap<String, Cell> cellsByName      = null;

    /**
     * Line type.
     */
    private String                          lineType         = EMPTY;

    /**
     * Creates an empty line without any cells.
     */
    public Line() {
        this.cellsByIndex = new java.util.ArrayList<Cell>();
        this.cellsByName = new java.util.HashMap<String, Cell>();
    }

    /**
     * Creates an empty line without any cells but with an initial capacity.
     * 
     * @param nInitialCapacity
     *            The initial capacity. Used only to reserve space. If capacity is exceeded, the
     *            capacity grows automatically.
     */
    public Line(int nInitialCapacity) {
        this.cellsByIndex = new java.util.ArrayList<Cell>(nInitialCapacity);
        this.cellsByName = new java.util.HashMap<String, Cell>(nInitialCapacity);

    }

    /**
     * Creates an empty line of a specified type, without any cells but with an initial capacity.
     * 
     * @param sLineType
     *            The type of the line.
     */
    public Line(String sLineType) {
        this();
        lineType = sLineType;
    }

    /**
     * Creates an empty line of a specified type, without any cells but with an initial capacity.
     * 
     * @param sLineType
     *            The type of the line.
     * @param nInitialCapacity
     *            The initial capacity. Used only to reserve space. If capacity is exceeded, the
     *            capacity grows automatically.
     */
    public Line(String sLineType, int nInitialCapacity) {
        this(nInitialCapacity);
        lineType = sLineType;
    }

    /**
     * For better performance while iterating multiple lines, it is better to call the
     * {@link #getCellIterator()} method.
     * 
     * @return A clone of the internal collection that contains all the cells of this documents.
     *         Altering the returned collection will not alter the original collection of the this
     *         Line.
     * @see #getCellIterator()
     */
    @SuppressWarnings("unchecked")
    public java.util.List<Cell> getCells() {
        return (java.util.List<Cell>) cellsByIndex.clone();
    }

    /**
     * Returns an iterator that will iterate all the cells of this line.
     * 
     * @return An iterator that will iterate all the cells of this line.
     */
    public Iterator<Cell> getCellIterator() {
        return cellsByIndex.iterator();
    }

    /**
     * Adds a cell to the cellsByName list.
     * 
     * @param cell
     *            The cell to add
     * @throws JSaParException
     *             If a cell with the same name already exists.
     */
    private void addCellByName(Cell cell) throws JSaParException {
        Cell oldCell = cellsByName.get(cell.getName());
        if (oldCell != null)
            throw new JSaParException("A cell with the name '" + cell.getName()
                    + "' already exists. Failed to add cell.");
        this.cellsByName.put(cell.getName(), cell);
    }

    /**
     * Adds a cell to the end of the line.
     * 
     * @param cell
     *            The cell to add
     * @throws JSaParException
     */
    public void addCell(Cell cell) throws JSaParException {
        this.cellsByIndex.add(cell);
        if (cell.getName() != null)
            addCellByName(cell);
    }

    /**
     * Removes cell with the given name.
     * 
     * @param sName
     *            The name of the cell to remove.
     * @return The removed cell
     */
    public Cell removeCell(String sName) {
        Cell foundCell = this.cellsByName.remove(sName);
        if (foundCell != null) {
            Iterator<Cell> i = this.cellsByIndex.iterator();
            while (i.hasNext()) {
                if (sName.equals(i.next().getName())) {
                    i.remove();
                    break;
                }
            }
        }
        return foundCell;

    }

    /**
     * Removes cell with specified index. Cells to the right of the removed cell will be moved one
     * step to the left.
     * 
     * @param index
     *            The index of the cell to remove.
     * @return The removed cell or null if the index was out of bounds.
     */
    public Cell removeCell(int index) {
        Cell removed = null;
        if (this.cellsByIndex.size() > index && this.cellsByIndex.get(index) != null) {
            removed = this.cellsByIndex.remove(index);
            if (removed.getName() != null)
                this.cellsByName.remove(removed.getName());
        }
        return removed;
    }

    /**
     * Adds a cell to the line end of the line, replacing any existing cell with the same name.
     * 
     * @param cell
     *            The cell to add
     * @return The replaced cell or null if there were no cell within the line with the same name.
     * @throws JSaParException
     */
    public Cell replaceCell(Cell cell) {
        Cell foundCell = null;
        if (cell.getName() != null) {
            foundCell = this.cellsByName.put(cell.getName(), cell);
            if (foundCell != null) {
                Iterator<Cell> i = this.cellsByIndex.iterator();
                while (i.hasNext()) {
                    if (cell.getName().equals(i.next().getName()))
                        i.remove();
                }
            }
        }
        this.cellsByIndex.add(cell);
        return foundCell;
    }

    /**
     * Adds a cell at specified index of a line. First cell has index 0. Existing cells to the right
     * of the new cell will have incremented indexes.
     * 
     * @param cell
     *            The cell to add
     * @param index
     *            The index the cell will have in the line.
     */
    public void addCell(Cell cell, int index) {
        this.cellsByIndex.add(index, cell);
        if (cell.getName() != null)
            this.cellsByName.put(cell.getName(), cell);
    }

    /**
     * Replaces a cell at specified index of a line. First cell has index 0.<br>
     * Note that if the line contains another cell with the name same name as the supplied cell,
     * both that cell and the cell at the specified index will be removed. This can lead to quite
     * unexpected behavior since this also affects the index of all cells to the left of the second
     * removed cell.
     * 
     * @param cell
     *            The cell to add
     * @param index
     *            The index the cell will have in the line.
     * @return The replaced cell (at the index) or null if there were no cell within the line at
     *         that index.
     */
    public Cell replaceCell(Cell cell, int index) {
        Cell removed = null;
        if (this.cellsByIndex.size() > index) {
            removed = this.cellsByIndex.set(index, cell);
            if (removed.getName() != null) {
                this.cellsByName.remove(removed.getName());
            }
        }
        if (cell.getName() != null) {
            Cell second = this.cellsByName.put(cell.getName(), cell);
            if (second != null && second != removed) {
                Iterator<Cell> i = this.cellsByIndex.iterator();
                while (i.hasNext()) {
                    Cell current = i.next();
                    if (cell.getName().equals(current.getName()) && cell != current) {
                        i.remove();
                        break;
                    }
                }
            }
        }
        return removed;
    }

    /**
     * Gets a cell at specified index. First cell has index 0.
     * 
     * @param index
     * @return The cell
     */
    public Cell getCell(int index) {
        return this.cellsByIndex.get(index);
    }

    /**
     * Gets a cell with specified name. Name is specified by the schema.
     * 
     * @param name
     * @return The cell or null if there is no cell with specified name.
     */
    public Cell getCell(String name) {
        return this.cellsByName.get(name);
    }

    /**
     * Gets the number of cells that this line contains.
     * 
     * @return the number of cells that this line contains.
     */
    public int getNumberOfCells() {
        return this.cellsByIndex.size();
    }

    /**
     * Returns the type of this line. The line type attribute is primarily used when parsing lines
     * of different types, distinguished by a control cell.
     * 
     * @return the lineType or an empty string if no line type has been set.
     */
    public String getLineType() {
        return lineType;
    }

    /**
     * Sets the type of this line. The line type attribute is primarily used when parsing lines of
     * different types, distinguished by a control cell.
     * 
     * @param lineType
     *            the lineType to set. Can not be null. Use empty string if there is no better
     *            value.
     */
    public void setLineType(String lineType) {
        if (lineType == null)
            throw new IllegalArgumentException("Line.lineType can not be set to null value.");
        this.lineType = lineType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.lineType != null && this.lineType.length() > 0) {
            sb.append("Line type=");
            sb.append(this.lineType);
            sb.append(", ");
        }
        sb.append("Cells: ");
        sb.append(this.cellsByIndex);
        return sb.toString();
    }
    
    /**
     * @param cellName
     * @return True if there is a cell with the specified name, false otherwise.
     */
    boolean isCell(String cellName){
        return this.getCell(cellName) != null ? true : false;
    }

    /**
     * Utility function that adds a cell with the specified name and value to the end of the line or
     * replaces an existing cell if there already is one with the same name.
     * 
     * @param cellName
     *            The name of the cell to add/replace.
     * @param value
     *            The string value to set.
     */
    public void setCellValue(String cellName, String value) {
        this.replaceCell(new StringCell(cellName, value));
    }

    /**
     * Utility function that adds a cell with the specified name and value to the end of the line or
     * replaces an existing cell if there already is one with the same name.
     * 
     * @param cellName
     *            The name of the cell to add/replace.
     * @param value
     *            The integer value to set.
     */
    public void setCellValue(String cellName, int value) {
        this.replaceCell(new IntegerCell(cellName, value));
    }

    /**
     * Utility function that adds a cell with the specified name and value to the end of the line or
     * replaces an existing cell if there already is one with the same name.
     * 
     * @param cellName
     *            The name of the cell to add/replace.
     * @param value
     *            The long integer value to set.
     */
    public void setCellValue(String cellName, long value) {
        this.replaceCell(new IntegerCell(cellName, value));
    }
    
    /**
     * Utility function that adds a cell with the specified name and value to the end of the line or
     * replaces an existing cell if there already is one with the same name.
     * 
     * @param cellName
     *            The name of the cell to add/replace.
     * @param value
     *            The double value to set.
     */
    public void setCellValue(String cellName, double value) {
        this.replaceCell(new FloatCell(cellName, value));
    }
    
    /**
     * Utility function that gets the string cell value of the specified cell.
     * 
     * @param cellName
     * @return The value of the specified cell or null if there is no such cell.
     */
    public String getStringCellValue(String cellName) {
        Cell cell = this.getCell(cellName);
        return (cell != null) ? cell.getStringValue() : null;
    }

    /**
     * @param cellName
     * @return The cell with the specified name.
     * @throws JSaParException if the cell does not exist.
     */
    private Cell getExistingCell(String cellName) throws JSaParException {
        Cell cell = this.getCell(cellName);
        if (cell == null)
            throw new JSaParException("There is no cell with the name '" + cellName + "' in this line");
        return cell;
    }

    /**
     * Utility function that gets the integer cell value of the specified cell. If the specified
     * cell does not exist, a JSaparException is thrown. Tries to parse an integer value if cell is
     * not of type IntegerCell. Throws a NumberFormatException if the value is not a parable integer.
     * 
     * @param cellName
     * @return The integer value of the cell with the specified name.
     * @throws JSaParException
     *             , NumberFormatException
     */
    public int getIntCellValue(String cellName) throws JSaParException, NumberFormatException {
        Cell cell = getExistingCell(cellName);
        if (cell instanceof NumberCell) {
            NumberCell numberCell = (NumberCell) cell;
            return numberCell.getNumberValue().intValue();
        }

        return Integer.parseInt(cell.getStringValue());
    }

    
    /**
     * Utility function that gets the double cell value of the specified cell. If the specified
     * cell does not exist, a JSaparException is thrown. Tries to parse a double value if cell is
     * not of type IntegerCell. Throws a NumberFormatException if the value is not a parable double.
     * 
     * @param cellName
     * @return The double value of the cell with the specified name.
     * @throws JSaParException
     *             , NumberFormatException
     */
    public double getDoubleCellValue(String cellName) throws JSaParException, NumberFormatException {
        Cell cell = getExistingCell(cellName);
        if (cell instanceof NumberCell) {
            NumberCell numberCell = (NumberCell) cell;
            return numberCell.getNumberValue().doubleValue();
        }

        return Double.parseDouble(cell.getStringValue());
    }

    /**
     * @param cellName
     * @return true if the cell with the specified name contains a value. 
     */
    public boolean isCellSet(String cellName) {
        Cell cell = getCell(cellName);
        if(cell == null)
            return false;
        
        if(cell instanceof EmptyCell)
            return false;
        
        return true;
    }

    /**
     * @param cellName
     * @param type
     * @return true if the cell with the specified name contains a value of the specified type. 
     */
    public boolean isCellSet(String cellName, CellType type) {
        Cell cell = getCell(cellName);
        if(cell == null)
            return false;
        
        if(cell instanceof EmptyCell)
            return false;
        
        return cell.getCellType().equals(type);
    }

    
}