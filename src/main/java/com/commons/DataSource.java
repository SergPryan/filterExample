package com.commons;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataSource {

    private static Logger log = Logger.getLogger(DataSource.class.getName());

    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    static {
        Properties properties = new Properties();
        InputStream inputStream = DataSource.class.getClassLoader().getResourceAsStream("database.properties");
        log.info("database property load");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            log.log(Level.SEVERE, "Exception: ", e);
        }
        URL = properties.getProperty("url");
        USERNAME = properties.getProperty("username");
        PASSWORD = properties.getProperty("password");
        log.info("register database driver");
        try {
            Driver driver = new org.postgresql.Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Exception: ", e);
        }
    }

    public static Connection getConnection() {
        log.info("create connection");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Exception: ", e);
        }
        return connection;
    }
}