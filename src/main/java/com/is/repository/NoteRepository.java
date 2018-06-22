package com.is.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.is.entity.TNode;
import com.is.json.entty.NoteVO;

public interface NoteRepository extends JpaRepository<TNode,Integer> {

	@Query("select u from TNode u where u.TUser.uid = :uid")
	public List<TNode> findByUid(@Param("uid")int uid);
	
	@Query("select u from TNode u where u.TUser.uid = :uid"
			+ " and u.TClassifyNode.classifyNotesId = :classifyNotesId")
	public List<TNode> findByUidAndClassifyId(@Param("uid")int uid,
			@Param("classifyNotesId")int classifyNotesId);

	@Query("select new com.is.json.entty.NoteVO"
			+ "(n.nodeId,n.title,n.time,n.skimNum,"
			+ "n.TClassifyNode.name,n.nodeTagNames,n.imgUrl) "
			+ "from TNode n "
			+ "order by n.skimNum desc")
	public List<NoteVO> findHotNotes();
	
	@Query("select new com.is.json.entty.NoteVO"
			+ "(n.nodeId,n.title,n.time,n.skimNum,"
			+ "n.TClassifyNode.name,n.nodeTagNames,n.imgUrl) "
			+ "from TNode n "
			+ "order by n.time desc")	
	public List<NoteVO> findNewNotes();

	@Query("select new com.is.json.entty.NoteVO"
			+ "(n.nodeId,n.title,n.time,n.skimNum,"
			+ "n.TClassifyNode.name,n.nodeTagNames,n.imgUrl) "
			+ "from TNode n "
			+ "where n.content like %:key% "
			+ "order by n.time desc")	
	public List<NoteVO> findKeyNotes(@Param("key")String key);
	
}
