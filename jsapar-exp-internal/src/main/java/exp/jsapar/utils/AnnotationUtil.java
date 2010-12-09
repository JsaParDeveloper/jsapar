package exp.jsapar.utils;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

import exp.jsapar.annotations.Cell;
import exp.jsapar.annotations.Line;

/**
 * Provides simple static utility methods for discovering and processing JsaPar
 * annotations in classes and fields.
 * 
 * @author JsaPar Developer
 */
public class AnnotationUtil {

	/**
	 * Indicates whether or not the specified class contains the @Line
	 * annotation.
	 * 
	 * @param clazz
	 *            the class to be investigated.
	 * 
	 * @return {@code true} when class contains the annotation, otherwise
	 *         {@code false}.
	 */
	public static boolean hasLineAnnotation(final Class<?> clazz) {
		return clazz.isAnnotationPresent(Line.class);
	}

	/**
	 * Indicates whether or not the specified field contains the @Cell
	 * annotation.
	 * 
	 * @param field
	 *            the field to be investigated.
	 * 
	 * @return {@code true} when field contains the annotation, otherwise
	 *         {@code false}.
	 */
	public static boolean hasCellAnnotation(final Field field) {
		return field.isAnnotationPresent(Cell.class);
	}

	public static boolean hasSectionAnnotation(final Class<?> cls) {
		// TODO
		return false;
	}

	/**
	 * Finds the fields of the specified class that have a @Cell annotation and
	 * returns them in a list.
	 * 
	 * @param clazz
	 *            the class with the annotated @Cell fields.
	 * 
	 * @return a list of fields that have the @Cell annotation.
	 */
	public static List<Field> getCellAnnotatedFields(final Class<?> clazz) {
		Class<?> aClass = clazz;
		List<Field> annotatedFields = new LinkedList<Field>();

		while ((aClass != null) && hasLineAnnotation(aClass)) {
			Field[] f = aClass.getDeclaredFields();
			for (int i = 0; i < f.length; i++) {
				if (hasCellAnnotation(f[i])) {
					annotatedFields.add(f[i]);
				}
			}
			aClass = aClass.getSuperclass();
		}
		return annotatedFields;
	}

	/**
	 * Gets the cell name for the annotated field.
	 * 
	 * @param field the field of a class that contains the @Cell annotation.
	 * 
	 * @return the cell name for the annotated field.
	 */
	public static String getCellNameFromAnnotatedField(final Field field) {
		String cellName = null;
		Cell cell = field.getAnnotation(Cell.class);
		cellName = cell.name();
		if ((cellName != null) && !cellName.isEmpty()) {
			return cellName;
		} else {
			cellName = field.getName();
			return cellName;
		}
	}
}