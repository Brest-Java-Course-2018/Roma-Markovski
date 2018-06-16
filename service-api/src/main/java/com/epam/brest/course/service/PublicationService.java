package com.epam.brest.course.service;

import com.epam.brest.course.dto.PublicationDTO;
import com.epam.brest.course.model.DateInterval;
import com.epam.brest.course.model.Publication;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Publication Service Interface.
 */
@Service
public interface PublicationService {

    /**
     * Gets collection of all publications from dao.
     * @return collection of publications.
     */
    Collection<Publication> getPublications();

    /**
     * Gets a publication by its id from dao.
     * @param publicationId - id of publication.
     * @return publication.
     */
    Publication getPublicationById(Integer publicationId);

    /**
     * Gets collection of all publicationDTOs from dao.
     * @return collection of publicationDTOs.
     */
    Collection<PublicationDTO> getPublicationDTOs();

    /**
     * Gets publicationDTOs between certain dates from dao.
     * @param interval - date span.
     * @return publicaionDTOs filtered by date.
     */
    Collection<PublicationDTO> getPublicationDTOsByDate(
            DateInterval interval);

    /**
     * Adds a publication to dao.
     * @param publication - publication.
     * @return - the same publication with generated id.
     */
    Publication addPublication(Publication publication);

    /**
     * Edits a publication in dao.
     * @param publication - editable publication.
     */
    void updatePublication(Publication publication);

    /**
     * Removes a publication from dao.
     * @param publicationId - publication.
     */
    void deletePublicationById(Integer publicationId);
}
