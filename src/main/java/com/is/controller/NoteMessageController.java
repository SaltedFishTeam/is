package com.is.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.is.json.entty.NoteMessageVO;
import com.is.json.entty.NoteReplyVO;
import com.is.json.status.NoteMessageStatus;
import com.is.json.status.Status;
import com.is.service.NoteMessageService;

@RestController
public class NoteMessageController {

	@Autowired
	private NoteMessageService noteMessageService;
	
	@RequestMapping(value="/note/message/{id}",method=RequestMethod.GET)
	public NoteMessageStatus find(@PathVariable("id")int id) {
		NoteMessageVO vo = noteMessageService.listById(id);
		NoteMessageStatus status = new NoteMessageStatus("success", 1, "成功");
		status.setVo(vo);
		return status;
	}
}
