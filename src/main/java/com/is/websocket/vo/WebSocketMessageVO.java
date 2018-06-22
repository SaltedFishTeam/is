package com.is.websocket.vo;


public class WebSocketMessageVO extends WebSocketBaseVO {

	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public WebSocketMessageVO(Integer uid, Integer fid, int type, String code, int mid) {
		super(uid, fid, type, code, mid);
		// TODO Auto-generated constructor stub
	}

	public WebSocketMessageVO() {
		
	}
}
