package com.epam.brest.course.web_app.controllers;

import com.epam.brest.course.dto.WriterDTO;
import com.epam.brest.course.model.Writer;
import com.epam.brest.course.service.WriterService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Arrays;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:web-spring-test.xml")
@WebAppConfiguration
public class WriterControllerTest {
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
    private WriterController writerController;

    @Autowired
    private WriterService mockWriterService;


    private MockMvc mockMvc;

    @Before
    public void setUp() {
        InternalResourceViewResolver viewResolver =
                new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/templates/");
        viewResolver.setSuffix(".html");

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
        mockMvc = MockMvcBuilders.standaloneSetup(writerController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @After
    public void tearDown() {
        verify(mockWriterService);
        reset(mockWriterService);
    }

    @Test
    public void getWriterDTOs() throws Exception {
        List<WriterDTO> writers = Arrays.asList(writerDTO1, writerDTO2);
        expect(mockWriterService.getWriterDTOs())
                .andReturn(writers);
        replay(mockWriterService);
        mockMvc.perform(
                get("/writers")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(view().name("writers"))
                .andExpect(model().attributeExists("writers"))
                .andExpect(model().attribute("writers", writers));
    }

//    @Test
//    public void gotoAddWriter() throws Exception {
//        replay(mockWriterService);
//        mockMvc.perform(
//                get("/writer")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//        ).andDo(print())
//                .andExpect(model().attributeExists("writer"))
//                .andExpect(model().attributeExists("isNew"))
//                .andExpect(model().attribute("isNew", true))
//                .andExpect(view().name("writer"));
//    }
//
//    @Test
//    public void wrongAddWriter() throws Exception {
//        replay(mockWriterService);
//        mockMvc.perform(
//                post("/writer")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .param("name","")
//                        .param("country", "")
//        ).andDo(print())
//                .andExpect(model().attributeHasFieldErrors(
//                        "writer", "name", "country"
//                        ))
//                .andExpect(status().isOk())
//                .andExpect(view().name("writer"));
//    }
//
//    @Test
//    public void addWriter() throws Exception {
//        expect(mockWriterService.addWriter(anyObject(Writer.class)))
//                .andReturn(writer1);
//        replay(mockWriterService);
//        mockMvc.perform(
//                post("/writer")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .param("name","yanka")
//                        .param("country", "belarus")
//        ).andDo(print())
//                .andExpect(view().name("redirect:/writers"));
//    }

//    @Test
//    public void getWriters() throws Exception {
//        expect(writerService.getWriters())
//                .andReturn(Arrays.asList(writer1, writer2));
//        replay(writerService);
//        mockMvc.perform(
//                get("/writer_models")
//                        .accept(MediaType.APPLICATION_JSON)
//        ).andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(view().contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(jsonPath("$[0].id", is(ID_1)))
//                .andExpect(jsonPath("$[0].name", is(PUSHKIN_ALEX)))
//                .andExpect(jsonPath("$[1].id", is(ID_2)))
//                .andExpect(jsonPath("$[1].name", is(KUPALA_YANKA)));
//    }
//
//    @Test
//    public void getWriterById() throws Exception {
//        expect(writerService.getWriterById(anyInt()))
//                .andReturn(writer1);
//        replay(writerService);
//        mockMvc.perform(
//                get("/writers/1")
//                        .accept(MediaType.APPLICATION_JSON)
//        ).andDo(print())
//                .andExpect(status().isFound())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(jsonPath("$.id", is(ID_1)))
//                .andExpect(jsonPath("$.name", is(PUSHKIN_ALEX)))
//                .andExpect(jsonPath("$.country", is(RUSSIA)));
//    }
//
//    @Test
//    public void addWriter() throws Exception {
//        expect(writerService.addWriter(anyObject(Writer.class)))
//                .andReturn(writer2);
//        replay(writerService);
//        mockMvc.perform(
//                post("/writers")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(mapper.writeValueAsString(writer2)) //not important which writer
//                        .accept(MediaType.APPLICATION_JSON)
//        ).andDo(print())
//                .andExpect(status().isCreated())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(jsonPath("$.id", is(ID_2)))
//                .andExpect(jsonPath("$.name", is(KUPALA_YANKA)))
//                .andExpect(jsonPath("$.country", is(BELARUS)));
//    }
//
//    @Test
//    public void updateWriter() throws Exception {
//        writerService.updateWriter(anyObject(Writer.class));
//        expectLastCall();
//        replay(writerService);
//        mockMvc.perform(
//                post("/writers/2")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(mapper.writeValueAsString(writer2)) //not important which writer
//                        .accept(MediaType.APPLICATION_JSON)
//        ).andDo(print())
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void deleteWriter() throws Exception {
//        writerService.deleteWriterById(1);
//        expectLastCall();
//        replay(writerService);
//        mockMvc.perform(
//                delete("/writers/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(mapper.writeValueAsString(writer1)) //not important which writer
//                        .accept(MediaType.APPLICATION_JSON)
//        ).andDo(print())
//                .andExpect(status().isFound());
//    }
}
