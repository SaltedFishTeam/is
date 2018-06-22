package com.is.json.status;

import java.util.List;

import com.is.entity.TClassifyNode;
import com.is.entity.TNodeTag;
import com.is.json.entty.NoteClassifyVO;

public class MakeNoteStatus extends Status {

	private List<NoteClassifyVO> classifies;
	private List<TNodeTag> tags;
	
	public MakeNoteStatus(String status, int code, String msg) {
		super(status, code, msg);
		// TODO Auto-generated constructor stub
	}

	public List<NoteClassifyVO> getClassifies() {
		return classifies;
	}



	public void setClassifies(List<NoteClassifyVO> classifies) {
		this.classifies = classifies;
	}



	public List<TNodeTag> getTags() {
		return tags;
	}

	public void setTags(List<TNodeTag> tags) {
		this.tags = tags;
	}
	
	
	
}
