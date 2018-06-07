package com.epam.brest.course.client.rest;

import com.epam.brest.course.dto.PublicationDTO;
import com.epam.brest.course.model.Publication;
import com.epam.brest.course.service.PublicationService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.easymock.EasyMock.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context-test.xml")
public class PublicationServiceRestMockTest {

    private static final String EVGENIY_ONEGIN = "Evgeniy Onegin";
    private static final String ALEX_PUSHKIN = "Alex Pushkin";
    private static final String DATE1 = "2018-03-21";
    private static final String DATE2 = "2016-05-21";
    private static final String FORMATTED_DATE1 = "21.03.2018";
    private static final String FORMATTED_DATE2 = "21.05.2016";
    private static final String POEM = "Poem";
    private static final int NUMBER_OF_PAGES1 = 235;
    private static final int NUMBER_OF_PAGES2 = 134;
    private static final String DUBROVSKI = "Dubrovski";
    private static final String PROSE = "Prose";
    private static final int DTO_ID_1 = 1;
    private static final int DTO_ID_2 = 2;
    private static final int ID_1 = 1;
    private static final int ID_2 = 2;
    private static final int WRITER_ID = 1;
    private static final int PUBLICATIONS_SIZE = 2;

    private static PublicationDTO publicationDTO1;

    private static PublicationDTO publicationDTO2;

    private static Publication publication1;

    private static Publication publication2;

    @Autowired
    private PublicationService publicationService;

    @Autowired
    private RestTemplate mockRestTemplate;

    @Before
    public void init() {
        publicationDTO1 = new PublicationDTO(
                EVGENIY_ONEGIN, ALEX_PUSHKIN, FORMATTED_DATE1,
                NUMBER_OF_PAGES1, POEM);
        publicationDTO1.setId(DTO_ID_1);
        publicationDTO2 = new PublicationDTO(
                DUBROVSKI, ALEX_PUSHKIN, FORMATTED_DATE2,
                NUMBER_OF_PAGES2, PROSE);
        publicationDTO2.setId(DTO_ID_2);
        publication1 = new Publication(
                EVGENIY_ONEGIN, WRITER_ID, Date.valueOf(DATE1),
                NUMBER_OF_PAGES1, POEM);
        publication1.setId(ID_1);
        publication2 = new Publication(
                DUBROVSKI, WRITER_ID, Date.valueOf(DATE2),
                NUMBER_OF_PAGES2, PROSE);
        publication2.setId(ID_2);
    }

    @After
    public void tearDown() {
        verify(mockRestTemplate);
        reset(mockRestTemplate);
    }

    @Test
    public void getPublications() {
        List<Publication> publications =
                Arrays.asList(publication1, publication2);
        ResponseEntity entity =
                new ResponseEntity<>(publications, HttpStatus.OK); //status doesn't matter
        expect(mockRestTemplate.getForEntity(anyString(), anyObject()))
                .andReturn(entity);
        replay(mockRestTemplate);
        Collection<Publication> resultPublications =
                publicationService.getPublications();
        Assert.assertNotNull(resultPublications);
        Assert.assertEquals(PUBLICATIONS_SIZE, resultPublications.size());
    }

    @Test
    public void getPublicationsDTOs() {
        List<PublicationDTO> writers =
                Arrays.asList(publicationDTO1, publicationDTO2);
        ResponseEntity entity = new ResponseEntity<>(writers, HttpStatus.OK); //status doesn't matter
        expect(mockRestTemplate.getForEntity(anyString(), anyObject()))
                .andReturn(entity);
        replay(mockRestTemplate);
        Collection<PublicationDTO> resultPublications =
                publicationService.getPublicationDTOs();
        Assert.assertNotNull(resultPublications);
        Assert.assertEquals(PUBLICATIONS_SIZE, resultPublications.size());
    }

    @Test
    public void getPublicationDTOsByDate() {
        List<PublicationDTO> writers =
                Arrays.asList(publicationDTO1, publicationDTO2);
        ResponseEntity entity = new ResponseEntity<>(writers, HttpStatus.OK); //status doesn't matter
        expect(mockRestTemplate.getForEntity(anyString(), anyObject()))
                .andReturn(entity);
        replay(mockRestTemplate);
        Collection<PublicationDTO> resultPublications =
                publicationService.getPublicationDTOsByDate(
                        Date.valueOf(DATE1), Date.valueOf(DATE2));
        Assert.assertNotNull(resultPublications);
        Assert.assertEquals(PUBLICATIONS_SIZE, resultPublications.size());
    }

    @Test
    public void getPublicationById() {
        ResponseEntity entity = new ResponseEntity<>(
                publication1, HttpStatus.OK);
        expect(mockRestTemplate.getForEntity(anyString(), anyObject()))
                .andReturn(entity);

        replay(mockRestTemplate);

        Publication resultPublication =
                publicationService.getPublicationById(ID_1);
        Assert.assertNotNull(resultPublication);
        Assert.assertEquals(ID_1, resultPublication.getId().intValue());
        Assert.assertEquals(EVGENIY_ONEGIN, resultPublication.getName());
        Assert.assertEquals(WRITER_ID,
                resultPublication.getWriterId().intValue());
        Assert.assertEquals(NUMBER_OF_PAGES1,
                resultPublication.getNumberOfPages().intValue());
        Assert.assertEquals(POEM, resultPublication.getDescription());
    }

    @Test
    public void addPublication() {
        ResponseEntity entity = new ResponseEntity<>(
                publication2, HttpStatus.OK);
        expect(mockRestTemplate.postForEntity(
                anyString(), anyObject(Publication.class), anyObject()))
                .andReturn(entity);

        replay(mockRestTemplate);

        Publication resultPublication =
                publicationService.addPublication(publication2);
        Assert.assertNotNull(resultPublication);
        Assert.assertEquals(ID_2, resultPublication.getId().intValue());
        Assert.assertEquals(DUBROVSKI, resultPublication.getName());
        Assert.assertEquals(WRITER_ID,
                resultPublication.getWriterId().intValue());
        Assert.assertEquals(NUMBER_OF_PAGES2,
                resultPublication.getNumberOfPages().intValue());
        Assert.assertEquals(PROSE, resultPublication.getDescription());
    }

    @Test
    public void updatePublication() {
        ResponseEntity entity = new ResponseEntity<>(HttpStatus.OK);
        expect(mockRestTemplate.postForEntity(
                anyString(), anyObject(Publication.class), anyObject()))
                .andReturn(entity);

        replay(mockRestTemplate);

        publicationService.updatePublication(publication1);
        Assert.assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

    @Test
    public void deletePublication() {
        mockRestTemplate.delete(anyString());
        expectLastCall();

        replay(mockRestTemplate);

        publicationService.deletePublicationById(ID_2);
    }
}