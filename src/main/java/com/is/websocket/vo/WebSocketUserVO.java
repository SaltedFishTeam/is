package com.is.websocket.vo;

import com.is.json.entty.UserVO;

public class WebSocketUserVO extends WebSocketBaseVO {

	private UserVO user;
	
	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}

	public WebSocketUserVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WebSocketUserVO(String uid, String fid, int type, String code, int mid) {
		super(uid, fid, type, code, mid);
		// TODO Auto-generated constructor stub
	}

	
	
}
