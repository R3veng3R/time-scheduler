package com.timescheduler.utils;

import java.text.SimpleDateFormat;

/**
 * Created by ALEX on 26.05.2018.
 */
public class AppConstants {
    public static final String PRINT_COMMAND = "-p";
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    public static final String TIME_RECORD_TABLE_NAME = "time_record";

    public static final String NO_CONNECTION_ERROR_MSG = "There is no connection to DB. Please check if DB is online";
    public static final String INTERRUPT_CONNECTION_ERROR_MSG = "Connection to DB has been interrupted. Retrying in 5 secs";
    public static final String UNABLE_TO_GET_DATA_ERROR_MSG = "Unable to get data from DB. It might be busy";
}

