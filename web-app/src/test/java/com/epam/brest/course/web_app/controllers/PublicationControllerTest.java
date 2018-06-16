package com.epam.brest.course.web_app.controllers;

import com.epam.brest.course.dto.PublicationDTO;
import com.epam.brest.course.model.DateInterval;
import com.epam.brest.course.model.Publication;
import com.epam.brest.course.model.Writer;
import com.epam.brest.course.service.PublicationService;
import com.epam.brest.course.service.WriterService;
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

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:web-spring-test.xml")
@WebAppConfiguration
public class PublicationControllerTest {
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
    private static final String PUSHKIN_ALEX = "Pushkin Alex";
    private static final String RUSSIA = "Russia";
    private static final String KUPALA_YANKA = "Kupala Yanka";
    private static final String BELARUS = "Belarus";

    private static PublicationDTO publicationDTO1;

    private static PublicationDTO publicationDTO2;

    private static Publication publication1;

    private static Publication publication2;

    private static Writer writer1;

    private static Writer writer2;


    private static final String START_DATE = "2017-07-03";

    private static final String END_DATE = "2018-03-13";

    @Autowired
    private PublicationController publicationController;

    @Autowired
    private PublicationService publicationService;

    @Autowired
    private WriterService writerService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        InternalResourceViewResolver viewResolver =
                new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/templates/");
        viewResolver.setSuffix(".html");

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
        writer1 = new Writer(PUSHKIN_ALEX, RUSSIA);
        writer1.setId(ID_1);
        writer2 = new Writer(KUPALA_YANKA, BELARUS);
        writer2.setId(ID_2);
        mockMvc = MockMvcBuilders.standaloneSetup(publicationController)
                .setViewResolvers(viewResolver)
                .build();
    }

    public void tearDown(Object object) {
        verify(object);
        reset(object);
    }

    @Test
    public void getPublicationDTOs() throws Exception {
        List<PublicationDTO> publications =
                Arrays.asList(publicationDTO1, publicationDTO2);
        expect(publicationService.getPublicationDTOs())
                .andReturn(publications);
        replay(publicationService);
        mockMvc.perform(
                get("/publications")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("publications"))
                .andExpect(model().attributeExists("publications",
                        "dateInterval", "isCollapsed"))
                .andExpect(model().attribute("isCollapsed", true));
        tearDown(publicationService);
    }

    @Test
    public void wrongGetPublicationDTOsByDate() throws Exception {
        List<PublicationDTO> publications =
                Arrays.asList(publicationDTO1, publicationDTO2);
        expect(publicationService.getPublicationDTOs())
                .andReturn(publications);
        replay(publicationService);
        mockMvc.perform(
                post("/publications")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("startDate", END_DATE)
                        .param("endDate", START_DATE)
        ).andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attributeHasErrors("dateInterval"))
                .andExpect(model().attributeExists("publications"))
                .andExpect(view().name("publications"));
        tearDown(publicationService);
    }

    @Test
    public void getPublicationDTOsByDate() throws Exception {
        List<PublicationDTO> publications =
                Arrays.asList(publicationDTO1, publicationDTO2);
        expect(publicationService.getPublicationDTOsByDate(
                anyObject(DateInterval.class)))
                .andReturn(publications);
        replay(publicationService);
        mockMvc.perform(
                post("/publications")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("startDate", START_DATE)
                        .param("endDate", END_DATE)
        ).andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attributeExists("publications",
                        "dateInterval", "isCollapsed"))
                .andExpect(model().attribute("isCollapsed", false))
                .andExpect(view().name("publications"));
        tearDown(publicationService);
    }


    @Test
    public void gotoAddPublication() throws Exception {
        List<Writer> writers =
                Arrays.asList(writer1, writer2);
        expect(writerService.getWriters())
                .andReturn(writers);
        replay(writerService);
        mockMvc.perform(
                get("/publication")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attributeExists("publication",
                        "isNew"))
                .andExpect(model().attribute("isNew", true))
                .andExpect(view().name("publication"));
        tearDown(writerService);
    }

    @Test
    public void wrongAddPublication() throws Exception {
        List<Writer> writers =
                Arrays.asList(writer1, writer2);
        expect(writerService.getWriters())
                .andReturn(writers);
        replay(writerService);
        mockMvc.perform(
                post("/publication")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("name","")
                        .param("writerId", "")
                        .param("date", "1995-12-20")
                        .param("numberOfPages","sixteen")
        ).andDo(print())
                .andExpect(model().attributeHasFieldErrors(
                        "publication", "name", "writerId",
                        "date", "numberOfPages"
                ))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("publication"));
        tearDown(writerService);
    }

    @Test
    public void addPublication() throws Exception {
        expect(publicationService.addPublication(
                anyObject(Publication.class)))
                .andReturn(publication1);
        replay(publicationService);
        mockMvc.perform(
                post("/publication")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("name","War and Peace")
                        .param("writerId", "1")
                        .param("date", "2017-12-20")
                        .param("numberOfPages","16")
        ).andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/publications"));
        tearDown(publicationService);
    }

    @Test
    public void gotoEditPublication() throws Exception {
        expect(publicationService.getPublicationById(anyInt()))
                .andReturn(publication1);
        List<Writer> writers =
                Arrays.asList(writer1, writer2);
        expect(writerService.getWriters())
                .andReturn(writers);
        replay(publicationService);
        replay(writerService);
        mockMvc.perform(
                get("/publication/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attributeExists("publication", "writers", "isNew"))
                .andExpect(model().attribute("isNew", false))
                .andExpect(view().name("publication"));
        tearDown(publicationService);
        tearDown(writerService);
    }

    @Test
    public void wrongEditPublication() throws Exception {
        List<Writer> writers =
                Arrays.asList(writer1, writer2);
        expect(writerService.getWriters())
                .andReturn(writers);
        replay(writerService);
        mockMvc.perform(
                post("/publication/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("name","")
                        .param("writerId", "")
                        .param("date", "1995-12-20")
                        .param("numberOfPages","sixteen")
        ).andDo(print())
                .andExpect(model().attributeHasFieldErrors(
                        "publication", "name", "writerId",
                        "date", "numberOfPages"
                ))
                .andExpect(model().attribute("isNew", false))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("publication"));
        tearDown(writerService);
    }

    @Test
    public void editPublication() throws Exception {
        publicationService.updatePublication(
                anyObject(Publication.class));
        expectLastCall();
        replay(publicationService);
        mockMvc.perform(
                post("/publication/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("name","War and Peace")
                        .param("writerId", "1")
                        .param("date", "2017-12-20")
                        .param("numberOfPages","16")
        ).andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/publications"));
        tearDown(publicationService);
    }

    @Test
    public void deletePublication() throws Exception {
        publicationService.deletePublicationById(anyInt());
        expectLastCall();
        replay(publicationService);
        mockMvc.perform(
                get("/publication/2/delete")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/publications"));
        tearDown(publicationService);
    }
}
