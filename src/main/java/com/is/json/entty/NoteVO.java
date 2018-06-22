package com.is.json.entty;

import java.util.List;

import com.is.util.RelativeDateFormat;

public class NoteVO {
	
	private int nodeId;
	private String title;
	private String time;
	private int statNum;
	private int skimNum;
	private String content;
	private Integer classify;
	private String classifyName;
	private String tagNames;
	private String tagIds;
	private String[] tagIdList;
	private String[] tagNameList;
	private String filesUrl;
	private String imgUrl;
	private boolean canStat;
	public String getTagIds() {
		return tagIds;
	}
	public void setTagIds(String tagIds) {
		this.tagIds = tagIds;
	}
	public String getFilesUrl() {
		return filesUrl;
	}
	public void setFilesUrl(String filesUrl) {
		this.filesUrl = filesUrl;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public int getNodeId() {
		return nodeId;
	}
	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getStatNum() {
		return statNum;
	}
	public void setStatNum(int statNum) {
		this.statNum = statNum;
	}
	public int getSkimNum() {
		return skimNum;
	}
	public void setSkimNum(int skimNum) {
		this.skimNum = skimNum;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getClassify() {
		return classify;
	}
	public void setClassify(Integer classify) {
		this.classify = classify;
	}
	public String getTagNames() {
		return tagNames;
	}
	public void setTagNames(String tagNames) {
		this.tagNames = tagNames;
	}
	public String getClassifyName() {
		return classifyName;
	}
	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}
	public String[] getTagIdList() {
		return tagIdList;
	}
	public void setTagIdList(String[] tagIdList) {
		this.tagIdList = tagIdList;
	}
	public String[] getTagNameList() {
		return tagNameList;
	}
	public void setTagNameList(String[] tagNameList) {
		this.tagNameList = tagNameList;
	}
	public boolean isCanStat() {
		return canStat;
	}
	public void setCanStat(boolean canStat) {
		this.canStat = canStat;
	}
	public NoteVO(int nodeId, String title, String time, int skimNum, String classifyName,
			String tagNames, String imgUrl) {
		super();
		this.nodeId = nodeId;
		this.title = title;
		this.time = time;
		this.skimNum = skimNum;
		this.classifyName = classifyName;
		this.tagNames = tagNames;
		this.imgUrl = imgUrl;
	}
	public NoteVO() {
	}
	
	
	
}
