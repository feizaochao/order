package com.order.service;

import java.util.Map;

import com.common.utils.PageUtils;
import com.common.utils.Query;
import com.common.utils.R;
import com.common.utils.ResultUtils;
import com.order.entity.AreaEntity;

/**
* @Description: TODO
* @author Robb G C LUO
* @date 2019年11月20日
* @version v1.0
*/
public interface AreaService {

	ResultUtils addArea(AreaEntity area);

	ResultUtils editArea(AreaEntity area);

	ResultUtils deleteArea(Long id);
	
	PageUtils queryList(Query query);

	ResultUtils queryAll();
}
