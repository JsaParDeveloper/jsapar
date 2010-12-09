package exp.jsapar.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation Line. Indicates if a user class contains members that should be
 * stored in a {@code exp.jsapar.types2.Line} object.
 * 
 * @author JsaPar Developer
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
public @interface Line {

}
