package com.timescheduler.services;

import com.timescheduler.mappers.TimeRecordMapper;
import com.timescheduler.tasks.AddTimeRecordTask;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.timescheduler.timescheduler.TimeSchedulerApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

/**
 * Created by ALEX on 27.05.2018.
 */
@RunWith(SpringRunner.class)
public class TimeRecordServiceTest {
    private TimeRecordService timeRecordService;

    @Mock
    private ConfigurableApplicationContext context;

    @Mock
    private TimeRecordMapper timeRecordMapper;

    @Before
    public void init() {
        timeRecordService = new TimeRecordService(timeRecordMapper, context);
        when(timeRecordMapper.testConnection()).thenThrow((new DataAccessException("..."){ }));
    }

    @After
    public void tearDown() {
        timeRecordService = null;
    }

    @Test
    public void hasNoConnectionTest() {
        assertTrue(TimeRecordService.isConnected);
        timeRecordService.hasConnection();
        assertFalse(TimeRecordService.isConnected);
    }

    @Test
    public void dbNoConnectionTest() {
        boolean isConnected = true;

        try {
            throw new CannotGetJdbcConnectionException("");

        } catch (DataAccessException e) {
            isConnected = false;
        }

        assertFalse(isConnected);
    }

    @Test
    public void queryTimeOutExceptionTest() {
        boolean isConnected = true;

        try {
            throw new QueryTimeoutException("");

        } catch (DataAccessException e) {
            isConnected = false;
        }

        assertFalse(isConnected);
    }
}
