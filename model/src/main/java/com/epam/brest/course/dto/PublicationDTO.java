package com.epam.brest.course.dto;

import java.sql.Date;

/**
 * Data-transfer Object class "PublicationDTO" - for output.
 */
public class PublicationDTO {

    /**
     * Constructor without params.
     */
    public PublicationDTO() {
    }

    /**
     * Constructor with params.
     * @param name
     * @param writerName
     * @param date
     * @param numberOfPages
     * @param description
     */
    public PublicationDTO(final String name,
                       final String writerName,
                       final Date date,
                       final Integer numberOfPages,
                       final String description) {
        this.name = name;
        this.writerName = writerName;
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
     * Name of the writer who wrote the publication.
     */
    private String writerName;

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

    public final String getWriterName() {
        return writerName;
    }

    public final void setWriterName(final String writerName) {
        this.writerName = writerName;
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
                + ", writerName=" + writerName
                + ", date=" + date
                + ", numberOfPages=" + numberOfPages
                + ", description='" + description + '\''
                + '}';
    }
}
