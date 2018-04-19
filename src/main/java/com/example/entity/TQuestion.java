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
 * TQuestion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_question", catalog = "is")
public class TQuestion implements java.io.Serializable {

	// Fields

	private Long questionId;
	private TUser TUser;
	private String questionLabel;
	private String answer;
	private Integer rightNum;
	private Integer doNum;
	private Double totalTime;

	// Constructors

	/** default constructor */
	public TQuestion() {
	}

	/** minimal constructor */
	public TQuestion(Long questionId, TUser TUser, Integer rightNum,
			Integer doNum, Double totalTime) {
		this.questionId = questionId;
		this.TUser = TUser;
		this.rightNum = rightNum;
		this.doNum = doNum;
		this.totalTime = totalTime;
	}

	/** full constructor */
	public TQuestion(Long questionId, TUser TUser, String questionLabel,
			String answer, Integer rightNum, Integer doNum, Double totalTime) {
		this.questionId = questionId;
		this.TUser = TUser;
		this.questionLabel = questionLabel;
		this.answer = answer;
		this.rightNum = rightNum;
		this.doNum = doNum;
		this.totalTime = totalTime;
	}

	// Property accessors
	@Id
	@Column(name = "question_id", unique = true, nullable = false)
	public Long getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uid", nullable = false)
	public TUser getTUser() {
		return this.TUser;
	}

	public void setTUser(TUser TUser) {
		this.TUser = TUser;
	}

	@Column(name = "question_label", length = 100)
	public String getQuestionLabel() {
		return this.questionLabel;
	}

	public void setQuestionLabel(String questionLabel) {
		this.questionLabel = questionLabel;
	}

	@Column(name = "answer", length = 50)
	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Column(name = "right_num", nullable = false)
	public Integer getRightNum() {
		return this.rightNum;
	}

	public void setRightNum(Integer rightNum) {
		this.rightNum = rightNum;
	}

	@Column(name = "do_num", nullable = false)
	public Integer getDoNum() {
		return this.doNum;
	}

	public void setDoNum(Integer doNum) {
		this.doNum = doNum;
	}

	@Column(name = "total_time", nullable = false, precision = 22, scale = 0)
	public Double getTotalTime() {
		return this.totalTime;
	}

	public void setTotalTime(Double totalTime) {
		this.totalTime = totalTime;
	}

}