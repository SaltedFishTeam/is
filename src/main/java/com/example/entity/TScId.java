package com.example.entity;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TScId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class TScId implements java.io.Serializable {

	// Fields

	private Long uid;
	private Long courseId;

	// Constructors

	/** default constructor */
	public TScId() {
	}

	/** full constructor */
	public TScId(Long uid, Long courseId) {
		this.uid = uid;
		this.courseId = courseId;
	}

	// Property accessors

	@Column(name = "uid", nullable = false)
	public Long getUid() {
		return this.uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	@Column(name = "course_id", nullable = false)
	public Long getCourseId() {
		return this.courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TScId))
			return false;
		TScId castOther = (TScId) other;

		return ((this.getUid() == castOther.getUid()) || (this.getUid() != null
				&& castOther.getUid() != null && this.getUid().equals(
				castOther.getUid())))
				&& ((this.getCourseId() == castOther.getCourseId()) || (this
						.getCourseId() != null
						&& castOther.getCourseId() != null && this
						.getCourseId().equals(castOther.getCourseId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUid() == null ? 0 : this.getUid().hashCode());
		result = 37 * result
				+ (getCourseId() == null ? 0 : this.getCourseId().hashCode());
		return result;
	}

}