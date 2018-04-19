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
 * TCoursecomment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_coursecomment", catalog = "is")
public class TCoursecomment implements java.io.Serializable {

	// Fields

	private Long courseCommentId;
	private TCourse TCourse;
	private TUser TUser;
	private String commentTime;
	private String commentContent;
	private Float teacherStarLevel;
	private Float courseStarLevel;

	// Constructors

	/** default constructor */
	public TCoursecomment() {
	}

	/** minimal constructor */
	public TCoursecomment(Long courseCommentId, TCourse TCourse, TUser TUser,
			Float teacherStarLevel, Float courseStarLevel) {
		this.courseCommentId = courseCommentId;
		this.TCourse = TCourse;
		this.TUser = TUser;
		this.teacherStarLevel = teacherStarLevel;
		this.courseStarLevel = courseStarLevel;
	}

	/** full constructor */
	public TCoursecomment(Long courseCommentId, TCourse TCourse, TUser TUser,
			String commentTime, String commentContent, Float teacherStarLevel,
			Float courseStarLevel) {
		this.courseCommentId = courseCommentId;
		this.TCourse = TCourse;
		this.TUser = TUser;
		this.commentTime = commentTime;
		this.commentContent = commentContent;
		this.teacherStarLevel = teacherStarLevel;
		this.courseStarLevel = courseStarLevel;
	}

	// Property accessors
	@Id
	@Column(name = "courseComment_id", unique = true, nullable = false)
	public Long getCourseCommentId() {
		return this.courseCommentId;
	}

	public void setCourseCommentId(Long courseCommentId) {
		this.courseCommentId = courseCommentId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id", nullable = false)
	public TCourse getTCourse() {
		return this.TCourse;
	}

	public void setTCourse(TCourse TCourse) {
		this.TCourse = TCourse;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uid", nullable = false)
	public TUser getTUser() {
		return this.TUser;
	}

	public void setTUser(TUser TUser) {
		this.TUser = TUser;
	}

	@Column(name = "comment_time", length = 100)
	public String getCommentTime() {
		return this.commentTime;
	}

	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}

	@Column(name = "comment_content", length = 500)
	public String getCommentContent() {
		return this.commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	@Column(name = "teacher_star_level", nullable = false, precision = 12, scale = 0)
	public Float getTeacherStarLevel() {
		return this.teacherStarLevel;
	}

	public void setTeacherStarLevel(Float teacherStarLevel) {
		this.teacherStarLevel = teacherStarLevel;
	}

	@Column(name = "course_star_level", nullable = false, precision = 12, scale = 0)
	public Float getCourseStarLevel() {
		return this.courseStarLevel;
	}

	public void setCourseStarLevel(Float courseStarLevel) {
		this.courseStarLevel = courseStarLevel;
	}

}