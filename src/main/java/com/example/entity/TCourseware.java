package com.example.entity;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TCourseware entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_courseware", catalog = "is")
public class TCourseware implements java.io.Serializable {

	// Fields

	private Long coursewareId;
	private TSection TSection;
	private String coursewareUrl;
	private Double coursewareSize;
	private String coursewareName;

	// Constructors

	/** default constructor */
	public TCourseware() {
	}

	/** minimal constructor */
	public TCourseware(Long coursewareId, TSection TSection) {
		this.coursewareId = coursewareId;
		this.TSection = TSection;
	}

	/** full constructor */
	public TCourseware(Long coursewareId, TSection TSection,
			String coursewareUrl, Double coursewareSize, String coursewareName) {
		this.coursewareId = coursewareId;
		this.TSection = TSection;
		this.coursewareUrl = coursewareUrl;
		this.coursewareSize = coursewareSize;
		this.coursewareName = coursewareName;
	}

	// Property accessors
	@Id
	@Column(name = "courseware_id", unique = true, nullable = false)
	public Long getCoursewareId() {
		return this.coursewareId;
	}

	public void setCoursewareId(Long coursewareId) {
		this.coursewareId = coursewareId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "section_id", nullable = false)
	public TSection getTSection() {
		return this.TSection;
	}

	public void setTSection(TSection TSection) {
		this.TSection = TSection;
	}

	@Column(name = "courseware_url", length = 500)
	public String getCoursewareUrl() {
		return this.coursewareUrl;
	}

	public void setCoursewareUrl(String coursewareUrl) {
		this.coursewareUrl = coursewareUrl;
	}

	@Column(name = "courseware_size", precision = 22, scale = 0)
	public Double getCoursewareSize() {
		return this.coursewareSize;
	}

	public void setCoursewareSize(Double coursewareSize) {
		this.coursewareSize = coursewareSize;
	}

	@Column(name = "courseware_name", length = 100)
	public String getCoursewareName() {
		return this.coursewareName;
	}

	public void setCoursewareName(String coursewareName) {
		this.coursewareName = coursewareName;
	}

}