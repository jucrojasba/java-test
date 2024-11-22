package com.printer.persistance.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbContext {
    private static final String URL="jdbc:mysql://bwxnul4rplitabgcwbv2-mysql.services.clever-cloud.com:3306/bwxnul4rplitabgcwbv2";
    private static final String USER="u6fgg0zylwlwonvr";
    private static final String PASSWORD="L9refSY0uOReSlPaCO8w";
    
    private DbContext(){}
    
    public static Connection  getConeection() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASSWORD);
    
    }

}