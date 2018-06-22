package com.is.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.is.entity.NoteReply;
import com.is.json.entty.NoteReplyVO;

public interface NoteReplyRepository extends JpaRepository<NoteReply, Integer> {

	@Query("select new com.is.json.entty.NoteReplyVO("
			+ "n.noteReplyId,n.content,n.time,n.TUser.uid,"
			+ "n.TUser.username,n.TUser.avatar,n.ruser.uid," 
			+ "n.ruser.username,n.ruser.avatar)"
			+ "from NoteReply n where n.TNodeMessage.nodeMsgId = :nmId")
	public List<NoteReplyVO> searchReplyByNMId(@Param("nmId")int nmId);
}
