package com.timescheduler.timescheduler;

import com.timescheduler.model.TimeRecord;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan("com.timescheduler.services")
@MappedTypes(TimeRecord.class)
@MapperScan("com.timescheduler.mappers")
@EnableScheduling
@EnableTransactionManagement
public class TimeSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimeSchedulerApplication.class, args);
	}
}
