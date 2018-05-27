package com.timescheduler.tasks;

import com.timescheduler.services.TimeRecordService;
import com.timescheduler.utils.ArgsAnalyzer;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Created by ALEX on 26.05.2018.
 */
@Setter
@Component
@Log
public class PrintTask {

    @Autowired
    public PrintTask(TimeRecordService timeRecordService, Environment environment) {
        if (ArgsAnalyzer.hasPrintCommand) {
            timeRecordService.printTimeTable();
        }
    }
}