package com.is.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.entity.TClassifyNode;
import com.is.repository.NoteClassifyRepository;
import com.is.repository.NoteTagRepository;
import com.is.repository.UserRepository;

@Service
public class NoteClassifyService {

	@Autowired
	private NoteClassifyRepository noteClassify;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<TClassifyNode> list(int uid) {
		List<TClassifyNode> list = noteClassify.listMyClassify(uid);
		return list;
	}
	
	public boolean delete(int nid) {
		try {
			noteClassify.deleteById(nid);			
		} catch(Exception e) {
			return false;
		}
		return true;
	}
	
	public TClassifyNode addClassify(String classify,int uid) {
		TClassifyNode oldClassify = new TClassifyNode();
		oldClassify.setName(classify);
		oldClassify.setTUser(userRepository.findById(uid).get());
		TClassifyNode newClassify = noteClassify.save(oldClassify);
		return newClassify;
	}
	
	
}
