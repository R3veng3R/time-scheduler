package com.timescheduler.services;

import com.timescheduler.utils.ArgsAnalyzer;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by ALEX on 26.05.2018.
 */
@Setter
@Component
public class PrintTask {
    private static final Logger LOG = LoggerFactory.getLogger(PrintTask.class);
    private TimeRecordService timeRecordService;

    @Autowired
    public PrintTask(TimeRecordService timeRecordService) {
        this.timeRecordService = timeRecordService;

        if (ArgsAnalyzer.hasPrintCommand) {
            this.timeRecordService.printTimeTable();
        }
    }
}