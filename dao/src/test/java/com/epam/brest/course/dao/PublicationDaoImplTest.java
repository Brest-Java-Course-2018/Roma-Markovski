package com.epam.brest.course.dao;

import com.epam.brest.course.model.Publication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-spring.xml", "classpath:test-dao.xml"})
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
        Publication publication = new Publication();
        publication.setPublicationName("War and Peace");
        publication.setWriterId(2);
        publication.setPublicationDate(Date.valueOf("2018-02-25"));
        publication.setPublicationNumOfPages(768);
        publication.setPublicationDescription("Novel");

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
}