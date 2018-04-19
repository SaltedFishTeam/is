package com.example.entity;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * TTopicreply entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_topicreply", catalog = "is")
public class TTopicreply implements java.io.Serializable {

	// Fields

	private Long topicReplyId;
	private TTopicmessage TTopicmessage;
	private TUser TUser;
	private String time;
	private String content;

	// Constructors

	/** default constructor */
	public TTopicreply() {
	}

	/** minimal constructor */
	public TTopicreply(Long topicReplyId, TTopicmessage TTopicmessage,
			TUser TUser) {
		this.topicReplyId = topicReplyId;
		this.TTopicmessage = TTopicmessage;
		this.TUser = TUser;
	}

	/** full constructor */
	public TTopicreply(Long topicReplyId, TTopicmessage TTopicmessage,
			TUser TUser, String time, String content) {
		this.topicReplyId = topicReplyId;
		this.TTopicmessage = TTopicmessage;
		this.TUser = TUser;
		this.time = time;
		this.content = content;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "topic_reply_id", unique = true, nullable = false)
	public Long getTopicReplyId() {
		return this.topicReplyId;
	}

	public void setTopicReplyId(Long topicReplyId) {
		this.topicReplyId = topicReplyId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "topic_msg_id", nullable = false)
	public TTopicmessage getTTopicmessage() {
		return this.TTopicmessage;
	}

	public void setTTopicmessage(TTopicmessage TTopicmessage) {
		this.TTopicmessage = TTopicmessage;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uid", nullable = false)
	public TUser getTUser() {
		return this.TUser;
	}

	public void setTUser(TUser TUser) {
		this.TUser = TUser;
	}

	@Column(name = "time", length = 100)
	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Column(name = "content", length = 500)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);
	}
}