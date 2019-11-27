package com.order.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
	
	@GetMapping(value = "/add")
	public R addContract(@RequestParam Map<String, Object> params) {
		return contractService.addContract(params);
	}
	
	@GetMapping(value = "/edit")
	public R editContract(@RequestParam Map<String, Object> params) {
		return contractService.editContract(params);
	}
	
	@PostMapping(value = "/delete")
	public R deleteContract(Long id) {
		return contractService.deleteContract(id);
	}
	
	@GetMapping(value = "/list")
	public R queryList(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		PageUtils page = contractService.queryList(query);
		return R.ok().put("data", page);
	}

	@PostMapping(value = "/one")
	public R queryOne(Long id) {
		return contractService.queryOne(id);
	}
}
