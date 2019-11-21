package com.order.service;

import java.util.List;
import java.util.Map;

/**
* @Description: TODO
* @author lguochao
* @date 2019年11月7日
* @version V1.0
*/
public interface MenuService {

	List<Map<String, Object>> queryList();
	
	List<Map<String, Object>> queryListByRoleId(Long roleId);
}
