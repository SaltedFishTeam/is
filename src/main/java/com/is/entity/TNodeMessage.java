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
 * TNodeMessage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_node_message", catalog = "is")
public class TNodeMessage implements java.io.Serializable {

	// Fields

	private Integer nodeMsgId;
	private TUser TUser;
	private String time;
	private String content;
	private TNode TNode;
	private Set<NoteReply> noteReplys = new HashSet<NoteReply>(0);
	// Constructors

	

	/** default constructor */
	public TNodeMessage() {
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TNodeMessage")
	public Set<NoteReply> getNoteReplys() {
		return noteReplys;
	}

	public void setNoteReplys(Set<NoteReply> noteReplys) {
		this.noteReplys = noteReplys;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nid", nullable = false)
	public TNode getTNode() {
		return TNode;
	}

	public void setTNode(TNode tNode) {
		TNode = tNode;
	}

	/** minimal constructor */
	public TNodeMessage(Integer nodeMsgId, TUser TUser) {
		this.nodeMsgId = nodeMsgId;
		this.TUser = TUser;
	}

	/** full constructor */
	public TNodeMessage(Integer nodeMsgId, TUser TUser, String time, String content) {
		this.nodeMsgId = nodeMsgId;
		this.TUser = TUser;
		this.time = time;
		this.content = content;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "node_msg_id", unique = true, nullable = false)
	public Integer getNodeMsgId() {
		return this.nodeMsgId;
	}

	public void setNodeMsgId(Integer nodeMsgId) {
		this.nodeMsgId = nodeMsgId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uid", nullable = false)
	public TUser getTUser() {
		return this.TUser;
	}

	public void setTUser(TUser TUser) {
		this.TUser = TUser;
	}

	@Column(name = "time", length = 50)
	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Column(name = "content", length = 500)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}