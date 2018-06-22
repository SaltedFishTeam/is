package com.is.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.is.entity.TNodeMessage;
import com.is.json.entty.NoteMessageVO;


public interface NoteMessageRepository extends JpaRepository<TNodeMessage, Integer> {


	@Query("select new com.is.json.entty.NoteMessageVO(n.nodeMsgId,n.content,n.time,"
			+ "n.TUser.uid,n.TUser.username,n.TUser.avatar)"
			+ " from TNodeMessage n where n.TNode.nodeId = :noteId")
	public List<NoteMessageVO> findByNoteId(@Param("noteId")int noteId);
	
	@Query("select new com.is.json.entty.NoteMessageVO(n.nodeMsgId,n.content,n.time,"
			+ "n.TUser.uid,n.TUser.username,n.TUser.avatar)"
			+ " from TNodeMessage n where n.nodeMsgId = :nodeMsgId")
	public NoteMessageVO findBynodeMsgId(@Param("nodeMsgId")int nodeMsgId);
	
}
