package ru.test.issue_tracker;

import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
@ComponentScan("ru.test.issue_tracker")
@MapperScan("ru.test.issue_tracker.mapper")
public class IssueTrackerProjectApplication {
	
	private final static Logger logger = Logger.getLogger(IssueTrackerProjectApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(IssueTrackerProjectApplication.class, args);
		
		logger.debug("start app");
	}
	
	@Bean
	public static ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.setTimeZone(TimeZone.getTimeZone("Asia/Novosibirsk"));
		return mapper; 
	}

}
