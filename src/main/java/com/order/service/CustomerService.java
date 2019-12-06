package com.order.service;

import java.util.List;
import java.util.Map;

import com.common.utils.PageUtils;
import com.common.utils.Query;
import com.common.utils.R;
import com.common.utils.ResultUtils;
import com.order.entity.CustomerEntity;

/**
* @Description: TODO
* @author Robb G C LUO
* @date 2019年11月20日
* @version v1.0
*/
public interface CustomerService {

	ResultUtils addCustomer(CustomerEntity params);

	ResultUtils editCustomer(CustomerEntity params);

	ResultUtils deleteCustomer(Long id);
	
	PageUtils queryList(Query query);

	R queryOne(Query query);

	List<CustomerEntity> exportAllData();
}
