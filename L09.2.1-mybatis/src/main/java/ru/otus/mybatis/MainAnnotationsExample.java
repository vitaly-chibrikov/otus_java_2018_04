package ru.otus.mybatis;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.h2.tools.Server;
import ru.otus.user.UsersDAO;
import ru.otus.user.UsersDataSet;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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
 *
 * https://s.mail.ru/KyDZ/d6YpYTGDY
 */
public class MainAnnotationsExample {
    public static void main(String[] args) throws SQLException {

        new MainAnnotationsExample().run();
    }

    private void run() throws SQLException {
        //MySQL
        //SqlSessionFactory sqlSessionFactory = getSqlSessionFactory(getMySQLDataSource());

        //H2
        Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory(getH2DataSource());

        try (SqlSession session = sqlSessionFactory.openSession(false)) {

            UsersDAO dao = session.getMapper(UsersDAO.class);
            createTableIfNotExists(dao);

            dao.save(new UsersDataSet("tully"));
            session.commit();

            UsersDataSet dataSet = dao.select("tully");
            System.out.println(dataSet);
        }
    }

    private void createTableIfNotExists(UsersDAO dao) {
        Map<String, String> createMap = new HashMap<>();
        createMap.put(UsersDAO.SQL, "create table if not exists user (id bigint auto_increment, name varchar(256), primary key (id))");
        dao.execute(createMap);
    }

    private SqlSessionFactory getSqlSessionFactory(DataSource dataSource) {
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);

        configuration.addMapper(UsersDAO.class);

        return new SqlSessionFactoryBuilder().build(configuration);
    }

    private DataSource getMySQLDataSource() {
        PooledDataSource ds = new PooledDataSource();

        ds.setDriver("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/dbexample?useSSL=false");
        ds.setUsername("tully");
        ds.setPassword("tully");

        return ds;
    }

    private DataSource getH2DataSource() {
        PooledDataSource ds = new PooledDataSource();

        ds.setDriver("org.h2.Driver");
        ds.setUrl("jdbc:h2:mem:testBatis");
        ds.setUsername("tully");
        ds.setPassword("tully");
        return ds;
    }

}
