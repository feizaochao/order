package com.order.repository;

import com.order.entity.DictValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author Robb G C LUO
 * @version v1.0
 * @Description: TODO
 * @date 2019/12/10
 */
public interface DictRepository extends JpaRepository<DictValueEntity, Long>, JpaSpecificationExecutor<DictValueEntity> {
    List<DictValueEntity> findAllByDictTypeId(Long dictTypeId);
}
