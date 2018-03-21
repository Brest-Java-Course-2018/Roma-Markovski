package com.epam.brest.course.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * POJO Department for model.
 */
public class Department {

    /**
     * The department's id.
     */
    private Integer departmentId;

    /**
     * The department's name.
     */
    @NotEmpty
    @Size(min = 2, max = 255)
    private String departmentName;

    /**
     * The department's description.
     */

    @Size(max=255)
    private String description;

    /**
     * Constructor without params.
     */
    public Department() {

    }

    /**
     * Constructor with params.
     */
    public Department(final String departmentName, final String description) {
        this.departmentName = departmentName;
        this.description = description;
    }

    public final Integer getDepartmentId() {
        return departmentId;
    }

    public final void setDepartmentId(final Integer departmentId) {
        this.departmentId = departmentId;
    }

    public final String getDepartmentName() {
        return departmentName;
    }

    public final void setDepartmentName(final String departmentName) {
        this.departmentName = departmentName;
    }

    public final String getDescription() {
        return description;
    }

    public final void setDescription(final String description) {
        this.description = description;
    }

    /**
     * ToString function.
     */
    @Override
    public final String toString() {
        return "Department{"
                + "departmentId=" + departmentId
                + ", departmentName='" + departmentName + '\''
                + ", description='" + description + '\''
                + '}';
    }
}
