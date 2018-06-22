package com.is.json.status;

import com.is.json.entty.CourseVO;
import com.is.json.entty.SectionVO;

public class StudyContentStatus extends Status {

	private SectionVO sectionVO;
	private CourseVO courseVO;
	private Integer teacherId;
	
	public StudyContentStatus(String status, int code, String msg) {
		super(status, code, msg);
		// TODO Auto-generated constructor stub
	}

	public SectionVO getSectionVO() {
		return sectionVO;
	}

	public void setSectionVO(SectionVO sectionVO) {
		this.sectionVO = sectionVO;
	}

	public CourseVO getCourseVO() {
		return courseVO;
	}

	public void setCourseVO(CourseVO courseVO) {
		this.courseVO = courseVO;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	
}
