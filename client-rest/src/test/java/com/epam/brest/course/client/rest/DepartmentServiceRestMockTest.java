package com.epam.brest.course.client.rest;

import com.epam.brest.course.dto.DepartmentForOutput;
import com.epam.brest.course.model.Department;
import com.epam.brest.course.service.DepartmentService;
import org.easymock.EasyMock;
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
public class DepartmentServiceRestMockTest {
    private static DepartmentForOutput departmentForOutput1;
    private static DepartmentForOutput departmentForOutput2;
    private static Department department;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    private RestTemplate mockRestTemplate;

    @Before
    public void init() {
        departmentForOutput1 = new DepartmentForOutput();
        departmentForOutput1.setDepartmentId(1);
        departmentForOutput1.setDepartmentName("name1");
        departmentForOutput2 = new DepartmentForOutput();
        departmentForOutput2.setDepartmentId(2);
        departmentForOutput2.setDepartmentName("name2");
        department = new Department("name", "desc");
        department.setDepartmentId(3);
    }

    @After
    public void tearDown() {
        verify(mockRestTemplate);
        reset(mockRestTemplate);
    }

    @Test
    public void getAllDepartments() {
        List departments = Arrays.asList(departmentForOutput1, departmentForOutput2);
        ResponseEntity entity = new ResponseEntity<>(departments, HttpStatus.OK);
        expect(mockRestTemplate.getForEntity(anyString(), anyObject())).andReturn(entity);
        replay(mockRestTemplate);

        Collection<DepartmentForOutput> results = departmentService.getAllDepartmentsForOutput();

        Assert.assertNotNull(results);
        Assert.assertEquals(2, results.size());
    }

    @Test
    public void getDepartmentById() {
        ResponseEntity entity = new ResponseEntity<> (department, HttpStatus.OK);
        expect(mockRestTemplate.getForEntity(anyString(), anyObject())).andReturn(entity);
        replay(mockRestTemplate);

        Department result = departmentService.getDepartmentById(3);

        Assert.assertNotNull(result);
        Assert.assertEquals("name", result.getDepartmentName());
    }

    @Test
    public void addDepartment() {
        ResponseEntity entity = new ResponseEntity<> (department, HttpStatus.OK);
        expect(mockRestTemplate.postForEntity(anyString(), anyObject(), anyObject())).andReturn(entity);
        replay(mockRestTemplate);

        Department result = departmentService.addDepartment(department);

        Assert.assertNotNull(result);
        Assert.assertEquals(3, result.getDepartmentId().intValue());
    }
}
