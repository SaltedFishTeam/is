package com.is.json.status;

import java.util.List;

import com.is.entity.TNodeTag;

public class ListTagStatus extends Status {
	
	private List<TNodeTag> list;
	
	public List<TNodeTag> getList() {
		return list;
	}

	public void setList(List<TNodeTag> list) {
		this.list = list;
	}

	public ListTagStatus(String status, int code, String msg) {
		super(status, code, msg);
		// TODO Auto-generated constructor stub
	}

}
