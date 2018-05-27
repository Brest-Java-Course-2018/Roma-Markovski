package com.epam.brest.course.service;

import com.epam.brest.course.dto.WriterDTO;
import com.epam.brest.course.model.Writer;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Writer Service Interface.
 */
@Service
public interface WriterService {

    /**
     * Gets collection of all writers from dao.
     * @return collection of writers.
     */
    Collection<Writer> getWriters();

    /**
     * Gets collection of all writerDTOs from dao.
     * @return collection of writerDTOs.
     */
    Collection<WriterDTO> getWriterDTOs();

    /**
     * Gets a writer by its id from dao.
     * @param writerId - id of writer.
     * @return writer.
     */
    Writer getWriterById(Integer writerId);

    /**
     * Adds a writer to dao.
     * @param writer - writer.
     * @return - the same writer with generated id.
     */
    Writer addWriter(Writer writer);

    /**
     * Edits a writer in dao.
     * @param writer - editable writer.
     */
    void updateWriter(Writer writer);

    /**
     * Removes a writer from dao.
     * @param writerId - writer.
     */
    void deleteWriterById(Integer writerId);
}
