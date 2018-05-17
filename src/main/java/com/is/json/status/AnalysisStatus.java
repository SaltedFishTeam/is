package com.is.json.status;

import org.apache.commons.lang.builder.ToStringBuilder;

public class AnalysisStatus extends Status {
	
	private int[] result;
	
	public AnalysisStatus(String status, int code, String msg) {
		super(status, code, msg);
		// TODO Auto-generated constructor stub
	}

	public int[] getResult() {
		return result;
	}

	public void setResult(int[] result) {
		this.result = result;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);
	}
}
