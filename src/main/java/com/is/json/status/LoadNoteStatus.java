package com.is.json.status;

import java.util.List;

import com.is.json.entty.NoteMessageVO;
import com.is.json.entty.NoteVO;
import com.is.json.entty.UserVO;

public class LoadNoteStatus extends Status {

	private UserVO user;
	
	private NoteVO note;
	
	private List<NoteMessageVO> messages;
	
	public LoadNoteStatus(String status, int code, String msg) {
		super(status, code, msg);
		// TODO Auto-generated constructor stub
	}

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}

	public NoteVO getNote() {
		return note;
	}

	public void setNote(NoteVO note) {
		this.note = note;
	}

	public List<NoteMessageVO> getMessages() {
		return messages;
	}

	public void setMessages(List<NoteMessageVO> messages) {
		this.messages = messages;
	}

	
}
