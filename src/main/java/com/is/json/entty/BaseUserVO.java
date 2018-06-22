package com.is.json.entty;

public class BaseUserVO {

	private int id;
	private String username;
	private String avatar;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public BaseUserVO(int id, String username, String avatar) {
		super();
		this.id = id;
		this.username = username;
		this.avatar = avatar;
	}
	
}
