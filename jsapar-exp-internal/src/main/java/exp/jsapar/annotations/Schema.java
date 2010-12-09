package exp.jsapar.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation Schema. Specifies the name of the JsaPar Schema that will be used
 * for validation purposes.
 * 
 * @author JsaPar Developer
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
public @interface Schema {

	/**
	 * Represents the name of the JsaPar Schema. A schema name can be specified
	 * to make sure that the @Line annotated user class is validated against the
	 * schema so that the cell names of the fields in the user classes match the
	 * cell names of a Line definition within a schema.
	 * 
	 * @return the name of the JsaPar Schema.
	 */
	public String name() default "";
}
