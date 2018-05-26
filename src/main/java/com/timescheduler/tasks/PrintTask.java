package com.timescheduler.tasks;

import com.timescheduler.services.TimeRecordService;
import com.timescheduler.utils.ArgsAnalyzer;
import lombok.Setter;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by ALEX on 26.05.2018.
 */
@Setter
@Component
@Log
public class PrintTask {
    private TimeRecordService timeRecordService;

    @Autowired
    public PrintTask(TimeRecordService timeRecordService) {
        this.timeRecordService = timeRecordService;

        if (ArgsAnalyzer.hasPrintCommand) {
            this.timeRecordService.printTimeTable();
        }
    }
}