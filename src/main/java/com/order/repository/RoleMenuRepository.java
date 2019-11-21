package com.order.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.order.entity.RoleMenuEntity;

/**
* @Description: TODO
* @author Robb G C LUO
* @date 2019年11月18日
* @version v1.0
*/
public interface RoleMenuRepository extends JpaRepository<RoleMenuEntity, Long> {

	@Modifying
	@Transactional
	@Query("delete from RoleMenuEntity where roleId = ?1")
	public void deleteByRole(Long roleId);
}
