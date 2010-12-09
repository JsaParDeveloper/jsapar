package exp.jsapar.types2;

import java.io.Serializable;
import java.util.Date;

import exp.jsapar.utils.DateUtil;
import exp.jsapar.utils.EqualsUtil;
import exp.jsapar.utils.HashCodeUtil;
import exp.jsapar.utils.ParamsUtil;
import exp.jsapar.utils.TypesUtil;

/**
 * The cell type within the Line {@link exp.jsapar.types2.Line} object. A cell
 * has a name and a value. The value type is generic, meaning that the value
 * type can be any of the normal data types available within java.<br>
 * <br>
 * The supported data types for use within the JsaPar library are:
 * <ul>
 * <li> {@link java.lang.Byte}
 * <li> {@link java.lang.Boolean}
 * <li> {@link java.lang.Character}
 * <li> {@link java.lang.Double}
 * <li> {@link java.lang.Enum}
 * <li> {@link java.lang.Float}
 * <li> {@link java.lang.Integer}
 * <li> {@link java.lang.Long}
 * <li> {@link java.lang.Short}
 * <li> {@link java.lang.String}
 * <li> {@link java.math.BigDecimal}
 * <li> {@link java.math.BigInteger}
 * <li> {@link java.util.Date}
 * </ul>
 * Trying to add a data type that is not supported for use within the JsaPar
 * library results in a thrown {@link IllegalArgumentException} exception.<br>
 * <br>
 * 
 * @throws IllegalArgumentException
 *             thrown when data type of the cell is not supported, or when cell
 *             name is empty.
 * @throws NullPointerException
 *             thrown when cell name is {@code null}.
 * 
 * @author JsaPar Developer
 */
public class Cell implements Serializable, Cloneable {
	/**
	 * The Serial version ID for this class.
	 */
	private static final long serialVersionUID = -8544646748499079406L;
	/**
	 * The name of the cell.
	 */
	private String name;
	/**
	 * The value of the cell.
	 */
	private Object value;

	// ------------------------------------------------------------------------

	/**
	 * Private constructor prevents instantiation from other classes.
	 */
	@SuppressWarnings("unused")
	private Cell() {
		// Intentionally left blank.
	}

	/**
	 * Constructs an {@link exp.jsapar.types2.Cell} with a cell name and no cell
	 * value.
	 * 
	 * @param name
	 *            the name of the cell. The name must be a valid String that is
	 *            not {@code null} and is not empty.
	 */
	public Cell(String name) {
		ParamsUtil.checkForNullPointer(name);
		ParamsUtil.checkForEmptyString(name);
		this.name = name;
	}

	/**
	 * Constructs an {@link exp.jsapar.types2.Cell} with a cell name and cell
	 * value specific to the given generic type definition.
	 * 
	 * @param <T>
	 *            the data type that is used for the value of the cell. The data
	 *            type must be one of the supported data types, see class
	 *            comments in {@link exp.jsapar2.types.Cell} for details.
	 * @param name
	 *            the name of the cell. The name must be a valid String that is
	 *            not {@code null} and is not empty.
	 * @param value
	 *            the value of the cell. The value can be set to null.
	 * @throws NullPointerException
	 *             thrown when cell name is {@code null}.
	 * @throws IllegalArgumentException
	 *             thrown when data type of the cell is not supported, or when
	 *             cell name is empty.
	 */
	public <T> Cell(String name, T value) {
		ParamsUtil.checkForNullPointer(name);
		ParamsUtil.checkForEmptyString(name);

		if (TypesUtil.isValidDataType(value)) {
			this.name = name;
			this.value = value;
		} else {
			throw new IllegalArgumentException(
					"The data type of the cell is not supported.");
		}
	}

	// ------------------------------------------------------------------------

	/**
	 * Gets the name of the cell.
	 * 
	 * @return the name of the cell.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the value of the cell. The type of the cell is already determined
	 * when constructed or set.
	 * 
	 * @param <T>
	 *            the type of the value hold by the cell.
	 * 
	 * @return the value and type of the value.
	 */
	@SuppressWarnings("unchecked")
	public <T> T getValue() {
		return (T) value;
	}

	/**
	 * Sets the value and type of the cell.
	 * 
	 * @param <T>
	 *            the type of the value hold by the cell.
	 * @param value
	 *            the value of the cell. The value can be set to null.
	 * @throws IllegalArgumentException
	 *             thrown when the data type of the cell value is not supported.
	 */
	public <T> void setValue(T value) {
		if (TypesUtil.isValidDataType(value)) {
			this.value = value;
		} else {
			throw new IllegalArgumentException();
		}
	}

	// ------------------------------------------------------------------------

	/**
	 * Indicates whether some other object is "equal to" this cell.
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
		if (!(aThat instanceof Cell)) {
			return false;
		}
		// cast to native object is now safe
		Cell that = (Cell) aThat;

		// FIXME also test the name of the cell?
		// should the test only work for the value? No matter what type?
		// this = double(5.0), that = float(5.0) -> still equals?

		// now a proper field-by-field evaluation can be made
		return EqualsUtil.areEqual(this.name, that.name)
				&& EqualsUtil.areEqual(this.value, that.value);
	}

	/**
	 * Returns a hash code value for this cell.
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int result = HashCodeUtil.SEED;
		result = HashCodeUtil.hash(result, name);
		result = HashCodeUtil.hash(result, value);
		return result;
	}

	// ------------------------------------------------------------------------

	/**
	 * Returns the textual representation of a exp.jsapar.types2.Cell.
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder representation = new StringBuilder();
		representation.append("[name: " + this.name + ", value: ");

		if (value instanceof Date) {
			Date dateValue = (Date) value;
			representation.append(DateUtil.formattedDateAsString(dateValue));
		} else {
			representation.append(String.valueOf(value));
		}
		representation.append("]");

		return representation.toString();
	}

    @Override
    public Object clone() throws CloneNotSupportedException {
        Cell clone = (Cell) super.clone();
        if (value instanceof Date) {
            clone.setValue(((Date)this.getValue()).clone());
        } 
        return clone;
    }	
	
}