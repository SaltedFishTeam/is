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
 * TStudent entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_student", catalog = "is")
public class TStudent implements java.io.Serializable {

	// Fields

	private Integer studentId;
	private TUser TUser;
	private Float studyLevel;
	private String studyWay;

	// Constructors

	/** default constructor */
	public TStudent() {
	}

	/** minimal constructor */
	public TStudent(Integer studentId, TUser TUser, Float studyLevel) {
		this.studentId = studentId;
		this.TUser = TUser;
		this.studyLevel = studyLevel;
	}

	/** full constructor */
	public TStudent(Integer studentId, TUser TUser, Float studyLevel,
			String studyWay) {
		this.studentId = studentId;
		this.TUser = TUser;
		this.studyLevel = studyLevel;
		this.studyWay = studyWay;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "student_id", unique = true, nullable = false)
	public Integer getStudentId() {
		return this.studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id", unique = true, nullable = false, insertable = false, updatable = false)
	public TUser getTUser() {
		return this.TUser;
	}

	public void setTUser(TUser TUser) {
		this.TUser = TUser;
	}

	@Column(name = "study_level", nullable = false, precision = 12, scale = 0)
	public Float getStudyLevel() {
		return this.studyLevel;
	}

	public void setStudyLevel(Float studyLevel) {
		this.studyLevel = studyLevel;
	}

	@Column(name = "study_way", length = 100)
	public String getStudyWay() {
		return this.studyWay;
	}

	public void setStudyWay(String studyWay) {
		this.studyWay = studyWay;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);
	}
}