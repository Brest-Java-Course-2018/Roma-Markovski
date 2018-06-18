package com.epam.brest.course.rest;

import com.epam.brest.course.dto.WriterDTO;
import com.epam.brest.course.model.Writer;
import com.epam.brest.course.service.WriterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Rest Controller for writers.
 */
@RestController
public class WriterRestController {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private WriterService writerService;

    /**
     * Gets writerDTOs.
     * @return collection of writerDTOs.
     */
    // curl -X GET -v http://localhost:8088/writers
    @GetMapping(value = "/writers")
    public final Collection<WriterDTO> getWriterDTOs() {
        LOGGER.debug("getWriterDTOs()");
        Collection<WriterDTO> writers = writerService.getWriterDTOs();
        LOGGER.debug("getWriterDTOs returned: {}", writers);
        return writers;
    }

    /**
     * Gets writers.
     * @return collection of writers.
     */
    // curl -X GET -v http://localhost:8088/writer_models
    @GetMapping(value = "/writer_models")
    public final Collection<Writer> getWriters() {
        LOGGER.debug("getWriters()");
        Collection<Writer> writers = writerService.getWriters();
        LOGGER.debug("getWriters returned: {}", writers);
        return writers;
    }

    /**
     * Gets writer by id.
     * @param id - id.
     * @return writer.
     */
    // curl -X GET -v http://localhost:8088/writers/1
    @GetMapping(value = "/writers/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public final Writer getWriterById(
            @PathVariable(value = "id") final Integer id) {
        LOGGER.debug("getWriterById({})", id);
        Writer writer = writerService.getWriterById(id);
        LOGGER.debug("getWriterById returned: {}", writer);
        return writer;
    }

    /**
     * Adds writer.
     * @param writer - writer.
     * @return - writer with generated id.
     */
    // curl -H "Content-Type: application/json" -X POST -d '{"name":"Dostoevski Fyodor","country":"Russia"}' -v http://localhost:8088/writers
    @PostMapping(value = "/writers")
    @ResponseStatus(HttpStatus.CREATED)
    public final Writer addWriter(@RequestBody final Writer writer) {
        LOGGER.debug("addWriter({})", writer);
        Writer newWriter = writerService.addWriter(writer);
        LOGGER.debug("addWriter returned: {}", newWriter);
        return newWriter;
    }

    /**
     * Edits writer.
     * @param writer - writer.
     * @param id -writer's id.
     */
    // curl -H "Content-Type: application/json" -X POST -d '{"name":"Lermontov Michael","country":"Russia", "id":"1"}' -v http://localhost:8088/writers/1
    @PostMapping(value = "/writers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public final void updateWriter(
            @RequestBody final Writer writer,
            @PathVariable (value = "id") final Integer id) {
        LOGGER.debug("updateWriter({})", writer, id);
        writerService.updateWriter(writer);
        LOGGER.debug("updateWriter returned: void");
    }

    /**
     * Deletes writer.
     * @param id - writer's id.
     */
    // curl -X DELETE -v http://localhost:8088/writers/5
    @DeleteMapping(value = "/writers/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public final void deleteWriter(
            @PathVariable(value = "id") final Integer id) {
        LOGGER.debug("deleteWriter({})", id);
        writerService.deleteWriterById(id);
        LOGGER.debug("deleteWriter returned: void");
    }

    /**
     * Throws exception.
     */
    @GetMapping(value = "/anyErrorExample")
    public final void throwAnyException() {
        LOGGER.debug("throwError()");
        throw new RuntimeException();
    }
}
