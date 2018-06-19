package com.epam.brest.course.service;

import com.epam.brest.course.model.Employee;

import java.util.Collection;

public interface EmployeeService {

    Collection<Employee> getAllEmployees();

    Employee getEmployeeById(Integer employeeId);

    Collection<Employee> getEmployeesByDepartmentId (Integer departmentId);

    Employee addEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployeeById(Integer employeeId);
}