package ru.learnUp.Learn22_JAVA2_DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreLocalConnect {
    public static Connection getPostgreLocalConnection() throws SQLException, ClassNotFoundException {
        String hostName = "localhost"; // имя хоста по умолчанию
        String dbName = "myDB";        //имя моей БД
        String user = "postgres";          //имя user по умолчанию для БД MySQL
        String password = "postgres";      //password по умолчанию для БД MySQL
        return getPostgreLocalConnection(hostName, dbName, user, password);
    }

    private static Connection getPostgreLocalConnection(String hostName, String dbName, String user, String password)
            throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String connectionURL = "jdbc:postgresql://" + hostName + ":5432/" + dbName;  //4532 - порт БД для локальной Postgre
        Connection connection = DriverManager.getConnection(connectionURL, user, password);
        return connection;
    }
}
