package com.is.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.entity.TMessage;
import com.is.entity.TUser;
import com.is.json.entty.UserVO;
import com.is.repository.MessageRepository;
import com.is.repository.UserRepository;
import com.is.util.PackageUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MessageRepository messageRepository;
	public UserRepository getUserRepository(){
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public TUser queryByUid(int uid) {
		Optional<TUser> optional = null;
		try {
			optional = userRepository.findById(uid);
		} catch(Exception e) {
			log.error("根据uid查询用户异常" + e.getMessage());
		}
		return optional == null ? null : optional.get();
	}
	/**
	 * 查询所有用户
	 * @return
	 */
	public List<TUser> query(){
		return userRepository.findAll();
		
	}
	
	/**
	 * 根据用户的账号查询用户
	 * 抛出异常代表账号不为空
	 * 但是系统查询异常
	 * @param account
	 * @return
	 * @throws Exception 
	 */
	public TUser findByAccount(String account) {
		TUser user = null;
		try {
			if(account != null)
				user = userRepository.findByAccount(account);
		}catch(Exception e) {
			log.error("根据用户账号查询用户失败" + e.getMessage());
		}
		return user;
	}
	
	/**
	 * 当uid 不存在或者为-1时
	 * 用户基类注册
	 * 当uid存在时
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	public TUser save(TUser user) {
		TUser u = null;
		try {
			u = userRepository.save(user);
		} catch(Exception e) {
			log.error("注册账号报错" + e.getMessage());
		}

		return u;
	}
	
	public List<UserVO> batchList(String[] ids) throws Exception {
		System.out.println(ids.length);
		List<UserVO> list = new ArrayList<UserVO>(); 
		for(int i = 0; i < ids.length; i++) {
			if("".equals(ids[i])) continue;
			try {
				Optional<TUser> user = userRepository.findById(Integer.valueOf(ids[i]));
				UserVO userVO = new UserVO();
				PackageUtil.packageObject(userVO, user.get());
				list.add(userVO);
			} catch(Exception e) {
				log.error("批量查询好友失败" + e.getMessage());
				throw new Exception("批量查询好友失败");
			}
		}
		return list;
	}
	
	/**
	 * 删除好友
	 * @param fid
	 * 好友的uid
	 * @param user
	 * @return
	 */
	public boolean delFriend(String fid,TUser user) {
		String replace = user.getFriends().replace(fid, "").replace("::", "");
		user.setFriends(replace);
		try {
			save(user);
		}catch(Exception e) {
			log.error("删除好友失败" + e.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * 添加好友成功则返回true
	 * 添加好友失败则返回false
	 * @param uid
	 * @param fid
	 * @return
	 */
	public TMessage agreeAddFriend(Integer uid,Integer mid) {
		TMessage message = null;
		try {
			message = messageRepository.getOne(mid);
			TUser user = userRepository.getOne(uid);
			TUser friend = userRepository.getOne(message.getTUserBySendId().getUid());
			user.setFriends(user.getFriends() + ":" + message.getTUserBySendId().getUid()+":");
			friend.setFriends(friend.getFriends() + ":" + uid+":");
			userRepository.save(user);
			userRepository.save(friend);
			messageRepository.delete(message);
		}catch(Exception e) {
			log.error("添加好友失败" + e.getMessage());
			return message;
		}
		return message;
	
		
	}
}
