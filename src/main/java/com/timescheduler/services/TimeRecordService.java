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
    private TimeRecordMapper timeRecordMapper;
    private DataSource dataSource;
    public static boolean isConnected = true;

    @Autowired
    public TimeRecordService(TimeRecordMapper timeRecordMapper, DataSource dataSource) {
        this.timeRecordMapper = timeRecordMapper;
        this.dataSource = dataSource;
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveLocalData() {

        if (hasConnection()) {
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
            System.exit(0);
        }
    }

    public void checkConnection() {
        hasConnection();
    }

    public boolean hasConnection() {
        try {
            this.timeRecordMapper.testConnection();
            isConnected = true;
        } catch (DataAccessException e) {
            isConnected = false;
        }

        return isConnected;
    }
}
