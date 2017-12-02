package com.training.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.training.common.service.SMSService;


@Controller
@RequestMapping("/sms")
public class SMSController
{

	private static final String SMS_CODE = "smsCode";

	@Autowired
	private SMSService smsService;

	@RequestMapping
	@ResponseBody
	public void code(HttpSession session, @RequestParam(value = "mobile") String mobile)
	{

		if (mobile == null)
		{

		}
		String smsCode = smsService.generator();
		System.out.println("mobile :" + mobile + ",smsCode:" + smsCode);
		session.setAttribute(SMS_CODE, smsCode);

		//send to mobile

	}

	//@RequestMapping(name = "/hello")
	public void test(HttpSession session)
	{
		System.out.println("...test hello...");
	}
}
