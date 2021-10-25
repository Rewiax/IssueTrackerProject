package ru.test.issue_tracker.entity;

import java.sql.Timestamp;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author maxim
 * class model for issue entity
 */
public final class Issue {
	
	private int id;
	
	private String name;
	
	private String description;
		
	@JsonProperty("startDate")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	private Timestamp start_date;
	
	@JsonIgnore
	private int author_id;
	
	@JsonIgnore
	private int status_id;
	
	private Author author;
	
	private Status status;
	
	private Set<Comment> comments;
	

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	

	public Timestamp getStartDate() {
		return start_date;
	}	

	public int getAuthorId() {
		return author_id;
	}

	public int getStatusId() {
		return status_id;
	}
		
	public void setAuthorId(int author_id) {
		this.author_id = author_id;
	}

	public void setStatusId(int status_id) {
		this.status_id = status_id;
	}

	public Author getAuthor() {
		return author;
	}

	public Status getStatus() {
		return status;
	}

	public Set<Comment> getComments() {
		return comments;
	}
	
	public void setAuthor(Author author) {
		this.author = author;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Issue [id=" + id + ", name=" + name + ", description=" + description + ", start_date=" + start_date
				+ ", author_id=" + author_id + ", status_id=" + status_id + ", author=" + author + ", status=" + status
				+ ", comments=" + comments + "]";
	}

	
}
