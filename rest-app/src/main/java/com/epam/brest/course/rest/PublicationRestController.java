package com.epam.brest.course.rest;

import com.epam.brest.course.dto.PublicationDTO;
import com.epam.brest.course.model.DateInterval;
import com.epam.brest.course.model.Publication;
import com.epam.brest.course.service.PublicationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Rest Controller for publications.
 */
@RestController
public class PublicationRestController {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private PublicationService publicationService;

    /**
     * Get publicationDTOs.
     * @return collection of publicationDTOs.
     */
    // curl -X GET -v http://localhost:8088/publications
    @GetMapping(value = "/publications")
    public final Collection<PublicationDTO> getPublicationDTOs() {
        LOGGER.debug("getPublicationDTOs()");
        Collection<PublicationDTO> publications =
                publicationService.getPublicationDTOs();
        LOGGER.debug("getPublicationDTOs returned: {}", publications);
        return publications;
    }

    /**
     * Gets publications.
     * @return collection of publications.
     */
    // curl -X GET -v http://localhost:8088/publication_models
    @GetMapping(value = "/publication_models")
    public final Collection<Publication> getPublications() {
        LOGGER.debug("getPublications()");
        Collection<Publication> publications =
                publicationService.getPublications();
        LOGGER.debug("getPublications returned: {}", publications);
        return publications;
    }

    /**
     * Gets publication by its id.
     * @param id - id.
     * @return publication.
     */
    // curl -X GET -v http://localhost:8088/publications/1
    @GetMapping(value = "/publications/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public final Publication getPublicationById(
            final @PathVariable(value = "id") Integer id) {
        LOGGER.debug("getPublicationById({})", id);
        Publication publication = publicationService.getPublicationById(id);
        LOGGER.debug("getPublicationById returned: {}", publication);
        return publication;
    }

    /**
     * Adds publication.
     * @param publication - publication.
     * @return publication with generated id.
     */
    // curl -H "Content-Type: application/json" -X POST -d '{"writerId":"1","name":"Otello","description":"Tragedy", "numberOfPages":"245"}' -v http://localhost:8088/publications
    @PostMapping(value = "/publications")
    @ResponseStatus(HttpStatus.CREATED)
    public final Publication addPublication(
            @RequestBody final Publication publication) {
        LOGGER.debug("addPublication({})", publication);
        Publication newPublication =
                publicationService.addPublication(publication);
        LOGGER.debug("addPublication returned: {}", newPublication);
        return newPublication;
    }

    /**
     * Edits publication.
     * @param publication - publication.
     * @param id - publication's id.
     */
    // curl -H "Content-Type: application/json" -X POST -d '{"id":4, "writerId":"1","name":"Otello"}' -v http://localhost:8088/publications/4
    @PostMapping(value = "/publications/{id}")
    @ResponseStatus(HttpStatus.OK)
    public final void updatePublication(
            @RequestBody final Publication publication,
            @PathVariable (value = "id") final Integer id) {
        LOGGER.debug("updatePublication({})", publication, id);
        publicationService.updatePublication(publication);
        LOGGER.debug("updatePublication returned: void");
    }

    /**
     * Deletes publication.
     * @param id - id of publication.
     */
    // curl -X DELETE -v http://localhost:8088/publications/2
    @DeleteMapping(value = "/publications/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public final void deletePublication(
            @PathVariable(value = "id") final Integer id) {
        LOGGER.debug("deletePublication({})", id);
        publicationService.deletePublicationById(id);
        LOGGER.debug("deletePublication returned: void");
    }

    /**
     * Gets publications filtered by date from service.
     * @param startDate - start of date interval.
     * @param endDate - end of date interval.
     * @return filtered publications.
     */
    // curl -X GET -v http://localhost:8088/publications/2017-07-03/2018-03-13
    @GetMapping(value = "/publications/{startDate}/{endDate}")
    @ResponseStatus(HttpStatus.OK)
    public final Collection<PublicationDTO> getPublicationDTOsByDate(
            @PathVariable(value = "startDate") final String startDate,
            @PathVariable(value = "endDate") final String endDate) {
        LOGGER.debug("getPublicationDTOsByDate({}, {})", startDate, endDate);
        Collection<PublicationDTO> publications =
                publicationService.getPublicationDTOsByDate(
                        new DateInterval(startDate, endDate));
        LOGGER.debug("getPublicationDTOsByDate returned: {}", publications);
        return publications;
    }
}
