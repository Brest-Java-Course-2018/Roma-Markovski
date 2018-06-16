package com.epam.brest.course.web_app.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.validation.constraints.AssertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:web-spring-test.xml")
@WebAppConfiguration
public class ErrorControllerTest {

    @Autowired
    private WriterController writerController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(writerController)
                .setControllerAdvice(new ErrorController())
                .build();
    }

    @Test
    public void iWillDeleteThisTestLater() {
        Assert.assertTrue(true);
    }
}
