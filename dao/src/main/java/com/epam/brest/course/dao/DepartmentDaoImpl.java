package com.epam.brest.course.dao;

import com.epam.brest.course.model.Department;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

public class DepartmentDaoImpl implements DepartmentDao {
    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final String GET_DEPARTMENTS_SQL = "SELECT departmentId, departmentName, description FROM department";

    private final String GET_DEPARTMENT_BY_ID_SQL =
            "SELECT departmentId, departmentName, description FROM department WHERE departmentId = :departmentId";

    private final String GET_DEPARTMENT_BY_NAME_SQL =
            "SELECT departmentId, departmentName, description FROM department WHERE departmentName = :departmentName";

    private final String ADD_DEPARTMENT_SQL =
            "INSERT INTO department (departmentName, description) VALUES (:departmentName, :description)";

    private final String UPDATE_DEPARTMENT_SQL =
            "UPDATE department SET departmentName = :departmentName, description = :description WHERE departmentId = :departmentId";

    private final String DELETE_DEPARTMENT_SQL =
            "DELETE FROM department WHERE departmentId = :departmentId";

    public DepartmentDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Department> getAllDepartments() {
        List<Department> departments = jdbcTemplate.query(GET_DEPARTMENTS_SQL, new DepartmentRowMapper());
        return departments;
    }

    @Override
    public Department getDepartmentById(Integer departmentId) {
        SqlParameterSource namedParameters =
                new MapSqlParameterSource("departmentId", departmentId);
        try {
            Department department = namedParameterJdbcTemplate.
                    queryForObject(GET_DEPARTMENT_BY_ID_SQL, namedParameters, new DepartmentRowMapper());
            return department;
        } catch (Exception e) {
            return null;
        }

    }

    public Department getDepartmentByName(String departmentName) {
        SqlParameterSource namedParameters =
                new MapSqlParameterSource("departmentName", departmentName);
        Department department = namedParameterJdbcTemplate.
                queryForObject(GET_DEPARTMENT_BY_NAME_SQL, namedParameters, new DepartmentRowMapper());
        return department;
    }

    private class DepartmentRowMapper implements RowMapper<Department> {

        @Override
        public Department mapRow(ResultSet resultSet, int i) throws SQLException {
            Department department = new Department();
            department.setDepartmentId(resultSet.getInt(1));
            department.setDepartmentName(resultSet.getString(2));
            department.setDescription(resultSet.getString(3));
            return department;
        }
    }

    @Override
    public Department addDepartment(Department department) {
        MapSqlParameterSource mapSqlParameterSource=new MapSqlParameterSource();
        mapSqlParameterSource.addValue("departmentName", department.getDepartmentName());
        mapSqlParameterSource.addValue("description", department.getDescription());
        SqlParameterSource namedParameters = mapSqlParameterSource;
        namedParameterJdbcTemplate.update(ADD_DEPARTMENT_SQL, namedParameters);
        return department;
    }

    @Override
    public void updateDepartment(Integer oldId, Department newDepartment) {
        MapSqlParameterSource mapSqlParameterSource=new MapSqlParameterSource();
        mapSqlParameterSource.addValue("departmentId", oldId);
        mapSqlParameterSource.addValue("departmentName", newDepartment.getDepartmentName());
        mapSqlParameterSource.addValue("description", newDepartment.getDescription());
        SqlParameterSource namedParameters = mapSqlParameterSource;
        namedParameterJdbcTemplate.update(UPDATE_DEPARTMENT_SQL, namedParameters);
    }

    @Override
    public void deleteDepartment(Integer id) {
        SqlParameterSource namedParameters =
                new MapSqlParameterSource("departmentId", id);
        namedParameterJdbcTemplate.update(DELETE_DEPARTMENT_SQL, namedParameters);
    }
}