package com.epam.brest.course.model;

import com.epam.brest.course.model.validator.Past;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.Calendar;

/**
 * Model class "Publication".
 */
public class Publication {

    private static final String NAME_WRONG_SIZE =
            "The name of publication must be between 1 and 255 characters.";
    private static final String DATE_IS_NULL =
            "The date of publication can\'t be null.";
    private static final String WRITER_ID_IS_NULL =
            "You didn\'t choose the writer.";
    private static final String PAGES_ARE_NOT_POSITIVE =
            "The number of pages must be positive number.";
    private static final String PAGES_ARE_TOO_BIG =
            "The number of pages must be less than 10000.";
    private static final String DESCRIPTION_IS_TOO_BIG =
            "The description of publication must be less than 256 characters.";
    private static final String VALID_DATE =
            "Enter valid date, which is not earlier"
                    + " than 01.01.2000 and not later than today.";
    private static final int MIN_NAME_SIZE = 1;
    private static final int MAX_NAME_SIZE = 255;
    private static final int MIN_PAGES_SIZE = 1;
    private static final int MAX_PAGES_SIZE = 9999;
    private static final int MAX_DESCRIPTION_SIZE = 255;

    /**
     * Constructor without params.
     */
    public Publication() {
        this.date = new Date(Calendar.getInstance().getTime().getTime());
    }

    /**
     * Constructor with params.
     * @param name
     * @param writerId
     * @param date
     * @param numberOfPages
     * @param description
     */
    public Publication(final String name,
                       final Integer writerId,
                       final Date date,
                       final Integer numberOfPages,
                       final String description) {
        this.name = name;
        this.writerId = writerId;
        this.date = date;
        this.numberOfPages = numberOfPages;
        this.description = description;
    }

    /**
     * The publication's id.
     */
    private Integer id;

    /**
     * The publication's name.
     */
    @Size(min = MIN_NAME_SIZE, max = MAX_NAME_SIZE, message = NAME_WRONG_SIZE)
    private String name;

    /**
     * Id of the writer who wrote the publication.
     */
    @NotNull (message = WRITER_ID_IS_NULL)
    private Integer writerId;

    /**
     * The publication's date.
     */
    //@Past (message = DATE_IS_PAST)
    @NotNull (message = DATE_IS_NULL)
    @Past (message = VALID_DATE)
    private Date date;

    /**
     * Number of pages.
     */
    @Min(value = MIN_PAGES_SIZE, message = PAGES_ARE_NOT_POSITIVE)
    @Max(value = MAX_PAGES_SIZE, message = PAGES_ARE_TOO_BIG)
    private Integer numberOfPages;

    /**
     * The publication's description.
     */
    @Size(max = MAX_DESCRIPTION_SIZE, message = DESCRIPTION_IS_TOO_BIG)
    private String description;

    /*Getters and Setters*/

    public final Integer getNumberOfPages() {
        return numberOfPages;
    }

    public final void setNumberOfPages(final Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public final Integer getId() {
        return id;
    }

    public final void setId(final Integer id) {
        this.id = id;
    }

    public final String getName() {
        return name;
    }

    public final void setName(final String name) {
        this.name = name;
    }

    public final Integer getWriterId() {
        return writerId;
    }

    public final void setWriterId(final Integer writerId) {
        this.writerId = writerId;
    }

    public final Date getDate() {
        return date;
    }

    public final void setDate(final Date date) {
        this.date = date;
    }

    public final String getDescription() {
        return description;
    }

    public final void setDescription(
            final String description) {
        this.description = description;
    }

    /**
     * Overrided toString method.
     *
     * @return string which describes the publication.
     */
    @Override
    public final String toString() {
        return "Publication{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", writerId=" + writerId
                + ", date=" + date
                + ", numberOfPages=" + numberOfPages
                + ", description='" + description + '\''
                + '}';
    }
}
