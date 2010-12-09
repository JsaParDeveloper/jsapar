package exp.jsapar.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation Cell. Indicates if a non static field should be translated into a
 * {@code exp.jsapar.types2.Cell} object, and vice versa.
 * 
 * Only object fields can be annotated with this annotation and only for
 * primitives, object data types and enumerated types.
 * 
 * Conversion from the attribute to primitive type is done with a
 * {@code Transform} object. If a suitable transform can be found then this will
 * convert the attribute string value to an object instance, which can be
 * assigned to the annotated field.
 * 
 * // TODO see the Transform interface in Simple framework 
 * 
 * @author JsaPar Developer
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
public @interface Cell {

	/**
	 * Represents the name of the Cell. Annotated fields can optionally provide
	 * the name of the Cell they represent. If a name is not provided then the
	 * field name is used in its place. A name can be specified if the field
	 * name is not suitable for the Cell name.
	 * 
	 * @return the name of the Cell that represents this field name.
	 */
	public String name() default "";

	/**
	 * Represents a default value for the annotated field if the field is null.
	 * Annotated fields can optionally provide a default value for the Cell they
	 * represent. This ensures that the Cell is instantiated with a value even
	 * if the value of the field is null. When the field is written, but the
	 * Cell is absent in the {@code Line}, the default value specified by the
	 * value attribute is written to the field.
	 * 
	 * @return the default value to use for the field and {@code Cell} when
	 *         field is {@code null}.
	 */
	public String value() default "";

	/**
	 * Determines whether the annotated field is required within a {@code Cell}
	 * element. Any field marked as not required will not have its value set
	 * when the {@code Cell} value is written into the field. If an object is
	 * written to a {@code Line}, the fields that are annotated with a
	 * {@code Cell} annotation and marked as not required, are skipped and will
	 * not appear in the {@code Line}.
	 * 
	 * @return {@code true} if the attribute is required, @{code false}
	 *         otherwise.
	 */
	public boolean required() default true;
}