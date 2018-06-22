package com.is.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_note_reply", catalog = "is")
public class NoteReply {
	
	private Integer noteReplyId;
	private String content;
	private	String time;
	private TUser TUser;
	private TNodeMessage TNodeMessage;
	private TUser ruser;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "note_reply_id", unique = true, nullable = false)
	public Integer getNoteReplyId() {
		return noteReplyId;
	}
	public void setNoteReplyId(Integer noteReplyId) {
		this.noteReplyId = noteReplyId;
	}
	@Column(name = "content")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Column(name = "time")
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="rid",nullable=false)
	public TUser getRuser() {
		return ruser;
	}
	public void setRuser(TUser ruser) {
		this.ruser = ruser;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="uid",nullable=false)
	public TUser getTUser() {
		return this.TUser;
	}
	public void setTUser(TUser tUser) {
		this.TUser = tUser;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="nmid",nullable=false)
	public TNodeMessage getTNodeMessage() {
		return TNodeMessage;
	}
	public void setTNodeMessage(TNodeMessage tNodeMessage) {
		TNodeMessage = tNodeMessage;
	}

	
}
