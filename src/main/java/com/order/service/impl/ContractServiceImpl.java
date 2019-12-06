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

import com.common.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.order.entity.ContractEntity;
import com.order.repository.ContractRepository;
import com.order.service.ContractService;

/**
* @Description: TODO
* @author Robb G C LUO
* @date 2019年11月19日
* @version v1.0
*/
@Service(value = "contractService")
@Transactional
public class ContractServiceImpl implements ContractService {

	@Autowired
	private EntityManager em;
	@Autowired
	private ContractRepository contractRepository;
	
	@Override
	public ResultUtils addContract(ContractEntity params) {
		ContractEntity contract = new ContractEntity();
		buildContract(contract, params);
		contract.setCreateTime(new Date());
		em.persist(contract);
		return ResultUtils.ok();
	}

	@Override
	public ResultUtils editContract(ContractEntity params) {
		ContractEntity contract = em.find(ContractEntity.class, params.getId());
		if(null == contract) {
			return ResultUtils.error(201, "");
		}
		buildContract(contract, params);
		contract.setUpdateTime(new Date());
		em.merge(contract);
		return ResultUtils.ok();
	}

	@Override
	public ResultUtils deleteContract(Long id) {
		ContractEntity contract = em.find(ContractEntity.class, id);
		if(null == contract) {
			return ResultUtils.error(201, "");
		}
		em.remove(contract);
		return ResultUtils.ok();
	}

	@Override
	public PageUtils queryList(final Query query) {
		Specification<ContractEntity> specification = new Specification<ContractEntity>() {
			@Override
			public Predicate toPredicate(Root<ContractEntity> root, CriteriaQuery<?> qy, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				String fieldName = (String) query.get("fieldName");
				if(null != fieldName && !"".equals(fieldName)) {
					Predicate predicate = cb.like(root.<String>get(fieldName), "%" + query.get("keyword") + "%");
					predicates.add(predicate);
				}
				return cb.and(predicates.toArray(new Predicate[0]));
			}
		};
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(Direction.ASC, "createTime"));
		Pageable pageable = new PageRequest(query.getPage() - 1, query.getLimit(), new Sort(orders));
		Page<ContractEntity> page = contractRepository.findAll(specification, pageable);
		PageUtils pageUtils = new PageUtils(page.getContent(), Long.valueOf(page.getTotalElements()).intValue(), query.getLimit(), query.getPage());
		return pageUtils;
	}

	@Override
	public ResultUtils queryOne(Long id) {
		ContractEntity contract = em.find(ContractEntity.class, id);
		if(null == contract) {
			return ResultUtils.error(201, "");
		}
		return ResultUtils.ok();
	}

	@Override
	public List<ContractEntity> exportAllData() {
		return contractRepository.findAll();
	}

	private void buildContract(ContractEntity contract, ContractEntity params) {
		contract.setSiteName(params.getSiteName());
		contract.setContractName(params.getContractName());
		contract.setContractNo(params.getContractNo());
		contract.setPartyA(params.getPartyA());
		contract.setPartyB(params.getPartyB());
		contract.setContractStartTime(params.getContractStartTime());
		contract.setContractEndTime(params.getContractEndTime());
		contract.setElectricityFee(params.getElectricityFee());
		contract.setStartTime(params.getStartTime());
		contract.setEndTime(params.getEndTime());
		contract.setElectricityCharge(params.getElectricityCharge());
		contract.setElectricitySubmitType(params.getElectricitySubmitType());
		contract.setElectricityPaid(params.getElectricityPaid());
		contract.setPaidTime(params.getPaidTime());
	}
}
