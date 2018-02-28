package com.epam.brest.course.model;

import org.junit.Assert;
import org.junit.Test;

public class EmployeeTest {

    public static final String VASIA = "Vasia";

    @Test
    public void getEmployeeName() {
        com.epam.brest.course.model.Employee employee = new Employee();
        employee.setEmployeeName(VASIA);
        Assert.assertTrue(employee.getEmployeeName().equals(VASIA));
        Assert.assertEquals(VASIA, employee.getEmployeeName());
    }
}