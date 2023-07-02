package ru.learnUp.Learn22_JAVA2_DB.lessonGuide.mySql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnect {
    public static Connection getMySQLConnection() throws SQLException, ClassNotFoundException {
        String hostName = "localhost"; // имя хоста по умолчанию
        String dbName = "myDB";        //имя моей БД
        String user = "root";          //имя user по умолчанию для БД MySQL
        String password = "root";      //password по умолчанию для БД MySQL
        return getMySQLConnection(hostName, dbName, user, password);
    }

    private static Connection getMySQLConnection(String hostName, String dbName, String user, String password)
            throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;  //3306 - порт БД для MySQL
        Connection connection = DriverManager.getConnection(connectionURL, user, password);
        return connection;
    }
}
