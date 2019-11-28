package com.order.controller;

import java.util.Map;

import com.common.utils.*;
import com.order.entity.ContractEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.order.service.ContractService;

/**
* @Description: TODO
* @author Robb G C LUO
* @date 2019年11月19日
* @version v1.0
*/
@Api(tags = "合同接口")
@RestController
@RequestMapping("/contract")
public class ContractController {
	
	@Autowired
	private ContractService contractService;

	@ApiOperation("合同新增")
	@PostMapping(value = "/add")
	public ResultUtils addContract(@RequestBody ContractEntity params) {
		return contractService.addContract(params);
	}

	@ApiOperation("合同编辑")
	@PostMapping(value = "/edit")
	public ResultUtils editContract(@RequestBody ContractEntity params) {
		return contractService.editContract(params);
	}

	@ApiOperation("合同删除")
	@PostMapping(value = "/delete")
	public ResultUtils deleteContract(Long id) {
		return contractService.deleteContract(id);
	}

	@ApiOperation("获取合同列表")
	@PostMapping(value = "/list")
	public ResultUtils queryList(@RequestBody PageParams params) {
		Query query = new Query(params.toMap());
		PageUtils page = contractService.queryList(query);
		return ResultUtils.success("", page);
	}

	@ApiOperation("获取单个合同")
	@PostMapping(value = "/one")
	public ResultUtils queryOne(Long id) {
		return contractService.queryOne(id);
	}
}
