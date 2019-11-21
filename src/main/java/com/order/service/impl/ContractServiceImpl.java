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

import com.common.utils.MathUtils;
import com.common.utils.PageUtils;
import com.common.utils.Query;
import com.common.utils.R;
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
	public R addContract(Map<String, Object> params) {
		ContractEntity contract = new ContractEntity();
		contract.setSiteName((String) params.get("siteName"));
		contract.setContractName((String) params.get("contractName"));
		contract.setContractNo((String) params.get("contractNo"));
		contract.setPartyA((String) params.get("partyA"));
		contract.setPartyB((String) params.get("partyB"));
		contract.setContractStartTime((String) params.get("contractStartTime"));
		contract.setContractEndTime((String) params.get("contractEndTime"));
		contract.setElectricityFee(MathUtils.getBigDecimal(params.get("electricityFee")));
		contract.setStartTime((String) params.get("startTime"));
		contract.setEndTime((String) params.get("endTime"));
		contract.setElectricityCharge(MathUtils.getBigDecimal(params.get("electricityCharge")));
		contract.setElectricitySubmitType((int) params.get("electricitySubmitType"));
		contract.setElectricityPaid(MathUtils.getBigDecimal(params.get("electricityPaid")));
		contract.setPaidTime((String) params.get("paidTime"));
		em.persist(contract);
		return R.ok();
	}

	@Override
	public R editContract(Map<String, Object> params) {
		ContractEntity contract = em.find(ContractEntity.class, Long.valueOf((String) params.get("id")));
		if(null == contract) {
			return R.error("修改失败");
		}
		contract.setSiteName((String) params.get("siteName"));
		contract.setContractName((String) params.get("contractName"));
		contract.setContractNo((String) params.get("contractNo"));
		contract.setPartyA((String) params.get("partyA"));
		contract.setPartyB((String) params.get("partyB"));
		contract.setContractStartTime((String) params.get("contractStartTime"));
		contract.setContractEndTime((String) params.get("contractEndTime"));
		contract.setElectricityFee(MathUtils.getBigDecimal(params.get("electricityFee")));
		contract.setStartTime((String) params.get("startTime"));
		contract.setEndTime((String) params.get("endTime"));
		contract.setElectricityCharge(MathUtils.getBigDecimal(params.get("electricityCharge")));
		contract.setElectricitySubmitType((int) params.get("electricitySubmitType"));
		contract.setElectricityPaid(MathUtils.getBigDecimal(params.get("electricityPaid")));
		contract.setPaidTime((String) params.get("paidTime"));
		em.merge(contract);
		return R.ok();
	}

	@Override
	public R deleteContract(Long id) {
		ContractEntity contract = em.find(ContractEntity.class, id);
		if(null == contract) {
			return R.error("删除失败");
		}
		em.remove(contract);
		return R.ok();
	}

	@Override
	public PageUtils queryList(final Query query) {
		Specification<ContractEntity> specification = new Specification<ContractEntity>() {
			@Override
			public Predicate toPredicate(Root<ContractEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				return null;
			}
		};
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(Direction.ASC, "createTime"));
		Pageable pageable = new PageRequest(query.getPage() - 1, query.getLimit(), new Sort(orders));
		Page<ContractEntity> page = contractRepository.findAll(specification, pageable);
		PageUtils pageUtils = new PageUtils(page.getContent(), Long.valueOf(page.getTotalElements()).intValue(), query.getLimit(), query.getPage());
		return pageUtils;
	}

}
