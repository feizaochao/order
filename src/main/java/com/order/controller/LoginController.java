package com.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.common.utils.R;
import com.order.service.LoginService;

/**
* @Description: TODO
* @author Robb G C LUO
* @date 2019年11月16日
* @version v1.0
*/
@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public R login(String username, String password) {
		return loginService.login(username, password);
	}
}
