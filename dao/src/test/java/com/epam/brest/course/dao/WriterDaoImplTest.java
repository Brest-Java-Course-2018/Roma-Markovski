package com.epam.brest.course.dao;

import com.epam.brest.course.model.Writer;
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
public class WriterDaoImplTest {

    @Autowired
    WriterDao writerDao;

    @Test
    public void getWriters() {
        Collection<Writer> writers = writerDao.getWriters();
        Assert.assertFalse(writers.isEmpty());
    }

    @Test
    public void getWriterById() {
        Writer writer = writerDao.getWriterById(1);
        Assert.assertNotNull(writer);
        Assert.assertTrue(writer.getWriterId().equals(1));
        Assert.assertEquals("Alex Pushkin", writer.getWriterName());
        Assert.assertEquals("Russia", writer.getWriterCountry());
    }

    @Test
    public void addWriter() {
        Collection<Writer> writers = writerDao.getWriters();
        int size = writers.size();
        Writer writer = new Writer();
        writer.setWriterName("Gogol Nikolay");
        writer.setWriterCountry("Ukraine");
        Writer returnedWriter = writerDao.addWriter(writer);
        Integer id = returnedWriter.getWriterId();
        Assert.assertNotNull(id);
        Writer addedWriter = writerDao.getWriterById(id);
        Assert.assertEquals("Gogol Nikolay", addedWriter.getWriterName());
        Assert.assertEquals("Ukraine", addedWriter.getWriterCountry());
        Assert.assertEquals(size+1, writerDao.getWriters().size());
    }

    @Test
    public void updateWriter() {

    }

    @Test
    public void deleteWriterById() {

    }
}