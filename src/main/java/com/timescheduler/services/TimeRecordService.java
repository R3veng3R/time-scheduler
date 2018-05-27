package com.timescheduler.services;

import com.timescheduler.mappers.TimeRecordMapper;
import com.timescheduler.model.TimeRecord;
import com.timescheduler.utils.DataAccessExceptionMessageHandler;
import com.timescheduler.utils.TablePrintUtil;
import com.timescheduler.utils.TimeRecordBuffer;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

            } catch (DataAccessException e) {
                isConnected = false;
            }
        }
    }

    public void printTimeTable() {
        try {
            List<TimeRecord> timeRecordList = this.timeRecordMapper.getAll();
            TablePrintUtil.print(timeRecordList);

        } catch (DataAccessException e) {
            log.info(DataAccessExceptionMessageHandler.getLogMessage(e));
            log.info("Application will now shut down...");

        } finally {
            log.info("Shutting down application...");
            System.exit(SpringApplication.exit(this.context));
        }
    }

    public boolean hasConnection() {
        try {
            this.timeRecordMapper.testConnection();
            isConnected = true;

        } catch (DataAccessException e) {
            log.info(DataAccessExceptionMessageHandler.getLogMessage(e));
            isConnected = false;
        }

        return isConnected;
    }
}
