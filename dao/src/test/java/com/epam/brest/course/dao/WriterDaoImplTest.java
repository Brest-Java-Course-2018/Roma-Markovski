package com.epam.brest.course.dao;

import com.epam.brest.course.model.Writer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-spring.xml", "classpath:test-dao.xml",
"classpath:dao.xml"})
@Rollback
@Transactional
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
        Writer writer = new Writer();
        writer.setWriterName("Bykov Alex");
        writer.setWriterCountry("Russia");
        Writer addedWriter = writerDao.addWriter(writer);
        addedWriter.setWriterName("Bykov Vasil");
        addedWriter.setWriterCountry("Belarus");
        writerDao.updateWriter(addedWriter);
        Writer updatedWriter = writerDao.getWriterById(addedWriter.getWriterId());
        Assert.assertEquals(addedWriter.getWriterId() ,updatedWriter.getWriterId());
        Assert.assertEquals("Bykov Vasil" ,updatedWriter.getWriterName());
        Assert.assertEquals("Belarus" ,updatedWriter.getWriterCountry());
    }

    @Test
    public void deleteWriterById() {
        Writer writer = new Writer();
        writer.setWriterName("Bykov Alex");
        writer.setWriterCountry("Russia");
        Writer addedWriter = writerDao.addWriter(writer);

        Collection<Writer> writers = writerDao.getWriters();
        int oldSize = writers.size();

        writerDao.deleteWriterById(addedWriter.getWriterId());
        writers = writerDao.getWriters();
        int newSize = writers.size();

        Assert.assertEquals(oldSize - 1, newSize);
    }
}