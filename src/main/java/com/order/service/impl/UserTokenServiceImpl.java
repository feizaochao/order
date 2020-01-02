package com.order.service.impl;

import com.modules.sys.oauth2.TokenGenerator;
import com.order.entity.TokenEntity;
import com.order.repository.TokenRepository;
import com.order.service.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Robb G C LUO
 * @version v1.0
 * @Description: TODO
 * @date 2020/1/2
 */
@Service(value = "userTokenService")
public class UserTokenServiceImpl implements UserTokenService {

    @Autowired
    private EntityManager em;
    @Autowired
    private TokenRepository tokenRepository;

    private final static int EXPIRE = 3000 * 12;

    @Override
    public Map<String, Object> createToken(Long userId) {
        // 生成token
        String token = TokenGenerator.generateValue();

        // 当前时间
        Date now = new Date();
        // 过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

        // 判断是否生成过token
        TokenEntity tokenEntity = tokenRepository.findByUserId(userId);
        if(null == tokenEntity) {
            tokenEntity = new TokenEntity();
            tokenEntity.setUserId(userId);
            tokenEntity.setToken(token);
            tokenEntity.setExpireTime(expireTime);
            tokenEntity.setCreateTime(now);
            em.persist(tokenEntity);
        } else {
            tokenEntity.setToken(token);
            tokenEntity.setExpireTime(expireTime);
            tokenEntity.setUpdateTime(now);
            em.merge(tokenEntity);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("expire", EXPIRE);
        return map;
    }
}
