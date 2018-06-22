package com.is.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.is.entity.TUser;
import com.is.json.entty.CourseUserVO;

public interface UserRepository extends JpaRepository<TUser, Integer>{
	
	@Query(value = "select u from TUser u where u.account = :account")
	public TUser findByAccount(@Param("account")String account);
	
}
