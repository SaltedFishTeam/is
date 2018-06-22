package com.is.entity;
// default package

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * TChapter entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_chapter", catalog = "is")
public class TChapter implements java.io.Serializable,Comparable<TChapter> {

	// Fields

	private Integer chapterId;
	private TCourse TCourse;
	private String chapterName;
	private String chapterIntro;
	private Integer order;
	private Set<TSection> TSections = new HashSet<TSection>(0);

	// Constructors

	/** default constructor */
	public TChapter() {
	}

	/** minimal constructor */
	public TChapter(Integer chapterId, String chapterName) {
		this.chapterId = chapterId;
		this.chapterName = chapterName;
	}

	/** full constructor */
	public TChapter(Integer chapterId, TCourse TCourse, String chapterName,
			String chapterIntro, Set<TSection> TSections) {
		this.chapterId = chapterId;
		this.TCourse = TCourse;
		this.chapterName = chapterName;
		this.chapterIntro = chapterIntro;
		this.TSections = TSections;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "chapter_id", unique = true, nullable = false)
	public Integer getChapterId() {
		return this.chapterId;
	}

	public void setChapterId(Integer chapterId) {
		this.chapterId = chapterId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id")
	public TCourse getTCourse() {
		return this.TCourse;
	}

	public void setTCourse(TCourse TCourse) {
		this.TCourse = TCourse;
	}

	@Column(name = "chapter_name", nullable = false, length = 100)
	public String getChapterName() {
		return this.chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	@Column(name = "chapter_intro", length = 500)
	public String getChapterIntro() {
		return this.chapterIntro;
	}

	public void setChapterIntro(String chapterIntro) {
		this.chapterIntro = chapterIntro;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TChapter")
	public Set<TSection> getTSections() {
		return this.TSections;
	}

	public void setTSections(Set<TSection> TSections) {
		this.TSections = TSections;
	}

	@Column(name = "corder",nullable = false)
	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
	
	@Override
	public int compareTo(TChapter c) {
		//自定义比较方法，如果认为此实体本身大则返回1，否则返回-1
		System.out.println("排序");
		if(this.order >= c.getOrder()) {
			return 1;
		}
		return -1;
	}
}