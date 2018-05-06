package com.epam.brest.course.dao;

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

import static org.junit.Assert.*;

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
    private static final int NUM_OF_PAGES_1 = 235;
    private static final String POEM = "Poem";
    private static final String WAR_AND_PEACE = "War and Peace";
    private static final int WRITER_2 = 2;
    private static final String DATE_2 = "2018-02-25";
    private static final int NUM_OF_PAGES_2 = 768;
    private static final String NOVEL = "Novel";
    private static final String PEACE_AND_WAR = "Peace and War";
    private static final int WRITER_3 = 3;
    private static final String DATE_3 = "2018-01-19";
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
        Assert.assertTrue(publication.getPublicationId().equals(PUBLICATION_1));
        Assert.assertEquals(EVGENIY_ONEGIN,
                publication.getPublicationName());
        Assert.assertTrue(publication.getWriterId().equals(WRITER_1));
        Assert.assertEquals(Date.valueOf(DATE_1),
                publication.getPublicationDate());
        Assert.assertTrue(publication.getPublicationNumOfPages().equals(NUM_OF_PAGES_1));
        Assert.assertEquals(POEM, publication.getPublicationDescription());
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
        Integer id = returnedPublication.getPublicationId();
        Assert.assertNotNull(id);
        Publication addedPublication = publicationDao.getPublicationById(id);

        Assert.assertEquals(WAR_AND_PEACE,
                addedPublication.getPublicationName());
        Assert.assertTrue(addedPublication.getWriterId().equals(WRITER_2));
        Assert.assertEquals(Date.valueOf(DATE_2),
                addedPublication.getPublicationDate());
        Assert.assertTrue(
                addedPublication.getPublicationNumOfPages().equals(NUM_OF_PAGES_2));
        Assert.assertEquals(NOVEL,
                addedPublication.getPublicationDescription());
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
        addedPublication.setPublicationName(PEACE_AND_WAR);
        addedPublication.setWriterId(WRITER_3);
        addedPublication.setPublicationDate(Date.valueOf(DATE_3));

        publicationDao.updatePublication(addedPublication);
        Publication updatedPublication =
                publicationDao.getPublicationById(
                        addedPublication.getPublicationId());
        Assert.assertEquals(addedPublication.getPublicationId(),
                updatedPublication.getPublicationId());
        Assert.assertEquals(PEACE_AND_WAR,
                updatedPublication.getPublicationName());
        Assert.assertTrue(updatedPublication.getWriterId().equals(WRITER_3));
        Assert.assertEquals(Date.valueOf(DATE_3),
                updatedPublication.getPublicationDate());
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
                addedPublication.getPublicationId());
        publications = publicationDao.getPublications();
        int newSize = publications.size();

        Assert.assertEquals(oldSize - 1, newSize);
    }
}