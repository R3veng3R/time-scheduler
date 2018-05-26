package com.timescheduler.tasks;

import com.timescheduler.services.TimeRecordService;
import lombok.Setter;
import lombok.extern.java.Log;
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
@Log
public class CheckConnectionTask {
    private TimeRecordService timeRecordService;

    @Autowired
    public CheckConnectionTask(TimeRecordService timeRecordService) {
        this.timeRecordService = timeRecordService;
    }

    @Scheduled(fixedRate = 5000)
    public void checkConnection() {
        try {
            timeRecordService.checkConnection();

        } catch (Exception e) {
            log.info("Unable to establish connection with database. Retrying in 5 secs.");
        }
    }
}
