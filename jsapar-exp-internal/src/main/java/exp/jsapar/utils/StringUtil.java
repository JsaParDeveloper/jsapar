package exp.jsapar.utils;

/**
 * Provides simple static utility methods for String manipulation and
 * formatting.
 * 
 * @author JsaPar Developer
 */
public class StringUtil {
	/**
	 * Returns a string containing only leading zeros based on the current
	 * number in range and the maximum number in range. This is needed to align
	 * the number-output in a sequence of numbers (also known as: a loop).
	 * 
	 * @param numberInRange
	 *            the current number (or index) within the range.
	 * @param maxNumberInRange
	 *            the maximum number within the range. For example:
	 *            collection.size()
	 * 
	 * @return a String containing leading zeros for the given number.
	 */
	public static String getLeadingZerosString(int numberInRange,
			int maxNumberInRange) {
		int numberOfLeadingZeros = calculateLeadingZeroPlaces(numberInRange,
				maxNumberInRange);
		StringBuilder sb = new StringBuilder();
		for (int index = 0; index < numberOfLeadingZeros; index++) {
			sb.append("0");
		}
		return sb.toString();
	}

	/**
	 * Calculates the number of leading zero places.
	 * 
	 * @param numberInRange
	 *            the current number (or index) within the range.
	 * 
	 * @param maxNumberInRange
	 *            the maximum number of the range.
	 * @return the number of leading zeros to be used.
	 */
	private static int calculateLeadingZeroPlaces(int numberInRange,
			int maxNumberInRange) {
		int number = maxNumberInRange;
		if (number < 0) {
			// make positive first.
			number = number * (-1);
		}
		int length = (number == 0) ? 0 : (int) Math.log10(number); // + 1;
		return length;
	}
}