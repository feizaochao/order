package com.order.service;

import java.util.Map;

import com.common.utils.PageUtils;
import com.common.utils.Query;
import com.common.utils.R;

/**
* @Description: TODO
* @author Robb G C LUO
* @date 2019年11月19日
* @version v1.0
*/
public interface ContractService {
	
	R addContract(Map<String, Object> params);
	
	R editContract(Map<String, Object> params);
	
	R deleteContract(Long id);
	
	PageUtils queryList(Query query);
}
