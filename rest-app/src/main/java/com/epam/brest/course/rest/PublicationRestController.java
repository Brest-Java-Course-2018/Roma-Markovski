package com.epam.brest.course.rest;

import com.epam.brest.course.dto.PublicationDTO;
import com.epam.brest.course.model.Publication;
import com.epam.brest.course.service.PublicationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Collection;

@RestController
public class PublicationRestController {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private PublicationService publicationService;

    @GetMapping(value = "/publications")
    public Collection<PublicationDTO> getPublicationDTOs() {
        LOGGER.debug("getPublicationDTOs()");
        Collection<PublicationDTO> publications =
                publicationService.getPublicationDTOs();
        return publications;
    }

    //TODO: address
    @GetMapping(value = "/publication_models")
    public Collection<Publication> getPublications() {
        LOGGER.debug("getPublications()");
        Collection<Publication> publications =
                publicationService.getPublications();
        return publications;
    }

    @GetMapping(value = "/publications/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Publication getPublicationById(
            @PathVariable(value = "id") Integer id) {
        LOGGER.debug("getPublicationById({})", id);
        Publication publication = publicationService.getPublicationById(id);
        return publication;
    }

    @PostMapping(value = "/publications")
    @ResponseStatus(HttpStatus.CREATED)
    public Publication addPublication(@RequestBody Publication publication) {
        LOGGER.debug("addPublication({})", publication);
        Publication newPublication =
                publicationService.addPublication(publication);
        return newPublication;
    }

    @PostMapping(value = "/publications/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePublication(
            @RequestBody Publication publication,
            @PathVariable (value="id") Integer id) {
        LOGGER.debug("updatePublication({})", publication, id);
        publicationService.updatePublication(publication);
    }

    @DeleteMapping(value = "/publications/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public void deletePublication(@PathVariable(value = "id") Integer id) {
        LOGGER.debug("deletePublication({})", id);
        publicationService.deletePublicationById(id);
    }

    @GetMapping(value = "/publications/{startDate}/{endDate}")
    @ResponseStatus(HttpStatus.OK)
    public Collection<PublicationDTO> getPublicationDTOsByDate(
            @PathVariable(value = "startDate") String startDate,
            @PathVariable(value = "endDate") String endDate) {
        LOGGER.debug("getPublicationDTOsByDate({}, {})", startDate, endDate);
        Collection<PublicationDTO> publications =
                publicationService.getPublicationDTOsByDate(
                        Date.valueOf(startDate), Date.valueOf(endDate));
        return publications;
    }
}
