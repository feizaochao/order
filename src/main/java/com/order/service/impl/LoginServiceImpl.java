package com.order.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.common.utils.ResultUtils;
import com.order.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.entity.UserEntity;

/**
* @Description: TODO
* @author Robb G C LUO
* @date 2019年11月15日
* @version v1.0
*/
@Service(value = "loginService")
@Transactional
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private UserTokenService userTokenService;
	
	@Override
	public ResultUtils login(String name, String password) {
		UserEntity user = userService.queryUserByName(name);
		// 1.查询账号信息是否正确
		if(user == null || !password.equals(user.getPassword())) {
			return ResultUtils.error(201, "");
		}

		// 2.生成Token
		Map<String, Object> tokenInfo = userTokenService.createToken(user.getId());

		// 3.查询账号角色和权限
		Long roleId = roleService.queryRoleByUserId(user.getId());
		List<Map<String, Object>> menuList = menuService.queryListByRoleId(roleId);

		Map<String, Object> map = new HashMap<>();
		map.put("menuList", menuList);
		map.put("username", user.getName());
		map.put("tokenInfo", tokenInfo);

		return ResultUtils.success("", map);
	}

}
