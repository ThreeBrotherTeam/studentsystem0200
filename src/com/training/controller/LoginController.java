package com.training.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.training.service.UserService;

@Controller
public class LoginController {

	private UserService userService;

	@RequestMapping("/login")
	public String login() {
		return "user/login";
	}
}
