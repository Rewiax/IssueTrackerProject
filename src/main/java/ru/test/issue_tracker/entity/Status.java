package ru.test.issue_tracker.entity;

/**
 * @author maxim
 * class model for status entity
 */
public final class Status {
	
	private int id;
	
	private String name;


	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Status [id=" + id + ", name=" + name + "]";
	}
	
	
}
