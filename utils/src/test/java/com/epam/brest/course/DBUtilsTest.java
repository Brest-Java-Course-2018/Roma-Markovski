package com.epam.brest.course;

import org.junit.Assert;

import java.sql.SQLException;

public class DBUtilsTest {

    @org.junit.Test
    public void getConnection() throws SQLException, ClassNotFoundException {
        DBUtils dbUtils=new DBUtils();
        Assert.assertNotNull(dbUtils.getConnection());
    }
}