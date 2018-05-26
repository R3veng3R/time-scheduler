package com.timescheduler.utils;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by ALEX on 26.05.2018.
 */
@RunWith(SpringRunner.class)
public class ArgsAnalyzerTest {

    @Test
    public void noArgumentsTest() {
        String[] args =  new String[0];
        boolean result = ArgsAnalyzer.canStartApp(args);
        assertTrue(result);
    }

    @Test
    public void printArgumentsTest() {
        String[] args = new String[] {"-p"};
        boolean result = ArgsAnalyzer.canStartApp(args);
        assertTrue(result);
    }

    @Test
    public void randomArgumentsTest() {
        String[] args = new String[] {"-ppp", "---p", "-asd"};
        boolean result = ArgsAnalyzer.canStartApp(args);
        assertFalse(result);
    }
}
