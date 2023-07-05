package ru.learnUp.Learn22_JAVA2_DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnect {
    public static Connection getSQLiteConnection(String dbName)
            throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String connectionURL = "jdbc:sqlite:" +  dbName  + ".db";
        Connection connection = DriverManager.getConnection(connectionURL);
        return connection;
    }
}
