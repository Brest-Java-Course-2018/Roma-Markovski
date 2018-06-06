package com.epam.brest.course.client.rest;

import com.epam.brest.course.dto.PublicationDTO;
import com.epam.brest.course.model.Publication;
import com.epam.brest.course.service.PublicationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.util.Collection;

public class PublicationConsumerRest implements PublicationService {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    private String url;

    private RestTemplate restTemplate;

    public PublicationConsumerRest(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Publication> getPublications() {
        LOGGER.debug("getPublications()");
        ResponseEntity responseEntity =
                restTemplate.getForEntity(url, Collection.class);
        Collection<Publication> publications =
                (Collection<Publication>) responseEntity.getBody();
        return publications;
    }

    @Override
    public Publication getPublicationById(Integer publicationId) {
        LOGGER.debug("getPublicationById({})", publicationId);
        ResponseEntity<Publication> responseEntity =
                restTemplate.getForEntity
                        (url + "/" + publicationId, Publication.class);
        Publication publication = responseEntity.getBody();
        return publication;
    }

    @Override
    public Collection<PublicationDTO> getPublicationDTOs() {
        LOGGER.debug("getPublicationDTOs()");
        ResponseEntity responseEntity =
                restTemplate.getForEntity(url, Collection.class);
        Collection<PublicationDTO> publications =
                (Collection <PublicationDTO>) responseEntity.getBody();
        return publications;
    }

    @Override
    public Collection<PublicationDTO> getPublicationDTOsByDate(
            Date startDate, Date endDate) {
        ResponseEntity responseEntity =
                restTemplate.getForEntity(url + "/" + startDate.toString() +
                        "/" + endDate.toString(), Collection.class);
        Collection<PublicationDTO> publications =
                (Collection <PublicationDTO>) responseEntity.getBody();
        return publications;
    }

    @Override
    public Publication addPublication(Publication publication) {
        LOGGER.debug("addPublication({})", publication);
        ResponseEntity<Publication> responseEntity =
                restTemplate.postForEntity(url,
                        publication, Publication.class);
        Publication newPublication = responseEntity.getBody();
        return newPublication;
    }

    @Override
    public void updatePublication(Publication publication) {
        LOGGER.debug("updatePublication({})", publication);
        restTemplate.postForEntity(
                url + "/" + publication.getId(), publication, void.class);
    }

    @Override
    public void deletePublicationById(Integer publicationId) {
        LOGGER.debug("deletePublicationById({})", publicationId);
        restTemplate.delete(
                url + "/" + publicationId);
    }
}
