package com.hive.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hive.api.IUserService;
import com.hive.model.User;

@Controller
public class UserController {
	@Autowired
	private IUserService userService;
	@ResponseBody
	@RequestMapping(value="getUser")
	public List<User> getUser(){
		return userService.getUser();
	}
}
