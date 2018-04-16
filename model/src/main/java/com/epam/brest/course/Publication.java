package com.epam.brest.course;

import java.sql.Date;

/**
 * Model class "Publication".
 */
public class Publication {

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
     * The publication's description.
     */
    private String publicationDescription;

    /*Getters and Setters*/

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

    public final void setPublicationDescription
            (final String publicationDescription) {
        this.publicationDescription = publicationDescription;
    }

    /**
     * Overrided toString method.
     * @return string which describes the publication.
     */
    @Override
    public final String toString() {
        return "Publication{"
                + "publicationId=" + publicationId
                + ", publicationName='" + publicationName + '\''
                + ", writerId=" + writerId
                + ", publicationDate=" + publicationDate
                + ", publicationDescription='" + publicationDescription + '\''
                + '}';
    }
}
