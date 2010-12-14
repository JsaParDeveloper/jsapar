package exp.jsapar.utils;

/**
 * Provides simple static utility methods for type checking. The JsaPar library can handle certain,
 * but not all, types available in the VM. These type checking utility methods provide a mechanism
 * to force the library user to only use types that the library can handle.
 * 
 * @author JsaPar Developer
 */
public final class TypesUtil {

    /**
     * Check style rule: utility classes should not have a public constructor.
     */
    private TypesUtil() {
        // Intentionally left blank.
    }

    /**
     * Validates the data type against one of the approved data types for use within the JsaPar
     * library.
     * 
     * @param <T>
     *            the data type that must match with one of the approved data types for use within
     *            the JsaPar library.
     * @param value
     *            the value.
     * 
     * @return {@code true} when data type is valid, {@code false} when data type doesn't match with
     *         one of the approved data types.
     */
    public static <T> boolean isValidDataType(T value) {
        boolean validDataType = false;

        if (value == null) {
            validDataType = true;
        } else if (value instanceof java.lang.Byte) {
            validDataType = true;
        } else if (value instanceof java.lang.Boolean) {
            validDataType = true;
        } else if (value instanceof java.lang.Character) {
            validDataType = true;
        } else if (value instanceof java.lang.Double) {
            validDataType = true;
        } else if (value instanceof java.lang.Enum) {
            validDataType = true;
        } else if (value instanceof java.lang.Float) {
            validDataType = true;
        } else if (value instanceof java.lang.Integer) {
            validDataType = true;
        } else if (value instanceof java.lang.Long) {
            validDataType = true;
        } else if (value instanceof java.lang.Short) {
            validDataType = true;
        } else if (value instanceof java.lang.String) {
            validDataType = true;
        } else if (value instanceof java.math.BigDecimal) {
            validDataType = true;
        } else if (value instanceof java.math.BigInteger) {
            validDataType = true;
        } else if (value instanceof java.util.Date) {
            validDataType = true;
        }
        return validDataType;
    }

    public static String[] numericLimits() {
        // TODO return the numeric limits for the selected type: Integer, BigInteger, Double, Float,
        // etc
        String str[] = { "", "" };
        return str;
    }
}
