package com.timescheduler.services;

import com.timescheduler.mappers.TimeRecordMapper;
import com.timescheduler.model.TimeRecord;
import com.timescheduler.utils.TimeRecordBuffer;
import com.timescheduler.utils.Utils;
import lombok.Setter;
import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ALEX on 26.05.2018.
 */
@Setter
@Component
@Transactional(rollbackFor = Exception.class)
public class TimeRecordService {
    private static final Logger LOG = LoggerFactory.getLogger(SchedulerService.class);

    private TimeRecordMapper timeRecordMapper;
    private boolean isConnected;

    @Autowired
    public TimeRecordService(TimeRecordMapper timeRecordMapper) {
        this.timeRecordMapper = timeRecordMapper;
    }

    public void saveLocalData() {
        if (isConnected) {
            try {
                timeRecordMapper.insertTimeList(TimeRecordBuffer.timeRecordBuffer);
                LOG.info("Saved " + TimeRecordBuffer.timeRecordBuffer.size() + " timestamp record(s) to DB.");
                TimeRecordBuffer.timeRecordBuffer.clear();

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

    public void printTimeTable() {
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

    public void checkConnection() {
        if (isConnected) {
            return;
        }

        try {
            this.timeRecordMapper.testConnection();
            saveLocalData();
            isConnected = true;

        } catch (MyBatisSystemException e) {
            LOG.warn("Unable to establish connection with database. Retrying in 5 secs.");
            isConnected = false;
        }
    }
}
