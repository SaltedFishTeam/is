package com.is.json.entty;

import java.text.SimpleDateFormat;
import java.util.List;

import com.is.util.RelativeDateFormat;

public class NoteMessageVO {

	private int id;
	private String content;
	private String time;
	private BaseUserVO user;
	private List<NoteReplyVO> reply;
	private String url;
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
	public BaseUserVO getUser() {
		return user;
	}
	public void setUser(BaseUserVO user) {
		this.user = user;
	}
	public NoteMessageVO(int id, String content, String time, BaseUserVO user) {
		this.id = id;
		this.content = content;
		this.time = time;
		this.user = user;
	}
	public NoteMessageVO(int id, String content, String time,
			Integer uid, String username, String avatar) {
		this.id = id;
		this.content = content;
		this.time = time;
		this.user = new BaseUserVO(uid, username, avatar);
	}
	public NoteMessageVO() {
	}
	public List<NoteReplyVO> getReply() {
		return reply;
	}
	public void setReply(List<NoteReplyVO> reply) {
		this.reply = reply;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
