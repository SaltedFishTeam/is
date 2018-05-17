package com.is.json.status;

import java.util.List;

import com.is.entity.TSurvey;

public class SurveyListStatus extends Status {

	private List<TSurvey> list;
	
	public SurveyListStatus(String status, int code, String msg) {
		super(status, code, msg);
		// TODO Auto-generated constructor stub
	}

	public SurveyListStatus(String status, int code, String msg, List<TSurvey> list) {
		super(status, code, msg);
		this.list = list;
	}

	public List<TSurvey> getList() {
		return list;
	}

	public void setList(List<TSurvey> list) {
		this.list = list;
	}
	
	

}
