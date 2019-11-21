package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.order.repository.RoleMenuRepository;

/**
* @Description: TODO
* @author Robb G C LUO
* @date 2019年11月18日
* @version v1.0
*/
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleMenuTest {
	
	@Autowired
	private RoleMenuRepository rm;

	@Test
	public void testDelete() {
		rm.deleteByRole(3l);
	}
}
