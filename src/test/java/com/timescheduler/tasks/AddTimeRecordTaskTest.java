package com.timescheduler.tasks;

import static org.junit.Assert.*;
import com.timescheduler.model.TimeRecord;
import com.timescheduler.utils.TimeRecordBuffer;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;

/**
 * Created by ALEX on 27.05.2018.
 */
@RunWith(SpringRunner.class)
public class AddTimeRecordTaskTest {
    private AddTimeRecordTask addTimeRecordTask;

    @Before
    public void init() {
        addTimeRecordTask = new AddTimeRecordTask();
    }

    @After
    public void tearDown() {
        addTimeRecordTask = null;
    }

    @Test
    public void addTimeRecordToBufferTest() {
        addTimeRecordTask.addTimeRecordToBuffer();
        boolean actual = TimeRecordBuffer.timeRecordBuffer.size() == 1;
        assertTrue(actual);
    }
}
