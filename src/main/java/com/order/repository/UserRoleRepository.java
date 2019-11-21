package com.order.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.order.entity.UserRoleEntity;

/**
* @Description: TODO
* @author Robb G C LUO
* @date 2019年11月18日
* @version v1.0
*/
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

	@Modifying
	@Transactional
	@Query("delete from UserRoleEntity where userId = ?1")
	void deleteRoleByUser(Long userId);
}
