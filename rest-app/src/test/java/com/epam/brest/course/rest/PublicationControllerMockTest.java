package com.epam.brest.course.rest;

import com.epam.brest.course.dto.PublicationDTO;
import com.epam.brest.course.model.DateInterval;
import com.epam.brest.course.model.Publication;
import com.epam.brest.course.service.PublicationService;
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

import java.sql.Date;
import java.util.Arrays;

import static org.easymock.EasyMock.*;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:rest-spring-test.xml"})
public class PublicationControllerMockTest {

    private static final String EVGENIY_ONEGIN = "Evgeniy Onegin";
    private static final String ALEX_PUSHKIN = "Alex Pushkin";
    private static final String DATE1 = "2018-03-21";
    private static final String DATE2 = "2016-05-21";
    private static final String FORMATTED_DATE1 = "21.03.2018";
    private static final String FORMATTED_DATE2 = "21.05.2016";
    private static final String POEM = "Poem";
    private static final int NUMBER_OF_PAGES1 = 235;
    private static final int NUMBER_OF_PAGES2 = 134;
    private static final String DUBROVSKI = "Dubrovski";
    private static final String PROSE = "Prose";
    private static final int DTO_ID_1 = 1;
    private static final int DTO_ID_2 = 2;
    private static final int ID_1 = 1;
    private static final int ID_2 = 2;
    private static final int WRITER_ID = 1;

    private static PublicationDTO publicationDTO1;

    private static PublicationDTO publicationDTO2;

    private static Publication publication1;

    private static Publication publication2;

    @Autowired
    private ObjectMapper mapper;

    private static final String START_DATE = "2017-07-03";

    private static final String END_DATE = "2018-03-13";

    @Autowired
    private PublicationRestController publicationRestController;

    private MockMvc mockMvc;

    @Autowired
    private PublicationService publicationService;

