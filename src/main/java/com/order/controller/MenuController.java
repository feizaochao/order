package com.order.controller;

import com.common.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.utils.R;
import com.order.service.MenuService;

import javax.xml.transform.Result;

/**
* @Description: TODO
* @author Robb G C LUO
* @date 2019年11月18日
* @version v1.0
*/
@Api(tags = "菜单接口")
@RestController
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;

	@ApiOperation("获取菜单接口")
	@GetMapping(value = "/list")
	public ResultUtils queryMenuList() {
		return ResultUtils.success("", menuService.queryList());
	}
}
