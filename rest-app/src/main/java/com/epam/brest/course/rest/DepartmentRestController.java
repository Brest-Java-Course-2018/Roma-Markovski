package com.epam.brest.course.rest;

import com.epam.brest.course.dto.DepartmentForOutput;
import com.epam.brest.course.model.Department;
import com.epam.brest.course.service.DepartmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class DepartmentRestController {
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private DepartmentService departmentService;

    @GetMapping(value = "/departments")
    Collection<DepartmentForOutput> departments() {
        LOGGER.debug("departments()");
        return departmentService.getAllDepartmentsForOutput();
    }

    @GetMapping(value = "/departments/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    Department departmentById(@PathVariable(value = "id") Integer id) {
        LOGGER.debug("departmentById({})", id);
        return departmentService.getDepartmentById(id);
    }

    @PostMapping(value="/departments")
    @ResponseStatus(HttpStatus.CREATED)
    Department addDepartment(@RequestBody Department department) {
        LOGGER.debug("addDepartment({})",department);
        return departmentService.addDepartment(department);
    }

    @DeleteMapping(value = "/departments/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    void deleteDepartment(@PathVariable(value = "id") Integer id) {
        LOGGER.debug("deleteDepartment({})", id);
        departmentService.deleteDepartmentById(id);
    }
}
