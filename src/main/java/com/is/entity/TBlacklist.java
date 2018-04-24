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
 * TBlacklist entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_blacklist", catalog = "is")
public class TBlacklist implements java.io.Serializable {

	// Fields

	private Integer uid;
	private TUser TUser;
	private String message;

	// Constructors

	/** default constructor */
	public TBlacklist() {
	}

	/** minimal constructor */
	public TBlacklist(Integer uid, TUser TUser) {
		this.uid = uid;
		this.TUser = TUser;
	}

	/** full constructor */
	public TBlacklist(Integer uid, TUser TUser, String message) {
		this.uid = uid;
		this.TUser = TUser;
		this.message = message;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "uid", unique = true, nullable = false)
	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
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
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);
	}
}