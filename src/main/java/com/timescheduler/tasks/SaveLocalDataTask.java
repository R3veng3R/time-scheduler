package com.timescheduler.tasks;

import com.timescheduler.services.TimeRecordService;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.CannotCreateTransactionException;

/**
 * Created by ALEX on 26.05.2018.
 */
@Component
@Log
public class SaveLocalDataTask {
    private TimeRecordService timeRecordService;

    @Autowired
    public SaveLocalDataTask(TimeRecordService timeRecordService) {
        this.timeRecordService = timeRecordService;
    }

    @Scheduled(fixedRate = 1000)
    public void saveRecordsToDB() {
        log.info("saveRecordsToDB");

        if (TimeRecordService.isConnected) {
            try {
                timeRecordService.saveLocalData();

            } catch (CannotCreateTransactionException e) {
                log.info("Unable to establish connection with database." +
                        "The data will be saved as soon as connection is available.");

                TimeRecordService.isConnected = false;
            }
        }
    }
}