package com.epam.brest.course.client.rest;

import com.epam.brest.course.dto.DepartmentForOutput;
import com.epam.brest.course.model.Department;
import com.epam.brest.course.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

public class DepartmentConsumerRest implements DepartmentService {

    private String url;

    private RestTemplate restTemplate;

    public DepartmentConsumerRest(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public Department getDepartmentById(Integer departmentId) {
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/" + departmentId, Department.class);
        Object department = responseEntity.getBody();
        return (Department) department;
    }

    @Override
    public void updateDepartmentDescription(Integer departmentId, String description) {

    }

    @Override
    public Collection<Department> getAllDepartments() {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<DepartmentForOutput> getAllDepartmentsForOutput() {
        ResponseEntity responseEntity = restTemplate.getForEntity(url, List.class);
        List<DepartmentForOutput> departments = (List<DepartmentForOutput>) responseEntity.getBody();
        return departments;
    }

    @Override
    public Department addDepartment(Department department) {
        ResponseEntity responseEntity = restTemplate.postForEntity(url, department, Department.class);
        Department result = (Department) responseEntity.getBody();
        return result;
    }

    @Override
    public void updateDepartment(Department department) {

    }

    @Override
    public void deleteDepartmentById(Integer id) {

    }
}
