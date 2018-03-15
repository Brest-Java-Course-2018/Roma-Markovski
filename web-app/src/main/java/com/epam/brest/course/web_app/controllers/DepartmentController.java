package com.epam.brest.course.web_app.controllers;

import com.epam.brest.course.model.Department;
import com.epam.brest.course.model.DepartmentForOutput;
import com.epam.brest.course.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

@Controller
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping(value = "/editDepartment/{id}")
    public String getDepartmentById(@PathVariable Integer id,
                                    Model model) {
        Department department = departmentService.getDepartmentById(id);
        model.addAttribute("department", department);
        return "editDepartment";
    }

    @GetMapping(value = "/departments")
    public String departments(Model model) {
        Collection<DepartmentForOutput> departments = departmentService.getAllDepartments();
        model.addAttribute("departments",departments);
        return "departments";
    }
}
