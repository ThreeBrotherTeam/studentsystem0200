package com.training.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;

import com.training.data.UserData;
import com.training.form.UserForm;
import com.training.service.UserService;

@Controller
public class LoginController {

	@Resource
	private UserService userService;

	// Validator Spring框架里的一个接口
	@Resource
	private Validator validator;

	@RequestMapping("/login")
	public String login() {
		return "user/login";
	}

	public String login(UserForm userForm, BindingResult bindingResult, HttpSession session) {

		validator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return "user/login";
		}

		UserData userData = userService.findUser(userForm);
		if (null == userData) {
			return "user/login";
		}

		session.setAttribute("userData", userData);

		return "student/loadStudentByFields";
	}
}
