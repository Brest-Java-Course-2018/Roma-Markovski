package com.epam.brest.course.web_app.controllers;

import com.epam.brest.course.model.Department;
import com.epam.brest.course.dto.DepartmentForOutput;
import com.epam.brest.course.service.DepartmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collection;

@Controller
public class DepartmentController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    DepartmentService departmentService;

    @GetMapping(value = "/department/{id}")
    public String gotoEditDepartmentPage(@PathVariable Integer id,
                                         Model model) {
        LOGGER.debug("gotoEditDepartmentPage({},{})", id, model);
        Department department = departmentService.getDepartmentById(id);
        model.addAttribute("isNew", false);
        model.addAttribute("department", department);
        return "editDepartment";
    }

    @PostMapping(value = "/department/{id}")
    public String updateDepartment(@Valid Department department,
                                   BindingResult result
    ) {
        LOGGER.debug("updateDepartment({}, {})", department, result);
        if (result.hasErrors()) {
            return "editDepartment";
        }
        else {
            this.departmentService.updateDepartment(department);
            return "redirect:/departments";
        }
    }

    @GetMapping(value = "/department/{id}/delete")
    public final String deleteDepartmentById(@PathVariable Integer id, Model model) {
        LOGGER.debug("deleteDepartmentById({},{})", id, model);
        departmentService.deleteDepartmentById(id);
        return "redirect:/departments";
    }

    @GetMapping(value = "/department")
    public String gotoAddDepartmentPage(Model model) {
        LOGGER.debug("addDepartment({})", model);
        Department department = new Department();
        model.addAttribute("isNew", true);
        model.addAttribute("department", department);
        return "editDepartment";
    }

    @GetMapping(value = "/departments")
    public String departments(Model model) {
        LOGGER.debug("getDepartments({})", model);
        Collection<DepartmentForOutput> departments = departmentService.getAllDepartmentsForOutput();
        model.addAttribute("departments", departments);
        return "departments";
    }

    @PostMapping(value = "/department")
    public String addDepartment(@Valid Department department,
                                BindingResult result) {
        //thymeleaf petclinic hasErrors
        LOGGER.debug("addDepartment({}, {})", department, result);
        if (result.hasErrors()) {
            return "department";
        } else {
            departmentService.addDepartment(department);
            return "redirect:/departments";
        }
    }
}
