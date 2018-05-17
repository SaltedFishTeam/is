package com.is.json.status;

import com.is.json.entty.UserVO;

public class LoginStatus extends Status {
	
	private UserVO user;
	
	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}

	public LoginStatus(String status, int code, String msg) {
		super(status, code, msg);
		// TODO Auto-generated constructor stub
	}
	
}
