package com.timescheduler.utils;

import lombok.extern.java.Log;
import org.springframework.dao.DataAccessException;

/**
 * Created by ALEX on 27.05.2018.
 */
@Log
public class DataAccessExceptionMessageHandler {
    public static String getLogMessage(DataAccessException e) {
        String errorMessage = e.getMessage();

        String result = "";

        if ( errorMessage.contains("Timeout"))
            result = AppConstants.UNABLE_TO_GET_DATA_ERROR_MSG;

        if ( errorMessage.contains("CannotGetJdbcConnectionException"))
            result = AppConstants.NO_CONNECTION_ERROR_MSG;

        if (errorMessage.contains("CommunicationsLinkFailure"))
            result = AppConstants.NO_CONNECTION_ERROR_MSG;

        return result;
    }
}
