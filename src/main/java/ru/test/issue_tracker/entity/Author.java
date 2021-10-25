package ru.test.issue_tracker.entity;

/**
 * @author maxim
 * class model for author entity
 */
public final class Author {
	
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
		return "Author [id=" + id + ", name=" + name + "]";
	}
	
}
