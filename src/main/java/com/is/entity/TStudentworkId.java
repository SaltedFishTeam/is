package com.is.entity;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * TStudentworkId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class TStudentworkId implements java.io.Serializable {

	// Fields

	private Integer uid;
	private Integer homeworkId;

	// Constructors

	/** default constructor */
	public TStudentworkId() {
	}

	/** full constructor */
	public TStudentworkId(Integer uid, Integer homeworkId) {
		this.uid = uid;
		this.homeworkId = homeworkId;
	}

	// Property accessors

	@Column(name = "uid", nullable = false)
	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	@Column(name = "homework_id", nullable = false)
	public Integer getHomeworkId() {
		return this.homeworkId;
	}

	public void setHomeworkId(Integer homeworkId) {
		this.homeworkId = homeworkId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TStudentworkId))
			return false;
		TStudentworkId castOther = (TStudentworkId) other;

		return ((this.getUid() == castOther.getUid()) || (this.getUid() != null
				&& castOther.getUid() != null && this.getUid().equals(
				castOther.getUid())))
				&& ((this.getHomeworkId() == castOther.getHomeworkId()) || (this
						.getHomeworkId() != null
						&& castOther.getHomeworkId() != null && this
						.getHomeworkId().equals(castOther.getHomeworkId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUid() == null ? 0 : this.getUid().hashCode());
		result = 37
				* result
				+ (getHomeworkId() == null ? 0 : this.getHomeworkId()
						.hashCode());
		return result;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);
	}
}