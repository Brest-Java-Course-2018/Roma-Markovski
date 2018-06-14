package com.epam.brest.course.model.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.sql.Date;
import java.util.Calendar;

public class PastValidator implements ConstraintValidator<Past, Date> {

    @Override
    public void initialize(Past constraintAnnotation) {

    }

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {
        return date.before(
                new Date(Calendar.getInstance().getTime().getTime())) &&
                !(date.before(Date.valueOf("2000-01-01")));
    }
}
