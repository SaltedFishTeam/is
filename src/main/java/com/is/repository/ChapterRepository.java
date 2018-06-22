package com.is.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.is.entity.TChapter;

public interface ChapterRepository extends JpaRepository<TChapter, Integer> {

}
