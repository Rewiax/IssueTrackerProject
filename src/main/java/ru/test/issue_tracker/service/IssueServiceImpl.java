package ru.test.issue_tracker.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.test.issue_tracker.entity.Author;
import ru.test.issue_tracker.entity.Comment;
import ru.test.issue_tracker.entity.Issue;
import ru.test.issue_tracker.entity.Status;
import ru.test.issue_tracker.mapper.AuthorMapper;
import ru.test.issue_tracker.mapper.CommentMapper;
import ru.test.issue_tracker.mapper.IssueMapper;
import ru.test.issue_tracker.mapper.StatusMapper;

/**
 * @author maxim
 * service for work with database
 */
@Service
class IssueServiceImpl implements IssueService{
	
	private final static Logger logger = Logger.getLogger(IssueServiceImpl.class);
	
	@Autowired
	private IssueMapper issueMapper;
	
	@Autowired
	private AuthorMapper authorMapper;
	
	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private StatusMapper statusMapper;

	@Override
	public List<Issue> getIssueList() {
		List<Issue> issues = issueMapper.findAll();
		List<Comment> comments = commentMapper.findAll();
		
		for (Issue issue : issues) {
			Set<Comment> commentsForIssue = new HashSet<Comment>();
			Author author = authorMapper.findById(issue.getAuthorId()); 
			issue.setAuthor(author);
			
			Status status = statusMapper.findById(issue.getStatusId()); 
			issue.setStatus(status);
			
			for (Comment comment : comments) {
				if (comment.getIssueId() == issue.getId()) {
					commentsForIssue.add(comment);
					
					Author authorComment = authorMapper.findById(comment.getAuthorId()); 
					comment.setAuthor(authorComment);
					
					Status statusComment = statusMapper.findById(comment.getStatusId()); 
					comment.setStatus(statusComment);
				}
			}
			
			issue.setComments(commentsForIssue);
		}		
		
		return issues;
	}
	
	@Override
	public Issue getIssue(int id) {
		Issue issue = issueMapper.findById(id);
		List<Comment> comments = commentMapper.findAll();
		
		Set<Comment> commentsForIssue = new HashSet<Comment>();
		Author author = authorMapper.findById(issue.getAuthorId()); 
		issue.setAuthor(author);
		
		Status status = statusMapper.findById(issue.getStatusId()); 
		issue.setStatus(status);
		
		for (Comment comment : comments) {
			if (comment.getIssueId() == issue.getId()) {
				commentsForIssue.add(comment);
				
				Author authorComment = authorMapper.findById(comment.getAuthorId()); 
				comment.setAuthor(authorComment);
				
				Status statusComment = statusMapper.findById(comment.getStatusId()); 
				comment.setStatus(statusComment);
			}
		}
		
		issue.setComments(commentsForIssue);		
		
		return issue;
	}
	
	@Override
	public List<Status> getStatusList() {
		return statusMapper.findAll();		
	}
	
	@Override
	public void addNewComment(Comment comment) {
		Author commentAuthor = comment.getAuthor();
		Optional<Author> authorOpt = getAuthor(commentAuthor.getName());
		if (authorOpt.isPresent()) {			
			comment.setAuthorId(authorOpt.get().getId());
		} else {
			authorMapper.insertAuthor(commentAuthor);
			comment.setAuthorId(commentAuthor.getId());
		}
		
		if (comment.getStatusId() <=0) {
			Status commentStatus = comment.getStatus();
			Optional<Status> statusOpt = getStatus(commentStatus.getName());
			if (statusOpt.isPresent()) {			
				comment.setStatusId(statusOpt.get().getId());
			}
		}
		
		logger.debug(comment);
		
		commentMapper.insertComment(comment);		
	}
	
	@Override
	public void addNewIssue(Issue issue) {
		Author issueAuthor = issue.getAuthor();
		Optional<Author> authorOpt = getAuthor(issueAuthor.getName());
		if (authorOpt.isPresent()) {			
			issue.setAuthorId(authorOpt.get().getId());
		} else {
			authorMapper.insertAuthor(issueAuthor);
			issue.setAuthorId(issueAuthor.getId());
		}
		
		Status issueStatus = issue.getStatus();
		Optional<Status> statusOpt = getStatus(issueStatus.getName());
		if (statusOpt.isPresent()) {			
			issue.setStatusId(statusOpt.get().getId());
		}
		
		logger.debug(issue);
		
		issueMapper.insertIssue(issue);
	}
	
	private Optional<Author> getAuthor(String name) {
		return authorMapper.findByName(name);
	}
	
	private Optional<Status> getStatus(String name) {
		return statusMapper.findByName(name);
	}

}
