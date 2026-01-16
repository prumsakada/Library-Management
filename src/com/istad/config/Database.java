package com.istad.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {
    private static Connection conn;

    public static Connection getConn(){
        return conn;
    }

    public static void init() {
        if (conn == null) {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            String url = "jdbc:postgresql://localhost:5432/library_db";
            Properties info = new Properties();
            info.put("user", "postgres");
            info.put("password", "qwer");

            try {
                conn = DriverManager.getConnection(url, info);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
