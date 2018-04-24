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
 * TTeacher entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_teacher", catalog = "is")
public class TTeacher implements java.io.Serializable {

	// Fields

	private Integer teacherId;
	private TUser TUser;
	private String style;
	private String eduBackground;
	private String school;
	private String goodForDirection;
	private Set<TCourse> TCourses = new HashSet<TCourse>(0);

	// Constructors

	/** default constructor */
	public TTeacher() {
	}

	/** minimal constructor */
	public TTeacher(Integer teacherId, TUser TUser, String style,
			String eduBackground, String school, String goodForDirection) {
		this.teacherId = teacherId;
		this.TUser = TUser;
		this.style = style;
		this.eduBackground = eduBackground;
		this.school = school;
		this.goodForDirection = goodForDirection;
	}

	/** full constructor */
	public TTeacher(Integer teacherId, TUser TUser, String style,
			String eduBackground, String school, String goodForDirection,
			Set<TCourse> TCourses) {
		this.teacherId = teacherId;
		this.TUser = TUser;
		this.style = style;
		this.eduBackground = eduBackground;
		this.school = school;
		this.goodForDirection = goodForDirection;
		this.TCourses = TCourses;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "teacher_id", unique = true, nullable = false)
	public Integer getTeacherId() {
		return this.teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "teacher_id", unique = true, nullable = false, insertable = false, updatable = false)
	public TUser getTUser() {
		return this.TUser;
	}

	public void setTUser(TUser TUser) {
		this.TUser = TUser;
	}

	@Column(name = "style", nullable = false, length = 100)
	public String getStyle() {
		return this.style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	@Column(name = "edu_background", nullable = false, length = 100)
	public String getEduBackground() {
		return this.eduBackground;
	}

	public void setEduBackground(String eduBackground) {
		this.eduBackground = eduBackground;
	}

	@Column(name = "school", nullable = false, length = 100)
	public String getSchool() {
		return this.school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	@Column(name = "good_for_direction", nullable = false, length = 100)
	public String getGoodForDirection() {
		return this.goodForDirection;
	}

	public void setGoodForDirection(String goodForDirection) {
		this.goodForDirection = goodForDirection;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TTeacher")
	public Set<TCourse> getTCourses() {
		return this.TCourses;
	}

	public void setTCourses(Set<TCourse> TCourses) {
		this.TCourses = TCourses;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);
	}
}