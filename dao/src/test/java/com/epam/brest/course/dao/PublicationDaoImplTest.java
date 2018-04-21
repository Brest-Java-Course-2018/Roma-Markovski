package com.epam.brest.course.dao;

import com.epam.brest.course.model.Publication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
        Assert.assertTrue(publications.isEmpty());
    }

    @Test
    public void getPublicationById() {
    }
}