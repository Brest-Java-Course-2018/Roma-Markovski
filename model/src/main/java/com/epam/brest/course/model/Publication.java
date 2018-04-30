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
     * @param publicationName
     * @param writerId
     * @param publicationDate
     * @param publicationNumOfPages
     * @param publicationDescription
     */
    public Publication(final String publicationName,
                       final Integer writerId,
                       final Date publicationDate,
                       final Integer publicationNumOfPages,
                       final String publicationDescription) {
        this.publicationName = publicationName;
        this.writerId = writerId;
        this.publicationDate = publicationDate;
        this.publicationNumOfPages = publicationNumOfPages;
        this.publicationDescription = publicationDescription;
    }

    /**
     * The publication's id.
     */
    private Integer publicationId;

    /**
     * The publication's name.
     */
    private String publicationName;

    /**
     * Id of the writer who wrote the publication.
     */
    private Integer writerId;

    /**
     * The publication's date.
     */
    private Date publicationDate;

    /**
     * Number of pages.
     */
    private Integer publicationNumOfPages;

    /**
     * The publication's description.
     */
    private String publicationDescription;

    /*Getters and Setters*/

    public Integer getPublicationNumOfPages() {
        return publicationNumOfPages;
    }

    public void setPublicationNumOfPages(Integer publicationNumOfPages) {
        this.publicationNumOfPages = publicationNumOfPages;
    }

    public final Integer getPublicationId() {
        return publicationId;
    }

    public final void setPublicationId(final Integer publicationId) {
        this.publicationId = publicationId;
    }

    public final String getPublicationName() {
        return publicationName;
    }

    public final void setPublicationName(final String publicationName) {
        this.publicationName = publicationName;
    }

    public final Integer getWriterId() {
        return writerId;
    }

    public final void setWriterId(final Integer writerId) {
        this.writerId = writerId;
    }

    public final Date getPublicationDate() {
        return publicationDate;
    }

    public final void setPublicationDate(final Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public final String getPublicationDescription() {
        return publicationDescription;
    }

    public final void setPublicationDescription(
            final String publicationDescription) {
        this.publicationDescription = publicationDescription;
    }

    /**
     * Overrided toString method.
     *
     * @return string which describes the publication.
     */
    @Override
    public final String toString() {
        return "Publication{"
                + "publicationId=" + publicationId
                + ", publicationName='" + publicationName + '\''
                + ", writerId=" + writerId
                + ", publicationDate=" + publicationDate
                + ", publicationNumOfPages=" + publicationNumOfPages
                + ", publicationDescription='" + publicationDescription + '\''
                + '}';
    }
}
