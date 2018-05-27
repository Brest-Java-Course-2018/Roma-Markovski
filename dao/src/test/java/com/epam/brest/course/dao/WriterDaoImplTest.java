package com.epam.brest.course.dao;

import com.epam.brest.course.dto.WriterDTO;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-spring.xml", "classpath:test-dao.xml",
"classpath:dao.xml"})
@Rollback
@Transactional
public class WriterDaoImplTest {

    private static final int WRITER_ID_1 = 1;
    private static final int NUMBER_OF_PUBLICATIONS_1 = 1;
    private static final String PUSHKIN = "Pushkin Alex";
    private static final String RUSSIA = "Russia";
    private static final String GOGOL = "Gogol Nikolay";
    private static final String UKRAINE = "Ukraine";
    private static final String BYKOV_ALEX = "Bykov Alex";
    private static final String BYKOV_VASIL = "Bykov Vasil";
    private static final String BELARUS = "Belarus";
    @Autowired
    WriterDao writerDao;

    @Test
    public void getWriters() {
        Collection<Writer> writers = writerDao.getWriters();
        Assert.assertFalse(writers.isEmpty());
    }

    @Test
    public void getWriterDTOs() {
        Collection <WriterDTO> writers = writerDao.getWriterDTOs();
        Assert.assertFalse(writers.isEmpty());
    }

    @Test
    public void getWriterById() {
        Writer writer = writerDao.getWriterById(1);
        Assert.assertNotNull(writer);
        Assert.assertTrue(writer.getWriterId().equals(WRITER_ID_1));
        Assert.assertEquals(PUSHKIN, writer.getWriterName());
        Assert.assertEquals(RUSSIA, writer.getWriterCountry());
    }

    @Test
    public void getWriterDTOById() {
        WriterDTO writerDTO = writerDao.getWriterDTOById(1);
        Assert.assertNotNull(writerDTO);
        Assert.assertTrue(writerDTO.getId().equals(WRITER_ID_1));
        Assert.assertEquals(PUSHKIN, writerDTO.getName());
        Assert.assertEquals(RUSSIA, writerDTO.getCountry());
        Assert.assertTrue(writerDTO.getNumberOfPublications().
                equals(NUMBER_OF_PUBLICATIONS_1));
    }


    @Test
    public void addWriter() {
        Collection<Writer> writers = writerDao.getWriters();
        int size = writers.size();
        Writer writer = new Writer(GOGOL, UKRAINE);
        Writer returnedWriter = writerDao.addWriter(writer);
        Integer id = returnedWriter.getWriterId();
        Assert.assertNotNull(id);
        Writer addedWriter = writerDao.getWriterById(id);
        Assert.assertEquals(GOGOL, addedWriter.getWriterName());
        Assert.assertEquals(UKRAINE, addedWriter.getWriterCountry());
        Assert.assertEquals(size+1, writerDao.getWriters().size());
    }

    @Test
    public void updateWriter() {
        Writer writer = new Writer(BYKOV_ALEX, RUSSIA);
        Writer addedWriter = writerDao.addWriter(writer);
        addedWriter.setWriterName(BYKOV_VASIL);
        addedWriter.setWriterCountry(BELARUS);
        writerDao.updateWriter(addedWriter);
        Writer updatedWriter =
                writerDao.getWriterById(addedWriter.getWriterId());
        Assert.assertEquals(addedWriter.getWriterId(),
                updatedWriter.getWriterId());
        Assert.assertEquals(BYKOV_VASIL, updatedWriter.getWriterName());
        Assert.assertEquals(BELARUS, updatedWriter.getWriterCountry());
    }

    @Test
    public void deleteWriterById() {
        Writer writer = new Writer(BYKOV_ALEX, RUSSIA);
        Writer addedWriter = writerDao.addWriter(writer);
        Collection<Writer> writers = writerDao.getWriters();
        int oldSize = writers.size();

        writerDao.deleteWriterById(addedWriter.getWriterId());
        writers = writerDao.getWriters();
        int newSize = writers.size();

        Assert.assertEquals(oldSize - 1, newSize);
    }
}