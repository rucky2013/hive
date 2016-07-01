package com.hive.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hive.api.IUserService;
import com.hive.model.User;

public class ServiceTest extends BaseTest{
	@Autowired
	private IUserService userService;
	@Test
	public void selectUserByParmaryKey(){
		List<User> list=userService.getUser();
		System.out.println(list);
	}
	
}
