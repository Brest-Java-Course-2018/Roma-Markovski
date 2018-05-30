package com.epam.brest.course.service;

import com.epam.brest.course.dao.WriterDao;
import com.epam.brest.course.dto.WriterDTO;
import com.epam.brest.course.model.Writer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;

/**
 * Implementation of WriterService.
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
        return writers;
    }

    @Override
    public final Collection<WriterDTO> getWriterDTOs() {
        LOGGER.debug("getWriterDTOs()");
        Collection<WriterDTO> writers = writerDao.getWriterDTOs();
        return writers;
    }

    @Override
    public final Writer getWriterById(final Integer writerId) {
        LOGGER.debug("getWriterById({})", writerId);
        Writer writer = writerDao.getWriterById(writerId);
        return writer;
    }

    @Override
    public final Writer addWriter(final Writer writer) {
        LOGGER.debug("addWriter({})", writer);
        Writer addedWriter = writerDao.addWriter(writer);
        return addedWriter;
    }

    @Override
    public final void updateWriter(final Writer writer) {
        LOGGER.debug("updateWriter({})", writer);
        writerDao.updateWriter(writer);
    }

    @Override
    public final void deleteWriterById(final Integer writerId) {
        LOGGER.debug("deleteWriterById({})", writerId);
        writerDao.deleteWriterById(writerId);
    }
}
