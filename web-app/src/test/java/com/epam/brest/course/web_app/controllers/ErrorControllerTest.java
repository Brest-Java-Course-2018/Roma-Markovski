package com.epam.brest.course.web_app.controllers;

import com.epam.brest.course.client.ServerDataAccessException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:web-spring-test.xml")
@WebAppConfiguration
public class ErrorControllerTest {

    @Autowired
    private WriterController writerController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        InternalResourceViewResolver viewResolver =
                new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/templates/");
        viewResolver.setSuffix(".html");
        mockMvc = MockMvcBuilders.standaloneSetup(writerController)
                .setViewResolvers(viewResolver)
                .setControllerAdvice(new ErrorHandler())
                .build();
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void errorTestWithRule() {
        thrown.expect(ServerDataAccessException.class);
        thrown.expectMessage("Example error.");
        writerController.throwError();
    }

    @Test
    public void errorTest() throws Exception {
        mockMvc.perform(
                get("/errorExample")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(model().attributeExists("message"))
                .andExpect(model().attribute("message", "Example error."))
                .andExpect(view().name("error"));
    }

}
