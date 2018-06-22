package com.is.json.status;

import java.util.List;

import com.is.json.entty.SectionMessageVO;

public class SectionCommentStatus extends Status {

	private List<SectionMessageVO> messages;
	
	private int count;
	
	public SectionCommentStatus(String status, int code, String msg) {
		super(status, code, msg);
		// TODO Auto-generated constructor stub
	}

	public List<SectionMessageVO> getMessages() {
		return messages;
	}

	public void setMessages(List<SectionMessageVO> messages) {
		this.messages = messages;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
