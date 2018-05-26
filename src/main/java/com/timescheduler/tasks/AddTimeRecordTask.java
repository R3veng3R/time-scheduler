package com.timescheduler.tasks;

import com.timescheduler.utils.TimeRecordBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by ALEX on 26.05.2018.
 */
@Component
public class AddTimeRecordTask {
    private static final Logger LOG = LoggerFactory.getLogger(AddTimeRecordTask.class);

    @Scheduled(fixedRate = 1000)
    public void addTimeRecordToBuffer() {
        LOG.info("addTimeRecordToBuffer");
        TimeRecordBuffer.addTimeRecord();
    }
}
