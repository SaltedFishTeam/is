package com.is.json.status;

import java.util.List;

import com.is.json.entty.CourseUserVO;

public class UserCourseListStatus extends Status {

	private List<CourseUserVO> list;
	
	private int count;
	public UserCourseListStatus(String status, int code, String msg) {
		super(status, code, msg);
		// TODO Auto-generated constructor stub
	}
	public List<CourseUserVO> getList() {
		return list;
	}
	public void setList(List<CourseUserVO> list) {
		this.list = list;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

}
