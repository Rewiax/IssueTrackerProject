package ru.test.issue_tracker.web;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ru.test.issue_tracker.entity.Issue;
import ru.test.issue_tracker.entity.Status;
import ru.test.issue_tracker.service.IssueService;

@RestController
public class IssueRestController {

	private final static Logger logger = Logger.getLogger(IssueRestController.class);
	
	@Autowired
	private IssueService issueService;

	
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR, reason="Database ERROR")
	@ExceptionHandler({Exception.class})
	private void handleError(Exception e) {
		logger.error("Handle Error", e);
	}
	
	@GetMapping("/api/issue/get_issue_list")
	public List<Issue> getIssues(){				
		return issueService.getIssueList();
	}
	
	@GetMapping("/api/issue/get_issue{id}")
	public Issue getIssue(@PathVariable("id") int id){			
		return issueService.getIssue(id);
	}
	
	@GetMapping("/api/issue/get_statuses")
	public List<Status> getIssueStatuses(){			
		return issueService.getStatusList();
	}
}
