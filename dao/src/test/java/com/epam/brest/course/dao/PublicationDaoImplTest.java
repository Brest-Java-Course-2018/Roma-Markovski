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
}