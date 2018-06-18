package com.epam.brest.course.model;


import javax.validation.constraints.Size;

/**
 * Model class "Writer".
 */
public class Writer {

    private static final String NAME_WRONG_SIZE =
            "The name of writer must be between 3 and 255 characters.";
    private static final String COUNTRY_WRONG_SIZE =
            "The country of writer must be between 3 and 63 characters.";
    private static final int MIN_NAME_SIZE = 3;
    private static final int MAX_NAME_SIZE = 255;
    private static final int MIN_COUNTRY_SIZE = 3;
    private static final int MAX_COUNTRY_SIZE = 63;

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
    @Size(min = MIN_NAME_SIZE, max = MAX_NAME_SIZE,
            message = NAME_WRONG_SIZE)
    private String name;

    /**
     * The writer's country.
     */
    @Size(min = MIN_COUNTRY_SIZE, max = MAX_COUNTRY_SIZE,
            message = COUNTRY_WRONG_SIZE)
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
