package com.order.service;

import java.util.Map;

/**
 * @author Robb G C LUO
 * @version v1.0
 * @Description: TODO
 * @date 2020/1/2
 */
public interface UserTokenService {
    Map<String, Object> createToken(Long userId);
}
