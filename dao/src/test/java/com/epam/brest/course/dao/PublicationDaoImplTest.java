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

    @Autowired
    PublicationDao publicationDao;

    @Test
    public void getPublications() {
        Collection<Publication> publications = publicationDao.getPublications();
        Assert.assertFalse(publications.isEmpty());
    }

    @Test
    public void getPublicationById() {
        Publication publication = publicationDao.getPublicationById(1);
        Assert.assertNotNull(publication);
        Assert.assertTrue(publication.getPublicationId().equals(1));
        Assert.assertEquals("Evgeniy Onegin", publication.getPublicationName());
        Assert.assertTrue(publication.getWriterId().equals(1));
        Assert.assertEquals(Date.valueOf("2018-03-21"), publication.getPublicationDate());
        Assert.assertTrue(publication.getPublicationNumOfPages().equals(235));
        Assert.assertEquals("Poem", publication.getPublicationDescription());
    }

    @Test
    public void addPublication() {
        Collection<Publication> publications = publicationDao.getPublications();
        int size = publications.size();
        Publication publication = new Publication(
                "War and Peace",
                2,
                Date.valueOf("2018-02-25"),
                768,
                "Novel");
        Publication returnedPublication = publicationDao.addPublication(publication);
        Integer id = returnedPublication.getPublicationId();
        Assert.assertNotNull(id);
        Publication addedPublication = publicationDao.getPublicationById(id);

        Assert.assertEquals("War and Peace",addedPublication.getPublicationName());
        Assert.assertTrue(addedPublication.getWriterId().equals(2));
        Assert.assertEquals(Date.valueOf("2018-02-25"), addedPublication.getPublicationDate());
        Assert.assertTrue(addedPublication.getPublicationNumOfPages().equals(768));
        Assert.assertEquals("Novel",addedPublication.getPublicationDescription());
        Assert.assertEquals(size + 1, publicationDao.getPublications().size());
    }

    @Test
    public void updatePublication() {

        Publication publication = new Publication(
                "War and Peace",
                2,
                Date.valueOf("2018-02-25"),
                768,
                "Novel");

        Publication addedPublication = publicationDao.addPublication(publication);
        addedPublication.setPublicationName("Peace and War");
        addedPublication.setWriterId(3);
        addedPublication.setPublicationDate(Date.valueOf("2018-01-19"));

        publicationDao.updatePublication(addedPublication);
        Publication updatedPublication = publicationDao.getPublicationById(addedPublication.getPublicationId());

        Assert.assertEquals(addedPublication.getPublicationId(), updatedPublication.getPublicationId());
        Assert.assertEquals("Peace and War", updatedPublication.getPublicationName());
        Assert.assertTrue(updatedPublication.getWriterId().equals(3));
        Assert.assertEquals(Date.valueOf("2018-01-19"), updatedPublication.getPublicationDate());
    }

    //TODO: Exception with a rule.
    @Test
    public void deletePublicationById () {
        Publication publication = new Publication(
                "War and Peace",
                2,
                Date.valueOf("2018-02-25"),
                768,
                "Novel");

        Publication addedPublication = publicationDao.addPublication(publication);

        Collection<Publication> publications = publicationDao.getPublications();
        int oldSize = publications.size();

        publicationDao.deletePublicationById(addedPublication.getPublicationId());

        publications = publicationDao.getPublications();
        int newSize = publications.size();

        Assert.assertEquals(oldSize - 1, newSize);
    }
}