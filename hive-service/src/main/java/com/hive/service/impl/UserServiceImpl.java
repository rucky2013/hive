package com.hive.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hive.api.IUserService;
import com.hive.dao.mapper.UserMapper;
import com.hive.model.User;
@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserMapper userMapper;
	@Override
	public List<User> getUser() {
		return userMapper.getUser();
	}

}
