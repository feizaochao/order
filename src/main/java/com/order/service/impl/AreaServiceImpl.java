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
	public R addArea(Map<String, Object> params) {
		AreaEntity area = new AreaEntity();
		area.setAreaNo((String) params.get("areaNo"));
		area.setAreaName((String) params.get("areaName"));
		area.setCreateTime(new Date());
		em.persist(area);
		return R.ok("保存成功");
	}

	@Override
	public R editArea(Map<String, Object> params) {
		AreaEntity area = em.find(AreaEntity.class, Long.valueOf((String) params.get("id")));
		if(null == area) {
			return R.error("修改失败");
		}
		area.setAreaNo((String) params.get("areaNo"));
		area.setAreaName((String) params.get("areaName"));
		area.setUpdateTime(new Date());
		em.merge(area);
		return R.ok("修改成功");
	}

	@Override
	public R deleteArea(Long id) {
		AreaEntity area = em.find(AreaEntity.class, id);
		if(null == area) {
			return R.error("删除失败");
		}
		em.remove(area);
		return R.ok("删除成功");
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
	public R queryAll() {
		List<AreaEntity> list = areaRepository.findAll();
		return R.ok().put("data", list); 
	}

}
