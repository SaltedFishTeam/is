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
 * TStudentwork entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_studentwork", catalog = "is")
public class TStudentwork implements java.io.Serializable {

	// Fields

	private TStudentworkId id;
	private THomework THomework;
	private TUser TUser;
	private String content;
	private String files;
	private Float grade;

	// Constructors

	/** default constructor */
	public TStudentwork() {
	}

	/** minimal constructor */
	public TStudentwork(TStudentworkId id, THomework THomework, TUser TUser,
			String content) {
		this.id = id;
		this.THomework = THomework;
		this.TUser = TUser;
		this.content = content;
	}

	/** full constructor */
	public TStudentwork(TStudentworkId id, THomework THomework, TUser TUser,
			String content, String files, Float grade) {
		this.id = id;
		this.THomework = THomework;
		this.TUser = TUser;
		this.content = content;
		this.files = files;
		this.grade = grade;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "uid", column = @Column(name = "uid", nullable = false)),
			@AttributeOverride(name = "homeworkId", column = @Column(name = "homework_id", nullable = false)) })
	public TStudentworkId getId() {
		return this.id;
	}

	public void setId(TStudentworkId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "homework_id", nullable = false, insertable = false, updatable = false)
	public THomework getTHomework() {
		return this.THomework;
	}

	public void setTHomework(THomework THomework) {
		this.THomework = THomework;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uid", nullable = false, insertable = false, updatable = false)
	public TUser getTUser() {
		return this.TUser;
	}

	public void setTUser(TUser TUser) {
		this.TUser = TUser;
	}

	@Column(name = "content", nullable = false, length = 500)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "files", length = 500)
	public String getFiles() {
		return this.files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	@Column(name = "grade", precision = 12, scale = 0)
	public Float getGrade() {
		return this.grade;
	}

	public void setGrade(Float grade) {
		this.grade = grade;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);
	}
}