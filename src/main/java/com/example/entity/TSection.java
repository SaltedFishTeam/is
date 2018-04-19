package com.example.entity;
// default package

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TSection entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_section", catalog = "is")
public class TSection implements java.io.Serializable {

	// Fields

	private Long sectionId;
	private TChapter TChapter;
	private String sectionName;
	private String sectionContent;
	private Set<TCourseware> TCoursewares = new HashSet<TCourseware>(0);

	// Constructors

	/** default constructor */
	public TSection() {
	}

	/** minimal constructor */
	public TSection(Long sectionId, TChapter TChapter, String sectionName) {
		this.sectionId = sectionId;
		this.TChapter = TChapter;
		this.sectionName = sectionName;
	}

	/** full constructor */
	public TSection(Long sectionId, TChapter TChapter, String sectionName,
			String sectionContent, Set<TCourseware> TCoursewares) {
		this.sectionId = sectionId;
		this.TChapter = TChapter;
		this.sectionName = sectionName;
		this.sectionContent = sectionContent;
		this.TCoursewares = TCoursewares;
	}

	// Property accessors
	@Id
	@Column(name = "section_id", unique = true, nullable = false)
	public Long getSectionId() {
		return this.sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chapter_id", nullable = false)
	public TChapter getTChapter() {
		return this.TChapter;
	}

	public void setTChapter(TChapter TChapter) {
		this.TChapter = TChapter;
	}

	@Column(name = "section_name", nullable = false, length = 100)
	public String getSectionName() {
		return this.sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	@Column(name = "section_content", length = 16777215)
	public String getSectionContent() {
		return this.sectionContent;
	}

	public void setSectionContent(String sectionContent) {
		this.sectionContent = sectionContent;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TSection")
	public Set<TCourseware> getTCoursewares() {
		return this.TCoursewares;
	}

	public void setTCoursewares(Set<TCourseware> TCoursewares) {
		this.TCoursewares = TCoursewares;
	}

}