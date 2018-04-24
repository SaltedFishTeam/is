package com.is.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.is.entity.Customer;
import com.is.repository.CustomerRepository;
import com.is.entity.TClassifyNode;
import com.is.entity.TUser;
import com.is.service.CustomerService;
import com.is.service.UserService;

@Controller
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@Autowired
	UserService userService;
	//测试
	
	public CustomerService getCustomerService() {
		return customerService;
	}



	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}


	@RequestMapping("add")
	public String index(Model m) {
		List<TUser> list = userService.query(2);
		TUser tUser = list.get(0);
		System.out.println(tUser);
		System.out.println(tUser.getTClassifyNodes().size());
		Iterator<TClassifyNode> iterator = tUser.getTClassifyNodes().iterator();
		for(;iterator.hasNext();) {
			TClassifyNode classifyNode = iterator.next();
			System.out.println(classifyNode.getName());
		}
//		Customer customer = new Customer(-1,"java进阶篇",10);
//		Customer add = customerService.add(customer);
//		System.out.println(add);
//		m.addAttribute("customer", add);
		return "hello";
	}
}