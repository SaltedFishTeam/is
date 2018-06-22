package com.is.json.status;

import com.is.json.entty.NoteReplyVO;

public class ReplyNoteStatus extends Status {

	private NoteReplyVO reply;
	
	public NoteReplyVO getReply() {
		return reply;
	}

	public void setReply(NoteReplyVO reply) {
		this.reply = reply;
	}

	public ReplyNoteStatus(String status, int code, String msg) {
		super(status, code, msg);
		// TODO Auto-generated constructor stub
	}

}
