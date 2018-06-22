package com.is.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.is.entity.TCoursecomment;
import com.is.json.entty.CourseCommentVO;

public interface CourseCommentRepository extends JpaRepository<TCoursecomment, Integer> {

	@Query("select new com.is.json.entty.CourseCommentVO"
			+ "(n.courseCommentId,n.TUser.uid,n.TUser.username,"
			+ "n.TUser.avatar,n.TUser.account,n.commentTime,n.commentContent,n.teacherStarLevel,n.courseStarLevel) "
			+ "from TCoursecomment n "
			+ "where n.TCourse.courseId = :clazzId")
	public List<CourseCommentVO> queryCourseCommentByClazzId(@Param("clazzId")int clazzId);
}
