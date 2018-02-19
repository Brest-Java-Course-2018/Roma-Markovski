package com.epam.brest.course;

import java.sql.*;

public class DBUtils {

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        //NAME OF DATABASE: test_db
        System.out.println("Connect to DB.");
        String databaseURL="jdbc:h2:mem:test_db;MODE=MYSQL;DB_CLOSE_DELAY=-1";
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection(databaseURL, "sa","");
        return connection;
    }

    public void createUserTable(Connection connection) throws SQLException {
        System.out.println("Connect app_user table.");
        String createTable=
                "CREATE TABLE app_user (" +
                        "user_id INT NOT NULL AUTO_INCREMENT,"+
                        "login VARCHAR (255) NOT NULL,"+
                        "password VARCHAR (255) NOT NULL,"+
                        "description VARCHAR (255) NULL,"+
                        "PRIMARY KEY (user_id))";
        try (Statement statement=connection.createStatement()) {
            statement.executeUpdate(createTable);
        }
    }

    public int addUser(Connection connection, String login, String password, String description) throws SQLException {
        System.out.println(String.format("Add user: %s",login));
        String newUser="INSERT INTO app_user (login, password, description) VALUES (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(newUser);
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);
        preparedStatement.setString(3, description);
        return preparedStatement.executeUpdate();
    }

    public void getUsers(Connection connection) throws SQLException{

        System.out.println("Get users: ");
        String getRecords = "SELECT user_id, login, description FROM app_user ORDER BY user_id";
        Statement statement = connection.createStatement();
        ResultSet resultSet=statement.executeQuery(getRecords);
        while (resultSet.next()) {
            System.out.println(String.format("User: %s, %s, %s",
                    resultSet.getInt("user_id"),
                    resultSet.getString("login"),
                    resultSet.getString("description")
                    ));
        }
    }

    public boolean existUser(Connection connection, String login) throws SQLException{
        System.out.println("Get users: ");
        String getRecords = "SELECT * FROM app_user WHERE login=?";
        PreparedStatement preparedStatement = connection.prepareStatement(getRecords);
        preparedStatement.setString(1, login);
        ResultSet resultSet=preparedStatement.executeQuery();
        if (resultSet.next())
            return true;
        return false;
    }



    public void deleteUser(Connection connection, String login) throws SQLException {
        System.out.println(String.format("Delete user: %s.",login));
        String delUser="DELETE FROM app_user WHERE login = ?";
        PreparedStatement preparedStatement= connection.prepareStatement(delUser);
        preparedStatement.setString(1,login);
        int numOfUsers=preparedStatement.executeUpdate();
        System.out.println(String.format("Number of deleted users: %d.",numOfUsers));
    }
}
