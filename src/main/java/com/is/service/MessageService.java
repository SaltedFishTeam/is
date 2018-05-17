package com.is.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.entity.TMessage;
import com.is.entity.TUser;
import com.is.json.entty.MessageVO;
import com.is.json.entty.UserMessagesVO;
import com.is.json.entty.UserVO;
import com.is.repository.MessageRepository;
import com.is.repository.UserRepository;
import com.is.util.PackageUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MessageService {

	@Autowired
	private MessageRepository message;
	@Autowired
	private UserRepository user;
	
	/**
	 * 修改或保存信息
	 * @param msg
	 */
	public TMessage save(TMessage msg,Integer sendId,Integer receiveId) {
		Optional<TUser> send = user.findById(sendId);
		Optional<TUser> receive = user.findById(receiveId);
		TMessage result = null;
		try {
			result = message.findAddFriendMessage(receive.get(), send.get(), msg.getStatus(), msg.getType());
		} catch(Exception e) {
			log.error("查询好友添加请求是否已存在出错" + e.getMessage());
			return null;
		}
		if(result != null) return null;
		msg.setTUserBySendId(send.get());
		msg.setTUserByReceiveId(receive.get());
		TMessage newMsg = message.save(msg);
		return newMsg;
	}
	
	/**
	 * 
	 * 根据id查询消息
	 * @param mid
	 * 
	 * @return
	 */
	public TMessage searchByMid(Integer mid) {
		Optional<TMessage> optional = message.findById(mid);
		return optional.get();
	}
	
	/**
	 * 根据uid来查询未处理消息
	 * @param receiveId
	 * @return
	 */
	public List<UserMessagesVO> loadMessagesByReceiveId(int receiveId) {
		Optional<TUser> optional = user.findById(receiveId);
		TUser receiver = optional.get();
		List<TUser> senders = message.findSender(receiver);
		List<UserMessagesVO> vos = new ArrayList<UserMessagesVO>();
		for(TUser sender : senders) {
			UserMessagesVO vo = new UserMessagesVO();
			List<TMessage> list = message.findByReceiveId(receiver, sender);
			List<MessageVO> msgs = new ArrayList<MessageVO>();
			UserVO userVO = new UserVO();
			PackageUtil.packageObject(userVO, sender);
			vo.setUser(userVO);
			vo.setMessages(msgs);
			for(TMessage msg : list) {
				MessageVO message = new MessageVO();
				PackageUtil.packageObject(message, msg);
				message.setReceiveId(msg.getTUserByReceiveId().getUid());
				message.setSendId(msg.getTUserBySendId().getUid());
				msgs.add(message);
				vo.setType(msg.getType());
			}
			vos.add(vo);
		}
		return vos;
	}
}
