package ru.test.issue;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import ru.test.issue_tracker.IssueTrackerProjectApplication;
import ru.test.issue_tracker.entity.Issue;
import ru.test.issue_tracker.entity.Status;
import ru.test.issue_tracker.mapper.IssueMapper;
import ru.test.issue_tracker.mapper.StatusMapper;
import ru.test.issue_tracker.service.IssueService;

@DisplayName("Test DB")
@SpringBootTest(classes = IssueTrackerProjectApplication.class)
@TestMethodOrder(OrderAnnotation.class)
public class DatabaseTest{

	private final static Logger logger = Logger.getLogger(DatabaseTest.class);

	@Autowired
	private IssueService issueService;
	
//	@Autowired
//	private IssueMapper issueMapper;
	
	
//	@Test
	@DisplayName("start status test")
	@Order(1)
	public void statuses() {
//		Status status = statusMapper.findById(2L);
//		logger.debug("status " + status);
//		
//		List<Status> statusList = statusMapper.findAll();
//		logger.debug("statusList " + statusList);
	}
	
	@Test
	@DisplayName("start issue test")
	@Order(2)
	public void issues() {		
		List<Issue> issues = issueService.getIssueList();
//		List<Issue> issues = issueMapper.findAll();
		logger.debug("issues " + issues);
	}
}
