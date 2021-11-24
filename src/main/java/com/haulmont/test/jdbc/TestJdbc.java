package com.haulmont.test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {
    public static void main(String[] args) {

//        String jdbcUrl = "jdbc:hsqldb:file:/src/main/resources/db/creditdb";
        String jdbcUrl = "jdbc:hsqldb:mem:tested;DB_CLOSE_DELAY=-1";
        String user = "SA";
        String pass = "";

        try {
            System.out.println("Connecting to database: " + jdbcUrl);
            Connection myConn =
                    DriverManager.getConnection(jdbcUrl, user, pass);
            System.out.println("Connection successful!!!");
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
