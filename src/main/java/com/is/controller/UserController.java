package com.is.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.jms.Session;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Path;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.is.entity.TMessage;
import com.is.entity.TUser;
import com.is.json.entty.UserVO;
import com.is.json.status.FindFriendStatus;
import com.is.json.status.LoadFriendsStatus;
import com.is.json.status.LoginStatus;
import com.is.json.status.RegisterStatus;
import com.is.json.status.Status;
import com.is.repository.UserRepository;
import com.is.service.MessageService;
import com.is.service.UserService;
import com.is.util.PackageUtil;
import com.is.websocket.SocketServer;
import com.is.websocket.vo.ApplyAddFriendVO;
import com.is.websocket.vo.MessageStatus;
import com.is.websocket.vo.WebSocketBaseVO;
import com.is.websocket.vo.WebSocketProtocol;
import com.is.websocket.vo.WebSocketUserVO;

import lombok.extern.slf4j.Slf4j;

/**
 * user的type为
 * 1 是管理员
 * 2 是教师
 * 3 是学生
 * @author www
 *
 */
@Slf4j
@Controller()
public class UserController {
	
	
	@Autowired
	private UserService userService;
	@Autowired
	private MessageService message;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@ResponseBody
	@RequestMapping(value = "test",method=RequestMethod.GET)
	public Status test() {
		Status status = new Status("1",10,"jk");
		return status;
	}
	
