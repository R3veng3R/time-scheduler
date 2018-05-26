package com.timescheduler.utils;

import com.timescheduler.model.TimeRecord;
import com.timescheduler.tasks.PrintTask;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.LinkedList;

/**
 * Created by ALEX on 26.05.2018.
 */
@Log
public class TimeRecordBuffer {
    public static LinkedList<TimeRecord> timeRecordBuffer;

    static {
        timeRecordBuffer = new LinkedList<>();
    }

    public static void addTimeRecord() {
        TimeRecord timeRecord = new TimeRecord();
        timeRecord.setTimestamp(new Timestamp(System.currentTimeMillis()));
        timeRecordBuffer.add(timeRecord);

        log.info("Records ready to be saved: " + timeRecordBuffer.size() +
                ". Last added time record was: " + AppConstants.DATE_FORMAT.format(timeRecord.getTimestamp()));
    }
}
