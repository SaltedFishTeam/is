package com.is.service;


import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.entity.TSectionMessage;
import com.is.entity.TSectionReply;
import com.is.entity.TUser;
import com.is.repository.SectionMessageRepository;
import com.is.repository.SectionReplyRepository;
import com.is.repository.UserRepository;

@Service
public class SectionReplyService {

	@Autowired
	private SectionReplyRepository sectionReplyRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SectionMessageRepository sectionMessageRepository;
	
	public void save(int uid,int rid,int smid,TSectionReply sectionReply) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		TUser user = userRepository.getOne(uid);
		TUser ruser = userRepository.getOne(rid);
		TSectionMessage sectionMessage = sectionMessageRepository.getOne(smid);
		if(user == null || ruser == null || sectionMessage == null) {
			throw new Exception("id不能为空");
		}
		sectionReply.setTime(format.format(System.currentTimeMillis()));
		sectionReply.setUser(user);
		sectionReply.setReply(ruser);
		sectionReply.setSectionMessage(sectionMessage);
		sectionReplyRepository.save(sectionReply);
	}
}
