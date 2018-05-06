package com.epam.brest.course.service;

import com.epam.brest.course.dao.WriterDao;
import com.epam.brest.course.model.Writer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;

/**
 * Implementation of WriterService.
 */
public class WriterServiceImpl implements WriterService {

    private static final Logger LOGGER = LogManager.getLogger();

    private WriterDao writerDao;

    public WriterServiceImpl(WriterDao writerDao) {
        this.writerDao = writerDao;
    }

    @Override
    public Collection<Writer> getWriters() {
        LOGGER.debug("getWriters()");
        Collection<Writer> writers = writerDao.getWriters();
        return writers;
    }

    @Override
    public Writer getWriterById(Integer writerId) {
        LOGGER.debug("getWriterById({})", writerId);
        Writer writer = writerDao.getWriterById(writerId);
        return writer;
    }

    @Override
    public Writer addWriter(Writer writer) {
        LOGGER.debug("addWriter({})", writer);
        Writer addedWriter = writerDao.addWriter(writer);
        return addedWriter;
    }

    @Override
    public void updateWriter(Writer writer) {
        LOGGER.debug("updateWriter({})", writer);
        writerDao.updateWriter(writer);
    }

    @Override
    public void deleteWriterById(Integer writerId) {
        LOGGER.debug("deleteWriterById({})", writerId);
        writerDao.deleteWriterById(writerId);
    }
}
