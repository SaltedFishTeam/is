package com.is.entity;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * TTestQuestionId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class TTestQuestionId implements java.io.Serializable {

	// Fields

	private Integer questionId;
	private Integer testId;

	// Constructors

	/** default constructor */
	public TTestQuestionId() {
	}

	/** full constructor */
	public TTestQuestionId(Integer questionId, Integer testId) {
		this.questionId = questionId;
		this.testId = testId;
	}

	// Property accessors

	@Column(name = "question_id", nullable = false)
	public Integer getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	@Column(name = "test_id", nullable = false)
	public Integer getTestId() {
		return this.testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TTestQuestionId))
			return false;
		TTestQuestionId castOther = (TTestQuestionId) other;

		return ((this.getQuestionId() == castOther.getQuestionId()) || (this
				.getQuestionId() != null && castOther.getQuestionId() != null && this
				.getQuestionId().equals(castOther.getQuestionId())))
				&& ((this.getTestId() == castOther.getTestId()) || (this
						.getTestId() != null && castOther.getTestId() != null && this
						.getTestId().equals(castOther.getTestId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getQuestionId() == null ? 0 : this.getQuestionId()
						.hashCode());
		result = 37 * result
				+ (getTestId() == null ? 0 : this.getTestId().hashCode());
		return result;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);
	}
}