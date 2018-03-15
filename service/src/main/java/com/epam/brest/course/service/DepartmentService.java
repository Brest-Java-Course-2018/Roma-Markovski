package com.epam.brest.course.service;

import com.epam.brest.course.model.Department;
import com.epam.brest.course.model.DepartmentForOutput;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface  DepartmentService {
    Department getDepartmentById(Integer departmentId);

    void updateDepartmentDescription(Integer departmentId, String description);

    Collection<DepartmentForOutput> getAllDepartments();
}