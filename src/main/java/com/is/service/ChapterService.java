package com.is.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.entity.TChapter;
import com.is.repository.ChapterRepository;

@Service
public class ChapterService {

	@Autowired
	private ChapterRepository chapterRepository;
	
	public void delChapter(int chapterId) {
		System.out.println(chapterId);
		TChapter chapter = chapterRepository.findById(chapterId).get();
		chapterRepository.delete(chapter);
	}
}
