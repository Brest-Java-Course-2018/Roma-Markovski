package com.epam.brest.course.model;


import javax.validation.constraints.Size;

/**
 * Model class "Writer".
 */
public class Writer {

    public static final String WRITER_WRONG_SIZE =
            "The name of writer must be between 3 and 255 characters.";
    public static final String COUNTRY_WRONG_SIZE =
            "The country of writer must be between 3 and 63 characters.";

    /**
     * Constructor without params.
     */
    public Writer() {
    }

    /**
     * Constructor with params.
     * @param name
     * @param country
     */
    public Writer(
            final String name,
            final String country) {
        this.name = name;
        this.country = country;
    }

    /**
     * The writer's id.
     */
    private Integer id;

    /**
     * The writer's name.
     */
    @Size(min = 3, max = 255, message = WRITER_WRONG_SIZE)
    private String name;

    /**
     * The writer's country.
     */
    @Size(min = 3, max = 63, message = COUNTRY_WRONG_SIZE)
    private String country;

    /*Getters and Setters*/

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

    public final String getCountry() {
        return country;
    }

    public final void setCountry(final String country) {
        this.country = country;
    }

    /**
     * Overrided toString method.
     * @return string which describes the writer.
     */
    @Override
    public final String toString() {
        return "Writer{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", country='" + country + '\''
                + '}';
    }
}
