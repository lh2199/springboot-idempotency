package com.ctgu.service;

import com.ctgu.entity.User;

public interface IUserService
{
	User getUserInfoById(Integer id);

	boolean addUser(User user);

	void updateUser(User user);

	void deleteUserById(Integer id);

	void deleteUser(User user);

}
