package com.is.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.is.entity.TUser;

public interface UserRepository extends JpaRepository<TUser, Integer> {
	
}
