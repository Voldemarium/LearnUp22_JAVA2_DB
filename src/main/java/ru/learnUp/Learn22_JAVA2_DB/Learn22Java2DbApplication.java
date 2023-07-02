package ru.learnUp.Learn22_JAVA2_DB;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
@Slf4j
public class Learn22Java2DbApplication {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try (Connection connection = PostgreLocalConnect.getPostgreLocalConnection();
             Statement statement = connection.createStatement();){
           statement.execute("CREATE SCHEMA IF NOT EXISTS learnUp");
           statement.execute("CREATE TABLE IF NOT EXISTS learnUp.user (" +
                   "id SERIAL PRIMARY KEY UNIQUE, " +
                   "name TEXT, " +
                   "surname TEXT, " +
                   "age INTEGER" +
                   ")");
           statement.execute("INSERT INTO learnUp.user (name, surname, age) VALUES" +
                   "('Ivan', 'Ivanov', 20)" );
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
