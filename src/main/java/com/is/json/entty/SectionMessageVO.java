package com.is.json.entty;

import java.util.List;

public class SectionMessageVO {
	
	private int id;
	private String content;
	private String time;
	private String avatar;
	private String username;
	private String account;
	private int uid;
	private List<SectionReplyVO> replys;
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
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<SectionReplyVO> getReplys() {
		return replys;
	}
	public void setReplys(List<SectionReplyVO> replys) {
		this.replys = replys;
	}
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public SectionMessageVO(int id, String content, String time, String avatar, String username,
			List<SectionReplyVO> replys) {
		super();
		this.id = id;
		this.content = content;
		this.time = time;
		this.avatar = avatar;
		this.username = username;
		this.replys = replys;
	}
	
	public SectionMessageVO(int id, String content, String time, int uid, String avatar, String username, String account) {
		super();
		this.id = id;
		this.content = content;
		this.time = time;
		this.uid = uid;
		this.avatar = avatar;
		this.username = username;
		this.account = account;
	}
	
	public SectionMessageVO() {
	}
	
	
}
