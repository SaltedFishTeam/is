package com.is.json.status;

import java.util.List;

import com.is.json.entty.NoteMessageVO;

public class NoteMessageStatus extends Status {

	private NoteMessageVO vo;
	
	public NoteMessageStatus(String status, int code, String msg) {
		super(status, code, msg);
		// TODO Auto-generated constructor stub
	}

	public NoteMessageVO getVo() {
		return vo;
	}

	public void setVo(NoteMessageVO vo) {
		this.vo = vo;
	}

	
}
