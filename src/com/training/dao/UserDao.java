package com.training.dao;

import com.training.data.UserData;

public interface UserDao {

	UserData queryUserByNameAndPassword(String name, String newPassword);

}
