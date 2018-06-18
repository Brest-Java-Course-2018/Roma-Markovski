package com.epam.brest.course.dto;


/**
 * Data-transfer Object class "WriterDTO" - for output.
 */
public class WriterDTO {

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

    /**
     * The number of the writer's publications.
     */
    private Integer numberOfPublications;

    /**
     * Constructor without params.
     */
    public WriterDTO() {
    }

    /**
     * Constructor with params.
     * @param name
     * @param country
     * @param numberOfPublications
     */
    public WriterDTO(final String name, final String country,
                     final Integer numberOfPublications) {
        this.name = name;
        this.country = country;
        this.numberOfPublications = numberOfPublications;
    }

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

    public final Integer getNumberOfPublications() {
        return numberOfPublications;
    }

    public final void setNumberOfPublications(
            final Integer numberOfPublications) {
        this.numberOfPublications = numberOfPublications;
    }

    @Override
    public final String toString() {
        return "WriterDTO{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", country='" + country + '\''
                + ", numberOfPublications=" + numberOfPublications
                + '}';
    }
}
