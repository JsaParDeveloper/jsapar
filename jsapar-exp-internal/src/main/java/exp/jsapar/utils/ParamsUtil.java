package exp.jsapar.utils;

/**
 * Provides simple static utility methods for method parameter checking.
 * 
 * @author JsaPar Developer
 */
public final class ParamsUtil {

    /**
     * Check style rule: utility classes should not have a public constructor.
     */
    private ParamsUtil() {
        // Intentionally left blank.
    }

    /**
     * Checks if given parameter isn't NULL.
     * 
     * @param obj
     *            the object to be checked for NULL.
     * @throws NullPointerException
     *             thrown when {@code obj} is {@code null}.
     */
    public static void checkForNullPointer(Object obj) throws NullPointerException {
        if (obj == null) {
            throw new NullPointerException("Parameter cannot be NULL.");
        }
    }

    /**
     * Checks if given String parameter isn't EMPTY.
     * 
     * @param str
     *            the String to be checked for EMPTY.
     * 
     * @throws IllegalArgumentException
     *             thrown when string is EMPTY.
     */
    public static void checkForEmptyString(String str) {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("String cannot be EMPTY.");
        }
    }
}
