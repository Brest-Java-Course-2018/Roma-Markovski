package com.epam.brest.course.service;

import com.epam.brest.course.dao.WriterDao;
import com.epam.brest.course.dto.WriterDTO;
import com.epam.brest.course.model.Writer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;

/**
 * Implementation of WriterService. Gets data from dao and database.
 */
public class WriterServiceImpl implements WriterService {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * DAO.
     */
    private WriterDao writerDao;

    /**
     * Service constructor.
     * @param writerDao - dao.
     */
    public WriterServiceImpl(final WriterDao writerDao) {
        this.writerDao = writerDao;
    }

    @Override
    public final Collection<Writer> getWriters() {
        LOGGER.debug("getWriters()");
        Collection<Writer> writers = writerDao.getWriters();
        LOGGER.debug("getWriters returned: {}", writers);
        return writers;
    }

    @Override
    public final Collection<WriterDTO> getWriterDTOs() {
        LOGGER.debug("getWriterDTOs()");
        Collection<WriterDTO> writers = writerDao.getWriterDTOs();
        LOGGER.debug("getWriterDTOs returned: {}", writers);
        return writers;
    }

    @Override
    public final Writer getWriterById(final Integer writerId) {
        LOGGER.debug("getWriterById({})", writerId);
        Writer writer = writerDao.getWriterById(writerId);
        LOGGER.debug("getWriterById returned: {}", writer);
        return writer;
    }

    @Override
    public final Writer addWriter(final Writer writer) {
        LOGGER.debug("addWriter({})", writer);
        Writer addedWriter = writerDao.addWriter(writer);
        LOGGER.debug("addWriter returned: {}", addedWriter);
        return addedWriter;
    }

    @Override
    public final void updateWriter(final Writer writer) {
        LOGGER.debug("updateWriter({})", writer);
        writerDao.updateWriter(writer);
        LOGGER.debug("updateWriter returned: void");
    }

    @Override
    public final void deleteWriterById(final Integer writerId) {
        LOGGER.debug("deleteWriterById({})", writerId);
        writerDao.deleteWriterById(writerId);
        LOGGER.debug("deleteWriterById returned: void");
    }
}
