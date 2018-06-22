package com.is.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.is.entity.TNodeTag;
import com.is.json.status.ListTagStatus;
import com.is.json.status.Status;
import com.is.service.NoteTagService;

/**
 * this is sun day
 * 时间: 2018年5月29日上午8:43:10
 *    `_`   
 * by wcm in
 */
@RestController
public class TagController {
	
	@Autowired
	private NoteTagService noteTagService;
	
	@RequestMapping(value="/tag/list",method=RequestMethod.GET)
	public Status tagList() {
		List<TNodeTag> list = noteTagService.list();
		ListTagStatus status = new ListTagStatus("success", 1, "查询成功");
		status.setList(list);
		return status;
	}
	
}
