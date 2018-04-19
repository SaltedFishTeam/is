package com.example.entity;
// default package

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TTopicmessage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_topicmessage", catalog = "is")
public class TTopicmessage implements java.io.Serializable {

	// Fields

	private Long topicMsgId;
	private TTopic TTopic;
	private TUser TUser;
	private Boolean staAnswer;
	private String time;
	private String msg;
	private Set<TTopicreply> TTopicreplies = new HashSet<TTopicreply>(0);

	// Constructors

	/** default constructor */
	public TTopicmessage() {
	}

	/** minimal constructor */
	public TTopicmessage(Long topicMsgId, TTopic TTopic, TUser TUser) {
		this.topicMsgId = topicMsgId;
		this.TTopic = TTopic;
		this.TUser = TUser;
	}

	/** full constructor */
	public TTopicmessage(Long topicMsgId, TTopic TTopic, TUser TUser,
			Boolean staAnswer, String time, String msg,
			Set<TTopicreply> TTopicreplies) {
		this.topicMsgId = topicMsgId;
		this.TTopic = TTopic;
		this.TUser = TUser;
		this.staAnswer = staAnswer;
		this.time = time;
		this.msg = msg;
		this.TTopicreplies = TTopicreplies;
	}

	// Property accessors
	@Id
	@Column(name = "topic_msg_id", unique = true, nullable = false)
	public Long getTopicMsgId() {
		return this.topicMsgId;
	}

	public void setTopicMsgId(Long topicMsgId) {
		this.topicMsgId = topicMsgId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "topic_id", nullable = false)
	public TTopic getTTopic() {
		return this.TTopic;
	}

	public void setTTopic(TTopic TTopic) {
		this.TTopic = TTopic;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uid", nullable = false)
	public TUser getTUser() {
		return this.TUser;
	}

	public void setTUser(TUser TUser) {
		this.TUser = TUser;
	}

	@Column(name = "sta_answer")
	public Boolean getStaAnswer() {
		return this.staAnswer;
	}

	public void setStaAnswer(Boolean staAnswer) {
		this.staAnswer = staAnswer;
	}

	@Column(name = "time", length = 100)
	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Column(name = "msg", length = 500)
	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TTopicmessage")
	public Set<TTopicreply> getTTopicreplies() {
		return this.TTopicreplies;
	}

	public void setTTopicreplies(Set<TTopicreply> TTopicreplies) {
		this.TTopicreplies = TTopicreplies;
	}

}