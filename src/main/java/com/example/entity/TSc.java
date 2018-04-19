package com.example.entity;
// default package

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TSc entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_sc", catalog = "is")
public class TSc implements java.io.Serializable {

	// Fields

	private TScId id;
	private TCourse TCourse;
	private TUser TUser;
	private Float grade;
	private Integer arriveTime;
	private Integer speakTime;

	// Constructors

	/** default constructor */
	public TSc() {
	}

	/** minimal constructor */
	public TSc(TScId id, TCourse TCourse, TUser TUser, Integer arriveTime,
			Integer speakTime) {
		this.id = id;
		this.TCourse = TCourse;
		this.TUser = TUser;
		this.arriveTime = arriveTime;
		this.speakTime = speakTime;
	}

	/** full constructor */
	public TSc(TScId id, TCourse TCourse, TUser TUser, Float grade,
			Integer arriveTime, Integer speakTime) {
		this.id = id;
		this.TCourse = TCourse;
		this.TUser = TUser;
		this.grade = grade;
		this.arriveTime = arriveTime;
		this.speakTime = speakTime;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "uid", column = @Column(name = "uid", nullable = false)),
			@AttributeOverride(name = "courseId", column = @Column(name = "course_id", nullable = false)) })
	public TScId getId() {
		return this.id;
	}

	public void setId(TScId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id", nullable = false, insertable = false, updatable = false)
	public TCourse getTCourse() {
		return this.TCourse;
	}

	public void setTCourse(TCourse TCourse) {
		this.TCourse = TCourse;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uid", nullable = false, insertable = false, updatable = false)
	public TUser getTUser() {
		return this.TUser;
	}

	public void setTUser(TUser TUser) {
		this.TUser = TUser;
	}

	@Column(name = "grade", precision = 12, scale = 0)
	public Float getGrade() {
		return this.grade;
	}

	public void setGrade(Float grade) {
		this.grade = grade;
	}

	@Column(name = "arrive_time", nullable = false)
	public Integer getArriveTime() {
		return this.arriveTime;
	}

	public void setArriveTime(Integer arriveTime) {
		this.arriveTime = arriveTime;
	}

	@Column(name = "speak_time", nullable = false)
	public Integer getSpeakTime() {
		return this.speakTime;
	}

	public void setSpeakTime(Integer speakTime) {
		this.speakTime = speakTime;
	}

}