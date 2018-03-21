package com.epam.brest.course.dao;

import com.epam.brest.course.model.Department;
import com.epam.brest.course.dto.DepartmentForOutput;

import java.util.Collection;

/**
 * Department DAO Interface.
 */
public interface DepartmentDao {

    Collection<DepartmentForOutput> getAllDepartmentsForOutput();

    Collection<Department> getAllDepartments();

    Department getDepartmentById(Integer departmentId);

    Department addDepartment(Department department);

    void updateDepartment(Department department);

    void deleteDepartmentById(Integer departmentId);
}
