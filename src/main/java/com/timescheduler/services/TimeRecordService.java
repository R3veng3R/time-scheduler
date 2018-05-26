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
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ALEX on 26.05.2018.
 */
@Setter
@Component
public class TimeRecordService {
    private static final Logger LOG = LoggerFactory.getLogger(TimeRecordService.class);

    private TimeRecordMapper timeRecordMapper;
    public static boolean isConnected = true;

    @Autowired
    public TimeRecordService(TimeRecordMapper timeRecordMapper) {
        this.timeRecordMapper = timeRecordMapper;
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveLocalData() {
        if (hasConnection()) {
            try {
                timeRecordMapper.insertTimeList(TimeRecordBuffer.timeRecordBuffer);
                LOG.info("Saved " + TimeRecordBuffer.timeRecordBuffer.size() + " timestamp record(s) to DB.");
                TimeRecordBuffer.timeRecordBuffer.clear();

            } catch (MyBatisSystemException e) {
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
//        if (isConnected) {
//            return;
//        }
//
//        try {
//            this.timeRecordMapper.testConnection();
//            isConnected = true;
//
//        } catch (MyBatisSystemException e) {
//            LOG.warn("Unable to establish connection with database. Retrying in 5 secs.");
//            isConnected = false;
//        }
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
