package com.timescheduler.utils;

import com.timescheduler.model.TimeRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by ALEX on 26.05.2018.
 */
public class Utils {
    private static final Logger LOG = LoggerFactory.getLogger(Utils.class);

    public static void printTable(List<TimeRecord> timeRecordList) {
        System.out.println();
        System.out.format("+------+---------------------+%n");
        System.out.format("| ID   | TimeStamp           |%n");
        System.out.format("+------+---------------------+%n");

        for (TimeRecord record : timeRecordList) {
            System.out.format("| %-4d | %-15s |%n", record.getId(),
                    AppConstants.DATE_FORMAT.format(record.getTimestamp()));
        }

        if (timeRecordList.size() == 0) {
            System.out.format("| No Data.                   |%n");
        }

        System.out.format("+------+---------------------+%n");
        System.out.println("TOTAL RECORDS: " + timeRecordList.size());
        System.out.println();
    }
}
