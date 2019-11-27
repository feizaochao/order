package com.order.service;

import java.util.Map;

import com.common.utils.PageUtils;
import com.common.utils.Query;
import com.common.utils.R;

/**
* @Description: TODO
* @author Robb G C LUO
* @date 2019年11月20日
* @version v1.0
*/
public interface CustomerService {

	R addCustomer(Map<String, Object> params);
	
	R editCustomer(Map<String, Object> params);
	
	R deleteCustomer(Long id);
	
	PageUtils queryList(Query query);

	R queryOne(Query query);
}
