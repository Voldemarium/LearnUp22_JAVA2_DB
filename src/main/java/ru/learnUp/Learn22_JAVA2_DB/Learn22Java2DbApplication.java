package ru.learnUp.Learn22_JAVA2_DB;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.learnUp.Learn22_JAVA2_DB.lessonGuide.mySql.MySqlConnect;

import java.sql.*;

@SpringBootApplication
@Slf4j
public class Learn22Java2DbApplication {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //SQLite по умолчанию создает БД с указанным названием в папке проекта
        //Если БД с указанным названием не существует, она ее создаст, если существует, то
        //установит с ней соединение
        Connection connection = SQLiteConnect.getSQLiteConnection("myDB");

        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS student (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                "name TEXT, " +
                "surname TEXT, " +
                "age INTEGER" +
                ")");
        statement.execute("INSERT INTO student (name, surname, age) VALUES" +
                "('Ivan', 'Ivanov', 20)");
        ResultSet resultSet = statement.executeQuery("SELECT * FROM student");
        while (resultSet.next()) {
            long id = resultSet.getLong("id");
            int age = resultSet.getInt("age");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            System.out.printf("User(%d %d %s %s)\n", id, age, name, surname);
        }
//        //Данные для подключения к БД Postgre (на локальном диске)
//        String hostName = "localhost"; // имя хоста по умолчанию
//        String port = "5432";          //4532 - порт БД для локальной Postgre
//        String dbName = "myDB";        //имя моей БД
//        String user = "postgres";      //имя user по умолчанию для БД Postgre
//        String password = "postgres";  //password по умолчанию для БД Postgre
//
//        Class.forName("org.postgresql.Driver");  //Регистрация драйвера
//        String connectionURL = "jdbc:postgresql://" + hostName + ":" + port +"/" + dbName;
//        Connection connection = DriverManager.getConnection(connectionURL, user, password);
//        Statement statement = connection.createStatement();
//
//        ResultSet resultSet = statement.executeQuery("SELECT * FROM learnUp.user");
//        while (resultSet.next()) {
//            long id = resultSet.getLong("id");
//            int age = resultSet.getInt("age");
//            String name = resultSet.getString("name");
//            String surname= resultSet.getString("surname");
//            System.out.printf("User(%d %d %s %s)\n", id, age, name, surname);
//        }

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
