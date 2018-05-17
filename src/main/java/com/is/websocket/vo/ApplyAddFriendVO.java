package com.is.websocket.vo;

import com.is.json.entty.UserVO;

public class ApplyAddFriendVO extends WebSocketBaseVO {

	private UserVO user;

	public ApplyAddFriendVO(String uid, String fid, int type, String code, int mid) {
		super(uid, fid, type, code, mid);
		// TODO Auto-generated constructor stub
	}

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}
	
	
}
