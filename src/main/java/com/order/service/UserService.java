package com.order.service;

import com.common.utils.*;
import com.order.entity.UserEntity;

/**
* @Description: TODO
* @author Robb G C LUO
* @date 2019年11月15日
* @version v1.0
*/
public interface UserService {

	UserEntity queryUserByName(String username);
	
	ResultUtils addUser(String name, String password, Long roleId);

	ResultUtils editUser(Long userId, String name, String password, Long roleId);

	ResultUtils deleteUser(Long id);
	
	PageUtils2<UserEntity> queryList(Query query);

	ResultUtils editPassword(String name, String oldPassword, String newPassword);
}
