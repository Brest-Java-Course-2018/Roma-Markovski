package com.epam.brest.course;


/**
 * Model class "Writer".
 */
public class Writer {

    /**
     * The writer's id.
     */
    private Integer writerId;

    /**
     * The writer's name.
     */
    private String writerName;

    /**
     * The writer's country.
     */
    private String writerCountry;

    /*Getters and Setters*/

    public final Integer getWriterId() {
        return writerId;
    }

    public final void setWriterId(final Integer writerId) {
        this.writerId = writerId;
    }

    public final String getWriterName() {
        return writerName;
    }

    public final void setWriterName(final String writerName) {
        this.writerName = writerName;
    }

    public final String getWriterCountry() {
        return writerCountry;
    }

    public final void setWriterCountry(final String writerCountry) {
        this.writerCountry = writerCountry;
    }

    /**
     * Overrided toString method.
     * @return string which describes the writer.
     */
    @Override
    public final String toString() {
        return "Writer{"
                + "writerId=" + writerId
                + ", writerName='" + writerName + '\''
                + ", writerCountry='" + writerCountry + '\''
                + '}';
    }
}
