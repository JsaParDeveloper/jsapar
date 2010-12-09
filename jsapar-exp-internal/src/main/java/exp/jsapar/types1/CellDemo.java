package exp.jsapar.types1;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class CellDemo {
	public static void main(String args[]) {
		// Integer
		Cell<Integer> integerCell;
		integerCell = new Cell<Integer>(88);
		integerCell.showType();
		int v = integerCell.getValue();
		System.out.println("value: " + v);
		System.out.println("\n");

		// Float
		Cell<Float> floatCell = new Cell<Float>((float) 44.4);
		floatCell.showType();
		float f = floatCell.getValue();
		System.out.println("value: " + f);
		System.out.println("\n");
		
		// Double
		Cell<Double> doubleCell = new Cell<Double>(77.7);
		doubleCell.showType();
		double d = doubleCell.getValue();
		System.out.println("value: " + d);
		System.out.println("\n");
		
		// String
		Cell<String> stringCell = new Cell<String>("a string value");
		stringCell.showType();
		String s = stringCell.getValue();
		System.out.println("value: " + s);
		System.out.println("\n");
		
		// Date. 
		// A date cell has no concept of formatting BECAUSE this is SET outside of the cell object.
		// Formatting has nothing to do with the actual data stored in a cell!
		Date aDate = new Date();
		Cell<Date> dateCell = new Cell<Date>(aDate);
		dateCell.showType();
		Date date = dateCell.getValue();
		System.out.println("value: " + date);
		System.out.println("\n");
		
		// Number (= Integer)
		Cell<Number> numberCell = new Cell<Number>(1234);
		numberCell.showType();
		Number n = numberCell.getValue();
		System.out.println("value: " + n);
		System.out.println("\n");
		
		// Character
		Cell<Character> charCell = new Cell<Character>('a');
		charCell.showType();
		Character c = charCell.getValue();
		System.out.println("value: " + c);
		System.out.println("\n");
		
		// BigDecimal
		BigDecimal bdvalue = new BigDecimal(1234567890);
		Cell<BigDecimal> bigDecimalCell = new Cell<BigDecimal>(bdvalue);
		bigDecimalCell.showType();
		BigDecimal bd = bigDecimalCell.getValue();
		System.out.println("value: " + bd);
		System.out.println("\n");
		
		// Boolean
		Cell<Boolean> booleanCell = new Cell<Boolean>(true);
		booleanCell.showType();
		boolean b = booleanCell.getValue();
		System.out.println("value: " + b);
		System.out.println("\n");
		
		// BigInteger
		BigInteger bivalue = new BigInteger("987654321");
		Cell<BigInteger> biCell = new Cell<BigInteger>(bivalue);
		biCell.showType();
		BigInteger bi = biCell.getValue();
		System.out.println("value: " + bi);
		System.out.println("\n");

		// Empty ???
		Cell<?> emptyCell = new Cell<Object>();
		emptyCell.showType();
		// What kind of object comes out of an Empty cell?
		Object e = emptyCell.getValue();
		System.out.println("value: " + e);
		System.out.println("\n");
		
		System.out.println("End of test");
	}
}
