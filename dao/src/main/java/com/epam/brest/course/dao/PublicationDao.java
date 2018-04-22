package com.epam.brest.course.dao;

import com.epam.brest.course.model.Publication;

import java.util.Collection;


/**
 * Publication DAO Interface.
 */
public interface PublicationDao {

    Collection<Publication> getPublications();

    Publication getPublicationById(Integer publicationId);

    Publication addPublication (Publication publication);
}
