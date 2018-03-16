package com.epam.brest.course.model;

/**
 * POJO Employee for model.
 */
public class Employee {

    /**
     * The employee's id.
     */
    private Integer employeeId;

    /**
     * The employee's name.
     */
    private String employeeName;

    /**
     * The employee's email.
     */
    private String employeeEmail;

    /**
     * The employee's salary.
     */
    private Integer salary;

    /**
     * The employee's department (its id).
     */
    private Integer departmentId;


    /**
     * Constructor without params.
     */
    public Employee() {

    }

    /**
     * Constructor with params.
     */
    public Employee(String employeeName, String employeeEmail, Integer salary, Integer departmentId) {
        this.employeeName = employeeName;
        this.employeeEmail = employeeEmail;
        this.salary = salary;
        this.departmentId = departmentId;
    }

    public final Integer getEmployeeId() {
        return employeeId;
    }

    public final void setEmployeeId(final Integer employeeId) {
        this.employeeId = employeeId;
    }

    public final String getEmployeeName() {
        return employeeName;
    }

    public final void setEmployeeName(final String employeeName) {
        this.employeeName = employeeName;
    }

    public final Integer getSalary() {
        return salary;
    }

    public final void setSalary(final Integer salary) {
        this.salary = salary;
    }

    public final Integer getDepartmentId() {
        return departmentId;
    }

    public final void setDepartmentId(final Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", employeeEmail='" + employeeEmail + '\'' +
                ", salary=" + salary +
                ", departmentId=" + departmentId +
                '}';
    }
}
