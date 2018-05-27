package com.timescheduler.utils;

import lombok.Setter;
import lombok.extern.java.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by ALEX on 27.05.2018.
 */
@Setter
@Log
public class DatabaseUtils {

    // TESTING CONNECTION WITH DATABASE BEFORE PERFORMING SCHEDULER TASKS
    public static void testConnection(String url, String userName, String password) throws SQLException{
        String connectionUrl =  url + "?" + "user=" + userName + "&password=" + password;
        Connection conn = DriverManager.getConnection(connectionUrl);
    }
}
