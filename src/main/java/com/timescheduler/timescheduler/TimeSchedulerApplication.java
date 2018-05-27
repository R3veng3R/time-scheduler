package com.timescheduler.timescheduler;

import com.timescheduler.model.TimeRecord;
import com.timescheduler.utils.ArgsAnalyzer;
import com.timescheduler.utils.DataAccessExceptionMessageHandler;
import com.timescheduler.utils.DatabaseUtils;
import lombok.extern.java.Log;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.CommunicationException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
@ComponentScan({"com.timescheduler.services", "com.timescheduler.tasks"})
@MappedTypes(TimeRecord.class)
@MapperScan("com.timescheduler.mappers")
@EnableScheduling
@EnableTransactionManagement
@Log
public class TimeSchedulerApplication {

	public static void main(String[] args) {
		if (ArgsAnalyzer.canStartApp(args)) {
			log.info("Starting Application...");
			SpringApplication.run(TimeSchedulerApplication.class, args);

		} else {
			shutDown();
		}
	}

	private static void shutDown() {
		log.info("Application does not support these arguments. Use -p or no arguments, please.");
		log.info("Application will now shut down.");
		System.exit(0);
	}
}
