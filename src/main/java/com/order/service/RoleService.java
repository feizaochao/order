package com.order.service;

import java.util.List;
import java.util.Map;

import com.common.utils.R;

/**
* @Description: TODO
* @author lguochao
* @date 2019年11月7日
* @version V1.0
*/
public interface RoleService {

	R addRole(String name);
	
	R editRole(Long id, String name);
	
	R deleteRole(Long id);
	
	List<Map<String, Object>> queryList();
	
	Long queryRoleByUserId(Long userId);
	
	R saveMenus(Long roleId, Long[] menuIds);
}
