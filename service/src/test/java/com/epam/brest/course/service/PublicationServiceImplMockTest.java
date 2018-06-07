package com.epam.brest.course.service;

import com.epam.brest.course.dao.PublicationDao;
import com.epam.brest.course.dto.PublicationDTO;
import com.epam.brest.course.model.Publication;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-mock-test.xml"})
public class PublicationServiceImplMockTest {

    private static final Publication ONEGIN = new Publication(
            "Evgeniy Onegin", 1, Date.valueOf("2018-03-21"), 235, "Poem");

    private static final Publication DUBROVSKI = new Publication(
            "Dubrovski", 1, Date.valueOf("2016-05-21"), 134, "Prose");

    private static final PublicationDTO ONEGIN_DTO = new PublicationDTO(
            "Evgeniy Onegin", "Alex Pushkin", "21.03.2018",
            235, "Poem");

    private static final PublicationDTO DUBROVSKI_DTO = new PublicationDTO(
            "Dubrovski", "Alex Pushkin", "21.05.2016",
            134, "Prose");

    private static final String START_DATE = "2017-07-03";

    private static final String END_DATE = "2018-03-13";

    private static final List<Publication> PUBLICATIONS =
            Arrays.asList(ONEGIN, DUBROVSKI);

    private static final List<PublicationDTO> PUBLICATION_DTOS =
            Arrays.asList(ONEGIN_DTO, DUBROVSKI_DTO);

    @Autowired
    private PublicationService publicationService;

    @Autowired
    private PublicationDao mockPublicationDao;

    @After
    public void verifyAndReset() {
        EasyMock.verify(mockPublicationDao);
        EasyMock.reset(mockPublicationDao);
    }

    @Test
    public void getPublications() {
        EasyMock.expect(mockPublicationDao.getPublications())
                .andReturn(PUBLICATIONS);
        EasyMock.replay(mockPublicationDao);
        Collection<Publication> publications =
                publicationService.getPublications();
        Assert.assertEquals(publications, PUBLICATIONS);
    }

    @Test
    public void getPublicationDTOs() {
        EasyMock.expect(mockPublicationDao.getPublicationDTOs())
                .andReturn(PUBLICATION_DTOS);
        EasyMock.replay(mockPublicationDao);
        Collection<PublicationDTO> publications =
                publicationService.getPublicationDTOs();
        Assert.assertEquals(publications, PUBLICATION_DTOS);
    }

    @Test
    public void getPublicationDTOsByDate() {
        EasyMock.expect(
                mockPublicationDao.getPublicationDTOsByDate(
                        EasyMock.anyObject(Date.class), EasyMock.anyObject(Date.class)))
                .andReturn(PUBLICATION_DTOS);
        EasyMock.replay(mockPublicationDao);
        Collection<PublicationDTO> publications =
                publicationService.getPublicationDTOsByDate(
                        Date.valueOf(START_DATE), Date.valueOf(END_DATE));
        Assert.assertEquals(publications, PUBLICATION_DTOS);
    }


    @Test
    public void getPublicationById() {
        EasyMock.expect(
                mockPublicationDao.getPublicationById(EasyMock.anyInt()))
                .andReturn(ONEGIN);
        EasyMock.replay(mockPublicationDao);
        Publication publication = publicationService.getPublicationById(1);
        Assert.assertEquals(publication, ONEGIN);
    }

    @Test
    public void addPublication() {
        EasyMock.expect(mockPublicationDao.addPublication(
                EasyMock.anyObject(Publication.class)))
                .andReturn(DUBROVSKI);
        EasyMock.replay(mockPublicationDao);
        Publication publication = publicationService.addPublication(DUBROVSKI);
        Assert.assertEquals(publication, DUBROVSKI);
    }

    @Test
    public void updatePublication() {
        mockPublicationDao.updatePublication(
                EasyMock.anyObject(Publication.class));
        EasyMock.expectLastCall();
        EasyMock.replay(mockPublicationDao);
        publicationService.updatePublication(ONEGIN);
    }

    @Test
    public void deletePublicationById() {
        mockPublicationDao.deletePublicationById(EasyMock.anyInt());
        EasyMock.expectLastCall();
        EasyMock.replay(mockPublicationDao);
        publicationService.deletePublicationById(1);
    }
}