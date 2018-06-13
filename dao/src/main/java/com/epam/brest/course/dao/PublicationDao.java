package com.epam.brest.course.dao;

import com.epam.brest.course.dto.PublicationDTO;
import com.epam.brest.course.model.DateInterval;
import com.epam.brest.course.model.Publication;

import java.util.Collection;

/**
 * Publication DAO Interface.
 */
public interface PublicationDao {

    /**
     * Gets collection of all publications from DB.
     * @return collection of publications.
     */
    Collection<Publication> getPublications();

    /**
     * Gets a publication by its id from DB.
     * @param publicationId - id of publication.
     * @return publication.
     */
    Publication getPublicationById(Integer publicationId);

    /**
     * Gets collection of all publicationDTOs from DB.
     * @return collection of publications.
     */
    Collection<PublicationDTO> getPublicationDTOs();

    /**
     * Gets a publicationDTO by its id from DB. JUST FOR TEST.
     * @param publicationId - id of publication.
     * @return publication.
     */
    PublicationDTO getPublicationDTOById(Integer publicationId);

    /**
     * Adds a publication to DB.
     * @param publication - publication.
     * @return - the same publication with generated id.
     */
    Publication addPublication(Publication publication);

    /**
     * Gets publicationDTOs between certain dates from DB.
     * @param interval - date span.
     * @return publicaionDTOs filtered by date.
     */
    Collection<PublicationDTO> getPublicationDTOsByDate(
            DateInterval interval);

    /**
     * Edits a publication in DB.
     * @param publication - editable publication.
     */
    void updatePublication(Publication publication);

    /**
     * Removes a publication from DB.
     * @param publicationId - publication.
     */
    void deletePublicationById(Integer publicationId);
}
