package ru.test.issue_tracker.entity;

public class Login {
	
	private String username;
	
	private String password;

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "Login [username=" + username + ", password=" + password + "]";
	}
	
}
