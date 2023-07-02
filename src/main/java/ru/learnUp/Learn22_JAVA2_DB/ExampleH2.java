package ru.learnUp.Learn22_JAVA2_DB;

import java.sql.*;

public class ExampleH2 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        //Пример с БД MySQL
//		try (Connection connectionMySQL = MySqlConnect.getMySQLConnection();
//			 Statement statement = connectionMySQL.createStatement()){
//			statement.execute("CREATE SCHEMA IF EXISTS learnUP");
//		} catch (SQLException | ClassNotFoundException e) {
//			throw new RuntimeException(e);
//		}

        init();   //инициализация драйвера БД

        try (Connection connection = getConnection()) {   //соединение с БД
            statements(connection);
            resultSet(connection);

            prepare(connection);
            resultSet(connection);

            transactions(connection);
            transactions(connection);
            resultSet(connection);
        }
    }

    private static void init() throws ClassNotFoundException {
        Class.forName("org.h2.Driver");
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:mem:test");
    }

    private static void statements(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE \"user\" (" +
                    "id INTEGER PRIMARY KEY UNIQUE AUTO_INCREMENT, " +
                    "name VARCHAR(100)" +
                    ")"
            );
        }
    }

    private static void resultSet(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM \"user\"");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + " " + resultSet.getString("name"));
            }
            System.out.println("_____________________________________");
        }
    }

    private static void prepare(Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO \"user\"(id, name) VALUES (?, ?)")) {
            preparedStatement.setInt(1, 101);
            preparedStatement.setString(2, "Petya");
            preparedStatement.addBatch();

            preparedStatement.setInt(1, 102);
            preparedStatement.setString(2, "Petya2");
            preparedStatement.addBatch();

            preparedStatement.setInt(1, 103);
            preparedStatement.setString(2, "Petya3");
            preparedStatement.addBatch();

            preparedStatement.executeBatch();
        }
    }

    private static void transactions(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            statement.execute("INSERT INTO \"user\"(name) VALUES ('iura')");
            connection.commit();
            connection.setAutoCommit(true);
        }
    }

}
