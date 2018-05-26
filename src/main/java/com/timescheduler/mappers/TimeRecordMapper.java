package com.timescheduler.mappers;

import com.timescheduler.model.TimeRecord;
import com.timescheduler.utils.AppConstants;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by ALEX on 26.05.2018.
 */
@Mapper
public interface TimeRecordMapper {
    @Select("SELECT id, timestamp FROM " + AppConstants.TIME_RECORD_TABLE_NAME)
    List<TimeRecord> getAll();

    @Insert("INSERT INTO " + AppConstants.TIME_RECORD_TABLE_NAME + " (timestamp) VALUES(#{timestamp})")
    void insertTime(TimeRecord timeTable);

    @Insert({"<script>",
            "INSERT INTO " + AppConstants.TIME_RECORD_TABLE_NAME + "(timestamp) VALUES ",
            "<foreach collection='timeRecordList' item='timeRecord' index='index' open='(' separator = '),(' close=')' >" +
                    "#{timeRecord.timestamp}",
            "</foreach>",
            "</script>"})
    int insertTimeList(@Param("timeRecordList") List<TimeRecord> timeRecordList);

    @Select("SELECT 1")
    List<Integer> testConnection();
}
