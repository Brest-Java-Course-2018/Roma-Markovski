package com.epam.brest.course.dao;

import com.epam.brest.course.model.Department;

import java.util.List;

/**
 * Department DAO Interface.
 */
public interface DepartmentDao {
    List<Department> getAllDepartments();

    Department getDepartmentById(Integer departmentId);

    Department addDepartment(Department department);

    //void updateDepartment(Integer oldId, Department newDepartment);

    void updateDepartment(Department department);

    void deleteDepartmentById(Integer departmentId);
}
