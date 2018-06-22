package com.is.json.entty;


public class SectionVO implements Comparable<SectionVO> {

	private Integer sectionId;
	private String sectionName;
	private String sectionContent;
	private Integer order;
	private boolean status = false;
	private Integer flag;
	public Integer getSectionId() {
		return sectionId;
	}
	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public String getSectionContent() {
		return sectionContent;
	}
	public void setSectionContent(String sectionContent) {
		this.sectionContent = sectionContent;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	@Override
	public int compareTo(SectionVO o) {
		System.out.println("排序");
		if(this.order > o.getOrder()) return 1;
		else return -1;
	}
}
