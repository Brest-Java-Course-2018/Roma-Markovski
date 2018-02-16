package com.epam.brest.course;


import java.sql.SQLException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.print("Hello world");
        DBUtils dbUtils = new DBUtils();
        dbUtils.getConnection();
    }
}
