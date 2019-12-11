package com.order.service;

import java.util.List;
import java.util.Map;

import com.common.utils.R;
import com.common.utils.ResultUtils;

/**
* @Description: TODO
* @author lguochao
* @date 2019年11月7日
* @version V1.0
*/
public interface RoleService {

	ResultUtils addRole(String name);

	ResultUtils editRole(Long id, String name);

	ResultUtils deleteRole(Long id);
	
	List<Map<String, Object>> queryList();

	ResultUtils queryOne(Long id);
	
	Long queryRoleByUserId(Long userId);

	ResultUtils saveMenus(Long roleId, Long[] menuIds);
}
