package com.epam.brest.course.web_app.controllers;

import com.epam.brest.course.model.Department;
import com.epam.brest.course.model.Employee;
import com.epam.brest.course.service.DepartmentService;
import com.epam.brest.course.service.EmployeeService;
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
public class EmployeeController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    EmployeeService employeeService;

    @Autowired
    DepartmentService departmentService;

    @GetMapping(value = "/employee/{id}")
    public String gotoEditEmployeePage(@PathVariable Integer id,
                               Model model) {
        LOGGER.debug("gotoEditEmployeePage({},{})", id, model);
        Employee employee = employeeService.getEmployeeById(id);
        Collection<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("isNew", false);
        model.addAttribute("employee", employee);
        model.addAttribute("departments", departments);
        return "editEmployee";
    }

    @PostMapping(value = "/employee/{id}")
    public String updateEmployee(@Valid Employee employee,
                                   BindingResult result
    ) {
        LOGGER.debug("updateEmployee({}, {})", employee, result);
        if (result.hasErrors()) {
            return "editEmployee";
        }
        else {
            this.employeeService.updateEmployee(employee);
            return "redirect:/employees";
        }
    }

    @GetMapping(value = "/employee/{id}/delete")
    public final String deleteEmployeeById(@PathVariable Integer id, Model model) {
        LOGGER.debug("deleteEmployeeById({},{})", id, model);
        employeeService.deleteEmployeeById(id);
        return "redirect:/employees";
    }

    @GetMapping(value = "/employee")
    public String gotoAddEmployeePage(Model model) {
        LOGGER.debug("gotoAddEmployeePage({})", model);
        Employee employee = new Employee();
        Collection<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("isNew", true);
        model.addAttribute("employee", employee);
        model.addAttribute("departments", departments);
        return "editEmployee";
    }

    @GetMapping(value = "/employees")
    public String getEmployees(Model model) {
        LOGGER.debug("getEmployees({})", model);
        Collection<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employees";
    }

    @PostMapping(value = "/employee")
    public String addEmployee(@Valid Employee employee,
                                BindingResult result) {
        LOGGER.debug("addEmployee({}, {})", employee, result);
        if (result.hasErrors()) {
            return "employee";
        } else {
            employeeService.addEmployee(employee);
            return "redirect:/employees";
        }
    }
}
