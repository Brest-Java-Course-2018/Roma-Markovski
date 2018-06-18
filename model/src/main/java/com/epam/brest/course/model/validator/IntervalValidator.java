package com.epam.brest.course.model.validator;

import com.epam.brest.course.model.DateInterval;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Interval implementation.
 */
public class IntervalValidator implements
        ConstraintValidator<Interval, DateInterval> {
    @Override
    public void initialize(final Interval constraintAnnotation) {
    }

    @Override
    public final boolean isValid(final DateInterval interval,
                           final ConstraintValidatorContext cxt) {
        return !(interval.getStartDate().after(interval.getEndDate()));
    }
}