	/**
	 * 用户登陆
	 * @param session
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/user/login",method=RequestMethod.POST)
	public @ResponseBody LoginStatus login(HttpSession session,@RequestBody TUser user) {
		LoginStatus status = null;
		//防止用户没有传账号过来
		if(user.getAccount() != null) {
			//根据账号去查询对应的用户
			TUser queryUser = null;
			try {
				queryUser = userService.findByAccount(user.getAccount());
			} catch (Exception e) {
				e.printStackTrace();
			}
			//如果根据账号没有查到用户
			//登陆失败
			if(queryUser == null) {
				status = new LoginStatus("fail",0,"账号不存在");
			} else {
				//输入了账号
				if(user.getPwd() != null) {
					String inputPwd = DigestUtils.md5DigestAsHex(user.getPwd().trim().getBytes());
					if(queryUser.getPwd().equals(inputPwd)) {
						//登陆成功
						status = new LoginStatus("success", 1, "登陆成功");
						UserVO userVO = new UserVO();
						PackageUtil.packageObject(userVO, queryUser);
						status.setUser(userVO);
						//保存到session
						session.setAttribute("user", queryUser);
					} else {
						status = new LoginStatus("fail",0, "密码错误");
					}
				}
			}
		}
		if(status == null) status = new LoginStatus("fail", -1, "账号或密码没输入");
		return status;
	}
	

	/**
	 * 用户基类保存 url: /registe/user   post
	 * 首先要判断该账号是否存在
	 * 然后防止在保存用户信息的时候发生异常
	 * @param session
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/registe/user",method=RequestMethod.POST) 
	public @ResponseBody RegisterStatus registerUser(HttpSession session,@RequestBody TUser user) {
		RegisterStatus status = null;
		TUser userByAccount = null;
		try {
			userByAccount = userService.findByAccount(user.getAccount());
		} catch (Exception e) {
			log.error("UserController接到在userService层调用findByAccount抛出的异常" + e.getMessage());
			status = new RegisterStatus("fail", -1, "注册失败,系统保存信息失败。");
			return status;
		}
		if(userByAccount == null) {
			//md5加密
			String pwd = DigestUtils.md5DigestAsHex(user.getPwd().getBytes());
			user.setPwd(pwd);
			user.setFriends("");
			TUser u = userService.save(user);
			if(u == null) {
				status = new RegisterStatus("fail", -1, "注册失败,系统保存信息失败。");
			} else {
				status = new RegisterStatus("success", 1, "注册成功。");
				session.setAttribute("user", u);
				PackageUtil.packageObject(status, u);
			}
		} else {
			status = new RegisterStatus("fail", 0, "注册失败,用户已存在,请更换account。");			
		}
		return status;
	}
	
	/**
	 * 根据用户的account查找用户是否存在 url: /registe/{account} GET
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/registe/{account}",method=RequestMethod.GET)
	public @ResponseBody Status checkAccount(@PathVariable("account")String account) {
		TUser user = userService.findByAccount(account);
		Status status = null;
		if(user != null) status = new Status("fail",0,"账号已存在,不能使用");
		else status = new Status("success",1,"账号不存在,可以注册");
		return status;
	}
	
	/**
	 * 保存用户的基本信息  url: /registe
	 * 非法注册的原因是我们在账号失去焦点的时候已经检查了一遍
	 * 用户账号是可行的。然后用户才可以进行调用该函数
	 * 说明用户可能是机器人
	 * @param session
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/registe",method=RequestMethod.POST)
	public @ResponseBody RegisterStatus registe(HttpSession session,@RequestBody TUser user) {
		TUser userByAccount = userService.findByAccount(user.getAccount());
		RegisterStatus status = null;
		if(userByAccount != null) {
			status = new RegisterStatus("fail", -1, "账号已存在，非法注册");
		} else {
			String digestPwd = DigestUtils.md5DigestAsHex(user.getPwd().getBytes());
			user.setPwd(digestPwd);
			TUser save = userService.save(user);
			if(save == null) {
				status = new RegisterStatus("fail", -1, "注册失败，系统内部错误");
			} else {
				status = new RegisterStatus("success", 1, "注册成功");
				session.setAttribute("user", save);
				PackageUtil.packageObject(status, save);
			}
		}
		return status;
	}

	/**
	 * 学习风格保存
	 * @param session
	 * @param style
	 * @return
	 */
	@RequestMapping(value="/style/save",method=RequestMethod.POST)
	public @ResponseBody Status saveStle(HttpSession session,@RequestBody TUser style) {
		Status status = null;
		if(style.getStyle() != null) {
			TUser user = (TUser) session.getAttribute("user");
			user.setStyle(style.getStyle());
			userService.save(user);
			status = new Status("success",1,"风格保存成功");
		} else {
			status = new Status("fail", -1, "风格保存失败");
		}
		return status;
	}
	
	
	/**
	 * 根据用户的账号(account)
	 * 查找用户
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/user/finduser",method=RequestMethod.GET)
	public @ResponseBody FindFriendStatus findUser(HttpSession session
			,@RequestParam("account")String account) {
		TUser me = (TUser) session.getAttribute("user");
		TUser friend = userService.findByAccount(account);
		
		FindFriendStatus status = null;
		if(friend != null) {
			if(friend.getFriends().contains(":"+me.getUid()+":")) {
				status = new FindFriendStatus("success", 2, "查找成功");
				PackageUtil.packageObject(status, friend);
			}
			else {
				status = new FindFriendStatus("success", 1, "查找成功");
				PackageUtil.packageObject(status, friend);
			}
		} else {
			status = new FindFriendStatus("fail", -1, "查找失败");
		}
		return status;
	}
	
	/**
	 * 用户请求向某位同学添加好友
	 * 这里转发消息给对方
	 * 但不会加为好友
	 * 直到对方同意
	 * 再将双方好友信息修改
	 * @param session
	 * @param fid
	 * @return
	 */
	@RequestMapping(value="/user/add/{fid}",method=RequestMethod.GET)
	public @ResponseBody Status addFriend(HttpSession session,@PathVariable("fid") Integer fid) {
		TUser user = (TUser) session.getAttribute("user");
		Status status = null;
		//判断用户是否登陆
		if(user == null) {
			status = new Status("fail", -1, "请先登陆");
			return status;
		}
		//执行到这用户已登陆
		//判断加的好友是否是自己
		if(user.getUid() == fid) {
			status = new Status("fail",-1,"加的是自己!");
			return status;
		}
		
		//检查是否在重复发送好友添加请求
		//将该消息存储到数据库中
		TMessage msg = new TMessage();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:MM:ss");
		msg.setSendTime(format.format(System.currentTimeMillis()));
		msg.setType(WebSocketProtocol.APPLY_ADD_FRIEND);
		msg.setStatus(MessageStatus.UNDO);
		TMessage save = message.save(msg, user.getUid(), fid);
		if(save == null) {
			status = new Status("success",1,"请不要重复添加好友噢！");
		} else if(SocketServer.sessionPool.get(fid) == null) {
			status = new Status("success",1,"好友不在线，请稍等");
		} else {
			
			//执行到这里
			//可以将消息转发给好友
			//为了实时性使用WebSocket转发
			ApplyAddFriendVO vo = new ApplyAddFriendVO(user.getUid()
					, fid,WebSocketProtocol.APPLY_ADD_FRIEND , "1",save.getMessageId());		
			//将申请人的信息加入
			//发给被申请人审核
			UserVO userVO = new UserVO();
			PackageUtil.packageObject(userVO, user);
			vo.setUser(userVO);
			SocketServer.sendMsg(fid, JSON.toJSONString(vo));
			status = new Status("success",1,"好友添加请求发送成功");			
		}
		return status;
	}

//	/user/loadfriend
	
