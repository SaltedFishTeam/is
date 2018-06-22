package com.is.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;

import com.is.entity.TCourse;
import com.is.entity.TMessage;
import com.is.entity.TUser;
import com.is.json.entty.CourseUserVO;
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
			user.setFriends("");
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
	public boolean delFriend(Integer fid,Integer uid) {
		TUser friend = userRepository.findById(fid).get();
		TUser user = userRepository.findById(uid).get();
		String userReplace = user.getFriends().replace(String.valueOf(fid), "").replace("::", "");
		user.setFriends(userReplace);
		String friendReplace = friend.getFriends().replace(String.valueOf(uid), "").replace("::", "");
		friend.setFriends(friendReplace);
		try {
			save(user);
			save(friend);
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
	
	public PagedListHolder<CourseUserVO> teacherCourseList(int uid) {
		TUser user = userRepository.getOne(uid);
		Set<TCourse> courses = user.getTCourses();
		List<CourseUserVO> list = new ArrayList<>();
		
		for(TCourse course : courses) {
			CourseUserVO vo = new CourseUserVO();
			vo.setCid(course.getCourseId());
			vo.setCourseName(course.getCourseName());
			vo.setAvatar(course.getCourseImg());
			vo.setStudyNum(course.getTScs().size());
			vo.setType(course.getCourseType());
			list.add(vo);
		}
		Collections.sort(list);
		PagedListHolder<CourseUserVO> pages = new PagedListHolder<>(list);
		return pages;
	}
	
	public void saveAvatar(int uid,String avatar) {
		TUser tUser = userRepository.findById(uid).get();
		tUser.setAvatar(avatar);
		userRepository.save(tUser);
	}
}
