package com.epam.brest.course.dao;

import com.epam.brest.course.model.Employee;
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
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-spring.xml",
        "classpath:test-dao.xml", "classpath:dao.xml"})

@Rollback
@Transactional
public class EmployeeDaoImplTest {

    @Autowired
    EmployeeDao employeeDao;

    @Test
    public void getAllEmployees() {
        Collection<Employee> employees = employeeDao.getAllEmployees();
        Assert.assertFalse(employees.isEmpty());
    }

    @Test
    public void getEmployeeById() {
        Employee employee = employeeDao.getEmployeeById(1);
        Assert.assertNotNull(employee);
        Assert.assertTrue(employee.getEmployeeId().equals(1));
        Assert.assertTrue(employee.getEmployeeName().equals("Roma Markovski"));
        Assert.assertTrue(employee.getSalary().equals(1000));
        Assert.assertTrue(employee.getDepartmentId().equals(1));
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void addEmployee() {

        Collection<Employee> employees=employeeDao.getAllEmployees();
        int sizeBefore = employees.size();
        Employee employee = new Employee("Ivan Ivanov", "ivanov@gmail.com", 3000, 2);
        Employee newEmployee = employeeDao.addEmployee(employee);
        Assert.assertNotNull(newEmployee.getEmployeeId());
        Assert.assertTrue(newEmployee.getEmployeeName().equals(employee.getEmployeeName()));
        Assert.assertTrue(newEmployee.getSalary().equals(employee.getSalary()));
        Assert.assertTrue(newEmployee.getDepartmentId().equals(employee.getDepartmentId()));
        Assert.assertTrue((sizeBefore+1)==employeeDao.getAllEmployees().size());
    }

//    @Test(expected=IllegalArgumentException.class)
//    public void addSameEmployee() {
//        Employee employee = new Employee("Ivan Ivanov", 3000, 2);
//        employeeDao.addEmployee(employee);
//        employeeDao.addEmployee(employee);
//    }
//
//    @Test()
//    public void addSameEmployeeWithRule() {
//        Employee employee = new Employee("Ivan Ivanov", 3000, 2);
//        employeeDao.addEmployee(employee);
//
//        thrown.expect(IllegalArgumentException.class);
//        thrown.expectMessage("Employee with the same name");
//        employeeDao.addEmployee(employee);
//    }

    @Test
    public void updateEmployee() {
        Employee employee = new Employee("Ivan Ivanov", "ivanov@gmail.com", 3000, 2);
        Employee newEmployee = employeeDao.addEmployee(employee);
        employee.setEmployeeName("Petr Petrov");
        employee.setSalary(2000);
        employee.setDepartmentId(1);

        employeeDao.updateEmployee(newEmployee);
        Employee updatedEmployee = employeeDao.getEmployeeById(newEmployee.getEmployeeId());
        Assert.assertTrue(newEmployee.getEmployeeId().
                equals(updatedEmployee.getEmployeeId()));
        Assert.assertTrue(newEmployee.getEmployeeName().
                equals(updatedEmployee.getEmployeeName()));
        Assert.assertTrue(newEmployee.getSalary().
                equals(updatedEmployee.getSalary()));
        Assert.assertTrue(newEmployee.getDepartmentId().
                equals(updatedEmployee.getDepartmentId()));
    }


    @Test
    public void deleteEmployee() {
        Employee employee = new Employee("Ivan Ivanov", "ivanov@gmail.com", 3000, 2);
        employee = employeeDao.addEmployee(employee);
        Collection <Employee> employees = employeeDao.getAllEmployees();
        int sizeBefore= employees.size();
        employeeDao.deleteEmployeeById(employee.getEmployeeId());
        Assert.assertTrue((sizeBefore-1)==employeeDao.getAllEmployees().size());
    }

    @Test
    public void getEmployeeByDepartmentId() {
        Collection <Employee> employees = employeeDao.getEmployeesByDepartmentId(2);
        Assert.assertNotNull(employees);
//        Assert.assertTrue(employees.get(0).getEmployeeName().equals("Dima Dmitriev"));
//        Assert.assertTrue(employees.get(0).getSalary().equals(2000));
//        Assert.assertTrue(employees.get(0).getDepartmentId().equals(2));
//        Assert.assertTrue(employees.get(1).getEmployeeName().equals("Pavel Pavlov"));
//        Assert.assertTrue(employees.get(1).getSalary().equals(3000));
//        Assert.assertTrue(employees.get(1).getDepartmentId().equals(2));
    }
}