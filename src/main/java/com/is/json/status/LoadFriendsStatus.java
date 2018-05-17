package com.is.json.status;

import java.util.List;

import com.is.json.entty.UserVO;

public class LoadFriendsStatus extends Status {

	private List<UserVO> friends;
	
	public List<UserVO> getFriends() {
		return friends;
	}


	public void setFriends(List<UserVO> friends) {
		this.friends = friends;
	}


	public LoadFriendsStatus(String status, int code, String msg) {
		super(status, code, msg);
		// TODO Auto-generated constructor stub
	}

	
}
