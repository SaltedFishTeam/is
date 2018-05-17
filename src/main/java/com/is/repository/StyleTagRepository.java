package com.is.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.is.entity.TStyleTag;

public interface StyleTagRepository extends JpaRepository<TStyleTag, Integer> {
	
}
