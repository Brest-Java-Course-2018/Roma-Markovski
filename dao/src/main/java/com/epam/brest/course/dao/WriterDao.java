package com.epam.brest.course.dao;

import com.epam.brest.course.dto.WriterDTO;
import com.epam.brest.course.model.Writer;

import java.util.Collection;

/**
 * Writer DAO Interface.
 */
public interface WriterDao {

    /**
     * Gets collection of all writers from DB.
     * @return collection of writers.
     */
    Collection<Writer> getWriters();

    /**
     * Gets collection of all writerDTOs from DB.
     * @return collection of writerDTOs.
     */
    Collection<WriterDTO> getWriterDTOs();

    /**
     * Gets a writer by its id from DB.
     * @param writerId - id of writer.
     * @return writer.
     */
    Writer getWriterById(Integer writerId);

    /**
     * Gets a writerDTO by its id from DB. JUST FOR TEST.
     * @param writerId - id of writer.
     * @return writer.
     */
    WriterDTO getWriterDTOById(Integer writerId);

    /**
     * Adds a writer to DB.
     * @param writer - writer.
     * @return - the same writer with generated id.
     */
    Writer addWriter(Writer writer);

    /**
     * Edits a writer in DB.
     * @param writer - editable writer.
     */
    void updateWriter(Writer writer);

    /**
     * Removes a writer from DB.
     * @param writerId - writer.
     */
    void deleteWriterById(Integer writerId);
}
