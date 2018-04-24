package com.is.jsonbean;

public class LoginStatus extends Status {
	
	private Integer uid;
	private String username;
	private String account;
	private String phone;
	private Integer role;
	private Double credit;
	private String intro;
	private String friends;
	
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	public Double getCredit() {
		return credit;
	}
	public void setCredit(Double credit) {
		this.credit = credit;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getFriends() {
		return friends;
	}
	public void setFriends(String friends) {
		this.friends = friends;
	}
	public LoginStatus(String status, int code, String msg, Integer uid, String username, String account,
			String phone, Integer role, Double credit, String intro, String friends) {
		super(status, code, msg);
		this.uid = uid;
		this.username = username;
		this.account = account;
		this.phone = phone;
		this.role = role;
		this.credit = credit;
		this.intro = intro;
		this.friends = friends;
	}
	
	
	
}
