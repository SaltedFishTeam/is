package com.example.entity;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TPlan entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_plan", catalog = "is")
public class TPlan implements java.io.Serializable {

	// Fields

	private Long uid;
	private TUser TUser;
	private String startTime;
	private String content;
	private String endTime;
	private Boolean statuc;

	// Constructors

	/** default constructor */
	public TPlan() {
	}

	/** minimal constructor */
	public TPlan(Long uid, TUser TUser) {
		this.uid = uid;
		this.TUser = TUser;
	}

	/** full constructor */
	public TPlan(Long uid, TUser TUser, String startTime, String content,
			String endTime, Boolean statuc) {
		this.uid = uid;
		this.TUser = TUser;
		this.startTime = startTime;
		this.content = content;
		this.endTime = endTime;
		this.statuc = statuc;
	}

	// Property accessors
	@Id
	@Column(name = "uid", unique = true, nullable = false)
	public Long getUid() {
		return this.uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uid", unique = true, nullable = false, insertable = false, updatable = false)
	public TUser getTUser() {
		return this.TUser;
	}

	public void setTUser(TUser TUser) {
		this.TUser = TUser;
	}

	@Column(name = "start_time", length = 100)
	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Column(name = "content", length = 500)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "end_time", length = 100)
	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Column(name = "statuc")
	public Boolean getStatuc() {
		return this.statuc;
	}

	public void setStatuc(Boolean statuc) {
		this.statuc = statuc;
	}

}