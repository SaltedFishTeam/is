package com.is.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.is.entity.TMessage;
import com.is.entity.TUser;
import com.is.json.entty.MessageVO;
import com.is.json.entty.UserMessagesVO;
import com.is.json.status.LoadMsgsStatus;
import com.is.json.status.Status;
import com.is.service.MessageService;
import com.is.util.PackageUtil;

/**
 * 
 * 时间: 2018年5月15日下午3:01:43
 * 不爱我就拉倒   `_`   
 * by wcm in
 */
@Controller
public class MessageController {

	@Autowired
	private MessageService message;

	/**
	 * 加载未处理的消息
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/message/load",method=RequestMethod.GET)
	public @ResponseBody LoadMsgsStatus loadMessage(HttpSession session) {
		TUser user = (TUser) session.getAttribute("user");
		if(user == null) {
			LoadMsgsStatus status = new LoadMsgsStatus("fail", -1, "请登陆");
			return status;
		}
		List<UserMessagesVO> vos = message.loadMessagesByReceiveId(user.getUid());
		LoadMsgsStatus status = new LoadMsgsStatus("success", 1, "加载成功");
		status.setMsgs(vos);
		return status;
	}
	
	@RequestMapping(value="/message/load/chat/{uid}-{fid}",method=RequestMethod.GET)
	public @ResponseBody UserMessagesVO loadChatMessage(@PathVariable("uid") int uid,@PathVariable("fid") int fid) {
		List<TMessage> list = message.listChat(uid, fid);
		UserMessagesVO vo = new UserMessagesVO();
		List<MessageVO> vos = new ArrayList<MessageVO>();
		for(TMessage msg : list) {
			MessageVO msgVO = new MessageVO();
			PackageUtil.packageObject(msgVO, msg);
			msgVO.setSendId(msg.getTUserBySendId().getUid());
			msgVO.setReceiveId(msg.getTUserByReceiveId().getUid());
			vos.add(msgVO);
		}
		vo.setMessages(vos);
		return vo;
	}
	
	@RequestMapping(value="/message/read/{sendId}-{receiveId}",method=RequestMethod.GET)
	public @ResponseBody Status readMsg(@PathVariable("sendId") int sendId
			,@PathVariable("receiveId") int receiveId) {
		message.readMsg(sendId, receiveId);
		Status status = new Status("success", 1, "成功");
		return status;
	}
}
