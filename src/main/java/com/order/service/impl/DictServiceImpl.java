package com.order.service.impl;

import com.common.utils.PageUtils;
import com.common.utils.Query;
import com.common.utils.ResultUtils;
import com.order.entity.DictTypeEntity;
import com.order.entity.DictValueEntity;
import com.order.repository.DictRepository;
import com.order.repository.DictTypeRepository;
import com.order.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Robb G C LUO
 * @version v1.0
 * @Description: TODO
 * @date 2019/12/10
 */
@Service(value = "dictService")
@Transactional
public class DictServiceImpl implements DictService {

    @Autowired
    private EntityManager em;
    @Autowired
    private DictRepository dictRepository;
    @Autowired
    private DictTypeRepository dictTypeRepository;

    @Override
    public ResultUtils addDict(DictValueEntity dict) {
        DictValueEntity dictValueEntity = new DictValueEntity();
        buildDict(dictValueEntity, dict);
        dictValueEntity.setCreateTime(new Date());
        em.persist(dictValueEntity);
        return ResultUtils.ok();
    }

    @Override
    public ResultUtils editDict(DictValueEntity dict) {
        DictValueEntity dictValueEntity = em.find(DictValueEntity.class, dict.getId());
        buildDict(dictValueEntity, dict);
        dictValueEntity.setUpdateTime(new Date());
        em.merge(dictValueEntity);
        return ResultUtils.ok();
    }

    @Override
    public ResultUtils deleteDict(Long id) {
        DictValueEntity dictValueEntity = em.find(DictValueEntity.class, id);
        if(null == dictValueEntity) {
            return ResultUtils.error(201, "");
        }
        em.remove(dictValueEntity);
        return ResultUtils.ok();
    }

    @Override
    public PageUtils queryList(Query query) {
        Long dictTypeId = (Long) query.get("dictTypeId");
        Specification<DictValueEntity> specification = new Specification<DictValueEntity>() {
            @Override
            public Predicate toPredicate(Root<DictValueEntity> root, CriteriaQuery<?> qy, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                Predicate predicate = cb.equal(root.<Long>get("dictTypeId"), dictTypeId);
                predicates.add(predicate);
                return cb.and(predicates.toArray(new Predicate[0]));
            }
        };
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "createTime"));
        Pageable pageable = new PageRequest(query.getPage() - 1, query.getLimit(), new Sort(orders));
        Page<DictValueEntity> page = dictRepository.findAll(specification, pageable);

        List<DictValueEntity> list = page.getContent();
        for(DictValueEntity dict : list) {
            DictTypeEntity dictTypeEntity = em.find(DictTypeEntity.class, dict.getDictTypeId());
            dict.setDictTypeName(dictTypeEntity.getName());
        }
        return new PageUtils(list, Long.valueOf(page.getTotalElements()).intValue(), query.getLimit(), query.getPage());
    }

    @Override
    public ResultUtils queryOne(Long id) {
        DictValueEntity dictValueEntity = em.find(DictValueEntity.class, id);
        return ResultUtils.success("", dictValueEntity);
    }

    @Override
    public List<DictTypeEntity> queryDictTypeList() {
        List<DictTypeEntity> list = dictTypeRepository.findAll();
        for(DictTypeEntity typeEntity : list) {
            List<DictValueEntity> dictValues = dictRepository.findAllByDictTypeId(typeEntity.getId());
            typeEntity.setDictValues(dictValues);
        }
        return list;
    }

    @Override
    public List<DictValueEntity> queryDictValueList(Long dictTypeId) {
        return dictRepository.findAllByDictTypeId(dictTypeId);
    }

    private void buildDict(DictValueEntity dict, DictValueEntity params) {
        dict.setDictTypeId(params.getDictTypeId());
        dict.setName(params.getName());
        dict.setValue(params.getValue());
    }
}
