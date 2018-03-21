package com.epam.brest.course.web_app.controllers;

import com.epam.brest.course.dto.DepartmentForOutput;
import com.epam.brest.course.model.Employee;
import com.epam.brest.course.service.DepartmentService;
import com.epam.brest.course.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    DepartmentService departmentService;

    @GetMapping(value = "/editEmployee/{id}")
    public String editEmployee(@PathVariable Integer id,
                               Model model) {
        Collection<DepartmentForOutput> departments = departmentService.getAllDepartmentsForOutput();
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("addOrEdit", "edit");
        model.addAttribute("departments", departments);
        model.addAttribute("employee", employee);
        return "editEmployee";
    }

    @GetMapping(value = "/addEmployee")
    public String addDepartment(Model model) {
        Collection<DepartmentForOutput> departments = departmentService.getAllDepartmentsForOutput();
        model.addAttribute("addOrEdit", "add");
        model.addAttribute("departments", departments);
        return "editEmployee";
    }

    @GetMapping(value = "/employees")
    public String employees(Model model)
    {
        Collection<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees",employees);
        return "employees";
    }
}
