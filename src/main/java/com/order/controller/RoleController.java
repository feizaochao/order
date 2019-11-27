package com.order.controller;

import java.util.Map;

import com.common.utils.ResultUtils;
import com.order.entity.RoleEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.common.utils.R;
import com.order.service.MenuService;
import com.order.service.RoleService;

/**
* @Description: TODO
* @author lguochao
* @date 2019年11月7日
* @version V1.0
*/
@Api(tags = "角色和权限接口")
@RestController
@RequestMapping(value = "/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuService menuService;

	@ApiOperation("添加角色")
	@PostMapping(value = "/add")
	public ResultUtils addRole(@RequestBody RoleEntity role) {
		return roleService.addRole(role.getName());
	}

	@ApiOperation("编辑角色")
	@PostMapping(value = "/edit")
	public ResultUtils editRole(@RequestBody RoleEntity role) {
		return roleService.editRole(role.getId(), role.getName());
	}

	@ApiOperation("删除角色")
	@PostMapping(value = "/delete")
	public ResultUtils deleteRole(Long id) {
		return roleService.deleteRole(id);
	}

	@ApiOperation("获取角色列表")
	@GetMapping(value = "/list")
	public ResultUtils queryList() {
		return ResultUtils.success("", roleService.queryList());
	}

	@ApiOperation("保存角色菜单信息")
	@PostMapping(value = "/save_authority")
	public ResultUtils saveMenus(Long roleId, Long[] menuIds) {
		return roleService.saveMenus(roleId, menuIds);
	}
}
