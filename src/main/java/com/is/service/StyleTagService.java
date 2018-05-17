package com.is.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.entity.TStyleTag;
import com.is.repository.StyleTagRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StyleTagService {
	
	@Autowired
	private StyleTagRepository styleTag;
	
	/**
	 * 返回所有学习风格标签
	 * @return
	 */
	public List<TStyleTag> list() {
		List<TStyleTag> list = styleTag.findAll();
		return list;
	}
	
	/**
	 * 返回学习风格种类数量
	 * @return
	 */
	public long count() {
		return styleTag.count();
	}
}
