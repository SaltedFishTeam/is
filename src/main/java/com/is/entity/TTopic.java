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
 * TTopic entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_topic", catalog = "is")
public class TTopic implements java.io.Serializable {

	// Fields

	private Integer topicId;
	private TUser TUser;
	private String topicTitle;
	private String topicContent;
	private String createTime;
	private Integer award;
	private Set<TTopicmessage> TTopicmessages = new HashSet<TTopicmessage>(0);

	// Constructors

	/** default constructor */
	public TTopic() {
	}

	/** minimal constructor */
	public TTopic(Integer topicId, TUser TUser, String topicTitle, Integer award) {
		this.topicId = topicId;
		this.TUser = TUser;
		this.topicTitle = topicTitle;
		this.award = award;
	}

	/** full constructor */
	public TTopic(Integer topicId, TUser TUser, String topicTitle,
			String topicContent, String createTime, Integer award,
			Set<TTopicmessage> TTopicmessages) {
		this.topicId = topicId;
		this.TUser = TUser;
		this.topicTitle = topicTitle;
		this.topicContent = topicContent;
		this.createTime = createTime;
		this.award = award;
		this.TTopicmessages = TTopicmessages;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "topic_id", unique = true, nullable = false)
	public Integer getTopicId() {
		return this.topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uid", nullable = false)
	public TUser getTUser() {
		return this.TUser;
	}

	public void setTUser(TUser TUser) {
		this.TUser = TUser;
	}

	@Column(name = "topic_title", nullable = false, length = 100)
	public String getTopicTitle() {
		return this.topicTitle;
	}

	public void setTopicTitle(String topicTitle) {
		this.topicTitle = topicTitle;
	}

	@Column(name = "topic_content", length = 500)
	public String getTopicContent() {
		return this.topicContent;
	}

	public void setTopicContent(String topicContent) {
		this.topicContent = topicContent;
	}

	@Column(name = "create_time", length = 100)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "award", nullable = false)
	public Integer getAward() {
		return this.award;
	}

	public void setAward(Integer award) {
		this.award = award;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TTopic")
	public Set<TTopicmessage> getTTopicmessages() {
		return this.TTopicmessages;
	}

	public void setTTopicmessages(Set<TTopicmessage> TTopicmessages) {
		this.TTopicmessages = TTopicmessages;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);
	}
}