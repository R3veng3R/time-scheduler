package com.timescheduler.utils;

import com.timescheduler.model.TimeRecord;
import com.timescheduler.services.PrintTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.LinkedList;

/**
 * Created by ALEX on 26.05.2018.
 */
public class TimeRecordBuffer {
    private static final Logger LOG = LoggerFactory.getLogger(PrintTask.class);
    public static LinkedList<TimeRecord> timeRecordBuffer;

    static {
        timeRecordBuffer = new LinkedList<>();
    }

    public static void addTimeRecord() {
        TimeRecord timeRecord = new TimeRecord();
        timeRecord.setTimestamp(new Timestamp(System.currentTimeMillis()));
        timeRecordBuffer.add(timeRecord);
        LOG.info("BUFFER HAS: " + timeRecordBuffer.size());
    }
}
