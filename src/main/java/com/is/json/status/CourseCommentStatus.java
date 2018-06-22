package com.is.json.status;

import java.util.List;

import com.is.json.entty.CourseCommentVO;

public class CourseCommentStatus extends Status {

	private List<CourseCommentVO> list;
	
	private int count;
	
	public CourseCommentStatus(String status, int code, String msg) {
		super(status, code, msg);
	}

	public List<CourseCommentVO> getList() {
		return list;
	}

	public void setList(List<CourseCommentVO> list) {
		this.list = list;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
