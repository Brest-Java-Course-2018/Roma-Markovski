package com.epam.brest.course.dao;

import com.epam.brest.course.dto.PublicationDTO;
import com.epam.brest.course.model.Publication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Collection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-spring.xml", "classpath:test-dao.xml",
"classpath:dao.xml"})
@Rollback
@Transactional
public class PublicationDaoImplTest {

    private static final String EVGENIY_ONEGIN = "Evgeniy Onegin";
    private static final String DATE_1 = "2018-03-21";
    private static final int WRITER_1 = 1;
    private static final int PUBLICATION_1 = 1;
    private static final int NUM_OF_PAGES_1 = 384;
    private static final String POEM = "Poem";
    private static final String WAR_AND_PEACE = "War and Peace";
    private static final int WRITER_2 = 2;
    private static final String DATE_2 = "2018-02-25";
    private static final int NUM_OF_PAGES_2 = 768;
    private static final String NOVEL = "Novel";
    private static final String PEACE_AND_WAR = "Peace and War";
    private static final int WRITER_3 = 3;
    private static final String DATE_3 = "2018-01-19";
    private static final String PUSHKIN_ALEX = "Pushkin Alex";
    private static final String START_DATE = "2017-07-03";
    private static final String END_DATE = "2018-03-13";

    @Autowired
    PublicationDao publicationDao;

    @Test
    public void getPublications() {
        Collection<Publication> publications =
                publicationDao.getPublications();
        Assert.assertFalse(publications.isEmpty());
    }

    @Test
    public void getPublicationById() {
        Publication publication = publicationDao.getPublicationById(1);
        Assert.assertNotNull(publication);
        Assert.assertEquals(publication.getId().intValue(), PUBLICATION_1);
        Assert.assertEquals(EVGENIY_ONEGIN,
                publication.getName());
        Assert.assertEquals(publication.getWriterId().intValue(), WRITER_1);
        Assert.assertEquals(Date.valueOf(DATE_1),
                publication.getDate());
        Assert.assertEquals(publication.getNumberOfPages().intValue(), NUM_OF_PAGES_1);
        Assert.assertEquals(POEM, publication.getDescription());
    }

    @Test
    public void getPublicationDTOs() {
        Collection<PublicationDTO> publications =
                publicationDao.getPublicationDTOs();
        Assert.assertFalse(publications.isEmpty());
    }

    @Test
    public void getPublicationDTOsByDate() {
        Date startDate = Date.valueOf(START_DATE);
        Date endDate = Date.valueOf(END_DATE);
        Collection<PublicationDTO> publications =
                publicationDao.getPublicationDTOsByDate(
                      startDate, endDate);
        for (PublicationDTO publication : publications) {
            Assert.assertFalse(publication.getDate().after(endDate));
            Assert.assertFalse(publication.getDate().before(startDate));
        }
    }

    @Test
    public void getPublicationDTOById() {
        PublicationDTO publication = publicationDao.getPublicationDTOById(1);
        Assert.assertNotNull(publication);
        Assert.assertEquals(publication.getId().intValue(), PUBLICATION_1);
        Assert.assertEquals(EVGENIY_ONEGIN,
                publication.getName());
        Assert.assertEquals(publication.getWriterName(), PUSHKIN_ALEX);
        Assert.assertEquals(Date.valueOf(DATE_1),
                publication.getDate());
        Assert.assertEquals(
                publication.getNumberOfPages().intValue(), NUM_OF_PAGES_1);
        Assert.assertEquals(POEM, publication.getDescription());
    }

    @Test
    public void addPublication() {
        Collection<Publication> publications =
                publicationDao.getPublications();
        int size = publications.size();
        Publication publication = new Publication(
                WAR_AND_PEACE,
                WRITER_2,
                Date.valueOf(DATE_2),
                NUM_OF_PAGES_2,
                NOVEL);
        Publication returnedPublication =
                publicationDao.addPublication(publication);
        Integer id = returnedPublication.getId();
        Assert.assertNotNull(id);
        Publication addedPublication = publicationDao.getPublicationById(id);

        Assert.assertEquals(WAR_AND_PEACE,
                addedPublication.getName());
        Assert.assertEquals(addedPublication.getWriterId().intValue(), WRITER_2);
        Assert.assertEquals(Date.valueOf(DATE_2),
                addedPublication.getDate());
        Assert.assertEquals(addedPublication.getNumberOfPages().intValue(), NUM_OF_PAGES_2);
        Assert.assertEquals(NOVEL,
                addedPublication.getDescription());
        Assert.assertEquals(size + 1,
                publicationDao.getPublications().size());
    }

    @Test
    public void updatePublication() {

        Publication publication = new Publication(
                WAR_AND_PEACE,
                WRITER_2,
                Date.valueOf(DATE_2),
                NUM_OF_PAGES_2,
                NOVEL);

        Publication addedPublication =
                publicationDao.addPublication(publication);
        addedPublication.setName(PEACE_AND_WAR);
        addedPublication.setWriterId(WRITER_3);
        addedPublication.setDate(Date.valueOf(DATE_3));

        publicationDao.updatePublication(addedPublication);
        Publication updatedPublication =
                publicationDao.getPublicationById(
                        addedPublication.getId());
        Assert.assertEquals(addedPublication.getId(),
                updatedPublication.getId());
        Assert.assertEquals(PEACE_AND_WAR,
                updatedPublication.getName());
        Assert.assertEquals(updatedPublication.getWriterId().intValue(), WRITER_3);
        Assert.assertEquals(Date.valueOf(DATE_3),
                updatedPublication.getDate());
    }

    //TODO: Exception with a rule.
    @Test
    public void deletePublicationById () {
        Publication publication = new Publication(
                WAR_AND_PEACE,
                WRITER_2,
                Date.valueOf(DATE_2),
                NUM_OF_PAGES_2,
                NOVEL);

        Publication addedPublication =
                publicationDao.addPublication(publication);
        Collection<Publication> publications =
                publicationDao.getPublications();
        int oldSize = publications.size();
        publicationDao.deletePublicationById(
                addedPublication.getId());
        publications = publicationDao.getPublications();
        int newSize = publications.size();

        Assert.assertEquals(oldSize - 1, newSize);
    }
}