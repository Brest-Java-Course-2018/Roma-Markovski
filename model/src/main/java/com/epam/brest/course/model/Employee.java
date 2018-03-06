package com.epam.brest.course.model;

/**
 * POJO Employee for model.
 */
public class Employee {

    private Integer employeeId; 

    private String employeeName; 

    private Integer salary; 

    private Integer departmentId;

    public Employee() {

    }

    public Employee(String employeeName, Integer salary, Integer departmentId) {
        this.employeeName = employeeName;
        this.salary = salary;
        this.departmentId = departmentId;
    }

    public final Integer getEmployeeId() {
        return employeeId;
    } 

    public final void setEmployeeId(final Integer employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * Get Employee ID.
     * @return ID.
     */
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

    @Override
    public final String toString() {
        return "Employee{"
                + "employeeId=" + employeeId
                + ", employeeName='" + employeeName + '\''
                + ", salary=" + salary
                + ", departmentId=" + departmentId
                + '}';
    }
}
