package com.epam.brest.course.dao;

import com.epam.brest.course.model.Department;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DepartmentDaoImpl implements DepartmentDao {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String DEPARTMENT_ID = "departmentId";
    private static final String DEPARTMENT_NAME = "departmentName";
    private static final String DESCRIPTION = "description";

    @Value("${department.select}")
    private String select;

    @Value("${department.selectById}")
    private String selectById;

    @Value("${department.checkDepartment}")
    private String checkDepartment;

    @Value("${department.insert}")
    private String insert;

    @Value("${department.update}")
    private String update;

    @Value("${department.delete}")
    private String delete;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;



    public final void setNamedParameterJdbcTemplate
            (final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

//    private static final String GET_DEPARTMENTS_SQL = "SELECT departmentId, departmentName, description FROM department";
//
//    private static final String GET_DEPARTMENT_BY_ID_SQL =
//            "SELECT departmentId, departmentName, description FROM department WHERE departmentId = :departmentId";
//
//    private static final String CHECK_DEPARTMENT_SQL = "SELECT count(departmentId) " +
//            "FROM department WHERE lower(departmentName) = lower(:departmentName)";
//
//    private static final String INSERT_DEPARTMENT_SQL =
//            "INSERT INTO department (departmentName, description) VALUES (:departmentName, :description)";
//
//    private static final String UPDATE_DEPARTMENT_SQL =
//            "UPDATE department SET departmentName = :departmentName, description = :description WHERE departmentId = :departmentId";
//
//    private static final String DELETE_DEPARTMENT_SQL =
//            "DELETE FROM department WHERE departmentId = ?";



    @Override
    public List<Department> getAllDepartments() {
        LOGGER.debug("getAllDepartments()");
        List<Department> departments =
                namedParameterJdbcTemplate.getJdbcOperations().query(select, new DepartmentRowMapper());
        return departments;
    }

//    @Override
//    public Department getDepartmentById(Integer departmentId) {
//        SqlParameterSource namedParameters =
//                new MapSqlParameterSource("departmentId", departmentId);
//        Department department = namedParameterJdbcTemplate.
//                queryForObject(SelectById, namedParameters, new DepartmentRowMapper());
//        return department;
//
//    }

    @Override
    public Department getDepartmentById(Integer departmentId) {
        LOGGER.debug("getDepartmentById({})", departmentId);
        SqlParameterSource namedParameters =
                new MapSqlParameterSource(DEPARTMENT_ID, departmentId);
        Department department = namedParameterJdbcTemplate.queryForObject(selectById, namedParameters,
                BeanPropertyRowMapper.newInstance(Department.class));
        return department;
    }

    private class DepartmentRowMapper implements RowMapper<Department> {

        @Override
        public Department mapRow(ResultSet resultSet, int i) throws SQLException {
            LOGGER.debug("mapRow({}, {})", resultSet, i);
            Department department = new Department();
            department.setDepartmentId(resultSet.getInt(DEPARTMENT_ID));
            department.setDepartmentName(resultSet.getString(DEPARTMENT_NAME));
            department.setDescription(resultSet.getString(DESCRIPTION));
            return department;
        }
    }

    @Override
    public Department addDepartment(Department department) {
        LOGGER.debug("addDepartment({})", department);
        MapSqlParameterSource namedParameters =
                new MapSqlParameterSource(DEPARTMENT_NAME, department.getDepartmentName());
        Integer result =
                namedParameterJdbcTemplate.queryForObject(checkDepartment, namedParameters, Integer.class);
        LOGGER.debug("result({})", result);
        if (result == 0) {
            namedParameters = new MapSqlParameterSource();
            namedParameters.addValue(DEPARTMENT_NAME, department.getDepartmentName());
            namedParameters.addValue(DESCRIPTION, department.getDescription());

            KeyHolder generateKeyHolder = new GeneratedKeyHolder();

            namedParameterJdbcTemplate.update(insert, namedParameters, generateKeyHolder);
            department.setDepartmentId(generateKeyHolder.getKey().intValue());
        } else {
            throw new IllegalArgumentException("Department with the same name already exists in DB.");
        }

        return department;
//        mapSqlParameterSource.addValue("departmentName", department.getDepartmentName());
//        mapSqlParameterSource.addValue("description", department.getDescription());
//        SqlParameterSource namedParameters = mapSqlParameterSource;
//        namedParameterJdbcTemplate.update(ADD_DEPARTMENT_SQL, namedParameters);
//        return department;
    }

    @Override
    public void updateDepartment(Department department /*Integer oldId, Department newDepartment*/) {
        LOGGER.log(Level.DEBUG, "updateDepartment({})", department);
        SqlParameterSource namedParameter = new BeanPropertySqlParameterSource(department);
        namedParameterJdbcTemplate.update(update, namedParameter);

//        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
//        mapSqlParameterSource.addValue("departmentId", oldId);
//        mapSqlParameterSource.addValue("departmentName", newDepartment.getDepartmentName());
//        mapSqlParameterSource.addValue("description", newDepartment.getDescription());
//        SqlParameterSource namedParameters = mapSqlParameterSource;
//        namedParameterJdbcTemplate.update(UPDATE_DEPARTMENT_SQL, namedParameters);
    }

    @Override
    public void deleteDepartmentById(Integer departmentId) {
        LOGGER.debug("deleteDepartmentById({})", departmentId);
        namedParameterJdbcTemplate.getJdbcOperations().update(delete, departmentId);
    }
}
