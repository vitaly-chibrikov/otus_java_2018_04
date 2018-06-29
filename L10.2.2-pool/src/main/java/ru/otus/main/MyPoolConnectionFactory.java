package ru.otus.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by tully.
 */
public class MyPoolConnectionFactory implements ConnectionFactory {
    private final ConnectionFactory factory;
    private final Queue<MyConnection> pool = new LinkedList<>();

    MyPoolConnectionFactory(ConnectionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Connection get() {
        if (pool.isEmpty()) {
            Connection connection = factory.get();
            pool.add(new MyConnection(connection));
            System.out.println("New connection created");
        }
        return pool.poll();
    }

    @Override
    public void dispose() {
        pool.forEach(connection -> {
            try {
                connection.superClose();
                System.out.println("MyConnection closed");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private class MyConnection extends ConnectionDecorator {
        MyConnection(Connection connection) {
            super(connection);
        }

        public void close() {
            pool.add(this);
            System.out.println("MyConnection returned to the pool");
        }

        void superClose() throws SQLException {
            super.close();
        }
    }

}
