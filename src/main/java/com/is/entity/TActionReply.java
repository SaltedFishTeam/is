package com.is.entity;
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
 * TActionReply entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_action_reply", catalog = "is")
public class TActionReply implements java.io.Serializable {

	// Fields

	private Integer actionReplyId;
	private TActionMessage TActionMessage;
	private TUser TUser;
	private String time;
	private String content;

	// Constructors

	/** default constructor */
	public TActionReply() {
	}

	/** minimal constructor */
	public TActionReply(Integer actionReplyId, TActionMessage TActionMessage,
			TUser TUser) {
		this.actionReplyId = actionReplyId;
		this.TActionMessage = TActionMessage;
		this.TUser = TUser;
	}

	/** full constructor */
	public TActionReply(Integer actionReplyId, TActionMessage TActionMessage,
			TUser TUser, String time, String content) {
		this.actionReplyId = actionReplyId;
		this.TActionMessage = TActionMessage;
		this.TUser = TUser;
		this.time = time;
		this.content = content;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "action_reply_id", unique = true, nullable = false)
	public Integer getActionReplyId() {
		return this.actionReplyId;
	}

	public void setActionReplyId(Integer actionReplyId) {
		this.actionReplyId = actionReplyId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "action_msg_id", nullable = false)
	public TActionMessage getTActionMessage() {
		return this.TActionMessage;
	}

	public void setTActionMessage(TActionMessage TActionMessage) {
		this.TActionMessage = TActionMessage;
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