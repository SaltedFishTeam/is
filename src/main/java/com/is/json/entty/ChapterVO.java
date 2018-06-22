package com.is.json.entty;

import java.util.List;

import com.is.entity.TCourse;
import com.is.entity.TSection;

/**
 * 
 * 时间: 2018年5月13日上午8:52:24
 *    `_`   good morning
 * by wcm in jxfue
 */
public class ChapterVO implements Comparable<ChapterVO> {

	private Integer chapterId;
	private String chapterName;
	private String chapterIntro;
	private Integer order;
	private List<SectionVO> tsections;
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
		return tsections;
	}
	public void setSections(List<SectionVO> sections) {
		this.tsections = sections;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	
	@Override
	public int compareTo(ChapterVO o) {
		System.out.println("排序");
		if(this.order > o.getOrder()) return 1;
		else return -1;
	}
	
}
