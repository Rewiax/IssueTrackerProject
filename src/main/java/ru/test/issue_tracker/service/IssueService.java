package ru.test.issue_tracker.service;

import java.util.List;

import ru.test.issue_tracker.entity.Comment;
import ru.test.issue_tracker.entity.Issue;
import ru.test.issue_tracker.entity.Status;

public interface IssueService {

	List<Issue> getIssueList();

	Issue getIssue(int id);

	List<Status> getStatusList();

	void addNewComment(Comment comment);

	void addNewIssue(Issue issue);
}
