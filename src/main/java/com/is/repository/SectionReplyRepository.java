package com.is.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.is.entity.TSectionReply;
import com.is.json.entty.SectionReplyVO;

public interface SectionReplyRepository extends JpaRepository<TSectionReply, Integer> {
	
	@Query("select new com.is.json.entty.SectionReplyVO("
			+ "n.id,n.content,n.time,n.user.uid,'avatar',"
			+ "n.user.username,n.user.account,n.reply.uid,'avatar',n.reply.username) "
			+ "from TSectionReply n "
			+ "where n.sectionMessage.id = :sectionMessageId")
	public List<SectionReplyVO> listBySMId(@Param("sectionMessageId")int sectionMessageId);
	
}
