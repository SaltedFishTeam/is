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
 * TTest entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_test", catalog = "is")
public class TTest implements java.io.Serializable {

	// Fields

	private Integer testId;
	private TUser TUser;
	private String time;
	private Float grade;
	private Set<TTestQuestion> TTestQuestions = new HashSet<TTestQuestion>(0);

	// Constructors

	/** default constructor */
	public TTest() {
	}

	/** minimal constructor */
	public TTest(Integer testId, TUser TUser, Float grade) {
		this.testId = testId;
		this.TUser = TUser;
		this.grade = grade;
	}

	/** full constructor */
	public TTest(Integer testId, TUser TUser, String time, Float grade,
			Set<TTestQuestion> TTestQuestions) {
		this.testId = testId;
		this.TUser = TUser;
		this.time = time;
		this.grade = grade;
		this.TTestQuestions = TTestQuestions;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "test_id", unique = true, nullable = false)
	public Integer getTestId() {
		return this.testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
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

	@Column(name = "grade", nullable = false, precision = 12, scale = 0)
	public Float getGrade() {
		return this.grade;
	}

	public void setGrade(Float grade) {
		this.grade = grade;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TTest")
	public Set<TTestQuestion> getTTestQuestions() {
		return this.TTestQuestions;
	}

	public void setTTestQuestions(Set<TTestQuestion> TTestQuestions) {
		this.TTestQuestions = TTestQuestions;
	}
	
}