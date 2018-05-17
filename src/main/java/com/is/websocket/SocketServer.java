package com.is.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@ServerEndpoint(value = "/socketServer/{userid}")
public class SocketServer {

	private Session session;
	public static Map<String,Session> sessionPool = new HashMap<>();
	public static Map<String,String> sessionIds = new HashMap<>();
	
	/**
	 * WebSocket连接函数
	 * @param session
	 * @param userid
	 */
	@OnOpen
	public void open(Session session,@PathParam(value="userid")String userid) {
		System.out.println("连接成功" + userid);
		this.session = session;
		sessionPool.put(userid, session);
		sessionIds.put(session.getId(), userid);
	}
	
	/**
	 * WebSocket接收消息回调函数
	 * @param message
	 */
	@OnMessage
	public void onMessage(String message) {
		System.out.println(message);
		JSONObject json = JSONObject.parseObject(message);
		switch(json.getInteger("type")) {
		case 2: sendToOther(json);break;
		}
	}
	
	private void sendToOther(JSONObject json) {
		String receiveId = json.getString("receiveId");
		sendMsg(receiveId, json.toJSONString());
	}
	
	/**
	 * WebSocket断开回调函数
	 */
	@OnClose
	public void onClose() {
		System.out.println("断开");
		sessionPool.remove(sessionIds.get(session.getId()));
		sessionIds.remove(session.getId());
	}
	
	/**
	 * WebSocket异常回调函数
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session,Throwable error) {
		error.printStackTrace();
	}

	/**
	 * 发布消息
	 * @param userId
	 * @param msg
	 */
	public static void sendMsg(String userId,String msg) {
		Session session = sessionPool.get(userId);
		try {
			session.getBasicRemote().sendText(msg);
		} catch (IOException e) {
			log.error("系统发布消息失败" + e.getMessage());
		}
	}
	
	

}
