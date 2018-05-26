package com.timescheduler.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * Created by ALEX on 26.05.2018.
 */
@Getter
@Setter
public class TimeRecord {
    private Long id;
    private Timestamp timestamp;
}

