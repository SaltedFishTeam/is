package com.is.json.status;

import java.util.List;

import com.is.json.entty.NoteClassifyVO;

public class GetClassifyStatus extends Status {

	private List<NoteClassifyVO> classifies;
	
	public List<NoteClassifyVO> getClassifies() {
		return classifies;
	}

	public void setClassifies(List<NoteClassifyVO> classifies) {
		this.classifies = classifies;
	}

	public GetClassifyStatus(String status, int code, String msg) {
		super(status, code, msg);
		// TODO Auto-generated constructor stub
	}

	
}
