package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.order.service.UserService;

/**
* @Description: TODO
* @author Robb G C LUO
* @date 2019年11月18日
* @version v1.0
*/
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRoleTest {

	@Autowired
	private UserService userService;
	
	@Test
	public void addUserTest() {
		String name = "销售用户";
		String password = "123456";
		Long roleId = 3l;
		userService.addUser(name, password, roleId);
	}
}
