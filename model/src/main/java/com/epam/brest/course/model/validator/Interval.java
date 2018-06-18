package com.epam.brest.course.model.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for interval validation.
 */
@Documented
@Constraint(validatedBy = IntervalValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Interval {

    /**
     * @return - default message.
     */
    String message() default "com.epam.brest.course.model.validator";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
