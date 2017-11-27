package com.training.dao.impl;

import com.training.common.dao.CommonDao;
import com.training.dao.UserDao;

public class UserDaoImpl implements UserDao {

	private CommonDao commonDao;

	public CommonDao getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

}
