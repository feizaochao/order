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
	public ResultUtils addCustomer(CustomerEntity params) {
		CustomerEntity customer= new CustomerEntity();
		buildCustomer(customer, params);
		customer.setCreateTime(new Date());
		em.persist(customer);
		return ResultUtils.ok();
	}

	@Override
	public ResultUtils editCustomer(CustomerEntity params) {
		CustomerEntity customer = em.find(CustomerEntity.class, params.getId());
		if(null == customer) {
			return ResultUtils.error(201, "");
		}
		buildCustomer(customer, params);
		customer.setUpdateTime(new Date());
		em.merge(customer);
		return ResultUtils.ok();
	}

	@Override
	public ResultUtils deleteCustomer(Long id) {
		CustomerEntity customer = em.find(CustomerEntity.class, id);
		if(null == customer) {
			return ResultUtils.error(201, "");
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
		return ResultUtils.ok();
	}

	@Override
	public PageUtils queryList(final Query query) {
		Specification<CustomerEntity> specification = new Specification<CustomerEntity>() {
			@Override
			public Predicate toPredicate(Root<CustomerEntity> root, CriteriaQuery<?> qy, CriteriaBuilder cb) {
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
		orders.add(new Order(Direction.DESC, "createTime"));
		Pageable pageable = new PageRequest(query.getPage() - 1, query.getLimit(), new Sort(orders));
		Page<CustomerEntity> page = customerRepository.findAll(specification, pageable);

		StringBuilder sql = new StringBuilder();
		sql.append("select areaName from AreaEntity where id = :areaId");
		List<CustomerEntity> list = page.getContent();
		for(CustomerEntity customer : list) {
			if(null != customer.getAreaId()) {
				javax.persistence.Query qy = em.createQuery(sql.toString());
				qy.setParameter("areaId", customer.getAreaId());
				String areaName = (String) qy.getSingleResult();
				customer.setAreaName(areaName);
			}
		}
		PageUtils pageUtils = new PageUtils(page.getContent(), Long.valueOf(page.getTotalElements()).intValue(), query.getLimit(), query.getPage());
		return pageUtils;
	}

	@Override
	public R queryOne(final Query query) {
		Long customerId = (Long) query.get("id");
		CustomerEntity customer = em.find(CustomerEntity.class, customerId);
		if (null == customer) {
			return R.error();
		}
		if (null != customer.getAreaId()) {
			AreaEntity area = em.find(AreaEntity.class, customer.getAreaId());
			customer.setAreaId(area.getId());
			customer.setAreaName(area.getAreaName());
		}
		R r = R.ok().put("customerData", customer);
		// 获取该客户订单信息
		Specification<OrderEntity> specification = new Specification<OrderEntity>() {
			@Override
			public Predicate toPredicate(Root<OrderEntity> root, CriteriaQuery<?> q, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.get("customerId"), customerId));
				return cb.and(predicates.toArray(new Predicate[0]));
			}
		};
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(Direction.DESC, "createTime"));
		Pageable pageable = new PageRequest(query.getPage() - 1, query.getLimit(), new Sort(orders));
		Page<OrderEntity> page = orderRepository.findAll(specification, pageable);

		List<OrderEntity> list = page.getContent();
		for(OrderEntity order : list) {
			ContractEntity contract = em.find(ContractEntity.class, order.getContractId());
			order.setCustomer(customer);
			order.setContract(contract);
			AreaEntity area = em.find(AreaEntity.class, customer.getAreaId());
			order.setAreaName(area.getAreaName());
		}
		PageUtils pageUtils = new PageUtils(list, Long.valueOf(page.getTotalElements()).intValue(), query.getLimit(), query.getPage());
		r.put("orderData", pageUtils);
		return r;
	}

	@Override
	public List<CustomerEntity> exportAllData() {
		List<CustomerEntity> results = customerRepository.findAll();
		for(CustomerEntity customer : results) {
			if(null != customer.getAreaId()) {
				AreaEntity area = em.find(AreaEntity.class, customer.getAreaId());
				customer.setAreaName(area.getAreaName());
			}
		}
		return results;
	}

	private void buildCustomer(CustomerEntity customer, CustomerEntity params) {
		customer.setName(params.getName());
		customer.setAreaId(params.getAreaId());
		customer.setContact(params.getContact());
		customer.setMallNo(params.getMallNo());
		customer.setLicenseNo(params.getLicenseNo());
		customer.setPersist(params.getPersist());
		customer.setLicenseAddress(params.getLicenseAddress());
		customer.setAddress(params.getAddress());
		customer.setRemarks(params.getRemarks());
	}
}
