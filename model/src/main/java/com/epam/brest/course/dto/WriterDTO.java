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
    public WriterDTO(String name, String country,
                     Integer numberOfPublications) {
        this.name = name;
        this.country = country;
        this.numberOfPublications = numberOfPublications;
    }

    /*Getters and Setters*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getNumberOfPublications() {
        return numberOfPublications;
    }

    public void setNumberOfPublications(Integer numberOfPublications) {
        this.numberOfPublications = numberOfPublications;
    }

    @Override
    public String toString() {
        return "WriterDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", numberOfPublications=" + numberOfPublications +
                '}';
    }
}
