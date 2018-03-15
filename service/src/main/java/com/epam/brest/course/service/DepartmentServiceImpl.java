package com.epam.brest.course.service;

import com.epam.brest.course.dao.DepartmentDao;
import com.epam.brest.course.model.Department;
import com.epam.brest.course.model.DepartmentForOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

public class DepartmentServiceImpl implements DepartmentService{

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private DepartmentDao departmentDao;

    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao=departmentDao;
    }

    @Override
    public Department getDepartmentById(Integer departmentId) {
        LOGGER.debug("getDepartmentById({})", departmentId);
        return departmentDao.getDepartmentById(departmentId);
    }

    @Override
    public void updateDepartmentDescription(Integer departmentId, String description) {
        LOGGER.debug("updateDepartmentDescription({}, {})",departmentId,description);
        Department department = departmentDao.getDepartmentById(departmentId);
        department.setDescription(description);
        departmentDao.updateDepartment(department);
    }

    @Override
    public Collection <DepartmentForOutput> getAllDepartments() {
        LOGGER.debug("getAllDepartments()");
        return departmentDao.getAllDepartments();
    }

}
