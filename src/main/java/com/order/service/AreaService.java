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
public interface AreaService {

	R addArea(Map<String, Object> params);
	
	R editArea(Map<String, Object> params);
	
	R deleteArea(Long id);
	
	PageUtils queryList(Query query);
	
	R queryAll();
}
