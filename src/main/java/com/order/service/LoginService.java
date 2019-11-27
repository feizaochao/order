package com.order.service;

import com.common.utils.ResultUtils;

/**
* @Description: TODO
* @author Robb G C LUO
* @date 2019年11月15日
* @version v1.0
*/
public interface LoginService {

	ResultUtils login(String name, String password);
}
