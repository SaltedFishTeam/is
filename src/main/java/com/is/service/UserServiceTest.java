package com.is.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Find;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.is.entity.TUser;
import com.is.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Autowired
	public UserService userService;

	@Test
	public void test() {
		System.out.println("测试");
		TUser fndByAccount = userService.findByAccount("0154025");
		System.out.println(fndByAccount);
	}
	
}
