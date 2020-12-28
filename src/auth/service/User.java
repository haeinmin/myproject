package auth.service;

public class User {
	private String userId;
	private String name;
	
	public String getUserId() {
		return userId;
	}
	public String getName() {
		return name;
	}
	public User(String userId, String name) {
		super();
		this.userId = userId;
		this.name = name;
	}
	
	
}
