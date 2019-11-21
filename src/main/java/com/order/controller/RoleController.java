package com.order.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.common.utils.R;
import com.order.service.MenuService;
import com.order.service.RoleService;

/**
* @Description: TODO
* @author lguochao
* @date 2019年11月7日
* @version V1.0
*/
@RestController
@RequestMapping(value = "/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuService menuService;
	
	@RequestMapping(value = "/add")
	public R addRole(@RequestParam Map<String, Object> params) {
		return roleService.addRole((String)params.get("name"));
	}
	
	@RequestMapping(value = "/edit")
	public R editRole(@RequestParam Map<String, Object> params) {
		return roleService.editRole(Long.valueOf((String) params.get("roleId")), (String)params.get("name"));
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public R deleteRole(Long id) {
		return roleService.deleteRole(id);
	}
	
	@RequestMapping(value = "/list")
	public R queryList() {
		return R.ok().put("data", roleService.queryList());
	}
	
	@RequestMapping(value = "/save_authority", method = RequestMethod.POST)
	public R saveMenus(Long roleId, Long[] menuIds) {
		return roleService.saveMenus(roleId, menuIds);
	}
}
