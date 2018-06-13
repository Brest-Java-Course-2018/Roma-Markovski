package com.epam.brest.course.model;

import com.epam.brest.course.model.validator.Interval;

import java.sql.Date;
import java.util.Calendar;

@Interval(message = "Start date should not be later than end date.")
public class DateInterval {

    private Date startDate;

    private Date endDate;

    public DateInterval(String strStartDate, String strEndDate) {
        this.startDate = Date.valueOf(strStartDate);
        this.endDate = Date.valueOf(strEndDate);
    }

    public DateInterval() {
        this.startDate = Date.valueOf("2000-01-01");
        this.endDate = new Date(Calendar.getInstance().getTime().getTime());
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return "DateInterval{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
