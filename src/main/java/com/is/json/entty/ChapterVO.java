package com.is.json.entty;

import java.util.List;

import com.is.entity.TCourse;

/**
 * 
 * 时间: 2018年5月13日上午8:52:24
 *    `_`   good morning
 * by wcm in jxfue
 */
public class ChapterVO {

	private Integer chapterId;
	private String chapterName;
	private String chapterIntro;
	private List<SectionVO> sections;
	public Integer getChapterId() {
		return chapterId;
	}
	public void setChapterId(Integer chapterId) {
		this.chapterId = chapterId;
	}
	public String getChapterName() {
		return chapterName;
	}
	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}
	public String getChapterIntro() {
		return chapterIntro;
	}
	public void setChapterIntro(String chapterIntro) {
		this.chapterIntro = chapterIntro;
	}
	public List<SectionVO> getSections() {
		return sections;
	}
	public void setSections(List<SectionVO> sections) {
		this.sections = sections;
	}
	
	
	
}
