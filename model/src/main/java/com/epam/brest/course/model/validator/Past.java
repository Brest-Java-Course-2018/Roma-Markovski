package com.epam.brest.course.model.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PastValidator.class)
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Past {
    String message() default "com.epam.brest.course.model.validator";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
