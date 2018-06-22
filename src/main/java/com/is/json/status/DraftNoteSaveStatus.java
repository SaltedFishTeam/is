package com.is.json.status;

public class DraftNoteSaveStatus extends Status {

	private int noteId;
	
	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	public DraftNoteSaveStatus(String status, int code, String msg) {
		super(status, code, msg);
		// TODO Auto-generated constructor stub
	}

}
