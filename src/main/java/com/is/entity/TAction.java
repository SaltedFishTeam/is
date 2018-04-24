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
 * TAction entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_action", catalog = "is")
public class TAction implements java.io.Serializable {

	// Fields

	private Integer actionId;
	private TUser TUser;
	private String title;
	private String time;
	private Integer max;
	private Integer attendNum;

	// Constructors

	/** default constructor */
	public TAction() {
	}

	/** minimal constructor */
	public TAction(Integer actionId, TUser TUser, Integer max, Integer attendNum) {
		this.actionId = actionId;
		this.TUser = TUser;
		this.max = max;
		this.attendNum = attendNum;
	}

	/** full constructor */
	public TAction(Integer actionId, TUser TUser, String title, String time,
			Integer max, Integer attendNum) {
		this.actionId = actionId;
		this.TUser = TUser;
		this.title = title;
		this.time = time;
		this.max = max;
		this.attendNum = attendNum;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "action_id", unique = true, nullable = false)
	public Integer getActionId() {
		return this.actionId;
	}

	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uid", nullable = false)
	public TUser getTUser() {
		return this.TUser;
	}

	public void setTUser(TUser TUser) {
		this.TUser = TUser;
	}

	@Column(name = "title", length = 100)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "time", length = 100)
	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Column(name = "max", nullable = false)
	public Integer getMax() {
		return this.max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	@Column(name = "attend_num", nullable = false)
	public Integer getAttendNum() {
		return this.attendNum;
	}

	public void setAttendNum(Integer attendNum) {
		this.attendNum = attendNum;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);
	}
}