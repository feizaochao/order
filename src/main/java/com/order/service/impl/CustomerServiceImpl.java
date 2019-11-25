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

import com.order.entity.*;
import com.order.repository.OrderRepository;
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
import com.order.repository.CustomerRepository;
import com.order.service.CustomerService;

/**
* @Description: TODO
* @author Robb G C LUO
* @date 2019年11月20日
* @version v1.0
*/
@Service(value = "customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private EntityManager em;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public R addCustomer(Map<String, Object> params) {
		CustomerEntity customer = new CustomerEntity();
		buildCustomer(customer, params);
		customer.setCreateTime(new Date());
		em.persist(customer);
		return R.ok();
	}

	@Override
	public R editCustomer(Map<String, Object> params) {
		CustomerEntity customer = em.find(CustomerEntity.class, Long.valueOf((String) params.get("id")));
		if(null == customer) {
			return R.error();
		}
		buildCustomer(customer, params);
		customer.setUpdateTime(new Date());
		em.merge(customer);
		return R.ok();
	}

	@Override
	public R deleteCustomer(Long id) {
		CustomerEntity customer = em.find(CustomerEntity.class, id);
		if(null == customer) {
			return R.error();
		}
		// 删除订单信息
		List<OrderEntity> orders = orderRepository.findByCustomerId(customer.getId());
		for(OrderEntity order : orders) {
			// 删除合同信息
			if(null != order.getContractId()) {
				ContractEntity contract = em.find(ContractEntity.class, order.getContractId());
				em.remove(contract);
			}
			// 删除宽带信息
			if(null != order.getBroadbandId()) {
				OrderBroadbandEntity broadbandEntity =  em.find(OrderBroadbandEntity.class, order.getBroadbandId());
				em.remove(broadbandEntity);
			}
			// 删除收费信息
			if(null != order.getChargeId()) {
				OrderChargeEntity chargeEntity = em.find(OrderChargeEntity.class, order.getChargeId());
				em.remove(chargeEntity);
			}
			// 删除开票信息
			if(null != order.getInvoiceId()) {
				OrderInvoiceEntity invoiceEntity = em.find(OrderInvoiceEntity.class, order.getInvoiceId());
				em.remove(invoiceEntity);
			}
			// 删除需缴纳运营商套餐成本信息
			if(null != order.getOperatorId()) {
				OrderOperatorEntity operatorEntity = em.find(OrderOperatorEntity.class, order.getOperatorId());
				em.remove(operatorEntity);
			}
			// 删除新业务信息
			if(null != order.getNewBusinessId()) {
				OrderNewBusinessEntity newBusinessEntity = em.find(OrderNewBusinessEntity.class, order.getNewBusinessId());
				em.remove(newBusinessEntity);
			}
			em.remove(order);
		}
		// 删除客户信息
		em.remove(customer);
		return R.ok();
	}

	@Override
	public PageUtils queryList(final Query query) {
		Specification<CustomerEntity> specification = new Specification<CustomerEntity>() {
			@Override
			public Predicate toPredicate(Root<CustomerEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				return null;
			}
		};
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(Direction.DESC, "createTime"));
		Pageable pageable = new PageRequest(query.getPage() - 1, query.getLimit(), new Sort(orders));
		Page<CustomerEntity> page = customerRepository.findAll(specification, pageable);
		
		StringBuilder sql = new StringBuilder();
		sql.append("select areaName from AreaEntity where id = :areaId");
		List<CustomerEntity> list = page.getContent();
		for(CustomerEntity customer : list) {
			javax.persistence.Query qy = em.createQuery(sql.toString());
			qy.setParameter("areaId", customer.getAreaId());
			String areaName = (String) qy.getSingleResult();
			customer.setAreaName(areaName);
		}
		PageUtils pageUtils = new PageUtils(page.getContent(), Long.valueOf(page.getTotalElements()).intValue(), query.getLimit(), query.getPage());
		return pageUtils;
	}

	@Override
	public R queryOne(Long id) {
		CustomerEntity customer = em.find(CustomerEntity.class, id);
		if (null == customer) {
			return R.error();
		}
		if (null != customer.getAreaId()) {
			AreaEntity area = em.find(AreaEntity.class, customer.getAreaId());
			customer.setAreaId(area.getId());
			customer.setAreaName(area.getAreaName());
		}
		return R.ok().put("data", customer);
	}

	private void buildCustomer(CustomerEntity customer, Map<String, Object> params) {
		customer.setName((String) params.get("name"));
		customer.setAreaId(Long.valueOf((String) params.get("areaId")));
		customer.setContact((String) params.get("contact"));
		customer.setMallNo((String) params.get("mallNo"));
		customer.setLicenseNo((String) params.get("licenseNo"));
		customer.setPersist(Integer.parseInt((String) params.get("persist")));
		customer.setLicenseAddress((String) params.get("licenseAddress"));
		customer.setAddress((String) params.get("address"));
		customer.setRemarks((String) params.get("remark"));
	}

}
