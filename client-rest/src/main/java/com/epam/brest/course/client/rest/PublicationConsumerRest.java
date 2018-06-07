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
        LOGGER.debug("getForEntity({}, {})", url, Collection.class);
        ResponseEntity responseEntity =
                restTemplate.getForEntity(url, Collection.class);

        Collection<Publication> publications =
                (Collection<Publication>) responseEntity.getBody();
        LOGGER.debug("getPublications returned: {}", publications);
        return publications;
    }

    @Override
    public Publication getPublicationById(Integer publicationId) {
        LOGGER.debug("getPublicationById({})", publicationId);
        LOGGER.debug("getForEntity({}, {})", url + "/" + publicationId, Publication.class);
        ResponseEntity<Publication> responseEntity =
                restTemplate.getForEntity
                        (url + "/" + publicationId, Publication.class);
        Publication publication = responseEntity.getBody();
        LOGGER.debug("getPublicationById returned: {}", publication);
        return publication;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<PublicationDTO> getPublicationDTOs() {
        LOGGER.debug("getPublicationDTOs()");
        LOGGER.debug("getForEntity({}, {})", url, Collection.class);
        ResponseEntity<Collection> responseEntity =
                restTemplate.getForEntity(url, Collection.class);
        Collection<PublicationDTO> publications =
                responseEntity.getBody();
        LOGGER.debug("getPublicationDTOs returned: {}", publications);
        return publications;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<PublicationDTO> getPublicationDTOsByDate(
            Date startDate, Date endDate) {
        LOGGER.debug("getPublicationDTOsByDate({}, {})", startDate, endDate);
        LOGGER.debug("getForEntity({}, {})",url + "/" + startDate.toString() +
                "/" + endDate.toString(), Collection.class);
        ResponseEntity responseEntity =
                restTemplate.getForEntity(url + "/" + startDate.toString() +
                        "/" + endDate.toString(), Collection.class);
        Collection<PublicationDTO> publications =
                (Collection <PublicationDTO>) responseEntity.getBody();
        LOGGER.debug("getPublicationDTOsByDate returned: {}", publications);
        return publications;
    }

    @Override
    public Publication addPublication(Publication publication) {
        LOGGER.debug("addPublication({})", publication);
        LOGGER.debug("postForEntity({}, {}, {})", url,
                publication, Publication.class);
        ResponseEntity<Publication> responseEntity =
                restTemplate.postForEntity(url,
                        publication, Publication.class);
        Publication newPublication = responseEntity.getBody();
        LOGGER.debug("addPublication returned: {}", newPublication);
        return newPublication;
    }

    @Override
    public void updatePublication(Publication publication) {
        LOGGER.debug("updatePublication({})", publication);
        LOGGER.debug("postForEntity({}, {}, {})",
                url + "/" + publication.getId(), publication, void.class);
        restTemplate.postForEntity(
                url + "/" + publication.getId(), publication, void.class);
        LOGGER.debug("updatePublication returned: void");
    }

    @Override
    public void deletePublicationById(Integer publicationId) {
        LOGGER.debug("deletePublicationById({})", publicationId);
        LOGGER.debug("postForEntity({})",
                url + "/" + publicationId);
        restTemplate.delete(
                url + "/" + publicationId);
        LOGGER.debug("deletePublicationById returned: void");
    }
}
