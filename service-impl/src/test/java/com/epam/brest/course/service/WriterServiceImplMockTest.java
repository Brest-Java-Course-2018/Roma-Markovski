package com.epam.brest.course.service;

import com.epam.brest.course.dao.WriterDao;
import com.epam.brest.course.dto.WriterDTO;
import com.epam.brest.course.model.Writer;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-mock-test.xml"})
public class WriterServiceImplMockTest {

    private static final Writer PUSHKIN = new Writer(
            "Pushkin Alex", "Russia");

    private static final Writer LERMONTOV = new Writer(
            "Lermontov Michael", "Russia");

    private static final WriterDTO PUSHKIN_DTO = new WriterDTO(
            "Pushkin Alex", "Russia", 1);

    private static final WriterDTO LERMONTOV_DTO = new WriterDTO(
            "Lermontov Michael", "Russia", 2);

    private static final List<Writer> WRITERS =
            Arrays.asList(PUSHKIN, LERMONTOV);

    private static final List<WriterDTO> WRITER_DTOS =
            Arrays.asList(PUSHKIN_DTO, LERMONTOV_DTO);

    @Autowired
    private WriterService writerService;

    @Autowired
    private WriterDao mockWriterDao;

    @After
    public void verifyAndReset() {
        EasyMock.verify(mockWriterDao);
        EasyMock.reset(mockWriterDao);
    }

    @Test
    public void getWriters() {
        EasyMock.expect(mockWriterDao.getWriters()).andReturn(WRITERS);
        EasyMock.replay(mockWriterDao);
        Collection<Writer> writers = writerService.getWriters();
        Assert.assertEquals(writers, WRITERS);
    }

    @Test
    public void getWriterDTOs() {
        EasyMock.expect(mockWriterDao.getWriterDTOs()).andReturn(WRITER_DTOS);
        EasyMock.replay(mockWriterDao);
        Collection<WriterDTO> writerDTOs = writerService.getWriterDTOs();
        Assert.assertEquals(writerDTOs, WRITER_DTOS);
    }

    @Test
    public void getWriterById() {
        EasyMock.expect(mockWriterDao.getWriterById(EasyMock.anyInt()))
                .andReturn(PUSHKIN);
        EasyMock.replay(mockWriterDao);
        Writer writer = writerService.getWriterById(1);
        Assert.assertEquals(writer, PUSHKIN);
    }

    @Test
    public void addWriter() {
        EasyMock.expect(mockWriterDao.addWriter(
                EasyMock.anyObject(Writer.class)))
                .andReturn(PUSHKIN);
        EasyMock.replay(mockWriterDao);
        Writer publication = writerService.addWriter(PUSHKIN);
        Assert.assertEquals(publication, PUSHKIN);
    }

    @Test
    public void updateWriter() {
        mockWriterDao.updateWriter(EasyMock.anyObject(Writer.class));
        EasyMock.expectLastCall();
        EasyMock.replay(mockWriterDao);
        writerService.updateWriter(PUSHKIN);
    }

    @Test
    public void deleteWriterById() {
        mockWriterDao.deleteWriterById(EasyMock.anyInt());
        EasyMock.expectLastCall();
        EasyMock.replay(mockWriterDao);
        writerService.deleteWriterById(1);
    }
}