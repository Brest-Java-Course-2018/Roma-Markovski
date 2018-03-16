package com.epam.brest.course.service;

import com.epam.brest.course.model.Employee;

import java.util.Collection;

public interface EmployeeService {
    Employee getEmployeeById(Integer employeeId);

    Collection<Employee> getAllEmployees();
}
