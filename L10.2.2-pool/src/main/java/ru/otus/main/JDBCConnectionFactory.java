package ru.otus.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnectionFactory implements ConnectionFactory {
    private String url;

    JDBCConnectionFactory() throws SQLException {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

        url = "jdbc:mysql://" +       //db type
                "localhost:" +               //host name
                "3306/" +                    //port
                "dbexample?" +              //db name
                "useSSL=false&" +            //do not use ssl
                "user=tully&" +              //login
                "password=tully";            //password
    }

    public Connection get() {
        try {
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dispose() {
        //do nothing
    }

}
