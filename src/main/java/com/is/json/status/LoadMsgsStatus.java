package com.is.json.status;

import java.util.List;

import com.is.json.entty.MessageVO;
import com.is.json.entty.UserMessagesVO;

public class LoadMsgsStatus extends Status {

	private List<UserMessagesVO> msgs;
	
	public LoadMsgsStatus(String status, int code, String msg) {
		super(status, code, msg);
		// TODO Auto-generated constructor stub
	}

	public List<UserMessagesVO> getMsgs() {
		return msgs;
	}

	public void setMsgs(List<UserMessagesVO> msgs) {
		this.msgs = msgs;
	}

	

}
