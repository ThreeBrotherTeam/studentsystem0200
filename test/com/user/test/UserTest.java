package com.user.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.training.model.UserModel;
import com.training.service.UserService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserTest
{

	@Autowired
	private UserService userService;

	@Autowired
	private Md5PasswordEncoder md5Encoder;

	@Test
	public void test()
	{
		UserModel user = new UserModel();
		user.setName("tim");
		user.setMobile("18202997620");
		user.setCreateDate(new Date());
		user.setUpdateDate(new Date());

		String password = md5Encoder.encodePassword("1", user.getName());

		user.setPassword(password);
		user.setAvailable(true);
		userService.save(user);

	}

	public static void main(String[] args)
	{
	}

}
