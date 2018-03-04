package com.epam.brest.course.dao;

import com.epam.brest.course.model.Department;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-spring.xml",
        "classpath:test-dao.xml"})
public class DepartmentDaoImplTest {

    @Autowired
    DepartmentDao departmentDao;

    @Test
    public void getAllDepartments() {
        List<Department> departments = departmentDao.getAllDepartments();
        Assert.assertFalse(departments.isEmpty());
    }

    @Test
    public void getDepartmentById() {
        Department department = departmentDao.getDepartmentById(1);
        //Assert.assertNotNull(department);
        assertRecord(department, "Distribution", "Distribution Department");
    }

    public void assertRecord(Department department, String departmentName, String description) {
        Assert.assertTrue(department.getDepartmentName().equals(departmentName));
        Assert.assertTrue(department.getDescription().equals(description));
    }

    @Test
    public void addDepartment() {
        Department department = new Department();
        department.setDepartmentName("Insert");
        department.setDescription("Insert test");
        department = departmentDao.addDepartment(department);
        department = departmentDao.getDepartmentByName("Insert");
        assertRecord(department, "Insert", "Insert test");
    }

    @Test
    public void updateDepartment() {
        Department department = new Department();
        department.setDepartmentName("Update");
        department.setDescription("Update test");
        departmentDao.updateDepartment(1, department);
        department = departmentDao.getDepartmentByName("Update");
        assertRecord(department, "Update", "Update test");
    }


    @Test
    public void deleteDepartment() {
        departmentDao.deleteDepartment(2);
        Assert.assertNull(departmentDao.getDepartmentById(2));
    }
}