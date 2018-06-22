package com.is.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.is.service.MessageService;
import com.is.websocket.vo.WebSocketBaseVO;
import com.is.websocket.vo.WebSocketMessageVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@ServerEndpoint(value = "/socketServer/{userid}")
public class SocketServer {

	private MessageService messageService;
	
	private Session session;
	private int userid;
	public static Map<Integer,Session> sessionPool = new HashMap<>();
	private static Map<Integer,List<Integer>> clazzSessions = new HashMap<>();
	private static Map<Integer,Integer> chats = new HashMap<>();
	private static ApplicationContext applicationContext;
	
	private int chatId = -1;
	
	public static void setApplicationContext(ApplicationContext context) {
		applicationContext = context;
	}
	
	public SocketServer() {
		System.out.println("开始");
	}


	public MessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	/**
	 * WebSocket连接函数
	 * @param session
	 * @param userid
	 */
	@OnOpen
	public void open(Session session,@PathParam(value="userid")int userid) {
		System.out.println("连接成功" + userid);
		this.userid = userid;
		this.session = session;
		sessionPool.put(userid, session);
		messageService = (MessageService) applicationContext
				.getBean("messageService");
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
		case 5: sendToClazz(json);break;
		case 6: System.out.println("进入" + json.getInteger("rid"));chats.put(userid, json.getInteger("rid"));break;
		}
	}
	
	private void sendToClazz(JSONObject json) {
		Integer clazzId = json.getInteger("clazzId");
		Integer sendId = json.getInteger("sendId");
		 if(!clazzSessions.containsKey(clazzId)) {
			 List<Integer> list = new ArrayList<>();
			 list.add(sendId);
			 clazzSessions.put(clazzId, list);
		 } else {
			 List<Integer> list = clazzSessions.get(clazzId);
			 if(!list.contains(sendId)) {
				 list.add(sendId);
			 }
			 for(Integer uid : list) {
				 System.out.println("sendTo" + uid);
				 sendMsg(uid, json.toJSONString());
			 }
		 }
	}

	private void sendToOther(JSONObject json) {
		Integer receiveId = json.getInteger("receiveId");
		if(sessionPool.containsKey(receiveId)) {
			sendMsg(receiveId, json.toJSONString());
			System.out.println("好友在线");
			if(chats.containsKey(receiveId) && chats.get(receiveId) == userid)
				messageService.saveChat(json,true);
			else
				messageService.saveChat(json,false);
		} else {
			System.out.println("好友不在线");
			messageService.saveChat(json,false);
			WebSocketMessageVO base = new WebSocketMessageVO(
					json.getInteger("sendId"), -1, 4, "1", -1);
			base.setMessage("您的好友还没有上线噢！");
			sendMsg(json.getInteger("sendId"),JSON.toJSONString(base));
		}
			
	}
	
	/**
	 * WebSocket断开回调函数
	 */
	@OnClose
	public void onClose() {
		System.out.println("断开");
		sessionPool.remove(this.userid);
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
	public static void sendMsg(Integer userId,String msg) {
		Session session = sessionPool.get(userId);
		try {
			session.getBasicRemote().sendText(msg);
		} catch (IOException e) {
			log.error("系统发布消息失败" + e.getMessage());
		}
	}
	
	

}
