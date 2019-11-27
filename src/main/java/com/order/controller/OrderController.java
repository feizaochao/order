package com.order.controller;

import java.util.Map;

import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.common.utils.PageUtils;
import com.common.utils.Query;
import com.common.utils.R;
import com.order.service.OrderService;

/**
* @Description: TODO
* @author Robb G C LUO
* @date 2019年11月21日
* @version v1.0
*/
@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping(value = "/add")
	public R addOrder(@RequestParam Map<String, Object> params) {
		return orderService.addOrder(params);
	}
	
	@GetMapping(value = "/edit")
	public R editOrder(@RequestParam Map<String, Object> params) {
		return orderService.editOrder(params);
	}
	
	@PostMapping(value = "/delete")
	public R deleteOrder(Long id) {
		return orderService.deleteOrder(id);
	}
	
	@GetMapping(value = "/list")
	public R queryList(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		PageUtils page = orderService.queryList(query);
		return R.ok().put("data", page);
	}

	@PostMapping("/one")
	public R queryOne(Long id) {
		return orderService.queryOne(id);
	}
}
