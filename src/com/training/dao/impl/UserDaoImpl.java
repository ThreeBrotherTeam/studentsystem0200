package com.training.dao.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.training.common.dao.CommonDao;
import com.training.dao.UserDao;
import com.training.data.UserData;
import com.training.model.UserModel;

public class UserDaoImpl implements UserDao {

	private CommonDao commonDao;

	@Override
	public UserData queryUserByNameAndPassword(String name, String password) {
		JdbcTemplate jdbcTemplate = commonDao.getJdbcTemplate();
		String sql = "select * from users where name=? and password=?";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, new Object[] { name, password });

		if (list == null) {
			return null;
		}
		for (Map<String, Object> map : list) {
			UserModel userModel = new UserModel();
			Integer id = (Integer) map.get(UserModel.ID);
			String userName = (String) map.get(UserModel.NAME);
			String mobile = (String) map.get(UserModel.MOBILE);
			boolean available = (boolean) map.get(UserModel.AVAILABLE);
			Timestamp createDate = (Timestamp) map.get(UserModel.CREATEDATE);
			userModel.setId(id);
			userModel.setName(userName);
			userModel.setMobile(mobile);
			userModel.setAvailable(available);
			userModel.setCreateDate(createDate);
			UserData userData = new UserData();
			userData.setId(userModel.getId());
			userData.setCreateDate(userModel.getCreateDate());
			userData.setMobile(userModel.getMobile());
			userData.setName(userModel.getName());
			userData.setUpdateDate(userModel.getUpdateDate());
			return userData;
		}
		return null;
	}

	public CommonDao getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

}
