package com.is.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Autowired
	UserService userService;
	
	@Test
	public void test() {
		System.out.println("测试");
		System.out.println("第一次");
		System.out.println(userService.query(2).get());
		System.out.println("第二次");
		System.out.println(userService.query(2).get());
	}
}
