package com.timescheduler.services;

import com.timescheduler.utils.DataAccessExceptionMessageHandler;
import com.timescheduler.utils.DatabaseUtils;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * Testing DB connection on Spring initialization
 * If no packages received from DB the application exits;
 *
 * NB:
 *    DB must be setup and accessible on Application runtime.
 *
 * Created by ALEX on 27.05.2018.
 */
@Setter
@Component
@Log
public class ApplicationStartupService {
    private Environment environment;
    private ConfigurableApplicationContext context;

    @Autowired
    private ApplicationStartupService(Environment environment, ConfigurableApplicationContext context) {
        this.environment = environment;
        this.context = context;

        log.info("Testing connection with Database, please wait a few seconds...");
        testConnection();
    }

    private void testConnection() {
        String url = environment.getProperty("spring.datasource.url");
        String user = environment.getProperty("spring.datasource.username");
        String password = environment.getProperty("spring.datasource.password");

        try {
            DatabaseUtils.testConnection(url, user, password);
            log.info("Connection established");

        } catch (SQLException e) {
            String errorMessage = e.getMessage();
            String message = "";

            if (errorMessage.contains("Communications link failure")) {
                message = DataAccessExceptionMessageHandler.getLogMessage(new DataAccessException("CommunicationsLinkFailure") { });

            } else if (errorMessage.contains("Unknown database")) {
                message =  errorMessage + " " + "Have you setup the database correctly?";
            }

            log.info(message);
            log.info("Application will shut down for now..");
            System.exit(SpringApplication.exit(this.context));
        }
    }
}
