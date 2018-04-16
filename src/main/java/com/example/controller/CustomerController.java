package com.example.controller;

import java.text.DateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bean.Customer;
import com.example.bean.CustomerRepository;
import com.example.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	
	
	public CustomerService getCustomerService() {
		return customerService;
	}



	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}



	@RequestMapping("/add")
	public String index(Model m) {
		Customer customer = new Customer(-1,"java进阶篇",10);
		Customer add = customerService.add(customer);
		System.out.println(add);
		m.addAttribute("customer", add);
		return "hello";
	}
}
