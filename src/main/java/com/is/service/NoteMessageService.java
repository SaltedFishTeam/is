package com.is.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.entity.TNode;
import com.is.entity.TNodeMessage;
import com.is.entity.TUser;
import com.is.json.entty.NoteMessageVO;
import com.is.json.entty.NoteReplyVO;
import com.is.repository.NoteMessageRepository;
import com.is.repository.NoteReplyRepository;
import com.is.repository.NoteRepository;
import com.is.repository.UserRepository;

@Service
public class NoteMessageService {

	@Autowired
	private NoteRepository noteRepository;
	@Autowired
	private NoteMessageRepository noteMessageRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private NoteReplyRepository noteReplyRepository;
	
	public TNodeMessage save(String content,Integer uid,Integer noteId) {
		TUser user = userRepository.findById(uid).get();
		TNode note = noteRepository.findById(noteId).get();
		TNodeMessage noteMessage = new TNodeMessage();
		noteMessage.setContent(content);
		noteMessage.setTUser(user);
		noteMessage.setTNode(note);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		noteMessage.setTime(format.format(System.currentTimeMillis()));
		noteMessage = noteMessageRepository.save(noteMessage);
		
		return noteMessage;
	}
	
	public List<NoteMessageVO> listByNoteId(Integer noteId) {
		List<NoteMessageVO> list = noteMessageRepository.findByNoteId(noteId);
		for(NoteMessageVO vo : list) {
			List<NoteReplyVO> replyByNMId = noteReplyRepository.searchReplyByNMId(vo.getId());
			vo.setReply(replyByNMId);
		}
		return list;
	}
	
	public NoteMessageVO listById(int id) {
		NoteMessageVO vo = noteMessageRepository.findBynodeMsgId(id);
//		return noteReplyRepository.searchReplyByNMId(id);
		List<NoteReplyVO> list = noteReplyRepository.searchReplyByNMId(vo.getId());
		vo.setReply(list);
		return vo;
	}
}