    @Before
    public void setUp() {
        publicationDTO1 = new PublicationDTO(
                EVGENIY_ONEGIN, ALEX_PUSHKIN, FORMATTED_DATE1,
                NUMBER_OF_PAGES1, POEM);
        publicationDTO1.setId(DTO_ID_1);
        publicationDTO2 = new PublicationDTO(
                DUBROVSKI, ALEX_PUSHKIN, FORMATTED_DATE2,
                NUMBER_OF_PAGES2, PROSE);
        publicationDTO2.setId(DTO_ID_2);
        publication1 = new Publication(
                EVGENIY_ONEGIN, WRITER_ID, Date.valueOf(DATE1),
                NUMBER_OF_PAGES1, POEM);
        publication1.setId(ID_1);
        publication2 = new Publication(
                DUBROVSKI, WRITER_ID, Date.valueOf(DATE2),
                NUMBER_OF_PAGES2, PROSE);
        publication2.setId(ID_2);
        mockMvc = MockMvcBuilders.standaloneSetup(publicationRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }

    @After
    public void tearDown() {
        verify(publicationService);
        reset(publicationService);
    }

    @Test
    public void getPublicationDTOs() throws Exception {
        expect(publicationService.getPublicationDTOs())
                .andReturn(Arrays.asList(publicationDTO1, publicationDTO2));
        replay(publicationService);
        mockMvc.perform(
                get("/publications")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id", is(DTO_ID_1)))
                .andExpect(jsonPath("$[0].name", is(EVGENIY_ONEGIN)))
                .andExpect(jsonPath("$[0].writerName", is(ALEX_PUSHKIN)))
                .andExpect(jsonPath("$[0].numberOfPages", is(NUMBER_OF_PAGES1)))
                .andExpect(jsonPath("$[0].description", is(POEM)))
                .andExpect(jsonPath("$[1].id", is(DTO_ID_2)))
                .andExpect(jsonPath("$[1].name", is(DUBROVSKI)))
                .andExpect(jsonPath("$[1].writerName", is(ALEX_PUSHKIN)))
                .andExpect(jsonPath("$[1].numberOfPages", is(NUMBER_OF_PAGES2)))
                .andExpect(jsonPath("$[1].description", is(PROSE)));
    }

    @Test
    public void getPublications() throws Exception {
        expect(publicationService.getPublications())
                .andReturn(Arrays.asList(publication1, publication2));
        replay(publicationService);
        mockMvc.perform(
                get("/publication_models")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id", is(DTO_ID_1)))
                .andExpect(jsonPath("$[0].name", is(EVGENIY_ONEGIN)))
                .andExpect(jsonPath("$[0].writerId", is(WRITER_ID)))
                .andExpect(jsonPath("$[0].numberOfPages", is(NUMBER_OF_PAGES1)))
                .andExpect(jsonPath("$[0].description", is(POEM)))
                .andExpect(jsonPath("$[1].id", is(DTO_ID_2)))
                .andExpect(jsonPath("$[1].name", is(DUBROVSKI)))
                .andExpect(jsonPath("$[1].writerId", is(WRITER_ID)))
                .andExpect(jsonPath("$[1].numberOfPages", is(NUMBER_OF_PAGES2)))
                .andExpect(jsonPath("$[1].description", is(PROSE)));
    }

    @Test
    public void getPublicationById() throws Exception {
        expect(publicationService.getPublicationById(anyInt()))
                .andReturn(publication1);
        replay(publicationService);
        mockMvc.perform(
                get("/publications/1")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(ID_1)))
                .andExpect(jsonPath("$.name", is(EVGENIY_ONEGIN)))
                .andExpect(jsonPath("$.writerId", is(WRITER_ID)))
                .andExpect(jsonPath("$.numberOfPages", is(NUMBER_OF_PAGES1)))
                .andExpect(jsonPath("$.description", is(POEM)));
    }

    @Test
    public void addPublication() throws Exception {
        expect(publicationService.addPublication(
                anyObject(Publication.class)))
                .andReturn(publication2);
        replay(publicationService);
        mockMvc.perform(
                post("/publications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(publication2))
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(ID_2)))
                .andExpect(jsonPath("$.name", is(DUBROVSKI)))
                .andExpect(jsonPath("$.writerId", is(WRITER_ID)))
                .andExpect(jsonPath("$.numberOfPages", is(NUMBER_OF_PAGES2)))
                .andExpect(jsonPath("$.description", is(PROSE)));
    }

    @Test
    public void updatePublication() throws Exception {
        publicationService.updatePublication(anyObject(Publication.class));
        expectLastCall();
        replay(publicationService);
        mockMvc.perform(
                post("/publications/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(publication2))
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deletePublicationById() throws Exception {
        publicationService.deletePublicationById(1);
        expectLastCall();
        replay(publicationService);
        mockMvc.perform(
                delete("/publications/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(publication1))
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isFound());
    }

    @Test
    public void getPublicationDTOsByDate() throws Exception {
        expect(publicationService.getPublicationDTOsByDate(
                anyObject(DateInterval.class)))
                .andReturn(Arrays.asList(publicationDTO1, publicationDTO2));
        replay(publicationService);
        mockMvc.perform(
                get("/publications/{startDate}/{newDate}",
                        START_DATE, END_DATE)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id", is(DTO_ID_1)))
                .andExpect(jsonPath("$[0].name", is(EVGENIY_ONEGIN)))
                .andExpect(jsonPath("$[0].writerName", is(ALEX_PUSHKIN)))
                .andExpect(jsonPath("$[0].numberOfPages", is(NUMBER_OF_PAGES1)))
                .andExpect(jsonPath("$[0].description", is(POEM)))
                .andExpect(jsonPath("$[1].id", is(DTO_ID_2)))
                .andExpect(jsonPath("$[1].name", is(DUBROVSKI)))
                .andExpect(jsonPath("$[1].writerName", is(ALEX_PUSHKIN)))
                .andExpect(jsonPath("$[1].numberOfPages", is(NUMBER_OF_PAGES2)))
                .andExpect(jsonPath("$[1].description", is(PROSE)));
    }
}