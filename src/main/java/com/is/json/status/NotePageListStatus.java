package com.is.json.status;

import java.util.List;

import com.is.json.entty.NoteVO;

public class NotePageListStatus extends Status {
	
	public NotePageListStatus(String status, int code, String msg) {
		super(status, code, msg);
		// TODO Auto-generated constructor stub
	}

	private List<NoteVO> list;
	
	private int now;
	
	private int pageCount;

	public List<NoteVO> getList() {
		return list;
	}

	public void setList(List<NoteVO> list) {
		this.list = list;
	}

	public int getNow() {
		return now;
	}

	public void setNow(int now) {
		this.now = now;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
	

}
