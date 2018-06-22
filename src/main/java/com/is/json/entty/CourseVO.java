package com.is.json.entty;

import java.util.List;

public class CourseVO {
	
	private Integer courseId;
	private String courseName;
	private String courseIntro;
	private Float courseStarLevel;
	private Float courseDiffLevel;
	private String courseLabel;
	private String courseType;
	private String courseLive;
	private Integer courseStatus;
	private String courseImg;
	private int studentNum;
	private List<ChapterVO> tchapters;
	
	public CourseVO(Integer courseId, String courseName, String courseIntro, Float courseStarLevel,
			Float courseDiffLevel, String courseLabel, String courseType, String courseLive, Integer courseStatus
			,int studentNum) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseIntro = courseIntro;
		this.courseStarLevel = courseStarLevel;
		this.courseDiffLevel = courseDiffLevel;
		this.courseLabel = courseLabel;
		this.courseType = courseType;
		this.courseLive = courseLive;
		this.courseStatus = courseStatus;
		this.studentNum = studentNum;
	}
	public CourseVO() {
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseIntro() {
		return courseIntro;
	}
	public void setCourseIntro(String courseIntro) {
		this.courseIntro = courseIntro;
	}
	public Float getCourseStarLevel() {
		return courseStarLevel;
	}
	public void setCourseStarLevel(Float courseStarLevel) {
		this.courseStarLevel = courseStarLevel;
	}
	public Float getCourseDiffLevel() {
		return courseDiffLevel;
	}
	public void setCourseDiffLevel(Float courseDiffLevel) {
		this.courseDiffLevel = courseDiffLevel;
	}
	public String getCourseLabel() {
		return courseLabel;
	}
	public void setCourseLabel(String courseLabel) {
		this.courseLabel = courseLabel;
	}
	public String getCourseType() {
		return courseType;
	}
	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
	public String getCourseLive() {
		return courseLive;
	}
	public void setCourseLive(String courseLive) {
		this.courseLive = courseLive;
	}
	public Integer getCourseStatus() {
		return courseStatus;
	}
	public void setCourseStatus(Integer courseStatus) {
		this.courseStatus = courseStatus;
	}
	public String getCourseImg() {
		return courseImg;
	}
	public void setCourseImg(String courseImg) {
		this.courseImg = courseImg;
	}
	public int getStudentNum() {
		return studentNum;
	}
	public void setStudentNum(int studentNum) {
		this.studentNum = studentNum;
	}
	public List<ChapterVO> getChapters() {
		return tchapters;
	}
	public void setChapters(List<ChapterVO> chapters) {
		this.tchapters = chapters;
	}
	
	
}
