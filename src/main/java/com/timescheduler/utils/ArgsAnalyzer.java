package com.timescheduler.utils;

import com.timescheduler.tasks.PrintTask;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ALEX on 26.05.2018.
 */
@Log
public class ArgsAnalyzer {
    public static boolean hasPrintCommand = false;

    public static boolean canStartApp(String[] args) {
        boolean result = false;

        switch (args.length) {
            case 0:
                result = true;
                break;

            case 1:
                if (args[0].equals(AppConstants.PRINT_COMMAND)) {
                    hasPrintCommand = true;
                    result = true;
                }
                break;

            default:
            break;
        }

        return result;
    }
}
