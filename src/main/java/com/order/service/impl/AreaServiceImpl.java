package com.order.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import com.common.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.common.utils.PageUtils;
import com.common.utils.Query;
import com.common.utils.R;
import com.order.entity.AreaEntity;
import com.order.repository.AreaRepository;
import com.order.service.AreaService;

/**
* @Description: TODO
* @author Robb G C LUO
* @date 2019年11月20日
* @version v1.0
*/
@Service(value = "areaService")
@Transactional
public class AreaServiceImpl implements AreaService {

	@Autowired
	private EntityManager em;
	@Autowired
	private AreaRepository areaRepository;
	
	@Override
	public ResultUtils addArea(AreaEntity area) {
		AreaEntity areaEntity = new AreaEntity();
		areaEntity.setAreaNo(area.getAreaNo());
		areaEntity.setAreaName(area.getAreaName());
		areaEntity.setCreateTime(new Date());
		em.persist(areaEntity);
		return ResultUtils.error(201, "");
	}

	@Override
	public ResultUtils editArea(AreaEntity area) {
		AreaEntity areaEntity = em.find(AreaEntity.class, area.getId());
		if(null == area) {
			return ResultUtils.error(201, "");
		}
		areaEntity.setAreaNo(area.getAreaNo());
		areaEntity.setAreaName(area.getAreaName());
		areaEntity.setUpdateTime(new Date());
		em.merge(areaEntity);
		return ResultUtils.ok();
	}

	@Override
	public ResultUtils deleteArea(Long id) {
		AreaEntity area = em.find(AreaEntity.class, id);
		if(null == area) {
			return ResultUtils.error(201, "");
		}
		em.remove(area);
		return ResultUtils.ok();
	}

	@Override
	public PageUtils queryList(final Query query) {
		Specification<AreaEntity> specification = new Specification<AreaEntity>() {
			@Override
			public Predicate toPredicate(Root<AreaEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				return null;
			}
		};
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(Direction.DESC, "createTime"));
		Pageable pageable = new PageRequest(query.getPage() - 1, query.getLimit(), new Sort(orders));
		Page<AreaEntity> page = areaRepository.findAll(specification, pageable);
		PageUtils pageUtils = new PageUtils(page.getContent(), Long.valueOf(page.getTotalElements()).intValue(), query.getLimit(), query.getPage());
		return pageUtils;
	}

	@Override
	public ResultUtils queryAll() {
		List<AreaEntity> list = areaRepository.findAll();
		return ResultUtils.success("", list);
	}
}
