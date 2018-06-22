package com.is.json.entty;

import com.is.entity.TUser;

public class SectionReplyVO {

	private int id;
	private String content;
	private String time;
	private int uid;
	private String userAvatar;
	private String username;
	private String account;
	private int rid;
	private String ruserAvatar;
	private String rusername;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUserAvatar() {
		return userAvatar;
	}
	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getRuserAvatar() {
		return ruserAvatar;
	}
	public void setRuserAvatar(String ruserAvatar) {
		this.ruserAvatar = ruserAvatar;
	}
	public String getRusername() {
		return rusername;
	}
	public void setRusername(String rusername) {
		this.rusername = rusername;
	}
	public SectionReplyVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SectionReplyVO(int id, String content, String time, int uid, String userAvatar, String username, String account, int rid,
			String ruserAvatar, String rusername) {
		super();
		this.id = id;
		this.content = content;
		this.time = time;
		this.uid = uid;
		this.userAvatar = userAvatar;
		this.username = username;
		this.account = account;
		this.rid = rid;
		this.ruserAvatar = ruserAvatar;
		this.rusername = rusername;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	
	
	
	
}
