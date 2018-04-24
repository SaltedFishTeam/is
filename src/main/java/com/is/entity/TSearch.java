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
 * TSearch entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_search", catalog = "is")
public class TSearch implements java.io.Serializable {

	// Fields

	private Integer searchId;
	private TUser TUser;
	private String content;
	private String time;

	// Constructors

	/** default constructor */
	public TSearch() {
	}

	/** minimal constructor */
	public TSearch(Integer searchId, TUser TUser) {
		this.searchId = searchId;
		this.TUser = TUser;
	}

	/** full constructor */
	public TSearch(Integer searchId, TUser TUser, String content, String time) {
		this.searchId = searchId;
		this.TUser = TUser;
		this.content = content;
		this.time = time;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "search_id", unique = true, nullable = false)
	public Integer getSearchId() {
		return this.searchId;
	}

	public void setSearchId(Integer searchId) {
		this.searchId = searchId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uid", nullable = false)
	public TUser getTUser() {
		return this.TUser;
	}

	public void setTUser(TUser TUser) {
		this.TUser = TUser;
	}

	@Column(name = "content", length = 100)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "time", length = 100)
	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);
	}
}