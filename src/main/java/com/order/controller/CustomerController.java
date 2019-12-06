package com.order.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import com.alibaba.excel.EasyExcel;
import com.common.utils.*;
import com.order.entity.CustomerEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.order.service.CustomerService;

import javax.servlet.http.HttpServletResponse;

/**
* @Description: TODO
* @author Robb G C LUO
* @date 2019年11月20日
* @version v1.0
*/
@Api(tags = "客户接口")
@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@ApiOperation("客户新增")
	@PostMapping(value = "/add")
	public ResultUtils addCustomer(@RequestBody CustomerEntity customer) {
		return customerService.addCustomer(customer);
	}

	@ApiOperation("客户编辑")
	@PostMapping(value = "/edit")
	public ResultUtils editCustomer(@RequestBody CustomerEntity customer) {
		return customerService.editCustomer(customer);
	}

	@ApiOperation("客户删除")
	@PostMapping(value = "/delete")
	public ResultUtils deleteCustomer(Long id) {
		return customerService.deleteCustomer(id);
	}

	@ApiOperation("获取客户列表")
	@PostMapping(value = "/list")
	public ResultUtils queryList(@RequestBody PageParams params) {
		Query query = new Query(params.toMap());
		PageUtils page = customerService.queryList(query);
		return ResultUtils.success("", page);
	}

	@ApiOperation("获取单个客户")
	@PostMapping(value = "/one/{customerId}")
	public R queryOne(@PathVariable Long customerId, @RequestBody PageParams params) {
		Map<String, Object> map = params.toMap();
		map.put("id", customerId);
		Query query = new Query(map);
		return customerService.queryOne(query);
	}

	@GetMapping("/exportAll")
	public void exportAllData(HttpServletResponse response) throws IOException {
		response.setContentType("application/vnd.ms-excel");
		response.setCharacterEncoding("utf-8");
		String fileName = URLEncoder.encode("客户", "UTF-8");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
		EasyExcel.write(response.getOutputStream(), CustomerEntity.class).sheet("模板").doWrite(customerService.exportAllData());
	}
}
