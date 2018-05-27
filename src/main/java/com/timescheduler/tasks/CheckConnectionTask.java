package com.timescheduler.tasks;

import com.timescheduler.services.TimeRecordService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by ALEX on 26.05.2018.
 */
@Component
@Log
public class CheckConnectionTask {
    private TimeRecordService timeRecordService;

    @Autowired
    public CheckConnectionTask(TimeRecordService timeRecordService) {
        this.timeRecordService = timeRecordService;
    }

    @Scheduled(fixedDelay = 5000)
    public void checkConnection() {
        timeRecordService.hasConnection();
    }
}
