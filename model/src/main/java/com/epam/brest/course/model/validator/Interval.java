package com.epam.brest.course.model.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IntervalValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Interval {

    String message() default "com.epam.brest.course.model.validator";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
