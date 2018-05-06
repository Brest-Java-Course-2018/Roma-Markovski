package com.epam.brest.course.service;

import com.epam.brest.course.model.Writer;

import java.util.Collection;

/**
 * Writer Service Interface.
 */
public interface WriterService {

    /**
     * Gets collection of all writers from DB.
     * @return collection of writers.
     */
    Collection<Writer> getWriters();

    /**
     * Gets a writer by its id from DB.
     * @param writerId - id of writer.
     * @return writer.
     */
    Writer getWriterById(Integer writerId);

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
