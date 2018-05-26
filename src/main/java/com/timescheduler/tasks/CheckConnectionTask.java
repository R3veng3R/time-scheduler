package com.timescheduler.tasks;

import com.timescheduler.services.TimeRecordService;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by ALEX on 26.05.2018.
 */
@Setter
@Component
public class CheckConnectionTask {
    private static final Logger LOG = LoggerFactory.getLogger(CheckConnectionTask.class);

    private TimeRecordService timeRecordService;

    @Autowired
    public CheckConnectionTask(TimeRecordService timeRecordService) {
        this.timeRecordService = timeRecordService;
    }

    @Scheduled(fixedRate = 5000)
    public void checkConnection() {
        LOG.info("checkConnection()");

        try {
            timeRecordService.checkConnection();

        } catch (Exception e) {
            LOG.warn("Unable to establish connection with database. Retrying in 5 secs.");
        }
    }
}
