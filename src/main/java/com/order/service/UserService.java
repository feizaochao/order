package com.order.service;

import com.common.utils.PageUtils;
import com.common.utils.Query;
import com.common.utils.R;
import com.order.entity.UserEntity;

/**
* @Description: TODO
* @author Robb G C LUO
* @date 2019年11月15日
* @version v1.0
*/
public interface UserService {

	UserEntity queryUserByName(String username);
	
	R addUser(String name, String password, Long roleId);
	
	R editUser(Long userId, String name, String password, Long roleId);
	
	R deleteUser(Long id);
	
	PageUtils queryList(Query query);
}
