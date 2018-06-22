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
 * TNode entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_node", catalog = "is")
public class TNode implements java.io.Serializable {

	// Fields

	private Integer nodeId;
	private TClassifyNode TClassifyNode;
	private String time;
	private String title;
	private String content;
	private Integer statNum;
	private Integer skimNum;
	private String nodeTagIds;
	private String nodeTagNames;
	private String filesUrl;
	private String imgUrl;
	private Integer flag;
	private TUser TUser;
	private String statIds;
	private Set<TNodeMessage> TNodeMessages = new HashSet<TNodeMessage>(0);
	// Constructors

	/** default constructor */
	public TNode() {
	}

	/** minimal constructor */
	public TNode(Integer nodeId, TClassifyNode TClassifyNode, Integer statNum,
			Integer skimNum) {
		this.nodeId = nodeId;
		this.TClassifyNode = TClassifyNode;
		this.statNum = statNum;
		this.skimNum = skimNum;
	}

	/** full constructor */
	public TNode(Integer nodeId, TClassifyNode TClassifyNode, String time,
			String title, String content, Integer statNum, Integer skimNum) {
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
	@Column(name = "node_id", unique = true)
	public Integer getNodeId() {
		return this.nodeId;
	}

	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "classify_note_id")
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

	@Column(name = "stat_num")
	public Integer getStatNum() {
		return this.statNum;
	}

	public void setStatNum(Integer statNum) {
		this.statNum = statNum;
	}

	@Column(name = "skim_num")
	public Integer getSkimNum() {
		return this.skimNum;
	}

	public void setSkimNum(Integer skimNum) {
		this.skimNum = skimNum;
	}

	@Column(name = "node_tag_ids")
	public String getNodeTagIds() {
		return nodeTagIds;
	}

	public void setNodeTagIds(String nodeTagIds) {
		this.nodeTagIds = nodeTagIds;
	}
	
	@Column(name = "node_tag_names")
	public String getNodeTagNames() {
		return nodeTagNames;
	}

	public void setNodeTagNames(String nodeTagNames) {
		this.nodeTagNames = nodeTagNames;
	}

	@Column(name = "files_url", nullable = true)
	public String getFilesUrl() {
		return filesUrl;
	}

	public void setFilesUrl(String fileUrls) {
		this.filesUrl = fileUrls;
	}

	@Column(name = "img_url")
	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	@Column(name = "flag")
	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uid", nullable = false)
	public TUser getTUser() {
		return TUser;
	}

	public void setTUser(TUser tUser) {
		this.TUser = tUser;
	}
	@Column(name = "stat_ids")
	public String getStatIds() {
		return statIds;
	}

	public void setStatIds(String statIds) {
		this.statIds = statIds;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TNode")
	public Set<TNodeMessage> getTNodeMessages() {
		return TNodeMessages;
	}

	public void setTNodeMessages(Set<TNodeMessage> tNodeMessages) {
		TNodeMessages = tNodeMessages;
	}
	
	
}