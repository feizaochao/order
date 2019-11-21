package com.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.order.entity.ContractEntity;

/**
* @Description: TODO
* @author Robb G C LUO
* @date 2019年11月19日
* @version v1.0
*/
public interface ContractRepository extends JpaRepository<ContractEntity, Long>, JpaSpecificationExecutor<ContractEntity> {

}
