package ru.test.issue_tracker.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ru.test.issue_tracker.entity.Comment;
import ru.test.issue_tracker.entity.Issue;
import ru.test.issue_tracker.entity.Login;
import ru.test.issue_tracker.service.IssueService;

@Controller
public class IssueController {	
	private final static Logger logger = Logger.getLogger(IssueController.class);
	
	@Autowired
	private IssueService issueService;
	
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR, reason="Database ERROR")
	@ExceptionHandler({Exception.class})
	private void handleError(Exception e) {
		logger.error("Handle Error", e);
	}
	
	@GetMapping("/")
	public String login(Model uiModel) {
		return "issue_login";
	}
	
	@GetMapping("/main")
	public String main(Model uiModel) {
		return "issue_main";
	}
	
	@GetMapping("/view")
	public String view(Model uiModel) {
		return "issue_view";
	}
	
	@GetMapping("/create")
	public String create(Model uiModel) {
		return "issue_create";
	}
	
	@PostMapping("/api/issue/login")
	public @ResponseBody ResponseEntity<String> checkLogin(@RequestBody Login login){
//		logger.debug(login); 
		if (login.getUsername().equals("test") && login.getPassword().equals("test")) {
			return new ResponseEntity<String>("POST Response", HttpStatus.OK);
		}
		return new ResponseEntity<String>("POST Response", HttpStatus.UNAUTHORIZED);	
	}
	
	@PostMapping("/issue/create")
	public @ResponseBody ResponseEntity<String> updateIssue(@RequestBody Issue issue) {	
		try {
			logger.debug(new ObjectMapper().writeValueAsString(issue));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		issueService.addNewIssue(issue);
		return new ResponseEntity<String>("POST Response", HttpStatus.OK);	
	}	
	
	
	@PostMapping("/issue/comment/create")
	public @ResponseBody ResponseEntity<String> newComment(@RequestBody Comment comment) {	
		try {
			logger.debug(new ObjectMapper().writeValueAsString(comment));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		issueService.addNewComment(comment);
		return new ResponseEntity<String>("POST Response", HttpStatus.OK);	
	}	
	
}
