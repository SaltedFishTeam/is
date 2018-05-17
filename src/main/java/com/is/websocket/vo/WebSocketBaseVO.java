package com.is.websocket.vo;

/**
 * 
 * 时间: 2018年5月15日上午8:57:10
 * today is a sunny day.   `_`   
 * by wcm in dormitory
 */
public class WebSocketBaseVO {
//	{uid,fid,type,code,msg}
	//发送者id
	private String uid;
	//接收者id
	private String fid;
	//消息类型
	private int type;
	//状态码
	private String code;
	//消息id
	private int mid;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public WebSocketBaseVO(String uid, String fid, int type, String code, int mid) {
		super();
		this.uid = uid;
		this.fid = fid;
		this.type = type;
		this.code = code;
		this.mid = mid;
	}
	public WebSocketBaseVO() {
	}
	
}
