package com.timescheduler.services;

import com.timescheduler.mappers.TimeRecordMapper;
import com.timescheduler.model.TimeRecord;
import com.timescheduler.utils.ArgsAnalyzer;
import com.timescheduler.utils.TimeRecordBuffer;
import com.timescheduler.utils.Utils;
import lombok.Setter;
import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ALEX on 26.05.2018.
 */
@Setter
@Component
@Transactional(rollbackFor = Exception.class)
public class SchedulerService {
    private static final Logger LOG = LoggerFactory.getLogger(SchedulerService.class);
    private TimeRecordService timeRecordService;

    @Autowired
    public SchedulerService( TimeRecordService timeRecordService) {
        this.timeRecordService = timeRecordService;

        if (ArgsAnalyzer.hasPrintCommand) {
            timeRecordService.printTimeTable();
        }
    }

    @Scheduled(fixedRate = 1000)
    public void addAndSaveTimeRecord() {
        LOG.info("doTask()");

        TimeRecordBuffer.addTimeRecord();
        timeRecordService.saveLocalData();
    }

    @Scheduled(fixedRate = 5000)
    public void checkConnection() {
        LOG.info("checkConnection()");
        timeRecordService.checkConnection();
    }
}