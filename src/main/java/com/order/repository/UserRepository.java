package com.order.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.order.entity.UserEntity;

/**
* @Description: TODO
* @author Robb G C LUO
* @date 2019年11月15日
* @version v1.0
*/
public interface UserRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {

	Optional<UserEntity> findByName(String name);
	
	UserEntity findOneByName(String name);
}
