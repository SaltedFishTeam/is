package com.is.demo;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.is.controller.CustomerController;
import com.is.entity.TUser;
import com.is.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	private MockMvc mvc;
	
	@Autowired
	public UserRepository userRepository;
	
	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(new CustomerController()).build();
	}
	
	
	@Test
	public void getHello() throws Exception {
		System.out.println("测试");
//		mvc.perform(MockMvcRequestBuilders
//				.get("/hello").accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk())
//				.andExpect(content().string(equalTo("Hell0 World")));
		TUser user = userRepository.findByAccount("0154025");
		if(user != null) System.out.println(user.toString());
		else System.out.println("为空");
	}

}
