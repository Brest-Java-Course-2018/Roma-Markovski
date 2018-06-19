package com.epam.brest.course.service;

import com.epam.brest.course.dao.EmployeeDao;
import com.epam.brest.course.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao=employeeDao;
    }

    @Override
    public Employee getEmployeeById(Integer employeeId) {
        LOGGER.debug("getEmployeeById({})", employeeId);
        return employeeDao.getEmployeeById(employeeId);
    }

    @Override
    public Collection<Employee> getEmployeesByDepartmentId(Integer departmentId) {
        LOGGER.debug("getEmployeeByDepartmentId({})", departmentId);
        return employeeDao.getEmployeesByDepartmentId(departmentId);
    }

    @Override
    public Employee addEmployee(Employee employee) {
        LOGGER.debug("addEmployee({})", employee);
        return employeeDao.addEmployee(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        LOGGER.debug("updateEmployee({})", employee);
        employeeDao.updateEmployee(employee);
    }

    @Override
    public void deleteEmployeeById(Integer employeeId) {
        LOGGER.debug("deleteEmployee({})", employeeId);
        employeeDao.deleteEmployeeById(employeeId);
    }

    @Override
    public Collection<Employee> getAllEmployees() {
        LOGGER.debug("getAllEmployees()");
        return employeeDao.getAllEmployees();
    }
}