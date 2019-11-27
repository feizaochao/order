package com.order.controller;

import java.util.Map;

import com.common.utils.*;
import com.order.entity.AreaEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.order.service.AreaService;

/**
* @Description: TODO
* @author Robb G C LUO
* @date 2019年11月20日
* @version v1.0
*/
@Api(tags = "片区接口")
@RestController
@RequestMapping("/area")
public class AreaController {

	@Autowired
	private AreaService areaService;

	@ApiOperation("片区新增")
	@PostMapping(value = "/add")
	public ResultUtils addArea(@RequestBody AreaEntity area) {
		return areaService.addArea(area);
	}

	@ApiOperation("片区编辑")
	@PostMapping(value = "/edit")
	public ResultUtils editArea(@RequestBody AreaEntity area) {
		return areaService.editArea(area);
	}

	@ApiOperation("片区删除")
	@PostMapping(value = "/delete")
	public ResultUtils deleteArea(Long id) {
		return areaService.deleteArea(id);
	}

	@ApiOperation("获取片区列表")
	@PostMapping(value = "/list")
	public ResultUtils queryList(@RequestBody PageParams params) {
		Query query = new Query(params.toMap());
		PageUtils data = areaService.queryList(query);
		return ResultUtils.success("", data);
	}

	@ApiOperation("获取所有片区信息")
	@GetMapping(value = "/all")
	public ResultUtils queryAll() {
		return areaService.queryAll();
	}
}
