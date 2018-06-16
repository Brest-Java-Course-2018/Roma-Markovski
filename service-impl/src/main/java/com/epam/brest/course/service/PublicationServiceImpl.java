package com.epam.brest.course.service;

import com.epam.brest.course.dao.PublicationDao;
import com.epam.brest.course.dto.PublicationDTO;
import com.epam.brest.course.model.DateInterval;
import com.epam.brest.course.model.Publication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;

/**
 * Implementation of PublicationService. Gets data from dao and database.
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
        LOGGER.debug("getPublications returned: {}", publications);
        return publications;
    }

    @Override
    public final Collection<PublicationDTO> getPublicationDTOs() {
        LOGGER.debug("getPublicationDTOs()");
        Collection<PublicationDTO> publications =
                publicationDao.getPublicationDTOs();
        LOGGER.debug("getPublicationDTOs returned: {}", publications);
        return publications;
    }

    @Override
    public Collection<PublicationDTO> getPublicationDTOsByDate(
            DateInterval interval) {
        LOGGER.debug("getPublicationDTOsByDate({}, {})", interval);
        Collection<PublicationDTO> publications =
                publicationDao.getPublicationDTOsByDate(interval);
        LOGGER.debug("getPublicationDTOsByDate returned: {}", publications);
        return publications;
    }

    @Override
    public final Publication getPublicationById(final Integer publicationId) {
        LOGGER.debug("getPublicationById({})", publicationId);
        Publication publication =
                publicationDao.getPublicationById(publicationId);
        LOGGER.debug("getPublicationById returned: {}", publication);
        return publication;
    }

    @Override
    public final Publication addPublication(final Publication publication) {
        LOGGER.debug("addPublication({})", publication);
        Publication addedPublication =
                publicationDao.addPublication(publication);
        LOGGER.debug("addPublication returned: {}", addedPublication);
        return addedPublication;
    }

    @Override
    public final void updatePublication(final Publication publication) {
        LOGGER.debug("updatePublication({})", publication);
        publicationDao.updatePublication(publication);
        LOGGER.debug("updatePublication returned: void");
    }

    @Override
    public final void deletePublicationById(final Integer publicationId) {
        LOGGER.debug("deletePublicationById({})", publicationId);
        publicationDao.deletePublicationById(publicationId);
        LOGGER.debug("deletePublicationById returned: void");
    }
}
