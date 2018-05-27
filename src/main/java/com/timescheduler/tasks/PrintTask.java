package com.timescheduler.tasks;

import com.timescheduler.services.TimeRecordService;
import com.timescheduler.utils.ArgsAnalyzer;
import com.timescheduler.utils.DataAccessExceptionMessageHandler;
import com.timescheduler.utils.DatabaseUtils;
import lombok.Setter;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

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