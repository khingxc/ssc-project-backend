package io.muzoo.ssc.project.backend.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class ConfigurationLoader {

    public static ApplicationProperties load(){
        String appFilename = "application.properties";
        try (FileInputStream fin = new FileInputStream(appFilename)){

            Properties properties = new Properties();
            properties.load(fin);
            String driverClassName = properties.getProperty("spring.datasource.driver-class-name");
            String connectionUrl = properties.getProperty("spring.datasource.url");
            String username = properties.getProperty("spring.datasource.username");
            String password = properties.getProperty("spring.datasource.password");

            ApplicationProperties applicationProperties = new ApplicationProperties();
            applicationProperties.setDatabaseDriverClassName(driverClassName);
            applicationProperties.setDatabaseConnectionUrl(connectionUrl);
            applicationProperties.setDatabaseUsername(username);
            applicationProperties.setDatabasePassword(password);

            return applicationProperties;

        } catch (IOException e) {
            return null;
        }
    }
}

