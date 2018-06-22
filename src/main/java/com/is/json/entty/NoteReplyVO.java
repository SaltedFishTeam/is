package com.is.json.entty;

import com.is.util.RelativeDateFormat;

public class NoteReplyVO {
	
	private int id;
	private String content;
	private String time;
	private BaseUserVO user;
	private BaseUserVO ruser;
	public BaseUserVO getRuser() {
		return ruser;
	}
	public void setRuser(BaseUserVO ruser) {
		this.ruser = ruser;
	}
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
		this.time = RelativeDateFormat.getTime(time);
	}
	public BaseUserVO getUser() {
		return user;
	}
	public void setUser(BaseUserVO user) {
		this.user = user;
	}
	public NoteReplyVO(int id, String content, String time, BaseUserVO user) {
		this.id = id;
		this.content = content;
		this.time = time;
		this.user = user;
	}
	public NoteReplyVO() {
	}
	public NoteReplyVO(int id, String content, String time,
			int uid, String username, String avatar,
			int ruid, String rusername, String ravatar) {
		this.id = id;
		this.content = content;
		this.time = time;
		this.user = new BaseUserVO(uid, username, avatar);
		this.ruser = new BaseUserVO(ruid, rusername, ravatar);
	}
	
	

}
