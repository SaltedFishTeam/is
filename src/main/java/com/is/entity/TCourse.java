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
 * TCourse entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_course", catalog = "is")
public class TCourse implements java.io.Serializable {

	// Fields

	private Integer courseId;
	private TUser tUser;
	private String courseName;
	private String courseIntro;
	private Float courseStarLevel;
	private Float courseDiffLevel;
	private String courseLabel;
	private String courseType;
	private String courseLive;
	private Boolean courseStatus;
	private String courseImg;
	private int studentNum;
	private Set<TSc> TScs = new HashSet<TSc>(0);
	private Set<TChapter> TChapters = new HashSet<TChapter>(0);
	private Set<TCoursecomment> TCoursecomments = new HashSet<TCoursecomment>(0);
	private Set<THomework> THomeworks = new HashSet<THomework>(0);

	// Constructors

	/** default constructor */
	public TCourse() {
	}

	/** minimal constructor */
	public TCourse(Integer courseId, TUser tUser, String courseName,
			Float courseStarLevel, Float courseDiffLevel, Boolean courseStatus,String courseImg) {
		this.courseId = courseId;
		this.tUser = tUser;
		this.courseName = courseName;
		this.courseStarLevel = courseStarLevel;
		this.courseDiffLevel = courseDiffLevel;
		this.courseStatus = courseStatus;
		this.courseImg = courseImg;
	}

	/** full constructor */
	public TCourse(Integer courseId, TUser tUser, String courseName,
			String courseIntro, Float courseStarLevel, Float courseDiffLevel,
			String courseLabel, String courseType, String courseLive,
			Boolean courseStatus, Set<TSc> TScs, Set<TChapter> TChapters,
			Set<TCoursecomment> TCoursecomments, Set<THomework> THomeworks) {
		this.courseId = courseId;
		this.tUser = tUser;
		this.courseName = courseName;
		this.courseIntro = courseIntro;
		this.courseStarLevel = courseStarLevel;
		this.courseDiffLevel = courseDiffLevel;
		this.courseLabel = courseLabel;
		this.courseType = courseType;
		this.courseLive = courseLive;
		this.courseStatus = courseStatus;
		this.TScs = TScs;
		this.TChapters = TChapters;
		this.TCoursecomments = TCoursecomments;
		this.THomeworks = THomeworks;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "course_id", unique = true, nullable = false)
	public Integer getCourseId() {
		return this.courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "teacher_id", nullable = false)
	public TUser getTUser() {
		return this.tUser;
	}

	public void setTUser(TUser tUser) {
		this.tUser = tUser;
	}

	@Column(name = "course_name", nullable = false, length = 100)
	public String getCourseName() {
		return this.courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	@Column(name = "course_intro", length = 500)
	public String getCourseIntro() {
		return this.courseIntro;
	}

	public void setCourseIntro(String courseIntro) {
		this.courseIntro = courseIntro;
	}

	@Column(name = "course_star_level", nullable = false, precision = 12, scale = 0)
	public Float getCourseStarLevel() {
		return this.courseStarLevel;
	}

	public void setCourseStarLevel(Float courseStarLevel) {
		this.courseStarLevel = courseStarLevel;
	}

	@Column(name = "course_diff_level", nullable = false, precision = 12, scale = 0)
	public Float getCourseDiffLevel() {
		return this.courseDiffLevel;
	}

	public void setCourseDiffLevel(Float courseDiffLevel) {
		this.courseDiffLevel = courseDiffLevel;
	}

	@Column(name = "course_label", length = 100)
	public String getCourseLabel() {
		return this.courseLabel;
	}

	public void setCourseLabel(String courseLabel) {
		this.courseLabel = courseLabel;
	}

	@Column(name = "course_type", length = 100)
	public String getCourseType() {
		return this.courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	@Column(name = "course_live", length = 100)
	public String getCourseLive() {
		return this.courseLive;
	}

	public void setCourseLive(String courseLive) {
		this.courseLive = courseLive;
	}

	@Column(name = "course_status", nullable = false)
	public Boolean getCourseStatus() {
		return this.courseStatus;
	}

	public void setCourseStatus(Boolean courseStatus) {
		this.courseStatus = courseStatus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TCourse")
	public Set<TSc> getTScs() {
		return this.TScs;
	}

	public void setTScs(Set<TSc> TScs) {
		this.TScs = TScs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TCourse")
	public Set<TChapter> getTChapters() {
		return this.TChapters;
	}

	public void setTChapters(Set<TChapter> TChapters) {
		this.TChapters = TChapters;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TCourse")
	public Set<TCoursecomment> getTCoursecomments() {
		return this.TCoursecomments;
	}

	public void setTCoursecomments(Set<TCoursecomment> TCoursecomments) {
		this.TCoursecomments = TCoursecomments;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TCourse")
	public Set<THomework> getTHomeworks() {
		return this.THomeworks;
	}

	public void setTHomeworks(Set<THomework> THomeworks) {
		this.THomeworks = THomeworks;
	}

	@Column(name = "course_img", length = 300)
	public String getCourseImg() {
		return courseImg;
	}

	public void setCourseImg(String courseImg) {
		this.courseImg = courseImg;
	}

	@Column(name = "student_num")
	public int getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(int studentNum) {
		this.studentNum = studentNum;
	}
	
}