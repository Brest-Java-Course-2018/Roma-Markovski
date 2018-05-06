package com.epam.brest.course.service;

import com.epam.brest.course.dao.PublicationDao;
import com.epam.brest.course.model.Publication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;

/**
 * Implementation of PublicationService.
 */
public class PublicationServiceImpl implements PublicationService {

    private static final Logger LOGGER = LogManager.getLogger();

    private PublicationDao publicationDao;

    public PublicationServiceImpl(PublicationDao publicationDao) {
        this.publicationDao = publicationDao;
    }

    @Override
    public Collection<Publication> getPublications() {
        LOGGER.debug("getPublications()");
        Collection<Publication> publications =
                publicationDao.getPublications();
        return publications;
    }

    @Override
    public Publication getPublicationById(Integer publicationId) {
        LOGGER.debug("getPublicationById({})", publicationId);
        Publication publication =
                publicationDao.getPublicationById(publicationId);
        return publication;
    }

    @Override
    public Publication addPublication(Publication publication) {
        LOGGER.debug("addPublication({})", publication);
        Publication addedPublication =
                publicationDao.addPublication(publication);
        return addedPublication;
    }

    @Override
    public void updatePublication(Publication publication) {
        LOGGER.debug("updatePublication({})", publication);
        publicationDao.updatePublication(publication);

    }

    @Override
    public void deletePublicationById(Integer publicationId) {
        LOGGER.debug("deletePublicationById({})", publicationId);
        publicationDao.deletePublicationById(publicationId);
    }
}
