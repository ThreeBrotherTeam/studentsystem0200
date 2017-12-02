package com.training.common.service.impl;

import org.apache.commons.lang.RandomStringUtils;

import com.training.common.service.SMSService;


public class SMSServiceImpl implements SMSService
{
	//默认验证码长度 4 位
	private int count = 4;

	public SMSServiceImpl(int count)
	{
		this.count = count;
	}

	public SMSServiceImpl()
	{
	}

	@Override
	public String generator()
	{
		String result = RandomStringUtils.randomNumeric(this.count);
		return result;
	}

	@Override
	public String generator(int count)
	{
		String result = RandomStringUtils.randomNumeric(count);
		return result;
	}

}
