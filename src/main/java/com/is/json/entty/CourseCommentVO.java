package com.is.json.entty;

import com.is.entity.TCourse;
import com.is.entity.TUser;

public class CourseCommentVO {

	private Integer courseCommentId;
//	private TUser TUser;
	private Integer userId;
	private String username;
	private String avatar;
	private String account;
	private String commentTime;
	private String commentContent;
	private Float teacherStarLevel;
	private Float courseStarLevel;
	public Integer getCourseCommentId() {
		return courseCommentId;
	}
	public void setCourseCommentId(Integer courseCommentId) {
		this.courseCommentId = courseCommentId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public Float getTeacherStarLevel() {
		return teacherStarLevel;
	}
	public void setTeacherStarLevel(Float teacherStarLevel) {
		this.teacherStarLevel = teacherStarLevel;
	}
	public Float getCourseStarLevel() {
		return courseStarLevel;
	}
	public void setCourseStarLevel(Float courseStarLevel) {
		this.courseStarLevel = courseStarLevel;
	}
	public CourseCommentVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CourseCommentVO(Integer courseCommentId, Integer userId, String username, String avatar, String account, String commentTime,
			String commentContent, Float teacherStarLevel, Float courseStarLevel) {
		super();
		this.courseCommentId = courseCommentId;
		this.userId = userId;
		this.username = username;
		this.account = account;
		this.avatar = avatar;
		this.commentTime = commentTime;
		this.commentContent = commentContent;
		this.teacherStarLevel = teacherStarLevel;
		this.courseStarLevel = courseStarLevel;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	
}
