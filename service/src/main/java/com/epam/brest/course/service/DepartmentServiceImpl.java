package com.epam.brest.course.service;

import com.epam.brest.course.dao.DepartmentDao;
import com.epam.brest.course.model.Department;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class DepartmentServiceImpl implements DepartmentService{

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private DepartmentDao departmentDao;

    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao=departmentDao;
    }

    @Override
    public Department getDepartmentById(Integer departmentId) {
        LOGGER.debug("getDepartmentById({})");
        return departmentDao.getDepartmentById(departmentId);
    }

    @Override
    public void updateDepartmentDescription(Integer departmentId, String description) {
        LOGGER.debug("updateDepartmentDescription({}, {})",departmentId,description);
        Department department = departmentDao.getDepartmentById(departmentId);
        department.setDescription(description);
        departmentDao.updateDepartment(department);
    }
}
