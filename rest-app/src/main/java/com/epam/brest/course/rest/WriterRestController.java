package com.epam.brest.course.rest;

import com.epam.brest.course.dto.WriterDTO;
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

    // curl -X GET -v http://localhost:8088/writers
    @GetMapping(value = "/writers")
    public Collection<WriterDTO> getWriterDTOs() {
        LOGGER.debug("getWriterDTOs()");
        Collection<WriterDTO> writers = writerService.getWriterDTOs();
        LOGGER.debug("getWriterDTOs returned: {}", writers);
        return writers;
    }

    // curl -X GET -v http://localhost:8088/writer_models
    @GetMapping(value = "/writer_models")
    public Collection<Writer> getWriters() {
        LOGGER.debug("getWriters()");
        Collection<Writer> writers = writerService.getWriters();
        LOGGER.debug("getWriters returned: {}", writers);
        return writers;
    }

    // curl -X GET -v http://localhost:8088/writers/1
    @GetMapping(value = "/writers/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Writer getWriterById(@PathVariable(value = "id") Integer id) {
        LOGGER.debug("getWriterById({})", id);
        Writer writer = writerService.getWriterById(id);
        LOGGER.debug("getWriterById returned: {}", writer);
        return writer;
    }

    // curl -H "Content-Type: application/json" -X POST -d '{"name":"Dostoevski Fyodor","country":"Russia"}' -v http://localhost:8088/writers
    @PostMapping(value = "/writers")
    @ResponseStatus(HttpStatus.CREATED)
    public Writer addWriter(@RequestBody Writer writer) {
        LOGGER.debug("addWriter({})", writer);
        Writer newWriter = writerService.addWriter(writer);
        LOGGER.debug("addWriter returned: {}", newWriter);
        return newWriter;
    }

    // curl -H "Content-Type: application/json" -X POST -d '{"name":"Lermontov Michael","country":"Russia", "id":"1"}' -v http://localhost:8088/writers/1
    @PostMapping(value = "/writers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateWriter(
            @RequestBody Writer writer,
            @PathVariable (value="id") Integer id) {
        LOGGER.debug("updateWriter({})", writer, id);
        writerService.updateWriter(writer);
        LOGGER.debug("updateWriter returned: void");
    }

    // curl -X DELETE -v http://localhost:8088/writers/5
    @DeleteMapping(value = "/writers/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public void deleteWriter(@PathVariable(value = "id") Integer id) {
        LOGGER.debug("deleteWriter({})", id);
        writerService.deleteWriterById(id);
        LOGGER.debug("deleteWriter returned: void");
    }
}
