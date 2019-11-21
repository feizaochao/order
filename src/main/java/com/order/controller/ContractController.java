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
import com.order.service.ContractService;

/**
* @Description: TODO
* @author Robb G C LUO
* @date 2019年11月19日
* @version v1.0
*/
@RestController
@RequestMapping("/contract")
public class ContractController {
	
	@Autowired
	private ContractService contractService;
	
	@RequestMapping(value = "/add")
	public R addContract(@RequestParam Map<String, Object> params) {
		return contractService.addContract(params);
	}
	
	@RequestMapping(value = "/edit")
	public R editContract(@RequestParam Map<String, Object> params) {
		return contractService.editContract(params);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public R deleteContract(Long id) {
		return contractService.deleteContract(id);
	}
	
	@RequestMapping(value = "/list")
	public R queryList(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		PageUtils page = contractService.queryList(query);
		return R.ok().put("data", page);
	}
}
