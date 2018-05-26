package com.timescheduler.mappers;

import com.timescheduler.model.TimeTable;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by ALEX on 26.05.2018.
 */
@Mapper
public interface TimeTableMapper {
    @Select("SELECT * FROM time_table")
    List<TimeTable> getAll();

    @Insert("INSERT INTO time_table (timestamp) VALUES(#{timestamp})")
    void insertTime(TimeTable timeTable);

    @Insert({"<script>",
            "INSERT INTO time_table (timestamp) VALUES ",
            "<foreach collection='timeTableList' item='timeRecord' index='index' open='(' separator = '),(' close=')' >" +
                    "#{timeRecord.timestamp}",
            "</foreach>",
            "</script>"})
    int insertTimeList(@Param("timeTableList") List<TimeTable> timeTableList);

    @Select("SELECT 1")
    List<Integer> testConnection();
}
