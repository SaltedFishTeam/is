package com.is.entity;

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

@Entity
@Table(name = "t_section_message")
public class TSectionMessage {

	private Integer id;
	private String content;
	private String time;
	private TUser user;
	private TSection section;
	private Set<TSectionReply> sectionReplys = new HashSet<TSectionReply>(0);
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "section_msg_id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "content", nullable = false)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Column(name = "time", nullable=false)
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uid", nullable = false)
	public TUser getUser() {
		return user;
	}
	public void setUser(TUser user) {
		this.user = user;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "section_id", nullable = false)
	public TSection getSection() {
		return section;
	}
	public void setSection(TSection section) {
		this.section = section;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sectionMessage")
	public Set<TSectionReply> getSectionReplys() {
		return sectionReplys;
	}
	public void setSectionReplys(Set<TSectionReply> sectionReplys) {
		this.sectionReplys = sectionReplys;
	}
	
	
	
}
