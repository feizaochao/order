package com.order.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.common.utils.PageUtils;
import com.common.utils.Query;
import com.common.utils.R;
import com.order.service.CustomerService;

/**
* @Description: TODO
* @author Robb G C LUO
* @date 2019年11月20日
* @version v1.0
*/
@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value = "/add")
	public R addCustomer(@RequestParam Map<String, Object> params) {
		return customerService.addCustomer(params);
	}
	
	@RequestMapping(value = "/edit")
	public R editCustomer(@RequestParam Map<String, Object> params) {
		return customerService.editCustomer(params);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public R deleteCustomer(Long id) {
		return customerService.deleteCustomer(id);
	}
	
	@RequestMapping(value = "/list")
	public R queryList(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		PageUtils page = customerService.queryList(query);
		return R.ok().put("data", page);
	}

	@PostMapping(value = "/one")
	public R queryOne(Long id) {
		return customerService.queryOne(id);
	}
}
