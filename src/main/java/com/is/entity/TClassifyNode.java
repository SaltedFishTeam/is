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
 * TClassifyNode entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_classify_node", catalog = "is")
public class TClassifyNode implements java.io.Serializable {

	// Fields

	private Integer classifyNotesId;
	private TUser TUser;
	private String name;
	private Set<TNode> TNodes = new HashSet<TNode>(0);

	// Constructors

	/** default constructor */
	public TClassifyNode() {
	}

	/** minimal constructor */
	public TClassifyNode(Integer classifyNotesId, TUser TUser, String name) {
		this.classifyNotesId = classifyNotesId;
		this.TUser = TUser;
		this.name = name;
	}

	/** full constructor */
	public TClassifyNode(Integer classifyNotesId, TUser TUser, String name,
			Set<TNode> TNodes) {
		this.classifyNotesId = classifyNotesId;
		this.TUser = TUser;
		this.name = name;
		this.TNodes = TNodes;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "classify_notes_id", unique = true, nullable = false)
	public Integer getClassifyNotesId() {
		return this.classifyNotesId;
	}

	public void setClassifyNotesId(Integer classifyNotesId) {
		this.classifyNotesId = classifyNotesId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uid", nullable = false)
	public TUser getTUser() {
		return this.TUser;
	}

	public void setTUser(TUser TUser) {
		this.TUser = TUser;
	}

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TClassifyNode")
	public Set<TNode> getTNodes() {
		return this.TNodes;
	}

	public void setTNodes(Set<TNode> TNodes) {
		this.TNodes = TNodes;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);
	}

	
	
}