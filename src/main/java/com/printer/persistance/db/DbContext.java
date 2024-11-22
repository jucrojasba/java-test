package com.printer.persistance.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbContext {
    private static final String URL = "jdbc:postgresql://ep-blue-glitter-a4x4xns1-pooler.us-east-1.aws.neon.tech:5432/verceldb?sslmode=require";
    private static final String USER = "default";
    private static final String PASSWORD = "S7Zy1HFKYLOe";

    private DbContext() {}

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
