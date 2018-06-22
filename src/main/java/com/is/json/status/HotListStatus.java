package com.is.json.status;

import java.util.List;

import com.is.json.entty.NoteVO;

public class HotListStatus extends Status {

	private List<NoteVO> page;
	
	private int count;
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<NoteVO> getPage() {
		return page;
	}

	public void setPage(List<NoteVO> page) {
		this.page = page;
	}

	public HotListStatus(String status, int code, String msg) {
		super(status, code, msg);
		// TODO Auto-generated constructor stub
	}

}
