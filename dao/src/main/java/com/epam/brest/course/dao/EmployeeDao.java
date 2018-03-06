package com.epam.brest.course.dao;

import com.epam.brest.course.model.Employee;

import java.util.List;

public interface EmployeeDao {
    List<Employee> getAllEmployees();

    Employee getEmployeeById(Integer EmployeeId);

    Employee addEmployee(Employee Employee);

    void updateEmployee(Employee Employee);

    void deleteEmployeeById(Integer EmployeeId);
}
