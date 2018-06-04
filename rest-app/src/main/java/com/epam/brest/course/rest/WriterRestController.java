package com.epam.brest.course.rest;

import com.epam.brest.course.model.Writer;
import com.epam.brest.course.service.WriterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class WriterRestController {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private WriterService writerService;

    @GetMapping(value = "/writers")
    public Collection<Writer> getWriters() {
        LOGGER.debug("getWriters()");
        Collection<Writer> writers = writerService.getWriters();
        return writers;
    }

    @GetMapping(value = "/writers/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Writer getWriterById(@PathVariable(value = "id") Integer id) {
        LOGGER.debug("getWriterById({})", id);
        Writer writer = writerService.getWriterById(id);
        return writer;
    }

    @PostMapping(value = "/writers")
    @ResponseStatus(HttpStatus.CREATED)
    public Writer addWriter(@RequestBody Writer writer) {
        LOGGER.debug("addWriter({})", writer);
        Writer newWriter = writerService.addWriter(writer);
        return newWriter;
    }

    @PostMapping(value = "/writers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateWriter(
            @RequestBody Writer writer,
            @PathVariable (value="id") Integer id) {
        LOGGER.debug("updateWriter({})", writer, id);
        writerService.updateWriter(writer);
    }

    @DeleteMapping(value = "/writers/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public void deleteWriter(@PathVariable(value = "id") Integer id) {
        LOGGER.debug("deleteWriter({})", id);
        writerService.deleteWriterById(id);
    }
}
