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
 * TBlacklist entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_blacklist", catalog = "is")
public class TBlacklist implements java.io.Serializable {

	// Fields

	private Long uid;
	private TUser TUser;
	private String message;

	// Constructors

	/** default constructor */
	public TBlacklist() {
	}

	/** minimal constructor */
	public TBlacklist(Long uid, TUser TUser) {
		this.uid = uid;
		this.TUser = TUser;
	}

	/** full constructor */
	public TBlacklist(Long uid, TUser TUser, String message) {
		this.uid = uid;
		this.TUser = TUser;
		this.message = message;
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

	@Column(name = "message", length = 500)
	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}