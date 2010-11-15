package exp.tutorial.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Unfinished("Just articleware")
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR,
		ElementType.ANNOTATION_TYPE, ElementType.PACKAGE })
@Inherited
public @interface Unfinished {
	public enum Priority {
		LOW, MEDIUM, HIGH
	}

	String value();

	String[] owners() default "";

	Priority priority() default Priority.MEDIUM;
}
