package com.epam.brest.course.dao;

import com.epam.brest.course.model.Employee;

import java.util.List;

public interface EmployeeDao {
    List<Employee> getAllEmployees();

    Employee getEmployeeById(Integer employeeId);

    List<Employee> getEmployeesByDepartmentId (Integer departmentId);

    Employee addEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployeeById(Integer employeeId);

}
