package ru.learnUp.Learn22_JAVA2_DB;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
@Slf4j
public class Learn22Java2DbApplication {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //Данные для подключения к БД Postgre (на локальном диске)
        String hostName = "localhost"; // имя хоста по умолчанию
        String port = "5432";          //4532 - порт БД для локальной Postgre
        String dbName = "myDB";        //имя моей БД
        String user = "postgres";      //имя user по умолчанию для БД MySQL
        String password = "postgres";  //password по умолчанию для БД MySQL

        Class.forName("org.postgresql.Driver");  //Регистрация драйвера
        String connectionURL = "jdbc:postgresql://" + hostName + ":" + port +"/" + dbName;
        Connection connection = DriverManager.getConnection(connectionURL, user, password);
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM learnUp.user");
        while (resultSet.next()) {
            long id = resultSet.getLong("id");
            int age = resultSet.getInt("age");
            String name = resultSet.getString("name");
            String surname= resultSet.getString("surname");
            System.out.printf("User(%d %d %s %s)\n", id, age, name, surname);
        }
//        try (Connection connection = PostgreLocalConnect.getPostgreLocalConnection();
//             Statement statement = connection.createStatement();){
//           statement.execute("CREATE SCHEMA IF NOT EXISTS learnUp");
//           statement.execute("CREATE TABLE IF NOT EXISTS learnUp.user (" +
//                   "id SERIAL PRIMARY KEY UNIQUE, " +
//                   "name TEXT, " +
//                   "surname TEXT, " +
//                   "age INTEGER" +
//                   ")");
//           statement.execute("INSERT INTO learnUp.user (name, surname, age) VALUES" +
//                   "('Ivan', 'Ivanov', 20)" );
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }
}
