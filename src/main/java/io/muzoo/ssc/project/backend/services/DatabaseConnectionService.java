package io.muzoo.ssc.project.backend.services;

import com.zaxxer.hikari.HikariDataSource;
import io.muzoo.ssc.project.backend.config.ApplicationProperties;
import io.muzoo.ssc.project.backend.config.ConfigurationLoader;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionService {

    private final HikariDataSource ds;
    private static DatabaseConnectionService connectionService;

    public static DatabaseConnectionService getInstance() {
        if (connectionService == null){
            connectionService = new DatabaseConnectionService();
        }
        return connectionService;
    }

    public DatabaseConnectionService(){
        ds = new HikariDataSource();
        ds.setMaximumPoolSize(20);

        ApplicationProperties appProperties = ConfigurationLoader.load();
        if (appProperties == null){
            throw new RuntimeException("Unable to read the config.properties.");
        }
        ds.setDriverClassName(appProperties.getDatabaseDriverClassName());
        ds.setJdbcUrl(appProperties.getDatabaseConnectionUrl());
        ds.addDataSourceProperty("user", appProperties.getDatabaseUsername());
        ds.addDataSourceProperty("password", appProperties.getDatabasePassword());
        ds.setAutoCommit(false);
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

}
