package com.is.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.is.entity.TCourse;

public interface CourseRepository extends JpaRepository<TCourse, Integer> {

	@Query("select u from TCourse u where u.courseStatus = 2")
	public List<TCourse> recommendList();
	
	@Query("select u from TCourse u where u.courseName like %:key%")
	public List<TCourse> listByKey(@Param("key") String key);
	
}
