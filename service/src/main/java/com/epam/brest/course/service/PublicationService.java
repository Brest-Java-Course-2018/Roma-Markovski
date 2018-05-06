package com.epam.brest.course.service;

import com.epam.brest.course.model.Publication;

import java.util.Collection;

/**
 * Publication Service Interface.
 */
public interface PublicationService {

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
     * Adds a publication to DB.
     * @param publication - publication.
     * @return - the same publication with generated id.
     */
    Publication addPublication(Publication publication);

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
