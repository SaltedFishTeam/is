package com.is.websocket.vo;

import org.springframework.web.socket.WebSocketMessage;

public class WebSocketMessageVO extends WebSocketBaseVO {

	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public WebSocketMessageVO(String uid, String fid, int type, String code, int mid) {
		super(uid, fid, type, code, mid);
		// TODO Auto-generated constructor stub
	}

	public WebSocketMessageVO() {
		
	}
}
