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
@Table(name = "t_section_reply")
public class TSectionReply {

	private Integer id;
	private String content;
	private String time;
	private TUser user;
	private TUser reply;
	private TSectionMessage sectionMessage;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "section_reply_id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "content", nullable = false)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Column(name = "time", nullable = false)
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uid", nullable = false)
	public TUser getUser() {
		return user;
	}
	public void setUser(TUser user) {
		this.user = user;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rid", nullable = false)
	public TUser getReply() {
		return reply;
	}
	public void setReply(TUser reply) {
		this.reply = reply;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "section_msg_id", nullable = false)
	public TSectionMessage getSectionMessage() {
		return sectionMessage;
	}
	public void setSectionMessage(TSectionMessage sectionMessage) {
		this.sectionMessage = sectionMessage;
	}
	
}
