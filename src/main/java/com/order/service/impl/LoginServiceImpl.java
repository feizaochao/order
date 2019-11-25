package com.order.service.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.modules.sys.service.SysUserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.utils.R;
import com.order.entity.UserEntity;
import com.order.service.LoginService;
import com.order.service.MenuService;
import com.order.service.RoleService;
import com.order.service.UserService;

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
	private SysUserTokenService tokenService;
	
	@Override
	public R login(String name, String password) {
		UserEntity user = userService.queryUserByName(name);
		// 1.查询账号信息是否正确
		if(user == null || !password.equals(user.getPassword())) {
			return R.error("账号或密码不正确");
		}

		// 2.生成Token
		tokenService.createToken(user.getId());

		// 3.查询账号角色和权限
		Long roleId = roleService.queryRoleByUserId(user.getId());
		List<Map<String, Object>> menuList = menuService.queryListByRoleId(roleId);


		return R.ok().put("menuList", menuList);
	}

}
