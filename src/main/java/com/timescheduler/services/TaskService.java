package com.timescheduler.services;

import com.timescheduler.mappers.TimeRecordMapper;
import com.timescheduler.model.TimeRecord;
import com.timescheduler.utils.AppConstants;
import com.timescheduler.utils.Utils;
import lombok.Setter;
import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ALEX on 26.05.2018.
 */
@Setter
@Component
public class TaskService {
    private static final Logger LOG = LoggerFactory.getLogger(TaskService.class);
    private ArrayList<TimeRecord> timeRecordBuffer = new ArrayList<>();

    @Autowired private TimeRecordMapper timeRecordMapper;
    @Autowired private ApplicationArguments appArgs;

    private boolean isConnected = true;

    @Scheduled(fixedRate = 1000)
    private void doTask() {
        if (hasPrintCommand()) {
            printTimeTable();

        } else {
            addTimeRecord();
            saveCurrentTimeStamp();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Scheduled(fixedRate = 5000)
    private void checkConnection() {
        if (isConnected) {
            return;
        }

        try {
            this.timeRecordMapper.testConnection();
            isConnected = true;

        } catch (MyBatisSystemException e) {
            LOG.warn("Unable to establish connection with database. Retrying in 5 secs.");
            isConnected = false;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    private void saveCurrentTimeStamp() {
        if (isConnected) {
            try {
                this.timeRecordMapper.insertTimeList(this.timeRecordBuffer);
                LOG.info("Inserted " + this.timeRecordBuffer.size() + " record(s) to DB.");
                this.timeRecordBuffer.clear();

            } catch (MyBatisSystemException e) {
                // THIS BLOCK WILL CATCH ALL THE DATABASE EXCEPTIONS INCLUDING:
                // JDBCConnectionException, CommunicationsException, SQLTimeoutException
                // UNFORTUNATELY, I COULDN'T FIND OTHER WAYS TO CATCH THEM AS THEY ARE ALL NESTED INSIDE THE FRAMEWORK
                // IF IT WOULD HAVE BEEN A WEB APP, I COULD WRITE MY OWN EXCEPTIONHANDLER CLASS AND HANDLE THEM THERE
                LOG.warn("Unable to establish connection with database. It might be busy" +
                        "The data will be saved as soon as connection is available.");
                isConnected = false;
            }
        }
    }

    private void printTimeTable() {
        try {
            List<TimeRecord> timeRecordList = this.timeRecordMapper.getAll();
            Utils.printTable(timeRecordList);

        } catch (MyBatisSystemException e) {
            LOG.warn("Unable to establish connection with database. Application will now shut down");

        } finally {
            LOG.info("Shutting down application...");
            System.exit(0);
        }
    }

    private void addTimeRecord() {
        TimeRecord timeRecord = new TimeRecord();
        timeRecord.setTimestamp(new Timestamp(System.currentTimeMillis()));
        this.timeRecordBuffer.add(timeRecord);
    }

    private boolean hasPrintCommand() {
        String[] appSourceArgs = appArgs.getSourceArgs();
        String result = Arrays.stream(appSourceArgs)
                .filter(arg -> arg.trim().equals(AppConstants.PRINT_COMMAND))
                .findFirst()
                .orElse(null);

        return result != null;
    }
}