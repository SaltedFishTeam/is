package com.is.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.entity.TUser;
import com.is.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public UserRepository getUserRepository(){
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<TUser> query(int uid){
		return userRepository.findAll();
		
	}
	
	public TUser findByAccount(String account) {
		return userRepository.findByAccount(account);
	}
	
	public boolean register(TUser user) {
		if(findByAccount(user.getAccount()) != null) {
			return false;
		} else {
			try{
				userRepository.save(user);	
			}catch (Exception e) {
				return false;
			}
			return true;
		}
	}
	
	public TUser login(TUser user){
		TUser findUser = findByAccount(user.getAccount());
		//账号不存在
		if(findUser == null){
			//账号不存在
			return null;
			//烟瘴账号
		}else if(findUser.getAccount() .equals(user.getAccount())){
			//验证密码
			if(findUser.getPwd().equals(user.getPwd())) {
				//登陆成功
				return findUser;
			} else {
				//密码错误登陆失败
				return null;
			}
			
		}  
		return null;
	}
}
