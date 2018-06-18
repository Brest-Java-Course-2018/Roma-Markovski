package com.epam.brest.course.model;

import com.epam.brest.course.model.validator.Interval;
import com.epam.brest.course.model.validator.Past;

import java.sql.Date;
import java.util.Calendar;

/**
 * Model class "DateInterval" with validation.
 */
@Interval(message = DateInterval.START_DATE_NOT_LATER_END_DATE)
public class DateInterval {

    static final String START_DATE_NOT_LATER_END_DATE =
            "Start date should not be later than end date.";
    private static final String VALID_START_DATE =
            "Start date should be valid, not"
                    + " earlier than 01.01.2000 and not later than today.";
    private static final String VALID_END_DATE =
            "End date should be valid, not"
                    + " earlier than 01.01.2000 and not later than today.";
    private static final String MINIMAL_DATE = "2000-01-01";

    /**
     * Start date of interval.
     */
    @Past(message = VALID_START_DATE)
    private Date startDate;

    /**
     * End date of interval.
     */
    @Past(message = VALID_END_DATE)
    private Date endDate;

    /**
     * Constructor with params.
     * @param strStartDate - start date string.
     * @param strEndDate - end date string.
     */
    public DateInterval(final String strStartDate, final String strEndDate) {
        this.startDate = Date.valueOf(strStartDate);
        this.endDate = Date.valueOf(strEndDate);
    }

    /**
     * Constructor with default params.
     */
    public DateInterval() {
        this.startDate = Date.valueOf(MINIMAL_DATE);
        this.endDate = new Date(Calendar.getInstance().getTime().getTime());
    }

    public final void setStartDate(final Date startDate) {
        this.startDate = startDate;
    }

    public final void setEndDate(final Date endDate) {
        this.endDate = endDate;
    }

    public final Date getStartDate() {
        return startDate;
    }

    public final Date getEndDate() {
        return endDate;
    }

    @Override
    public final String toString() {
        return "DateInterval{" + "startDate="
                + startDate + ", endDate="
                + endDate + '}';
    }
}
