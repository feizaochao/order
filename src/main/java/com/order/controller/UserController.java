package com.order.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.common.utils.PageUtils;
import com.common.utils.Query;
import com.common.utils.R;
import com.order.service.UserService;

/**
* @Description: TODO
* @author Robb G C LUO
* @date 2019年11月18日
* @version v1.0
*/
@RestController
@RequestMapping("/user")
public class UserController {
 
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/list")
	public R queryList(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		PageUtils pageUtils = userService.querylist(query);
		return R.ok().put("data", pageUtils);
	}
	
	@RequestMapping(value = "/add")
	public R addUser(@RequestParam Map<String, Object> params) {
		return userService.addUser((String)params.get("name"), (String)params.get("password"), Long.valueOf((String) params.get("roleId")));
	}
	
	@RequestMapping(value = "/edit")
	public R editUser(@RequestParam Map<String, Object> params) {
		return userService.editUser(Long.valueOf((String) params.get("userId")), (String)params.get("name"), (String)params.get("password"), Long.valueOf((String) params.get("roleId")));
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public R deleteUser(Long userId) {
		return userService.deleteUser(userId);
	}
}
