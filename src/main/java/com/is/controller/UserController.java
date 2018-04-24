package com.is.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.is.entity.TUser;
import com.is.jsonbean.LoginStatus;
import com.is.jsonbean.RegisterStatus;
import com.is.repository.UserRepository;
import com.is.service.UserService;
import com.is.util.PackageUtil;

@Controller
public class UserController {
	@Autowired
	UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@ResponseBody
	@RequestMapping(value = "login",method=RequestMethod.GET)
	public LoginStatus longin(HttpSession session,TUser user) {
		TUser login = userService.login(user);
		LoginStatus status = null;
		try{
			if(login.getAccount().equals(user)&& login.getPwd().equals(user)){
				status.setCode(status.SUCCESS);
				status.setMsg(status.SUCCESS_LOGIN_MSG);
				PackageUtil.packageObject(status, login);
			} else {
				status.setCode(status.FAILED);
				status.setMsg(status.FAILED_LOGIN_MSG);
			}
		}catch(Exception e){
			status.setCode(status.FAILED);
			status.setMsg(status.FAILED_LOGIN_MSG);
		}
		return status;
	}
	
	@RequestMapping(value = "register",method = RequestMethod.GET)
	public RegisterStatus register(HttpSession session,TUser user){ 
		TUser register = userService.findByAccount(user.getAccount());
		RegisterStatus status = null;
		if(!register.getAccount().equals(user.getAccount())){
			userService.register(user);
			status.setCode(status.SUCCESS);
			status.setMsg(status.SUCCESS_REGISTER_MSG);
			PackageUtil.packageObject(status, register);
		}else {
			status.setCode(status.FAILED);
			status.setMsg(status.FAILED_REGISTER_MSG);
		}
		
		return status;
		
	}
	
}
