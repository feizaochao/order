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
import com.order.service.AreaService;

/**
* @Description: TODO
* @author Robb G C LUO
* @date 2019年11月20日
* @version v1.0
*/
@RestController
@RequestMapping("/area")
public class AreaController {

	@Autowired
	private AreaService areaService;
	
	@RequestMapping(value = "/add")
	public R addArea(@RequestParam Map<String, Object> params) {
		return areaService.addArea(params);
	}
	
	@RequestMapping(value = "/edit")
	public R editArea(@RequestParam Map<String, Object> params) {
		return areaService.editArea(params);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public R deleteArea(Long id) {
		return areaService.deleteArea(id);
	}
	
	@RequestMapping(value = "/list")
	public R queryList(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		PageUtils data = areaService.queryList(query);
		return R.ok().put("data", data);
	}
	
	@RequestMapping(value = "/all")
	public R queryAll() {
		return areaService.queryAll();
	}
}
