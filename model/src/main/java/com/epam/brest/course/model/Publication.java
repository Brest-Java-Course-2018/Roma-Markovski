package com.epam.brest.course.model;

import java.sql.Date;

/**
 * Model class "Publication".
 */
public class Publication {

    /**
     * Constructor without params.
     */
    public Publication() {
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
    private String name;

    /**
     * Id of the writer who wrote the publication.
     */
    private Integer writerId;

    /**
     * The publication's date.
     */
    private Date date;

    /**
     * Number of pages.
     */
    private Integer numberOfPages;

    /**
     * The publication's description.
     */
    private String description;

    /*Getters and Setters*/

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
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
