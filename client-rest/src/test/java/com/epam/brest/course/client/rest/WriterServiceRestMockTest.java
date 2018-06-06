package com.epam.brest.course.client.rest;

import com.epam.brest.course.dto.WriterDTO;
import com.epam.brest.course.model.Writer;
import com.epam.brest.course.service.WriterService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.easymock.EasyMock.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context-test.xml")
public class WriterServiceRestMockTest {

    private static final int ID_1 = 1;
    private static final String PUSHKIN_ALEX = "Pushkin Alex";
    private static final String RUSSIA = "Russia";
    private static final int ID_2 = 2;
    private static final String KUPALA_YANKA = "Kupala Yanka";
    private static final String BELARUS = "Belarus";
    private static final int WRITERS_SIZE = 2;

    private static WriterDTO writerDTO1;

    private static WriterDTO writerDTO2;

    private static Writer writer1;

    private static Writer writer2;

    @Autowired
    private WriterService writerService;

    @Autowired
    private RestTemplate mockRestTemplate;

    @Before
    public void init() {
        writerDTO1 = new WriterDTO();
        writerDTO1.setId(ID_1);
        writerDTO1.setName(PUSHKIN_ALEX);
        writerDTO1.setCountry(RUSSIA);
        writerDTO2 = new WriterDTO();
        writerDTO2.setId(ID_2);
        writerDTO2.setName(KUPALA_YANKA);
        writerDTO2.setCountry(BELARUS);
        writer1 = new Writer(PUSHKIN_ALEX, RUSSIA);
        writer1.setId(ID_1);
        writer2 = new Writer(KUPALA_YANKA, BELARUS);
        writer2.setId(ID_2);
    }

    @After
    public void tearDown() {
        verify(mockRestTemplate);
        reset(mockRestTemplate);
    }

    @Test
    public void getWriters() {
        List<Writer> writers = Arrays.asList(writer1, writer2);
        ResponseEntity entity = new ResponseEntity<>(writers, HttpStatus.OK); //status doesn't matter
        expect(mockRestTemplate.getForEntity(anyString(), anyObject()))
                .andReturn(entity);
        replay(mockRestTemplate);
        Collection<Writer> resultWriters =
                writerService.getWriters();
        Assert.assertNotNull(resultWriters);
        Assert.assertEquals(WRITERS_SIZE, resultWriters.size());
    }

    @Test
    public void getWriterDTOs() {
        List<WriterDTO> writers = Arrays.asList(writerDTO1, writerDTO2);
        ResponseEntity entity = new ResponseEntity<>(writers, HttpStatus.OK); //status doesn't matter
        expect(mockRestTemplate.getForEntity(anyString(), anyObject()))
            .andReturn(entity);
        replay(mockRestTemplate);
        Collection<WriterDTO> resultWriters =
                writerService.getWriterDTOs();
        Assert.assertNotNull(resultWriters);
        Assert.assertEquals(WRITERS_SIZE, resultWriters.size());
    }

    @Test
    public void getWriterById() {
        ResponseEntity entity = new ResponseEntity<>(
                writer1, HttpStatus.OK);
        expect(mockRestTemplate.getForEntity(anyString(), anyObject()))
                .andReturn(entity);

        replay(mockRestTemplate);

        Writer resultWriter = writerService.getWriterById(ID_1);
        Assert.assertNotNull(resultWriter);
        Assert.assertEquals(ID_1, resultWriter.getId().intValue());
        Assert.assertEquals(PUSHKIN_ALEX, resultWriter.getName());
    }

    @Test
    public void addWriter() {
        ResponseEntity entity = new ResponseEntity<>(
                writer2, HttpStatus.OK);
        expect(mockRestTemplate.postForEntity(
                anyString(), anyObject(Writer.class), anyObject()))
                .andReturn(entity);

        replay(mockRestTemplate);

        Writer resultWriter = writerService.addWriter(writer2);
        Assert.assertNotNull(resultWriter);
        Assert.assertEquals(ID_2, resultWriter.getId().intValue());
        Assert.assertEquals(KUPALA_YANKA, resultWriter.getName());
    }

    @Test
    public void updateWriter() {
        ResponseEntity entity = new ResponseEntity<>(HttpStatus.OK);
        expect(mockRestTemplate.postForEntity(
                anyString(), anyObject(Writer.class), anyObject()))
                .andReturn(entity);

        replay(mockRestTemplate);

        writerService.updateWriter(writer1);
        Assert.assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

    @Test
    public void deleteWriter() {
        mockRestTemplate.delete(anyString());
        expectLastCall();

        replay(mockRestTemplate);

        writerService.deleteWriterById(ID_2);
    }
}
