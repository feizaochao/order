package com.order.controller;

import java.util.HashMap;
import java.util.Map;

import com.common.utils.*;
import com.order.entity.OrderEntity;
import com.order.entity.UserEntity;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.order.service.UserService;

/**
* @Description: TODO
* @author Robb G C LUO
* @date 2019年11月18日
* @version v1.0
*/
@Api(tags = "用户接口")
@RestController
@RequestMapping("/user")
public class UserController {
 
	@Autowired
	private UserService userService;

	@ApiOperation("获取用户列表")
	@PostMapping(value = "/list")
	public ResultUtils queryList(@RequestBody PageParams params) {
		Query query = new Query(params.toMap());
		PageUtils2<UserEntity> pageUtils = userService.queryList(query);
		ResultUtils result = ResultUtils.ok();
		result.setData(pageUtils);
		return result;
	}

	@ApiOperation("用户新增")
	@PostMapping(value = "/add")
	public ResultUtils addUser(@RequestBody UserEntity user) {
		return userService.addUser(user.getName(), user.getPassword(), user.getRoleId());
	}

	@ApiOperation("用户编辑")
	@PostMapping(value = "/edit")
	public ResultUtils editUser(@RequestBody UserEntity user) {
		return userService.editUser(user.getId(), user.getName(), user.getPassword(), user.getRoleId());
	}

	@ApiOperation("用户删除")
	@PostMapping(value = "/delete")
	public ResultUtils deleteUser(Long userId) {
		return userService.deleteUser(userId);
	}

	@ApiOperation("更改密码")
	@PostMapping(value = "/edit_password")
	public ResultUtils editPassword(String name, String oldPassword, String newPassword) {
		return userService.editPassword(name, oldPassword, newPassword);
	}
}
