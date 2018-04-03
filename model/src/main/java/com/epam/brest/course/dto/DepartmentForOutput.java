package com.epam.brest.course.dto;

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

    @Override
    public String toString() {
        return "DepartmentForOutput{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", departmentAvgSalary=" + departmentAvgSalary +
                '}';
    }
}
