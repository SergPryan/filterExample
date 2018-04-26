package com;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

    private static final String URL="jdbc:postgresql://127.0.0.1:5432/demo";
    private static final String USERNAME="postgres";
    private static final String PASSWORD="root";

    static {
        try {
            Driver driver = new org.postgresql.Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {

        }
    }

    public static Connection getConnection()  {
        Connection connection = null;
        try {
           connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
}