package com.is.json.status;

import com.is.json.entty.NoteMessageVO;

public class EvaluateStatus extends Status {

	private NoteMessageVO vo;
	
	public EvaluateStatus(String status, int code, String msg) {
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
