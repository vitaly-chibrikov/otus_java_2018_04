package ru.otus.main;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

/**
 * Created by tully.
 */
public class HikariCPDataSourceFactory implements DataSourceFactory {
    private HikariDataSource hikariDataSource;

    @Override
    public DataSource get() {
        if (hikariDataSource != null) {
            return hikariDataSource;
        }

        HikariConfig config = new HikariConfig();

        config.setJdbcUrl("jdbc:mysql://localhost:3306/dbexample?useSSL=false");
        config.setUsername("tully");
        config.setPassword("tully");

        config.setMaximumPoolSize(1);
        config.setConnectionTimeout(0);
        config.setValidationTimeout(1000);
        config.setAutoCommit(false);
        config.setRegisterMbeans(true);

        hikariDataSource = new HikariDataSource(config);
        return hikariDataSource;
    }

}
