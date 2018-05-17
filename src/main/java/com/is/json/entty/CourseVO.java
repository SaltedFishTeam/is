package com.is.json.entty;

import java.util.List;

public class CourseVO {
	
	private Integer courseId;
	private String courseName;
	private String courseIntro;
	private Float courseStarLevel;
	private Float courseDiffLevel;
	private String[] courseLabels;
	private String courseType;
	private String courseLive;
	private Boolean courseStatus;
	private String courseImg;
	private int studentNum;
	private List<ChapterVO> chapters;
	public List<ChapterVO> getChapters() {
		return chapters;
	}
	public void setChapters(List<ChapterVO> chapters) {
		this.chapters = chapters;
	}
	public String getCourseImg() {
		return courseImg;
	}
	public void setCourseImg(String courseImg) {
		this.courseImg = courseImg;
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
	public String[] getCourseLabels() {
		return courseLabels;
	}
	public void setCourseLabels(String[] courseLabels) {
		this.courseLabels = courseLabels;
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
	public Boolean getCourseStatus() {
		return courseStatus;
	}
	public void setCourseStatus(Boolean courseStatus) {
		this.courseStatus = courseStatus;
	}
	public int getStudentNum() {
		return studentNum;
	}
	public void setStudentNum(int studentNum) {
		this.studentNum = studentNum;
	}
	public CourseVO(Integer courseId, String courseName, String courseIntro, Float courseStarLevel,
			Float courseDiffLevel, String[] courseLabels, String courseType, String courseLive, Boolean courseStatus
			,int studentNum) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseIntro = courseIntro;
		this.courseStarLevel = courseStarLevel;
		this.courseDiffLevel = courseDiffLevel;
		this.courseLabels = courseLabels;
		this.courseType = courseType;
		this.courseLive = courseLive;
		this.courseStatus = courseStatus;
		this.studentNum = studentNum;
	}
	public CourseVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
