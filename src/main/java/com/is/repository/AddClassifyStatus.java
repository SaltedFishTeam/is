package com.is.repository;

import com.is.json.entty.NoteClassifyVO;
import com.is.json.status.Status;

public class AddClassifyStatus extends Status {
	
	private NoteClassifyVO noteClassify;
	
	public AddClassifyStatus(String status, int code, String msg) {
		super(status, code, msg);
		// TODO Auto-generated constructor stub
	}

	public NoteClassifyVO getNoteClassify() {
		return noteClassify;
	}

	public void setNoteClassify(NoteClassifyVO noteClassify) {
		this.noteClassify = noteClassify;
	}

	
}
