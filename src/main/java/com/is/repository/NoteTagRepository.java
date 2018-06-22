package com.is.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.is.entity.TNodeTag;

public interface NoteTagRepository extends JpaRepository<TNodeTag, Integer> {
	
}
