package com.epam.brest.course.model.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.sql.Date;
import java.util.Calendar;

/**
 * 'Past' implementation.
 */
public class PastValidator implements ConstraintValidator<Past, Date> {

    private static final String MINIMAL_DATE = "2000-01-01";

    @Override
    public void initialize(final Past constraintAnnotation) {

    }

    @Override
    public final boolean isValid(
            final Date date, final ConstraintValidatorContext
            constraintValidatorContext) {
        return date.before(
                new Date(Calendar.getInstance().getTime().getTime()))
                && !(date.before(Date.valueOf(MINIMAL_DATE)));
    }
}
