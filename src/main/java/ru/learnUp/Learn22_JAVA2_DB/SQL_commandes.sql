CREATE SCHEMA IF NOT EXISTS learnUp;
CREATE TABLE IF NOT EXISTS learnUp.user (
                   id SERIAL PRIMARY KEY UNIQUE,
                   "name" TEXT,
                   surname TEXT,
                   age INTEGER
                   );

 CREATE TABLE IF NOT EXISTS learnUp.student (
                   id SERIAL PRIMARY KEY UNIQUE,
                   "name" TEXT,
                   surname TEXT,
                   group_id INTEGER
                   );

 CREATE TABLE IF NOT EXISTS learnUp."group" (
                   id SERIAL PRIMARY KEY UNIQUE,
                   title TEXT
                   );

 CREATE TABLE IF NOT EXISTS learnUp.teacher (
                   id SERIAL PRIMARY KEY UNIQUE,
                   "name" TEXT,
                   surname TEXT,
                   group_id INTEGER
                   );

INSERT INTO learnUp.user (name, surname, age) VALUES('Ivan', 'Ivanov', 20)
INSERT INTO learnUp.user (name, surname, age) VALUES('Petr', 'Petrov', 21)
INSERT INTO learnUp.user (name, surname, age) VALUES('Vovo', 'Kozlov', 44)

DELETE FROM learnUp.user WHERE id = 1;

INSERT INTO learnUp.student (name, surname, group_id) VALUES('Ivan', 'Ivanov', 1)
INSERT INTO learnUp.student (name, surname, group_id) VALUES('Petr', 'Petrov', 1)
INSERT INTO learnUp.student (name, surname, group_id) VALUES('Kolja', 'Sidorov', 2)

INSERT INTO learnUp."group" (title) VALUES('Group 1');
I
INSERT INTO learnUp.teacher (name, surname, group_id) VALUES('Oleg', 'Olegov', 1)
INSERT INTO learnUp.teacher (name, surname, group_id) VALUES('Taras', 'Bulba', 2)


--SELECT * FROM learnup."user";
SELECT * FROM learnup."user" WHERE age > 40;

SELECT * FROM learnup.teacher WHERE id = 1;

-- найти всех студентов преподавателя с id = 1 (с совпадающими group_id)
SELECT * FROM learnup.student WHERE group_id IN (
      SELECT id
      FROM learnup.teacher
      WHERE learnup.teacher.id = 1
      );

-- найти всех студентов всех преподавателей (с совпадающими group_id)
--  с объединением в одну таблицу преподавателя и его студентов
      SELECT *
      FROM learnup.teacher t JOIN learnup.student s
      ON t.group_id = s.group_id;

-- найти всех студентов преподавателя с id=1 (и с совпадающими group_id)
--  с объединением в одну таблицу преподавателя и его студентов
      SELECT *
      FROM learnup.teacher t JOIN learnup.student s
      ON t.group_id = s.group_id;