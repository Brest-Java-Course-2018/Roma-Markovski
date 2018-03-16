package com.epam.brest.course.dao;

import com.epam.brest.course.model.Employee;
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
import java.util.Collection;

public class EmployeeDaoImpl implements EmployeeDao {
    private static final String EMPLOYEE_ID = "employeeId";
    private static final String EMPLOYEE_NAME = "employeeName";
    private static final String EMPLOYEE_EMAIL = "employeeEmail";
    private static final String SALARY = "salary";
    private static final String DEPARTMENT_ID = "departmentId";

    private static final Logger LOGGER = LogManager.getLogger();

    @Value("${employee.select}")
    private String select;

    @Value("${employee.selectById}")
    private String selectById;

    @Value("${employee.selectByDepId}")
    private String selectByDepId;

    @Value("${employee.insert}")
    private String insert;

    @Value("${employee.update}")
    private String update;

    @Value("${employee.delete}")
    private String delete;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public final void setNamedParameterJdbcTemplate
            (final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Collection<Employee> getAllEmployees() {
        LOGGER.debug("getAllEmployees()");
        Collection<Employee> employees =
                namedParameterJdbcTemplate.getJdbcOperations().query(select, new EmployeeRowMapper());
        return employees;
    }

    @Override
    public Employee getEmployeeById(Integer EmployeeId) {
        LOGGER.debug("getEmployeeById({})", EmployeeId);
        SqlParameterSource namedParameters =
                new MapSqlParameterSource(EMPLOYEE_ID, EmployeeId);
        Employee employee = namedParameterJdbcTemplate.queryForObject(selectById, namedParameters,
                BeanPropertyRowMapper.newInstance(Employee.class));
        return employee;
    }

    private class EmployeeRowMapper implements RowMapper<Employee> {

        @Override
        public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
            LOGGER.debug("mapRow({}, {})", resultSet, i);
            Employee employee = new Employee();
            employee.setEmployeeId(resultSet.getInt(EMPLOYEE_ID));
            employee.setEmployeeName(resultSet.getString(EMPLOYEE_NAME));
            employee.setEmployeeEmail(resultSet.getString(EMPLOYEE_EMAIL));
            employee.setSalary(resultSet.getInt(SALARY));
            employee.setDepartmentId(resultSet.getInt(DEPARTMENT_ID));
            return employee;
        }
    }

    @Override
    public Employee addEmployee(Employee employee) {
        LOGGER.debug("addEmployee({})", employee);
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue(EMPLOYEE_NAME, employee.getEmployeeName());
        namedParameters.addValue(EMPLOYEE_EMAIL, employee.getEmployeeName());
        namedParameters.addValue(SALARY, employee.getSalary());
        namedParameters.addValue(DEPARTMENT_ID, employee.getDepartmentId());
        KeyHolder generateKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insert, namedParameters, generateKeyHolder);
        employee.setEmployeeId(generateKeyHolder.getKey().intValue());
        return employee;
    }

    @Override
    public void updateEmployee(Employee employee) {
        LOGGER.debug("updateEmployee({})", employee);
        SqlParameterSource namedParameter = new BeanPropertySqlParameterSource(employee);
        namedParameterJdbcTemplate.update(update, namedParameter);
    }

    @Override
    public void deleteEmployeeById(Integer employeeId) {
        LOGGER.debug("deleteEmployeeById({})", employeeId);
        namedParameterJdbcTemplate.getJdbcOperations().update(delete, employeeId);
    }

    @Override
    public Collection<Employee> getEmployeesByDepartmentId(Integer DepartmentId) {
        LOGGER.debug("getEmployeesByDepartmentId({})", DepartmentId);
        SqlParameterSource namedParameters =
                new MapSqlParameterSource(DEPARTMENT_ID, DepartmentId);
        Collection<Employee> employees =
                namedParameterJdbcTemplate.query(selectByDepId, namedParameters,
                        BeanPropertyRowMapper.newInstance(Employee.class));
        int size = employees.size();
        LOGGER.debug ("Size of employees: {}", size);
        return employees;
    }
}
