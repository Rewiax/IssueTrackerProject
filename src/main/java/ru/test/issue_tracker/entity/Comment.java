package ru.test.issue_tracker.entity;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author maxim
 * class model for issue comments entity
 */
public final class Comment {
	
	private int id;
	
	@JsonProperty("updateTime")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	private Timestamp update_time;
	
	@JsonProperty("commentText")
	private String comment_text;
	
	@JsonProperty("issueId")
	private int issue_id;
	
	@JsonIgnore
	private int author_id;
	
	@JsonProperty("statusId")
	private int status_id;
	
	private Author author;
	
	private Status status;

	public int getId() {
		return id;
	}
	
	public Timestamp getUpdateTime() {
		return update_time;
	}

	public String getCommentText() {
		return comment_text;
	}
	
	public int getIssueId() {
		return issue_id;
	}

	public int getAuthorId() {
		return author_id;
	}

	public int getStatusId() {
		return status_id;
	}

	public Author getAuthor() {
		return author;
	}

	public Status getStatus() {
		return status;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setAuthorId(int author_id) {
		this.author_id = author_id;
	}

	public void setStatusId(int status_id) {
		this.status_id = status_id;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", updateTime=" + update_time + ", comment_text=" + comment_text + ", issue_id="
				+ issue_id + ", author_id=" + author_id + ", status_id=" + status_id + ", author="
				+ author + "]";
	}
}
