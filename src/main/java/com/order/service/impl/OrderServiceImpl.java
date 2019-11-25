package com.order.service.impl;

import java.awt.geom.Area;
import java.math.BigDecimal;
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
import com.order.repository.OrderRepository;
import com.order.service.OrderService;

/**
* @Description: TODO
* @author Robb G C LUO
* @date 2019年11月21日
* @version v1.0
*/
@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private EntityManager em;
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public R addOrder(Map<String, Object> params) {
		// 保存客户信息
		CustomerEntity customer = new CustomerEntity();
		buildCustomer(customer, params);
		customer.setCreateTime(new Date());
		em.persist(customer);
		// 保存合同信息
		ContractEntity contract = new ContractEntity();
		buildContract(contract, params);
		contract.setCreateTime(new Date());
		em.persist(contract);
		// 保存宽带信息
		OrderBroadbandEntity broadband = new OrderBroadbandEntity();
		buildBroadband(broadband, params);
		broadband.setCreateTime(new Date());
		em.persist(broadband);
		// 保存收费信息
		OrderChargeEntity chargeEntity = new OrderChargeEntity();
		buildCharge(chargeEntity, params);
		chargeEntity.setCreateTime(new Date());
		em.persist(chargeEntity);
		// 保存开票信息
		OrderInvoiceEntity invoiceEntity = new OrderInvoiceEntity();
		buildInvoice(invoiceEntity, params);
		invoiceEntity.setCreateTime(new Date());
		em.persist(invoiceEntity);
		// 保存缴纳运营商套餐成本信息
		OrderOperatorEntity operatorEntity = new OrderOperatorEntity();
		buildOperator(operatorEntity, params);
		operatorEntity.setCreateTime(new Date());
		em.persist(operatorEntity);
		// 保存新业务归属信息
		OrderNewBusinessEntity newBusinessEntity = new OrderNewBusinessEntity();
		buildNewBusiness(newBusinessEntity, params);
		newBusinessEntity.setCreateTime(new Date());
		em.persist(newBusinessEntity);
		// 保存订单信息
		em.flush();
		OrderEntity order = new OrderEntity();
		buildOrder(order, customer, contract, broadband, chargeEntity, invoiceEntity, operatorEntity, newBusinessEntity, params);
		order.setCreateTime(new Date());
		em.persist(order);
		return R.ok();
	}

	@Override
	public R editOrder(Map<String, Object> params) {
		OrderEntity order = em.find(OrderEntity.class, Long.valueOf((String) params.get("id")));
		if(null == order) {
			return R.error();
		}
		// 修改客户信息
		CustomerEntity customer = null;
		if(null != order.getCustomerId()) {
			customer = em.find(CustomerEntity.class, order.getCustomerId());
			buildCustomer(customer, params);
			customer.setUpdateTime(new Date());
			em.merge(customer);
		} else {
			customer = new CustomerEntity();
			buildCustomer(customer, params);
			customer.setCreateTime(new Date());
			em.persist(customer);
		}
		// 修改合同信息
		ContractEntity contract = null;
		if(null != order.getContractId()) {
			contract = em.find(ContractEntity.class, order.getContractId());
			buildContract(contract, params);
			contract.setUpdateTime(new Date());
			em.merge(contract);
		} else {
			contract = new ContractEntity();
			buildContract(contract, params);
			contract.setCreateTime(new Date());
			em.persist(contract);
		}
		// 修改宽带信息
		OrderBroadbandEntity broadband = null;
		if(null != order.getBroadbandId()) {
			broadband = em.find(OrderBroadbandEntity.class, order.getBroadbandId());
			buildBroadband(broadband, params);
			broadband.setUpdateTime(new Date());
			em.merge(broadband);
		} else {
			broadband = new OrderBroadbandEntity();
			buildBroadband(broadband, params);
			broadband.setCreateTime(new Date());
			em.persist(broadband);
		}
		// 修改收费信息
		OrderChargeEntity charge = null;
		if(null != order.getChargeId()) {
			charge = em.find(OrderChargeEntity.class, order.getChargeId());
			buildCharge(charge, params);
			charge.setUpdateTime(new Date());
			em.merge(charge);
		} else {
			charge = new OrderChargeEntity();
			buildCharge(charge, params);
			charge.setCreateTime(new Date());
			em.persist(charge);
		}
		// 修改开票信息
		OrderInvoiceEntity invoice = null;
		if(null != order.getInvoiceId()) {
			invoice = em.find(OrderInvoiceEntity.class, order.getInvoiceId());
			buildInvoice(invoice, params);
			invoice.setUpdateTime(new Date());
			em.merge(invoice);
		} else {
			invoice = new OrderInvoiceEntity();
			buildInvoice(invoice, params);
			invoice.setCreateTime(new Date());
			em.persist(invoice);
		}
		// 修改运营商信息
		OrderOperatorEntity operator = null;
		if(null != order.getOperatorId()) {
			operator = em.find(OrderOperatorEntity.class, order.getOperatorId());
			buildOperator(operator, params);
			operator.setUpdateTime(new Date());
			em.merge(operator);
		} else {
			operator = new OrderOperatorEntity();
			buildOperator(operator, params);
			operator.setCreateTime(new Date());
			em.persist(operator);
		}
		// 修改新业务信息
		OrderNewBusinessEntity newBusiness = null;
		if(null != order.getNewBusinessId()) {
			newBusiness = em.find(OrderNewBusinessEntity.class, order.getNewBusinessId());
			buildNewBusiness(newBusiness, params);
			newBusiness.setUpdateTime(new Date());
			em.merge(newBusiness);
		} else {
			newBusiness = new OrderNewBusinessEntity();
			buildNewBusiness(newBusiness, params);
			newBusiness.setCreateTime(new Date());
			em.persist(newBusiness);
		}
		// 修改订单信息
		em.flush();
		buildOrder(order, customer, contract, broadband, charge, invoice, operator, newBusiness, params);
		order.setUpdateTime(new Date());
		em.merge(order);
		return R.ok();
	}

	@Override
	public R deleteOrder(Long id) {
		OrderEntity order = em.find(OrderEntity.class, id);
		if(null == order) {
			return R.error();
		}
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
		return R.ok();
	}

	@Override
	public PageUtils queryList(final Query query) {
		Specification<OrderEntity> specification = new Specification<OrderEntity>() {
			@Override
			public Predicate toPredicate(Root<OrderEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				return null; 
			}
		};
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(Direction.DESC, "createTime"));
		Pageable pageable = new PageRequest(query.getPage() - 1, query.getLimit(), new Sort(orders));
		Page<OrderEntity> page = orderRepository.findAll(specification, pageable);
		
		List<OrderEntity> list = page.getContent();
		for(OrderEntity order : list) {
			CustomerEntity customer = em.find(CustomerEntity.class, order.getCustomerId());
			ContractEntity contract = em.find(ContractEntity.class, order.getContractId());
			order.setCustomer(customer);
			order.setContract(contract);
			AreaEntity area = em.find(AreaEntity.class, customer.getAreaId());
			order.setAreaName(area.getAreaName());
		}
		PageUtils pageUtils = new PageUtils(list, Long.valueOf(page.getTotalElements()).intValue(), query.getLimit(), query.getPage());
		return pageUtils;
	}

	@Override
	public R queryOne(Long id) {
		OrderEntity order = em.find(OrderEntity.class, id);
		if(null == order) {
			return R.error();
		}
		CustomerEntity customer = em.find(CustomerEntity.class, order.getCustomerId());
		if(null == customer) {
			return R.error();
		}
		AreaEntity area = em.find(AreaEntity.class, customer.getAreaId());
		if(null != area) {
			order.setAreaName(area.getAreaName());
		}
		ContractEntity contract = null;
		if(null != order.getContractId()) {
			contract = em.find(ContractEntity.class, order.getContractId());
		}
		OrderBroadbandEntity broadband = null;
		if(null != order.getBroadbandId()) {
			broadband = em.find(OrderBroadbandEntity.class, order.getBroadbandId());
		}
		OrderChargeEntity charge = null;
		if(null != order.getChargeId()) {
			charge = em.find(OrderChargeEntity.class, order.getChargeId());
		}
		OrderInvoiceEntity invoice = null;
		if(null != order.getInvoiceId()) {
			invoice = em.find(OrderInvoiceEntity.class, order.getInvoiceId());
		}
		OrderOperatorEntity operator = null;
		if(null != order.getOperatorId()) {
			operator = em.find(OrderOperatorEntity.class, order.getOperatorId());
		}
		OrderNewBusinessEntity newBusiness = null;
		if(null != order.getNewBusinessId()) {
			newBusiness =  em.find(OrderNewBusinessEntity.class, order.getNewBusinessId());
		}
		order.setCustomer(customer);
		order.setContract(contract);
		order.setBroadband(broadband);
		order.setCharge(charge);
		order.setInvoice(invoice);
		order.setOperator(operator);
		order.setNewBusiness(newBusiness);
		return R.ok().put("data", order);
	}

	/*组装客户信息*/
	private void buildCustomer(CustomerEntity customer, Map<String, Object> params) {
		customer.setMallNo((String) params.get("mallNo"));
		customer.setName((String) params.get("customerName"));
		customer.setAreaId(Long.valueOf((String) params.get("areaId")));
//		customer.setPersist((Integer) params.get("persist"));
		customer.setAddress((String) params.get("address"));
		customer.setContact((String) params.get("contact"));
		customer.setLicenseNo((String) params.get("licenseNo"));
		customer.setLicenseAddress((String) params.get("licenseAddress"));
		customer.setRemarks((String) params.get("remarks"));
	}

	private void buildContract(ContractEntity contract, Map<String, Object> params) {
		contract.setContractNo((String) params.get("contractNo"));
		contract.setContractAmount(MathUtils.getBigDecimal(params.get("contractAmount")));
		contract.setContractStartTime((String) params.get("contractStartTime"));
		contract.setContractEndTime((String) params.get("contractEndTime"));
	}

	/*组装宽带信息*/
	private void buildBroadband(OrderBroadbandEntity broadband, Map<String, Object> params) {
		broadband.setOperator((String) params.get("broadbandOperator"));
		broadband.setType((String) params.get("broadbandType"));
		broadband.setPrice(MathUtils.getBigDecimal(params.get("broadbandPrice")));
		broadband.setPhone((String) params.get("broadbandPhone"));
		broadband.setPhoneNum(Integer.parseInt((String) params.get("broadbandPhoneNum")));
		broadband.setVoiceTariff(MathUtils.getBigDecimal(params.get("voiceTariff")));
	}

	private void buildCharge(OrderChargeEntity charge, Map<String, Object> params) {
		charge.setChargeAmount(MathUtils.getBigDecimal(params.get("chargeAmount")));
		charge.setChargeDate((String) params.get("chargeDate"));
		charge.setNextChargeDate((String) params.get("nextChargeDate"));
	}

	/*组装收费信息*/
	private void buildInvoice(OrderInvoiceEntity invoice, Map<String, Object> params) {
		invoice.setIsInvoice(Integer.parseInt((String) params.get("isInvoice")));
		invoice.setType(Integer.parseInt((String) params.get("invoiceType")));
		invoice.setInvoiceDate((String) params.get("invoiceDate"));
		invoice.setInvoiceNum((String) params.get("invoiceNum"));
	}

	/*组装需缴纳运营商套餐成本*/
	private void buildOperator(OrderOperatorEntity operator, Map<String, Object> params) {
		operator.setProductNum((String) params.get("productNum"));
		operator.setAccountStatus(Integer.parseInt((String) params.get("accountStatus")));
		operator.setInternetAccount((String) params.get("internetAccount"));
		operator.setBroadband((String) params.get("broadband"));
		operator.setPhone((String) params.get("operatorPhone"));
		operator.setPaymentCycle((String) params.get("paymentCycle"));
		operator.setPaymentDate((String) params.get("paymentDate"));
		operator.setInvoiceDate((String) params.get("operatorInvoiceDate"));
		operator.setContact((String) params.get("operatorContact"));
		operator.setRenewContract((String) params.get("renewContract"));
		operator.setInvoiceNum((String) params.get("operatorInvoiceNum"));
		operator.setPrimaryCharge((MathUtils.getBigDecimal(params.get("primaryCharge"))));
	}

	/*组装新业务信息*/
	private void buildNewBusiness(OrderNewBusinessEntity newBusiness, Map<String, Object> params) {
		newBusiness.setRequestOrder((String) params.get("requestOrder"));
		newBusiness.setMonth((String) params.get("newBusinessMonth"));
		newBusiness.setDate((String) params.get("newBusinessDate"));
		newBusiness.setCounterpart((String) params.get("counterpart"));
	}

	/*组装订单信息*/
	private void buildOrder(OrderEntity order, CustomerEntity customer, ContractEntity contract, OrderBroadbandEntity broadband,
							OrderChargeEntity chargeEntity, OrderInvoiceEntity invoiceEntity, OrderOperatorEntity operatorEntity,
							OrderNewBusinessEntity newBusinessEntity,Map<String, Object> params) {
		order.setOrderNo((String) params.get("orderNo"));
		order.setOwnerProject((String) params.get("ownerProject"));
//		order.setStatus((int) params.get("status"));
//		order.setIsUseNetwork((int) params.get("isUseNetwork"));
		order.setCustomerId(customer.getId());
		order.setContractId(contract.getId());
		order.setBroadbandId(broadband.getId());
		order.setChargeId(chargeEntity.getId());
		order.setInvoiceId(invoiceEntity.getId());
		order.setOperatorId(operatorEntity.getId());
		order.setNewBusinessId(newBusinessEntity.getId());
	}
}
