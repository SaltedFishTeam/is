package com.is.json.entty;

public class CourseUserVO implements Comparable<CourseUserVO> {
	
	private int cid;
	private String courseName;
	private String avatar;
	private float rate;
	private int studyNum;
	private String type;
	public int getStudyNum() {
		return studyNum;
	}
	public void setStudyNum(int studyNum) {
		this.studyNum = studyNum;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	public CourseUserVO() {
	}
	public CourseUserVO(int cid, String courseName, String avatar, float rate, int studyNum) {
		super();
		this.cid = cid;
		this.courseName = courseName;
		this.avatar = avatar;
		this.rate = rate;
		this.studyNum = studyNum;
	}
	
	public CourseUserVO(int cid, String courseName, String avatar, double rate) {
		super();
		this.cid = cid;
		this.courseName = courseName;
		this.avatar = avatar;
		this.rate = (float)rate;
	}
	public CourseUserVO(int cid, String courseName, String avatar, int studyNum,String type) {
		super();
		this.cid = cid;
		this.courseName = courseName;
		this.avatar = avatar;
		this.studyNum = studyNum;
		this.type = type;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public int compareTo(CourseUserVO o) {
		if(this.cid > o.getCid()) return 1;
		else return -1;
	}
	
}
