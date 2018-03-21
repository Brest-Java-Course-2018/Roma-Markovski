package com.epam.brest.course.service;

import com.epam.brest.course.dao.DepartmentDao;
import com.epam.brest.course.model.Department;
import com.epam.brest.course.dto.DepartmentForOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger LOGGER = LogManager.getLogger();

    private DepartmentDao departmentDao;

    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public Department getDepartmentById(Integer departmentId) {
        LOGGER.debug("getDepartmentById({})", departmentId);
        return departmentDao.getDepartmentById(departmentId);
    }

    @Override
    public Department addDepartment(Department department) {
        LOGGER.debug("addDepartment({})", department);
        return departmentDao.addDepartment(department);
    }

    @Override
    public void updateDepartment(Department department) {
        LOGGER.debug("updateDepartment({})", department);
        departmentDao.updateDepartment(department);
    }

    @Override
    public void updateDepartmentDescription(Integer departmentId, String description) {
        LOGGER.debug("updateDepartmentDescription({}, {})", departmentId, description);
        Department department = departmentDao.getDepartmentById(departmentId);
        department.setDescription(description);
        departmentDao.updateDepartment(department);
    }

    @Override
    public Collection<Department> getAllDepartments() {
        LOGGER.debug("getAllDepartments()");
        return departmentDao.getAllDepartments();
    }

    @Override
    public Collection<DepartmentForOutput> getAllDepartmentsForOutput() {
        LOGGER.debug("getAllDepartmentsForOutput()");
        return departmentDao.getAllDepartmentsForOutput();
    }

    @Override
    public void deleteDepartmentById(Integer id) {
        LOGGER.debug("deleteDepartmentById({})", id);
        departmentDao.deleteDepartmentById(id);
    }
}
