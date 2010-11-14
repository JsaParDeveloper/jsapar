package exp.jsapar.types;

import java.io.Serializable;
import java.text.Format;
import java.util.Locale;

// JsaPar Cell object that can hold any type specified. 
// FIXME really any type?
public class Cell<T> implements Serializable, Comparable<T>, Cloneable {
	private static final long serialVersionUID = -1732377640943061547L;
	private String name;
	private T value;

	// create an empty cell
	public Cell() {
		// FIXME is this a good way to create a empty Cell object?
	}

	// create an empty cell with a name.
	// public Cell(String aName) {
	// name = aName;
	// }
	// FIXME cannot be done: Cell<String> doesn't seem to be working with this.

	// create a cell with a value but without a name.
	// TODO is this possible ?
	public Cell(T aValue) {
		value = aValue;
	}

	public Cell(String aName, T aValue) {
		name = aName;
		value = aValue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public void setValue(T value, Locale locale) {
		this.value = value;
		// TODO locale
	}

	public void setValue(T value, Format format) {
		this.value = value;
	}

	// FIXME return value
	void showType() {
		if (value == null) {
			System.out.println("Type of T is null");
		} else {
			System.out.println("Type of T is " + value.getClass().getName());
		}
	}

	@Override
	public String toString() {
		// FIXME what to return as string value?
		if (value == null) {
			return "";
		} else {
			return value.toString();
		}
	}

	@Override
	public int compareTo(T o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
