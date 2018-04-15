package com.example.controller;

import java.text.DateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class CustomerController {

	@RequestMapping("/hello")
	public String index(Model m) {
		m.addAttribute("now", DateFormat
				.getDateTimeInstance().format(new Date()));
		return "hello";
	}
}
