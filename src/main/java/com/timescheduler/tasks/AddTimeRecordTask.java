package com.timescheduler.tasks;

import com.timescheduler.utils.TimeRecordBuffer;
import lombok.extern.java.Log;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Adds a record to the queue,
 * to be saved later.
 *
 * Created by ALEX on 26.05.2018.
 */
@Component
@Log
public class AddTimeRecordTask {
    @Scheduled(fixedRate = 1000)
    public void addTimeRecordToBuffer() {
        TimeRecordBuffer.addTimeRecord();
    }
}
