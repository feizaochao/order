package com.order.service;

import java.util.Map;

import com.common.utils.PageUtils;
import com.common.utils.Query;
import com.common.utils.R;

/**
* @Description: TODO
* @author Robb G C LUO
* @date 2019年11月21日
* @version v1.0
*/
public interface OrderService {

	R addOrder(Map<String, Object> params);

	R editOrder(Map<String, Object> params);
	
	R deleteOrder(Long id);
	
	PageUtils queryList(Query query);
}
