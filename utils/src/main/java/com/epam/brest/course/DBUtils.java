package com.epam.brest.course;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        String databaseURL="jdbc:h2:mem:test_db;MODE=MYSQL;DB_CLOSE_DELAY=-1";
        Class.forName("org.h2.Driver");

        Connection connection = DriverManager.getConnection(databaseURL, "sa","");
        return connection;
    }
}
