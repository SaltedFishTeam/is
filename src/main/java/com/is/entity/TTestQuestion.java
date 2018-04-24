package com.is.entity;
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

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * TTestQuestion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_test_question", catalog = "is")
public class TTestQuestion implements java.io.Serializable {

	// Fields

	private TTestQuestionId id;
	private TTest TTest;
	private String result;
	private Float useTime;

	// Constructors

	/** default constructor */
	public TTestQuestion() {
	}

	/** minimal constructor */
	public TTestQuestion(TTestQuestionId id, TTest TTest, Float useTime) {
		this.id = id;
		this.TTest = TTest;
		this.useTime = useTime;
	}

	/** full constructor */
	public TTestQuestion(TTestQuestionId id, TTest TTest, String result,
			Float useTime) {
		this.id = id;
		this.TTest = TTest;
		this.result = result;
		this.useTime = useTime;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "questionId", column = @Column(name = "question_id", nullable = false)),
			@AttributeOverride(name = "testId", column = @Column(name = "test_id", nullable = false)) })
	public TTestQuestionId getId() {
		return this.id;
	}

	public void setId(TTestQuestionId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "test_id", nullable = false, insertable = false, updatable = false)
	public TTest getTTest() {
		return this.TTest;
	}

	public void setTTest(TTest TTest) {
		this.TTest = TTest;
	}

	@Column(name = "result", length = 100)
	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Column(name = "use_time", nullable = false, precision = 12, scale = 0)
	public Float getUseTime() {
		return this.useTime;
	}

	public void setUseTime(Float useTime) {
		this.useTime = useTime;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);
	}
}