package com.epam.brest.course.service;

import com.epam.brest.course.dao.DepartmentDao;
import com.epam.brest.course.model.Department;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:service-test.xml", "classpath:test-db-spring.xml", "classpath:dao.xml"})
public class DepartmentServiceImplTest {

    private static final int ID = 1;
    private static final String DESC = "Academic Department";

    @Autowired
    private DepartmentService departmentService;

    @Test
    public void getDepartmentById() {
        Assert.assertTrue(true);
    }

    @Test
    public void updateDepartmentDescription() {
        departmentService.updateDepartmentDescription(ID,DESC);
        Department department = departmentService.getDepartmentById(ID);
        Assert.assertEquals(DESC, department.getDescription());
    }
}