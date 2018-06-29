package ru.otus.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import ru.otus.user.UsersDataSet;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by tully.
 *
 * mysql> CREATE USER 'tully'@'localhost' IDENTIFIED BY 'tully';
 * mysql> GRANT ALL PRIVILEGES ON * . * TO 'tully'@'localhost';
 * mysql> select user, host from mysql.user;
 * mysql> create database db_example;
 * mysql> SET GLOBAL time_zone = '+3:00';
 *
 * MyBatis plans support JDK 9 officially since next minor version up(3.5).
 */
public class MainXMLExample {
    public static void main(String[] args) throws IOException {
        Reader reader = Resources.getResourceAsReader("config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        try (SqlSession session = sqlSessionFactory.openSession(false)) {
            session.insert("ru.otus.mybatis.insert", new UsersDataSet(1,"sully"));
            session.commit();

            UsersDataSet dataSet = session.selectOne("ru.otus.mybatis.select", "sully");
            System.out.println(dataSet);
        }
    }
}
