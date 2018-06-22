package com.is.json.entty;

import com.is.json.status.Status;

public class FileUrlStatus extends Status {

	private String url;
	
	public FileUrlStatus(String status, int code, String msg) {
		super(status, code, msg);
		// TODO Auto-generated constructor stub
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
	
}
