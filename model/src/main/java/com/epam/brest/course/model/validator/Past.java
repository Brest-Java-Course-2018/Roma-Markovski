package com.epam.brest.course.model.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for date validation.
 */
@Documented
@Constraint(validatedBy = PastValidator.class)
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Past {

    /**
     * @return - default message.
     */
    String message() default "com.epam.brest.course.model.validator";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
