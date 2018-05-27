package com.epam.brest.course.model;


/**
 * Model class "Writer".
 */
public class Writer {

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
    private String name;

    /**
     * The writer's country.
     */
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
