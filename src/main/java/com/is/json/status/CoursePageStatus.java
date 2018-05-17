package com.is.json.status;

import java.util.List;

import com.is.json.entty.CourseVO;

public class CoursePageStatus extends Status {

	private List<CourseVO> courses;
	
	public CoursePageStatus(String status, int code, String msg) {
		super(status, code, msg);
		// TODO Auto-generated constructor stub
	}

	public List<CourseVO> getCourses() {
		return courses;
	}

	public void setCourses(List<CourseVO> courses) {
		this.courses = courses;
	}
	
	
}
