package com.is.service;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.entity.NoteReply;
import com.is.entity.TNodeMessage;
import com.is.entity.TUser;
import com.is.repository.NoteMessageRepository;
import com.is.repository.NoteReplyRepository;
import com.is.repository.UserRepository;

@Service
public class NoteReplyService {

	@Autowired
	private NoteReplyRepository noteReplyRepository;
	@Autowired
	private NoteMessageRepository noteMessagerepository;
	@Autowired
	private UserRepository userRepository;
	
	public NoteReply save(int uid,int rid,int nmId,String content) {
		TUser user = userRepository.findById(uid).get();
		TUser ruser = userRepository.findById(rid).get();
		TNodeMessage nm = noteMessagerepository.findById(nmId).get();
		NoteReply noteReply = new NoteReply();
		noteReply.setContent(content);
		noteReply.setTUser(user);
		noteReply.setRuser(ruser);
		noteReply.setTNodeMessage(nm);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		noteReply.setTime(format.format(System.currentTimeMillis()));
		noteReply = noteReplyRepository.save(noteReply);
		return noteReply;
	}
}
