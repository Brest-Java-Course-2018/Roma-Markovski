package com.epam.brest.course.rest;

import com.epam.brest.course.dto.WriterDTO;
import com.epam.brest.course.model.Writer;
import com.epam.brest.course.service.WriterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.easymock.EasyMock.*;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:rest-spring-test.xml"})
public class WriterControllerMockTest {

    private static final int ID_1 = 1;
    private static final String PUSHKIN_ALEX = "Pushkin Alex";
    private static final String RUSSIA = "Russia";
    private static final int ID_2 = 2;
    private static final String KUPALA_YANKA = "Kupala Yanka";
    private static final String BELARUS = "Belarus";
    
    private static WriterDTO writerDTO1;

    private static WriterDTO writerDTO2;

    private static Writer writer1;

    private static Writer writer2;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private WriterRestController writerRestController;

    private MockMvc mockMvc;

    @Autowired
    private WriterService writerService;

    @Before
    public void setUp() {
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
        mockMvc = MockMvcBuilders.standaloneSetup(writerRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }

    @After
    public void tearDown() {
        verify(writerService);
        reset(writerService);
    }

    @Test
    public void getWriterDTOs() throws Exception {
        expect(writerService.getWriterDTOs())
                .andReturn(Arrays.asList(writerDTO1, writerDTO2));
        replay(writerService);
        mockMvc.perform(
                get("/writers")
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id", is(ID_1)))
                .andExpect(jsonPath("$[0].name", is(PUSHKIN_ALEX)))
                .andExpect(jsonPath("$[1].id", is(ID_2)))
                .andExpect(jsonPath("$[1].name", is(KUPALA_YANKA)));
    }

    @Test
    public void getWriters() throws Exception {
        expect(writerService.getWriters())
                .andReturn(Arrays.asList(writer1, writer2));
        replay(writerService);
        mockMvc.perform(
                get("/writer_models")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id", is(ID_1)))
                .andExpect(jsonPath("$[0].name", is(PUSHKIN_ALEX)))
                .andExpect(jsonPath("$[1].id", is(ID_2)))
                .andExpect(jsonPath("$[1].name", is(KUPALA_YANKA)));
    }

    @Test
    public void getWriterById() throws Exception {
        expect(writerService.getWriterById(anyInt()))
                .andReturn(writer1);
        replay(writerService);
        mockMvc.perform(
                get("/writers/1")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(ID_1)))
                .andExpect(jsonPath("$.name", is(PUSHKIN_ALEX)))
                .andExpect(jsonPath("$.country", is(RUSSIA)));
    }

    @Test
    public void addWriter() throws Exception {
        expect(writerService.addWriter(anyObject(Writer.class)))
                .andReturn(writer2);
        replay(writerService);
        mockMvc.perform(
                post("/writers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(writer2)) //not important which writer
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(ID_2)))
                .andExpect(jsonPath("$.name", is(KUPALA_YANKA)))
                .andExpect(jsonPath("$.country", is(BELARUS)));
    }

    @Test
    public void updateWriter() throws Exception {
        writerService.updateWriter(anyObject(Writer.class));
        expectLastCall();
        replay(writerService);
        mockMvc.perform(
                post("/writers/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(writer2)) //not important which writer
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteWriter() throws Exception {
        writerService.deleteWriterById(1);
        expectLastCall();
        replay(writerService);
        mockMvc.perform(
                delete("/writers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(writer1)) //not important which writer
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isFound());
    }
}