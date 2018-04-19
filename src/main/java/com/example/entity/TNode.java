package com.example.entity;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * TNode entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_node", catalog = "is")
public class TNode implements java.io.Serializable {

	// Fields

	private Long nodeId;
	private TClassifyNode TClassifyNode;
	private String time;
	private String title;
	private String content;
	private Integer statNum;
	private Long skimNum;

	// Constructors

	/** default constructor */
	public TNode() {
	}

	/** minimal constructor */
	public TNode(Long nodeId, TClassifyNode TClassifyNode, Integer statNum,
			Long skimNum) {
		this.nodeId = nodeId;
		this.TClassifyNode = TClassifyNode;
		this.statNum = statNum;
		this.skimNum = skimNum;
	}

	/** full constructor */
	public TNode(Long nodeId, TClassifyNode TClassifyNode, String time,
			String title, String content, Integer statNum, Long skimNum) {
		this.nodeId = nodeId;
		this.TClassifyNode = TClassifyNode;
		this.time = time;
		this.title = title;
		this.content = content;
		this.statNum = statNum;
		this.skimNum = skimNum;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "node_id", unique = true, nullable = false)
	public Long getNodeId() {
		return this.nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "classify_note_id", nullable = false)
	public TClassifyNode getTClassifyNode() {
		return this.TClassifyNode;
	}

	public void setTClassifyNode(TClassifyNode TClassifyNode) {
		this.TClassifyNode = TClassifyNode;
	}

	@Column(name = "time", length = 100)
	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Column(name = "title", length = 100)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content", length = 5000)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "stat_num", nullable = false)
	public Integer getStatNum() {
		return this.statNum;
	}

	public void setStatNum(Integer statNum) {
		this.statNum = statNum;
	}

	@Column(name = "skim_num", nullable = false)
	public Long getSkimNum() {
		return this.skimNum;
	}

	public void setSkimNum(Long skimNum) {
		this.skimNum = skimNum;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);
	}
}