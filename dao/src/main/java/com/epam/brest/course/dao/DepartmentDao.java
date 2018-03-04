package com.epam.brest.course.dao;

import com.epam.brest.course.model.Department;

import java.util.List;

/**
 * Department DAO Interface.
 */
public interface DepartmentDao {
    List<Department> getAllDepartments();

    Department getDepartmentById(Integer departmentId);

    Department getDepartmentByName(String departmentName);

    Department addDepartment(Department department);

    void updateDepartment(Integer oldId, Department newDepartment);

    void deleteDepartment(Integer id);
}
