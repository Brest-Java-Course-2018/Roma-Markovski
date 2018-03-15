package com.epam.brest.course.dao;

import com.epam.brest.course.model.Department;
import com.epam.brest.course.model.DepartmentForOutput;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-spring.xml",
        "classpath:test-dao.xml", "classpath:dao.xml"})

@Rollback
@Transactional
public class DepartmentDaoImplTest {

    @Autowired
    DepartmentDao departmentDao;

    @Test
    public void getAllDepartments() {
        Collection<DepartmentForOutput> departments = departmentDao.getAllDepartments();
        Assert.assertFalse(departments.isEmpty());
    }

    @Test
    public void getDepartmentById() {
        Department department = departmentDao.getDepartmentById(1);
        Assert.assertNotNull(department);
        Assert.assertTrue(department.getDepartmentId().equals(1));
        Assert.assertTrue(department.getDepartmentName().equals("Distribution"));
        Assert.assertTrue(department.getDescription().equals("Distribution Department"));
    }

//    public void assertRecord(Department department, String departmentName, String description) {
////        Assert.assertTrue(department.getDepartmentId().equals(departmentId));
//        Assert.assertTrue(department.getDepartmentName().equals(departmentName));
//        Assert.assertTrue(department.getDescription().equals(description));
//    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void addDepartment() {
//        Department department = new Department();
//        department.setDepartmentName("Insert");
//        department.setDescription("Insert test");
//        department = departmentDao.addDepartment(department);
//        department = departmentDao.getDepartmentByName("Insert");
//        assertRecord(department, "Insert", "Insert test");

        Collection<DepartmentForOutput> departments=departmentDao.getAllDepartments();
        int sizeBefore = departments.size();
        Department department = new Department("Education and Training", "Department Education and Training");
        Department newDepartment = departmentDao.addDepartment(department);
        Assert.assertNotNull(newDepartment.getDepartmentId());
        Assert.assertTrue(newDepartment.getDepartmentName().equals(department.getDepartmentName()));
        Assert.assertTrue(newDepartment.getDescription().equals(department.getDescription()));
        Assert.assertTrue((sizeBefore+1)==departmentDao.getAllDepartments().size());
    }

    @Test(expected=IllegalArgumentException.class)
    public void addSameDepartment() {
        Department department = new Department("Education and Training", "Department of Education and Training");
        departmentDao.addDepartment(department);
        departmentDao.addDepartment(department);
    }

    @Test()
    public void addSameDepartmentWithRule() {
        Department department = new Department("Education and Training", "Department of Education and Training");
        departmentDao.addDepartment(department);

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Department with the same name");
        departmentDao.addDepartment(department);
    }

    @Test
    public void updateDepartment() {
        Department department = new Department("Education and Training", "Department of Education");
        Department newDepartment = departmentDao.addDepartment(department);
        department.setDepartmentName("NEW Education");
        department.setDescription("NEW Department of Education");

        departmentDao.updateDepartment(newDepartment);
        Department updatedDepartment = departmentDao.getDepartmentById(newDepartment.getDepartmentId());
        Assert.assertTrue(newDepartment.getDepartmentId().
                equals(updatedDepartment.getDepartmentId()));
        Assert.assertTrue(newDepartment.getDepartmentName().
                equals(updatedDepartment.getDepartmentName()));
        Assert.assertTrue(newDepartment.getDescription().
                equals(updatedDepartment.getDescription()));

//        Department department = new Department();
//        department.setDepartmentName("Update");
//        department.setDescription("Update test");
//        departmentDao.updateDepartment(1, department);
//        department = departmentDao.getDepartmentByName("Update");
//        assertRecord(department, "Update", "Update test");
    }


    @Test
    public void deleteDepartment() {
//        departmentDao.deleteDepartment(2);
//        Assert.assertNull(departmentDao.getDepartmentById(2));
        Department department = new Department("Education", "Department of Education");
        department = departmentDao.addDepartment(department);
        Collection<DepartmentForOutput> departments = departmentDao.getAllDepartments();
        int sizeBefore= departments.size();
        departmentDao.deleteDepartmentById(department.getDepartmentId());
        Assert.assertTrue((sizeBefore-1)==departmentDao.getAllDepartments().size());
    }
}