	/**
	 * 加载好友列表
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/user/loadfriends",method=RequestMethod.GET)
	public @ResponseBody LoadFriendsStatus loadFriend(HttpSession session) {
		TUser user = (TUser) session.getAttribute("user");
		user = userService.queryByUid(user.getUid());
		LoadFriendsStatus status = null;
		if(user == null) {
			status = new LoadFriendsStatus("fail", -1, "请先登陆");
			return status;
		}
		String friends = user.getFriends();
		if(friends == null || "".equals(friends.trim())) {
			status = new LoadFriendsStatus("success", 1, "您还没有其他的朋友");
		} else {
//			String[] split = friends.split(":");
//			System.out.println(split.length);
			try {
				List<UserVO> list = userService.batchList(friends.split(":"));
				System.out.println("好友列表");
				System.out.println(list.size());
				status = new LoadFriendsStatus("success", 1, "批量查询成功");
				status.setFriends(list);
			} catch (Exception e) {
				log.error("批量查询异常" + e.getMessage());
				status = new LoadFriendsStatus("fail", -1, "批量查询出错");
			}
		}
		return status;
	}

	/**
	 * 根据前台发来的fid
	 * 双向删除好友
	 * @param session
	 * @param fid
	 * @return
	 */
	@RequestMapping(value="/user/del/{fid}",method=RequestMethod.GET)
	public @ResponseBody Status delFriend(HttpSession session,
			@PathVariable("fid") Integer fid) {
		TUser user = (TUser) session.getAttribute("user");
		boolean flag = userService.delFriend(fid, user.getUid());
		if(flag) {
			return new Status("success",1,"删除好友成功");
		} else {
			return new Status("fail",-1,"删除好友失败");			
		}
	}
	
	@RequestMapping(value="/user/add/agree/{mid}",method=RequestMethod.GET)
	public @ResponseBody Status agreeAddFriend(HttpSession session
			,@PathVariable("mid") Integer mid) {
		TUser user = (TUser) session.getAttribute("user");
		if(user != null) {
			TMessage result = userService.agreeAddFriend(user.getUid(),mid);
			//通知客户端发送请求到各自的服务端去更新session里的user
			if(result != null) {
				WebSocketBaseVO sender = new WebSocketBaseVO();
				sender.setType(3);
				WebSocketBaseVO receiver = new WebSocketBaseVO();
				receiver.setType(3);
				SocketServer.sendMsg(result.getTUserByReceiveId().getUid()
						, JSONObject.toJSONString(sender));
				SocketServer.sendMsg(result.getTUserBySendId().getUid()
						, JSONObject.toJSONString(receiver));
				return new Status("success",1,"添加好友成功");
			}
			else return new Status("fail",-1,"添加好友失败");
		}
		return new Status("fail",-1,"添加好友失败");
	}
	
	@RequestMapping(value="/user/avatar",method = RequestMethod.POST)
	public @ResponseBody Status updateAvatar(HttpSession session,
			@RequestBody TUser avatar) {
		System.out.println(avatar.getAvatar());
		try {
			TUser user = (TUser) session.getAttribute("user");
			userService.saveAvatar(user.getUid(), avatar.getAvatar());
		}catch (Exception e) {
			return new Status("fail", -1, ExceptionUtils.getFullStackTrace(e));
		}
		return new Status("success", 1,"成功");
	}
	
	@RequestMapping(value="/user/exit",method=RequestMethod.GET)
	public @ResponseBody Status exit(HttpSession session) {
		session.setAttribute("user", null);
		return new Status("success", 1, "成功");
	}
	
	@RequestMapping(value="/user/refuse",method=RequestMethod.POST)
	public @ResponseBody Status refuse(@RequestBody Map<String,Object> params) {
		Integer mid = (Integer) params.get("mid");
		if(mid != null) {
			message.delMsg(mid);
			return new Status("success", 1, "成功");
		} else {
			return new Status("fail", -1, "失败");
		}
	}

}
