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

    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * DAO.
     */
    private PublicationDao publicationDao;

    /**
     * Service constructor.
     * @param publicationDao - dao.
     */
    public PublicationServiceImpl(final PublicationDao publicationDao) {
        this.publicationDao = publicationDao;
    }

    @Override
    public final Collection<Publication> getPublications() {
        LOGGER.debug("getPublications()");
        Collection<Publication> publications =
                publicationDao.getPublications();
        return publications;
    }

    @Override
    public final Publication getPublicationById(Integer publicationId) {
        LOGGER.debug("getPublicationById({})", publicationId);
        Publication publication =
                publicationDao.getPublicationById(publicationId);
        return publication;
    }

    @Override
    public final Publication addPublication(final Publication publication) {
        LOGGER.debug("addPublication({})", publication);
        Publication addedPublication =
                publicationDao.addPublication(publication);
        return addedPublication;
    }

    @Override
    public final void updatePublication(final Publication publication) {
        LOGGER.debug("updatePublication({})", publication);
        publicationDao.updatePublication(publication);

    }

    @Override
    public final void deletePublicationById(final Integer publicationId) {
        LOGGER.debug("deletePublicationById({})", publicationId);
        publicationDao.deletePublicationById(publicationId);
    }
}
