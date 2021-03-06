package com.training.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.training.common.service.CommonService;
import com.training.dao.UserDao;
import com.training.data.UserData;
import com.training.form.UserForm;
import com.training.model.UserModel;
import com.training.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao userDao;
	private CommonService commonService;
	private Md5PasswordEncoder md5Encoder;

	@Override
	public UserData findUser(UserForm userForm) {
		UserData userData = new UserData();
		Map<String, Object> fields = new HashMap<String, Object>();
		fields.put(UserModel.NAME, userForm.getName());
		String password = md5Encoder.encodePassword(userForm.getPassword(), userForm.getName());
		fields.put(UserModel.PASSWORD, password);
		List<UserModel> userModels = commonService.getEntitiesByFields(UserModel.class, fields);
		List<UserData> userDatas = new ArrayList<UserData>();
		if (null != userModels) {
			for (UserModel model : userModels) {
				UserData data = new UserData();
				data.setId(model.getId());
				data.setName(model.getName());
				data.setMobile(model.getMobile());
				data.setCreateDate(model.getCreateDate());
				data.setUpdateDate(model.getUpdateDate());
				userDatas.add(data);
			}
			userData = userDatas.get(0);
		}
		return userData;
	}

	@Override
	public UserData queryUserByNameAndPassword(String name, String password) {
		String newPassword = md5Encoder.encodePassword(password, name);
		return userDao.queryUserByNameAndPassword(name, newPassword);
	}

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

	public Md5PasswordEncoder getMd5Encoder() {
		return md5Encoder;
	}

	public void setMd5Encoder(Md5PasswordEncoder md5Encoder) {
		this.md5Encoder = md5Encoder;
	}

}
