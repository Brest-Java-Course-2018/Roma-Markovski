package com.epam.brest.course.rest;

import com.epam.brest.course.dto.DepartmentForOutput;
import com.epam.brest.course.service.DepartmentService;
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

import static org.hamcrest.Matchers.is;
import static org.easymock.EasyMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:rest-spring-test.xml"})
public class DepartmentControllerMockTest {

    private static DepartmentForOutput departmentForOutput1;
    private static DepartmentForOutput departmentForOutput2;

    @Autowired
    private DepartmentRestController departmentRestController;

    private MockMvc mockMvc;

    @Autowired
    private DepartmentService departmentService;

    @Before
    public void setUp() {
        departmentForOutput1 = new DepartmentForOutput();
        departmentForOutput1.setDepartmentId(1);
        departmentForOutput1.setDepartmentName("name1");
        departmentForOutput2 = new DepartmentForOutput();
        departmentForOutput2.setDepartmentId(2);
        departmentForOutput2.setDepartmentName("name2");
        mockMvc = MockMvcBuilders.standaloneSetup(departmentRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }

    @After
    public void tearDown() {
        verify(departmentService);
        reset(departmentService);
    }

    @Test
    public void getAllDepartmentsForOutput() throws Exception {
        expect(departmentService.getAllDepartmentsForOutput())
                .andReturn(Arrays.asList(departmentForOutput1, departmentForOutput2));
        replay(departmentService);

        mockMvc.perform(
                get("/departments")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].departmentId", is(1)))
                .andExpect(jsonPath("$[0].departmentName", is("name1")))
                .andExpect(jsonPath("$[1].departmentId", is(2)))
                .andExpect(jsonPath("$[1].departmentName", is("name2")));
    }
}
