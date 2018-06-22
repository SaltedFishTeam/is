package com.is.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.entity.TSection;
import com.is.repository.SectionRepository;

/**
 * It's rainning today.
 * I'm fun.
 * 时间: 2018年5月30日下午3:07:49
 *    `_`   
 * by wcm in
 */
@Service
public class SectionService {
	
	@Autowired
	private SectionRepository sectionRepository;
	
	public TSection getSection(int sectionId) {
		TSection section = sectionRepository.findById(sectionId).get();
		return section;
	}
	
	public void delSection(int sectionId) {
		TSection section = sectionRepository.getOne(sectionId);
		sectionRepository.delete(section);
	}

	
}
