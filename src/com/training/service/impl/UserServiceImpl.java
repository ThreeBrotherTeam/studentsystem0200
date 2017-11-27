package com.training.service.impl;

import com.training.common.service.CommonService;
import com.training.dao.UserDao;
import com.training.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao userDao;
	private CommonService commonService;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

}
