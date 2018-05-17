package com.is.json.entty;

import java.util.List;

public class UserMessagesVO {

	private List<MessageVO> messages;
	
	private UserVO user;
	
	//跟MessageVO里面的type一致
	private int type;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<MessageVO> getMessages() {
		return messages;
	}

	public void setMessages(List<MessageVO> messages) {
		this.messages = messages;
	}

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}
	
	
}
