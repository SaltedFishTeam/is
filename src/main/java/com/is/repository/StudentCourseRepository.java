package com.is.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.is.entity.TSc;
import com.is.entity.TScId;
import com.is.json.entty.CourseUserVO;
import com.is.json.entty.CourseVO;

public interface StudentCourseRepository extends JpaRepository<TSc, TScId> {

	@Query("select n from TSc n where n.TUser.uid = :uid and n.TCourse.courseId = :cid")
	public List<TSc> queryByUidAndCid(@Param("uid")int uid,@Param("cid")int cid);
	
	@Query("select new com.is.json.entty.CourseUserVO("
			+ "n.TCourse.courseId,n.TCourse.courseName,"
			+ "n.TCourse.courseImg,n.studyNum / (n.TCourse.sectionNum * 1.0)) "
			+ "from TSc n where n.TUser.uid = :uid order by n.TCourse.courseId")
	public List<CourseUserVO> listByUid(@Param("uid")int uid);
}

