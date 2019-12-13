package com.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.order.entity.AreaEntity;

/**
* @Description: TODO
* @author Robb G C LUO
* @date 2019年11月20日
* @version v1.0
*/
public interface AreaRepository extends JpaRepository<AreaEntity, Long>, JpaSpecificationExecutor<AreaEntity> {

    AreaEntity findByAreaName(String areaName);
}
