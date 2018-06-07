package com.epam.brest.course.client.rest;

import com.epam.brest.course.dto.WriterDTO;
import com.epam.brest.course.model.Writer;
import com.epam.brest.course.service.WriterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

/**
 * Implementation of WriterService. Gets data from rest-app.
 */
public class WriterConsumerRest implements WriterService {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    private String url;

    private RestTemplate restTemplate;

    public WriterConsumerRest(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Writer> getWriters() {
        LOGGER.debug("getWriters()");
        ResponseEntity responseEntity =
                restTemplate.getForEntity(url, Collection.class);
        Collection<Writer> writers = (Collection<Writer>) responseEntity.getBody();
        LOGGER.debug("getWriters returned: {}", writers);
        return writers;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<WriterDTO> getWriterDTOs() {
        LOGGER.debug("getWriterDTOs()");
        ResponseEntity responseEntity =
                restTemplate.getForEntity(url, Collection.class);
        Collection<WriterDTO> writers =
                (Collection <WriterDTO>) responseEntity.getBody();
        LOGGER.debug("getWriterDTOs returned: {}", writers);
        return writers;
    }

    @Override
    public Writer getWriterById(Integer writerId) {
        LOGGER.debug("getWriterById({})", writerId);
        ResponseEntity<Writer> responseEntity =
                restTemplate.getForEntity(url + "/" + writerId, Writer.class);
        Writer writer = responseEntity.getBody();
        LOGGER.debug("getWriterById returned: {}", writer);
        return writer;
    }

    @Override
    public Writer addWriter(Writer writer) {
        LOGGER.debug("addWriter({})", writer);
        ResponseEntity<Writer> responseEntity =
                restTemplate.postForEntity(url, writer, Writer.class);
        Writer newWriter = responseEntity.getBody();
        LOGGER.debug("addWriter returned: {}", newWriter);
        return newWriter;
    }

    @Override
    public void updateWriter(Writer writer) {
        LOGGER.debug("updateWriter({})", writer);
        restTemplate.postForEntity(
                url + "/" + writer.getId(), writer, void.class);
        LOGGER.debug("updateWriter returned: void");
    }

    @Override
    public void deleteWriterById(Integer writerId) {
        LOGGER.debug("deleteWriterById({})", writerId);
        restTemplate.delete(
                url + "/" + writerId);
        LOGGER.debug("deleteWriterById returned: void");
    }
}
