package com.timescheduler.timescheduler;

import com.timescheduler.model.TimeRecord;
import com.timescheduler.utils.ArgsAnalyzer;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan({"com.timescheduler.services", "com.timescheduler.tasks"})
@MappedTypes(TimeRecord.class)
@MapperScan("com.timescheduler.mappers")
@EnableScheduling
@EnableTransactionManagement
public class TimeSchedulerApplication {
	private static final Logger LOG = LoggerFactory.getLogger(TimeSchedulerApplication.class);

	public static void main(String[] args) {

		if (ArgsAnalyzer.canStartApp(args)) {
			LOG.info("Starting Application...");
			SpringApplication.run(TimeSchedulerApplication.class, args);

		} else {
			shutDown();
		}
	}

	private static void shutDown() {
		LOG.info("Application does not support these arguments. Use -p, or no arguments, please.");
		LOG.info("Application will now shut down.");
		System.exit(0);
	}
}
