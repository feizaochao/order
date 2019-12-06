package com.order.service;

import java.util.List;
import java.util.Map;

import com.common.utils.PageUtils;
import com.common.utils.Query;
import com.common.utils.R;
import com.common.utils.ResultUtils;
import com.order.entity.ContractEntity;

/**
* @Description: TODO
* @author Robb G C LUO
* @date 2019年11月19日
* @version v1.0
*/
public interface ContractService {
	
	ResultUtils addContract(ContractEntity params);

	ResultUtils editContract(ContractEntity params);

	ResultUtils deleteContract(Long id);
	
	PageUtils queryList(Query query);

	ResultUtils queryOne(Long id);

	List<ContractEntity> exportAllData();
}
