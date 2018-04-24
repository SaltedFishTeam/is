package com.is.entity;
// default package

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * TActionMessage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_action_message", catalog = "is")
public class TActionMessage implements java.io.Serializable {

	// Fields

	private Integer actionMsgId;
	private TUser TUser;
	private String time;
	private String content;
	private Set<TActionReply> TActionReplies = new HashSet<TActionReply>(0);

	// Constructors

	/** default constructor */
	public TActionMessage() {
	}

	/** minimal constructor */
	public TActionMessage(Integer actionMsgId, TUser TUser) {
		this.actionMsgId = actionMsgId;
		this.TUser = TUser;
	}

	/** full constructor */
	public TActionMessage(Integer actionMsgId, TUser TUser, String time,
			String content, Set<TActionReply> TActionReplies) {
		this.actionMsgId = actionMsgId;
		this.TUser = TUser;
		this.time = time;
		this.content = content;
		this.TActionReplies = TActionReplies;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "action_msg_id", unique = true, nullable = false)
	public Integer getActionMsgId() {
		return this.actionMsgId;
	}

	public void setActionMsgId(Integer actionMsgId) {
		this.actionMsgId = actionMsgId;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TActionMessage")
	public Set<TActionReply> getTActionReplies() {
		return this.TActionReplies;
	}

	public void setTActionReplies(Set<TActionReply> TActionReplies) {
		this.TActionReplies = TActionReplies;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);
	}
}