package com.epam.brest.course.model.validator;

import com.epam.brest.course.model.DateInterval;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IntervalValidator implements ConstraintValidator<Interval, DateInterval> {
    @Override
    public void initialize(Interval constraintAnnotation) {
    }

    @Override
    public boolean isValid(DateInterval interval, ConstraintValidatorContext cxt) {
        return !(interval.getStartDate().after(interval.getEndDate()));
    }
}
