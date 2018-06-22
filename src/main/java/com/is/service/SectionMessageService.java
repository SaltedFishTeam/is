package com.is.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;

import com.is.entity.TSection;
import com.is.entity.TSectionMessage;
import com.is.entity.TUser;
import com.is.json.entty.SectionMessageVO;
import com.is.json.entty.SectionReplyVO;
import com.is.repository.SectionMessageRepository;
import com.is.repository.SectionReplyRepository;
import com.is.repository.SectionRepository;
import com.is.repository.UserRepository;

@Service
public class SectionMessageService {

	@Autowired
	private SectionMessageRepository sectionMessageRepository;
	@Autowired
	private SectionReplyRepository sectionReplyRepository;
	@Autowired
	private SectionRepository sectionRepository;
	@Autowired
	private UserRepository userRepository;
	
	public PagedListHolder<SectionMessageVO> listBySectionId(int sectionId,int nowPage) {
		List<SectionMessageVO> source = sectionMessageRepository.listBySectionId(sectionId);
		PagedListHolder<SectionMessageVO> pages = new PagedListHolder<>(source);
		pages.setPageSize(5);
		pages.setPage(nowPage - 1);
		List<SectionMessageVO> page = pages.getPageList();
		for(SectionMessageVO smVO : page) {
			List<SectionReplyVO> replys = sectionReplyRepository.listBySMId(smVO.getId());
			smVO.setReplys(replys);
		}
		return pages;
	}
	
	public void save(int uid,int sectionId,TSectionMessage sectionMessage) throws Exception {
		
		TSection section = sectionRepository.getOne(sectionId);
		if(section == null)  throw new Exception("sectionId不存在");
		sectionMessage.setSection(section);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sectionMessage.setTime(format.format(System.currentTimeMillis()));
		TUser user = userRepository.getOne(uid);
		if(user == null) throw new Exception("uid不存");
		sectionMessage.setUser(user);
		sectionMessageRepository.save(sectionMessage);
	}
}
