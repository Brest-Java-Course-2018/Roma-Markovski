package com.epam.brest.course;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class DBUtilsTest {

    @org.junit.Test
    public void getConnection() throws SQLException, ClassNotFoundException {
        DBUtils dbUtils=new DBUtils();
        Assert.assertNotNull(dbUtils.getConnection());
    }

    @Test
    public void addUser() throws SQLException, ClassNotFoundException {
        DBUtils dbUtils=new DBUtils();
        Connection connection = dbUtils.getConnection();
        dbUtils.createUserTable(connection);
        Assert.assertEquals(dbUtils.addUser(connection,"test","test","desc"),1);
        Assert.assertEquals(dbUtils.existUser(connection, "test"),true);
    }
}