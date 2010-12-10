package exp.jsapar.utils;

/**
 * Provides simple static utility methods for String manipulation and formatting.
 * 
 * @author JsaPar Developer
 */
public final class StringUtil {

    /**
     * Check style rule: utility classes should not have a public constructor.
     */
    private StringUtil() {
        // Intentionally left blank.
    }

    /**
     * Returns a string containing only leading zeros based on the current number in range and the
     * maximum number in range. This is needed to align the number-output in a sequence of numbers
     * (also known as: a loop).
     * 
     * @param numberInRange
     *            the current number (or index) within the range.
     * @param maxNumberInRange
     *            the maximum number within the range. For example: collection.size()
     * 
     * @return a String containing leading zeros for the given number.
     */
    public static String getLeadingZerosString(int numberInRange, int maxNumberInRange) {
        int numberOfLeadingZeros = calculateLeadingZeroPlaces(numberInRange, maxNumberInRange);
        StringBuilder sb = new StringBuilder();
        for (int index = 0; index < numberOfLeadingZeros; index++) {
            sb.append("0");
        }
        return sb.toString();
    }

    /**
     * Right pads a given string.
     * 
     * @param s
     *            the string to be padded to the right.
     * @param n
     *            the number of pads to the right.
     * 
     * @return the padded string.
     */
    public static String padRight(String s, int n) {
        return String.format("%1$-" + n + "s", s);
    }

    /**
     * Left pads a given string.
     * 
     * @param s
     *            the string to be padded to the left.
     * @param n
     *            the number of pads to the left.
     * 
     * @return the padded string.
     */
    public static String padLeft(String s, int n) {
        return String.format("%1$#" + n + "s", s);
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
    private static int calculateLeadingZeroPlaces(int numberInRange, int maxNumberInRange) {
        int number = numberInRange;
        int maxNumber = maxNumberInRange; 
        int numberOfleadingPlaces;
        
        // make positive first.
        if (number < 0) {
            number = number * (-1);
        }
        if (maxNumber < 0 ) {
            maxNumber = maxNumber * (-1);
        }
        
        numberOfleadingPlaces = (String.valueOf(maxNumber)).length() - (String.valueOf(number)).length(); 
        
        return numberOfleadingPlaces;
    }
}