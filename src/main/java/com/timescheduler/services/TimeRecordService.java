package com.timescheduler.services;

import com.timescheduler.mappers.TimeRecordMapper;
import com.timescheduler.model.TimeRecord;
import com.timescheduler.utils.TimeRecordBuffer;
import com.timescheduler.utils.TablePrintUtil;
import lombok.Setter;
import lombok.extern.java.Log;
import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.List;

/**
 * Created by ALEX on 26.05.2018.
 */
@Setter
@Component
@Log
public class TimeRecordService {
    public static boolean isConnected = true;
    private TimeRecordMapper timeRecordMapper;
    private ConfigurableApplicationContext context;

    @Autowired
    public TimeRecordService(TimeRecordMapper timeRecordMapper, ConfigurableApplicationContext context) {
        this.timeRecordMapper = timeRecordMapper;
        this.context = context;
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveLocalData() {
        if (isConnected) {
            try {
                timeRecordMapper.insertTimeList(TimeRecordBuffer.timeRecordBuffer);
                log.info("Saved " + TimeRecordBuffer.timeRecordBuffer.size() + " timestamp record(s) to DB.");
                TimeRecordBuffer.timeRecordBuffer.clear();

            } catch (MyBatisSystemException e) {
                isConnected = false;
            }
        }
    }

    public void printTimeTable() {
        try {
            List<TimeRecord> timeRecordList = this.timeRecordMapper.getAll();
            TablePrintUtil.print(timeRecordList);

        } catch (MyBatisSystemException e) {
            log.info("Unable to establish connection with database. Application will now shut down");

        } finally {
            log.info("Shutting down application...");
            System.exit(SpringApplication.exit(this.context));
        }
    }

    public void checkConnection() {
        try {
            this.timeRecordMapper.testConnection();
            isConnected = true;

        } catch (DataAccessException e) {
            log.info("Unable to establish connection with database. Retrying in 5 secs.");
            isConnected = false;
        }
    }
}
