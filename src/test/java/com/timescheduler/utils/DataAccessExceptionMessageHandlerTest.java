package com.timescheduler.utils;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;

/**
 * Created by ALEX on 27.05.2018.
 */
public class DataAccessExceptionMessageHandlerTest {

    @Test
    public void logMessageTest() {
        String actual = DataAccessExceptionMessageHandler.getLogMessage(new CannotGetJdbcConnectionException("CannotGetJdbcConnectionException"));
        String expected = AppConstants.NO_CONNECTION_ERROR_MSG;
        assertTrue(actual.equals(expected));

        actual = DataAccessExceptionMessageHandler.getLogMessage(new QueryTimeoutException("Timeout"));
        expected = AppConstants.UNABLE_TO_GET_DATA_ERROR_MSG;
        assertTrue(actual.equals(expected));
    }
}
