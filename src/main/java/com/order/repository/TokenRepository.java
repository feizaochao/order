package com.order.repository;

import com.order.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Robb G C LUO
 * @version v1.0
 * @Description: TODO
 * @date 2020/1/2
 */
public interface TokenRepository extends JpaRepository<TokenEntity, Long> {
    TokenEntity findByUserId(Long userId);
}
