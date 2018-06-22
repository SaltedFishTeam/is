package com.is.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.is.entity.TClassifyNode;

public interface NoteClassifyRepository extends JpaRepository<TClassifyNode, Integer> {

	@Query("select n from TClassifyNode n where n.TUser.uid = :uid")
	public List<TClassifyNode> listMyClassify(@Param("uid") int uid);
}
