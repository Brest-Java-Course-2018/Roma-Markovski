package com.epam.brest.course.model;

public class DepartmentForOutput {

    private Integer departmentId;

    private String departmentName;

    private Integer departmentAvgSalary;

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getDepartmentAvgSalary() {
        return departmentAvgSalary;
    }

    public void setDepartmentAvgSalary(Integer departmentAvgSalary) {
        this.departmentAvgSalary = departmentAvgSalary;
    }

    public DepartmentForOutput() {

    }

    public DepartmentForOutput(Integer departmentId, String departmentName, Integer departmentAvgSalary) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.departmentAvgSalary = departmentAvgSalary;
    }
}
