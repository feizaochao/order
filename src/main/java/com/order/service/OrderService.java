package com.order.service;

import java.util.Map;

import com.common.utils.PageUtils;
import com.common.utils.Query;
import com.common.utils.R;
import com.common.utils.ResultUtils;
import com.order.entity.OrderEntity;

/**
* @Description: TODO
* @author Robb G C LUO
* @date 2019年11月21日
* @version v1.0
*/
public interface OrderService {

	ResultUtils addOrder(OrderEntity params);

	ResultUtils editOrder(OrderEntity params);

	ResultUtils deleteOrder(Long id);
	
	PageUtils queryList(Query query);

	ResultUtils queryOne(Long id);
}
