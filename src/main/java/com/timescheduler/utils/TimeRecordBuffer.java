package com.timescheduler.utils;

import com.timescheduler.model.TimeRecord;

import java.sql.Timestamp;
import java.util.LinkedList;

/**
 * Created by ALEX on 26.05.2018.
 */
public class TimeRecordBuffer {
    public static LinkedList<TimeRecord> timeRecordBuffer;

    public static void addTimeRecord() {
        TimeRecord timeRecord = new TimeRecord();
        timeRecord.setTimestamp(new Timestamp(System.currentTimeMillis()));
        timeRecordBuffer.add(timeRecord);
    }
}
