package com.is.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.entity.TNodeTag;
import com.is.repository.NoteTagRepository;

@Service
public class NoteTagService {

	@Autowired
	private NoteTagRepository noteTag;
	
	public List<TNodeTag> list() {
		List<TNodeTag> list = noteTag.findAll();
		return list;
	}
}
