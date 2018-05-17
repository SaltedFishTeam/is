package com.is.json.status;

import com.is.json.entty.CourseVO;
import com.is.json.entty.UserVO;

public class CourseDetailStatus extends Status {

	private CourseVO courses;
	private UserVO user;
	public CourseDetailStatus(String status, int code, String msg) {
		super(status, code, msg);
		// TODO Auto-generated constructor stub
	}
	public CourseVO getCourses() {
		return courses;
	}
	public void setCourses(CourseVO courses) {
		this.courses = courses;
	}
	public UserVO getUser() {
		return user;
	}
	public void setUser(UserVO user) {
		this.user = user;
	}
	

}
