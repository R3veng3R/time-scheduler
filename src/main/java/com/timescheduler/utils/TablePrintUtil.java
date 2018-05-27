package com.timescheduler.utils;

import com.timescheduler.model.TimeRecord;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by ALEX on 26.05.2018.
 */
@Log
public class TablePrintUtil {
    public static void print(List<TimeRecord> timeRecordList) {
        log.info("");
        log.info("+------+---------------------+");
        log.info("| ID   | TimeStamp           |");
        log.info("+------+---------------------+");

        String formatMessage;
        for (TimeRecord record : timeRecordList) {
           formatMessage = String.format("| %-4d | %-15s |", record.getId(),
                    AppConstants.DATE_FORMAT.format(record.getTimestamp()));

            log.info(formatMessage);
        }

        if (timeRecordList.size() == 0) {
            log.info("| No Data.                   |");
        }

        log.info("+------+---------------------+");
        log.info("TOTAL RECORDS: " + timeRecordList.size());
        log.info("");
    }
}
