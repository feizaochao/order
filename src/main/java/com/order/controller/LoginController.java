package com.order.controller;

import com.common.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
@Api(tags = "登录接口")
@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;

	@ApiOperation("登录")
	@PostMapping(value = "/login")
	public ResultUtils login(String username, String password) {
		return loginService.login(username, password);
	}
}
