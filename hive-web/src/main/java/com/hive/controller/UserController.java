package com.hive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hive.apiversion.ApiVersion;
import com.hive.model.User;

@Controller
@RequestMapping(value="user/{version}")
public class UserController {
	@ResponseBody
	@ApiVersion(1)
	@RequestMapping(value="insert")
	public User insert(String abc){
		System.out.println("abc="+abc);
		User user=new User();
		user.setUserName("张三");
		return user;
	}
}
