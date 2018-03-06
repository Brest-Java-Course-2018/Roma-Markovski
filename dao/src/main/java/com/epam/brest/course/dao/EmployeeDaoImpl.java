package com.epam.brest.course.dao;

import com.epam.brest.course.model.Employee;
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

public class EmployeeDaoImpl implements EmployeeDao {
    private static final String EMPLOYEE_ID = "employeeId";
    private static final String EMPLOYEE_NAME = "employeeName";
    private static final String SALARY = "salary";
    private static final String DEPARTMENT_ID = "departmentId";

    @Value("${employee.select}")
    private String select;

    @Value("${employee.selectById}")
    private String selectById;

    @Value("${employee.checkEmployee}")
    private String checkEmployee;

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
    public List<Employee> getAllEmployees() {
        List<Employee> employees =
                namedParameterJdbcTemplate.getJdbcOperations().query(select, new EmployeeRowMapper());
        return employees;
    }

    @Override
    public Employee getEmployeeById(Integer EmployeeId) {
        SqlParameterSource namedParameters =
                new MapSqlParameterSource(EMPLOYEE_ID, EmployeeId);
        Employee employee = namedParameterJdbcTemplate.queryForObject(selectById, namedParameters,
                BeanPropertyRowMapper.newInstance(Employee.class));
        return employee;
    }

    private class EmployeeRowMapper implements RowMapper<Employee> {

        @Override
        public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
            Employee employee = new Employee();
            employee.setEmployeeId(resultSet.getInt(EMPLOYEE_ID));
            employee.setEmployeeName(resultSet.getString(EMPLOYEE_NAME));
            employee.setSalary(resultSet.getInt(SALARY));
            employee.setDepartmentId(resultSet.getInt(DEPARTMENT_ID));
            return employee;
        }
    }

    @Override
    public Employee addEmployee(Employee employee) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue(EMPLOYEE_NAME, employee.getEmployeeName());
        namedParameters.addValue(SALARY, employee.getSalary());
        namedParameters.addValue(DEPARTMENT_ID, employee.getDepartmentId());
        KeyHolder generateKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insert, namedParameters, generateKeyHolder);
        employee.setEmployeeId(generateKeyHolder.getKey().intValue());
        return employee;
    }

    @Override
    public void updateEmployee(Employee employee) {
        SqlParameterSource namedParameter = new BeanPropertySqlParameterSource(employee);
        namedParameterJdbcTemplate.update(update, namedParameter);
    }

    @Override
    public void deleteEmployeeById(Integer employeeId) {
        namedParameterJdbcTemplate.getJdbcOperations().update(delete, employeeId);
    }
}
