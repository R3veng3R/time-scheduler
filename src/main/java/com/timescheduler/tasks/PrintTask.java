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
@Component
@Log
public class PrintTask {

    @Autowired
    public PrintTask(TimeRecordService timeRecordService) {
        if (ArgsAnalyzer.hasPrintCommand) {
            timeRecordService.printTimeTable();
        }
    }
}