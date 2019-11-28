package com.order.controller;

import java.util.Map;

import com.common.utils.*;
import com.order.entity.OrderEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.order.service.OrderService;

/**
* @Description: TODO
* @author Robb G C LUO
* @date 2019年11月21日
* @version v1.0
*/
@Api(tags = "订单接口")
@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping(value = "/add")
	@ApiOperation("订单新增")
	public ResultUtils addOrder(@RequestBody OrderEntity params) {
		return orderService.addOrder(params);
	}
	
	@PostMapping(value = "/edit")
	@ApiOperation("订单编辑")
	public ResultUtils editOrder(@RequestBody OrderEntity params) {
		return orderService.editOrder(params);
	}
	
	@PostMapping(value = "/delete")
	@ApiOperation("订单删除")
	public ResultUtils deleteOrder(Long id) {
		return orderService.deleteOrder(id);
	}
	
	@PostMapping(value = "/list")
	@ApiOperation("获取订单列表")
	public ResultUtils queryList(@RequestBody PageParams params) {
		Query query = new Query(params.toMap());
		PageUtils page = orderService.queryList(query);
		return ResultUtils.success("", page);
	}

	@PostMapping("/one")
	@ApiOperation("获取单个订单")
	public ResultUtils queryOne(Long id) {
		return orderService.queryOne(id);
	}
}
