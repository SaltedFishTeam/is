package com.is.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.is.entity.TSectionMessage;
import com.is.json.entty.SectionMessageVO;
public interface SectionMessageRepository extends JpaRepository<TSectionMessage, Integer> {

	@Query("select new com.is.json.entty.SectionMessageVO("
			+ "n.id,n.content,n.time,n.user.uid,n.user.avatar,n.user.username,n.user.account) "
			+ "from TSectionMessage n "
			+ "where n.section.sectionId = :sectionId")
	public List<SectionMessageVO> listBySectionId(@Param("sectionId")int sectionId);
}
