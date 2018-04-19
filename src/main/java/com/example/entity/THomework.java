package com.example.entity;
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
 * THomework entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_homework", catalog = "is")
public class THomework implements java.io.Serializable {

	// Fields

	private Long homeworkId;
	private TCourse TCourse;
	private String homeworkTitle;
	private String homeworkContent;
	private String homeworkFiles;
	private Integer allgrade;
	private String startTime;
	private String endTime;
	private Set<TStudentwork> TStudentworks = new HashSet<TStudentwork>(0);

	// Constructors

	/** default constructor */
	public THomework() {
	}

	/** minimal constructor */
	public THomework(Long homeworkId, TCourse TCourse, String homeworkTitle,
			Integer allgrade, String startTime, String endTime) {
		this.homeworkId = homeworkId;
		this.TCourse = TCourse;
		this.homeworkTitle = homeworkTitle;
		this.allgrade = allgrade;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/** full constructor */
	public THomework(Long homeworkId, TCourse TCourse, String homeworkTitle,
			String homeworkContent, String homeworkFiles, Integer allgrade,
			String startTime, String endTime, Set<TStudentwork> TStudentworks) {
		this.homeworkId = homeworkId;
		this.TCourse = TCourse;
		this.homeworkTitle = homeworkTitle;
		this.homeworkContent = homeworkContent;
		this.homeworkFiles = homeworkFiles;
		this.allgrade = allgrade;
		this.startTime = startTime;
		this.endTime = endTime;
		this.TStudentworks = TStudentworks;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "homework_id", unique = true, nullable = false)
	public Long getHomeworkId() {
		return this.homeworkId;
	}

	public void setHomeworkId(Long homeworkId) {
		this.homeworkId = homeworkId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id", nullable = false)
	public TCourse getTCourse() {
		return this.TCourse;
	}

	public void setTCourse(TCourse TCourse) {
		this.TCourse = TCourse;
	}

	@Column(name = "homework_title", nullable = false, length = 100)
	public String getHomeworkTitle() {
		return this.homeworkTitle;
	}

	public void setHomeworkTitle(String homeworkTitle) {
		this.homeworkTitle = homeworkTitle;
	}

	@Column(name = "homework_content", length = 500)
	public String getHomeworkContent() {
		return this.homeworkContent;
	}

	public void setHomeworkContent(String homeworkContent) {
		this.homeworkContent = homeworkContent;
	}

	@Column(name = "homework_files", length = 500)
	public String getHomeworkFiles() {
		return this.homeworkFiles;
	}

	public void setHomeworkFiles(String homeworkFiles) {
		this.homeworkFiles = homeworkFiles;
	}

	@Column(name = "allgrade", nullable = false)
	public Integer getAllgrade() {
		return this.allgrade;
	}

	public void setAllgrade(Integer allgrade) {
		this.allgrade = allgrade;
	}

	@Column(name = "start_time", nullable = false, length = 100)
	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Column(name = "end_time", nullable = false, length = 100)
	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "THomework")
	public Set<TStudentwork> getTStudentworks() {
		return this.TStudentworks;
	}

	public void setTStudentworks(Set<TStudentwork> TStudentworks) {
		this.TStudentworks = TStudentworks;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);
	}
}