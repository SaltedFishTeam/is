package com.is.json.status;

import java.util.List;

import com.is.entity.TCourse;
import com.is.json.entty.CourseVO;

public class RecommendStatus extends Status {

	private List<CourseVO> courses;
	
	public RecommendStatus(String status, int code, String msg) {
		super(status, code, msg);
	}

	public List<CourseVO> getCourses() {
		return courses;
	}

	public void setCourses(List<CourseVO> courses) {
		this.courses = courses;
	}
	
	
}
