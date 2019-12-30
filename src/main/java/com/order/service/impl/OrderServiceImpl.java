package com.order.service.impl;

import java.awt.geom.Area;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;

import com.common.utils.*;
import com.order.data.DictType;
import com.order.data.OrderData;
import com.order.entity.*;
import com.order.repository.ContractRepository;
import com.order.repository.CustomerRepository;
import com.order.repository.DictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ContractRepository contractRepository;
	@Autowired
	private DictRepository dictRepository;

	@Override
	public ResultUtils addOrder(OrderEntity params) {
		// 保存客户信息
		CustomerEntity customer = new CustomerEntity();
		buildCustomer(customer, params.getCustomer());
		customer.setCreateTime(new Date());
		em.persist(customer);
		// 保存合同信息
		ContractEntity contract = new ContractEntity();
		buildContract(contract, params.getContract());
		contract.setCreateTime(new Date());
		em.persist(contract);
		// 保存宽带信息
		OrderBroadbandEntity broadband = new OrderBroadbandEntity();
		buildBroadband(broadband, params.getBroadband());
		broadband.setCreateTime(new Date());
		em.persist(broadband);
		// 保存收费信息
		OrderChargeEntity chargeEntity = new OrderChargeEntity();
		buildCharge(chargeEntity, params.getCharge());
		chargeEntity.setCreateTime(new Date());
		em.persist(chargeEntity);
		// 保存开票信息
		OrderInvoiceEntity invoiceEntity = new OrderInvoiceEntity();
		buildInvoice(invoiceEntity, params.getInvoice());
		invoiceEntity.setCreateTime(new Date());
		em.persist(invoiceEntity);
		// 保存缴纳运营商套餐成本信息
		OrderOperatorEntity operatorEntity = new OrderOperatorEntity();
		buildOperator(operatorEntity, params.getOperator());
		operatorEntity.setCreateTime(new Date());
		em.persist(operatorEntity);
		// 保存新业务归属信息
		OrderNewBusinessEntity newBusinessEntity = new OrderNewBusinessEntity();
		buildNewBusiness(newBusinessEntity, params.getNewBusiness());
		newBusinessEntity.setCreateTime(new Date());
		em.persist(newBusinessEntity);
		// 保存订单信息
		em.flush();
		OrderEntity order = new OrderEntity();
		buildOrder(order, customer, contract, broadband, chargeEntity, invoiceEntity, operatorEntity, newBusinessEntity, params);
		order.setCreateTime(new Date());
		em.persist(order);
		return ResultUtils.ok();
	}

	@Override
	public ResultUtils editOrder(OrderEntity params) {
		OrderEntity order = em.find(OrderEntity.class, params.getId());
		if(null == order) {
			return ResultUtils.error(201, "");
		}
		// 修改客户信息
		CustomerEntity customer = null;
		if(null != order.getCustomerId()) {
			customer = em.find(CustomerEntity.class, order.getCustomerId());
			buildCustomer(customer, params.getCustomer());
			customer.setUpdateTime(new Date());
			em.merge(customer);
		} else {
			customer = new CustomerEntity();
			buildCustomer(customer, params.getCustomer());
			customer.setCreateTime(new Date());
			em.persist(customer);
		}
		// 修改合同信息
		ContractEntity contract = null;
		if(null != order.getContractId()) {
			contract = em.find(ContractEntity.class, order.getContractId());
			buildContract(contract, params.getContract());
			contract.setUpdateTime(new Date());
			em.merge(contract);
		} else {
			contract = new ContractEntity();
			buildContract(contract, params.getContract());
			contract.setCreateTime(new Date());
			em.persist(contract);
		}
		// 修改宽带信息
		OrderBroadbandEntity broadband = null;
		if(null != order.getBroadbandId()) {
			broadband = em.find(OrderBroadbandEntity.class, order.getBroadbandId());
			buildBroadband(broadband, params.getBroadband());
			broadband.setUpdateTime(new Date());
			em.merge(broadband);
		} else {
			broadband = new OrderBroadbandEntity();
			buildBroadband(broadband, params.getBroadband());
			broadband.setCreateTime(new Date());
			em.persist(broadband);
		}
		// 修改收费信息
		OrderChargeEntity charge = null;
		if(null != order.getChargeId()) {
			charge = em.find(OrderChargeEntity.class, order.getChargeId());
			buildCharge(charge, params.getCharge());
			charge.setUpdateTime(new Date());
			em.merge(charge);
		} else {
			charge = new OrderChargeEntity();
			buildCharge(charge, params.getCharge());
			charge.setCreateTime(new Date());
			em.persist(charge);
		}
		// 修改开票信息
		OrderInvoiceEntity invoice = null;
		if(null != order.getInvoiceId()) {
			invoice = em.find(OrderInvoiceEntity.class, order.getInvoiceId());
			buildInvoice(invoice, params.getInvoice());
			invoice.setUpdateTime(new Date());
			em.merge(invoice);
		} else {
			invoice = new OrderInvoiceEntity();
			buildInvoice(invoice, params.getInvoice());
			invoice.setCreateTime(new Date());
			em.persist(invoice);
		}
		// 修改运营商信息
		OrderOperatorEntity operator = null;
		if(null != order.getOperatorId()) {
			operator = em.find(OrderOperatorEntity.class, order.getOperatorId());
			buildOperator(operator, params.getOperator());
			operator.setUpdateTime(new Date());
			em.merge(operator);
		} else {
			operator = new OrderOperatorEntity();
			buildOperator(operator, params.getOperator());
			operator.setCreateTime(new Date());
			em.persist(operator);
		}
		// 修改新业务信息
		OrderNewBusinessEntity newBusiness = null;
		if(null != order.getNewBusinessId()) {
			newBusiness = em.find(OrderNewBusinessEntity.class, order.getNewBusinessId());
			buildNewBusiness(newBusiness, params.getNewBusiness());
			newBusiness.setUpdateTime(new Date());
			em.merge(newBusiness);
		} else {
			newBusiness = new OrderNewBusinessEntity();
			buildNewBusiness(newBusiness, params.getNewBusiness());
			newBusiness.setCreateTime(new Date());
			em.persist(newBusiness);
		}
		// 修改订单信息
		em.flush();
		buildOrder(order, customer, contract, broadband, charge, invoice, operator, newBusiness, params);
		order.setUpdateTime(new Date());
		em.merge(order);
		return ResultUtils.ok();
	}

	@Override
	public ResultUtils deleteOrder(Long id) {
		OrderEntity order = em.find(OrderEntity.class, id);
		if(null == order) {
			return ResultUtils.error(201, "");
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
		return ResultUtils.ok();
	}

	@Override
	public PageUtils queryList(final Query query) {
		Specification<OrderEntity> specification = new Specification<OrderEntity>() {
			@Override
			public Predicate toPredicate(Root<OrderEntity> root, CriteriaQuery<?> qy, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				String fieldName = (String) query.get("fieldName");
				String keyword = (String) query.get("keyword");
				if(null != fieldName && !"".equals(fieldName)) {
					Predicate predicate = cb.like(root.<String>get(fieldName), "%" + keyword + "%");
					predicates.add(predicate);
				} else if(null != keyword && !"".equals(keyword)) {
					Predicate noPredicate = cb.like(root.<String>get("orderNo"), "%" +keyword + "%");
					predicates.add(noPredicate);
					Predicate cNamePredicate = cb.like(root.<String>get("customerName"), "%" + keyword + "%");
					predicates.add(cNamePredicate);
					Predicate areaNamePredicate = cb.like(root.<String>get("areaName"), "%" + keyword + "%");
					predicates.add(areaNamePredicate);
					return cb.or(predicates.toArray(new Predicate[0]));
				}
				return cb.and(predicates.toArray(new Predicate[0]));
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
			DictValueEntity dictValueEntity = dictRepository.findByDictTypeIdAndValue(DictType.TRADE_STATUS, order.getStatus());
			if(null != dictValueEntity) {
				order.setStatusName(dictValueEntity.getName());
			}
		}
		PageUtils pageUtils = new PageUtils(list, Long.valueOf(page.getTotalElements()).intValue(), query.getLimit(), query.getPage());
		return pageUtils;
	}

	@Override
	public ResultUtils queryOne(Long id) {
		OrderEntity order = em.find(OrderEntity.class, id);
		if(null == order) {
			return ResultUtils.error(201, "");
		}
		CustomerEntity customer = em.find(CustomerEntity.class, order.getCustomerId());
		if(null == customer) {
			return ResultUtils.error(201, "");
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
		return ResultUtils.success("", order);
	}

	@Override
	public List<OrderData> exportAllData(List<Long> ids) {
		List<OrderEntity> orders = null;
		if(null != ids && ids.size() != 0) {
			orders = orderRepository.findAll(ids);
		} else {
			orders = orderRepository.findAll();
		}
		return buildExportData(orders);
	}

	/*组装客户信息*/
	private void buildCustomer(CustomerEntity customer, CustomerEntity params) {
		customer.setMallNo(params.getMallNo());
		customer.setName(params.getName());
		if(null != params.getAreaId()) {
			AreaEntity area = em.find(AreaEntity.class, params.getAreaId());
			customer.setAreaId(area.getId());
			customer.setAreaName(area.getAreaName());
		}
		customer.setPersist(params.getPersist());
		customer.setAddress(params.getAddress());
		customer.setContact(params.getContact());
		customer.setLicenseNo(params.getLicenseNo());
		customer.setLicenseAddress(params.getLicenseAddress());
		customer.setRemarks(params.getRemarks());
	}

	/*组装合同信息*/
	private void buildContract(ContractEntity contract, ContractEntity params) {
		contract.setContractNo(params.getContractNo());
		contract.setContractAmount(params.getContractAmount());
		contract.setContractStartTime(params.getContractStartTime());
		contract.setContractEndTime(params.getContractEndTime());
	}

	/*组装宽带信息*/
	private void buildBroadband(OrderBroadbandEntity broadband, OrderBroadbandEntity params) {
		broadband.setOperator(params.getOperator());
		broadband.setType(params.getType());
		broadband.setPrice(params.getPrice());
		broadband.setPhone(params.getPhone());
		broadband.setPhoneNum(params.getPhoneNum());
		broadband.setVoiceTariff(params.getVoiceTariff());
	}

	/*组装收费信息*/
	private void buildCharge(OrderChargeEntity charge, OrderChargeEntity params) {
		charge.setChargeAmount(params.getChargeAmount());
		charge.setChargeDate(params.getChargeDate());
		charge.setNextChargeDate(params.getNextChargeDate());
	}

	/*组装开票信息*/
	private void buildInvoice(OrderInvoiceEntity invoice, OrderInvoiceEntity params) {
		invoice.setIsInvoice(params.getIsInvoice());
		invoice.setType(params.getType());
		invoice.setInvoiceDate(params.getInvoiceDate());
		invoice.setInvoiceNum(params.getInvoiceNum());
	}

	/*组装需缴纳运营商套餐成本*/
	private void buildOperator(OrderOperatorEntity operator, OrderOperatorEntity params) {
		operator.setProductNum(params.getProductNum());
		operator.setAccountStatus(params.getAccountStatus());
		operator.setInternetAccount(params.getInternetAccount());
		operator.setBroadband(params.getBroadband());
		operator.setPhone(params.getPhone());
		operator.setPaymentCycle(params.getPaymentCycle());
		operator.setPaymentDate(params.getPaymentDate());
		operator.setInvoiceDate(params.getInvoiceDate());
		operator.setContact(params.getContact());
		operator.setRenewContract(params.getRenewContract());
		operator.setInvoiceNum(params.getInvoiceNum());
		operator.setPrimaryCharge(params.getPrimaryCharge());
	}

	/*组装新业务信息*/
	private void buildNewBusiness(OrderNewBusinessEntity newBusiness, OrderNewBusinessEntity params) {
		newBusiness.setRequestOrder(params.getRequestOrder());
		newBusiness.setMonth(params.getMonth());
		newBusiness.setDate(params.getDate());
		newBusiness.setCounterpart(params.getCounterpart());
	}

	/*组装订单信息*/
	private void buildOrder(OrderEntity order, CustomerEntity customer, ContractEntity contract, OrderBroadbandEntity broadband,
							OrderChargeEntity chargeEntity, OrderInvoiceEntity invoiceEntity, OrderOperatorEntity operatorEntity,
							OrderNewBusinessEntity newBusinessEntity, OrderEntity params) {
		order.setOrderNo(params.getOrderNo());
		order.setOwnerProject(params.getOwnerProject());
		order.setStatus(params.getStatus());
		order.setIsUseNetwork(params.getIsUseNetwork());
        order.setCustomerName(customer.getName());
        order.setContractAmount(contract.getContractAmount());
        if(null != customer.getAreaId()) {
        	AreaEntity area = em.find(AreaEntity.class, customer.getAreaId());
        	order.setAreaId(area.getId());
        	order.setAreaName(area.getAreaName());
		}
        order.setAddress(customer.getAddress());
		order.setCustomerId(customer.getId());
		order.setContractId(contract.getId());
		order.setBroadbandId(broadband.getId());
		order.setChargeId(chargeEntity.getId());
		order.setInvoiceId(invoiceEntity.getId());
		order.setOperatorId(operatorEntity.getId());
		order.setNewBusinessId(newBusinessEntity.getId());
	}

	private List<OrderData> buildExportData(List<OrderEntity> orders) {
		List<OrderData> results = new ArrayList<>();
		for(OrderEntity order : orders) {
			OrderData orderData = new OrderData();
			CustomerEntity customer = null;
			AreaEntity area = null;
			DictValueEntity dictValueEntity = null;
			if(null != order.getCustomerId()) {
				customer = em.find(CustomerEntity.class, order.getCustomerId());
				orderData.setCustomerName(customer.getName());
				orderData.setContact(customer.getContact());
				orderData.setMailNo(customer.getMallNo());
				orderData.setLicenseNo(customer.getLicenseNo());
				dictValueEntity = dictRepository.findByDictTypeIdAndValue(DictType.PERSIST, customer.getPersist());
				if(null != dictValueEntity) {
					orderData.setPersist(dictValueEntity.getName());
				}
				orderData.setLicenseAddress(customer.getLicenseAddress());
				orderData.setAddress(customer.getAddress());
				orderData.setRemarks(customer.getRemarks());
			}

			ContractEntity contract = null;
			if(null != order.getContractId()) {
				contract = em.find(ContractEntity.class, order.getContractId());
				orderData.setSiteName(contract.getSiteName());
				orderData.setContractNo(contract.getContractNo());
				orderData.setPartyA(contract.getPartyA());
				orderData.setPartyB(contract.getPartyB());
				orderData.setContractStartTime(contract.getContractStartTime());
				orderData.setContractEndTime(contract.getContractEndTime());
				orderData.setContractAmount(contract.getContractAmount());
				orderData.setElectricityFee(contract.getElectricityFee());
				orderData.setStartTime(contract.getStartTime());
				orderData.setEndTime(contract.getEndTime());
				orderData.setElectricityCharge(contract.getElectricityCharge());
				orderData.setElectricitySubmitType(contract.getElectricitySubmitType());
				orderData.setElectricityPaid(contract.getElectricityPaid());
				orderData.setPaidTime(contract.getPaidTime());
			}
			OrderBroadbandEntity broadband = null;
			if(null != order.getBroadbandId()) {
				broadband = em.find(OrderBroadbandEntity.class, order.getBroadbandId());
				orderData.setOperator(broadband.getOperator());
				dictValueEntity = dictRepository.findByDictTypeIdAndValue(DictType.BROADBAND_TYPE, broadband.getType());
				if(null != dictValueEntity) {
					orderData.setType(dictValueEntity.getName());
				}
				orderData.setPrice(broadband.getPrice());
				orderData.setPhoneNum(broadband.getPhoneNum());
				orderData.setVoiceTariff(broadband.getVoiceTariff());
			}
			OrderChargeEntity charge = null;
			if(null != order.getChargeId()) {
				charge = em.find(OrderChargeEntity.class, order.getChargeId());
				orderData.setChargeAmount(charge.getChargeAmount());
				orderData.setChargeDate(charge.getChargeDate());
				orderData.setNextChargeDate(charge.getNextChargeDate());
			}
			OrderInvoiceEntity invoice = null;
			if(null != order.getInvoiceId()) {
				invoice = em.find(OrderInvoiceEntity.class, order.getInvoiceId());
				orderData.setIsInvoice(invoice.getIsInvoice());
				dictValueEntity = dictRepository.findByDictTypeIdAndValue(DictType.INVOICE_TYPE, invoice.getType());
				if(null != dictValueEntity) {
					orderData.setInvoiceType(dictValueEntity.getName());
				}
				orderData.setInvoiceDate(invoice.getInvoiceDate());
				orderData.setInvoiceNum(invoice.getInvoiceNum());
			}
			OrderOperatorEntity operator = null;
			if(null != order.getOperatorId()) {
				operator = em.find(OrderOperatorEntity.class, order.getOperatorId());
				orderData.setProductNum(operator.getProductNum());
				dictValueEntity = dictRepository.findByDictTypeIdAndValue(DictType.OPERATOR_ACCOUNT_TYPE, operator.getAccountStatus());
				if(null != dictValueEntity) {
					orderData.setAccountStatus(dictValueEntity.getName());
				}
				orderData.setInternetAccount(operator.getInternetAccount());
				orderData.setBroadband(operator.getBroadband());
				orderData.setOperatorPhone(operator.getPhone());
				orderData.setPaymentCycle(operator.getPaymentCycle());
				orderData.setPaymentDate(operator.getPaymentDate());
				orderData.setOperatorInvoiceDate(operator.getInvoiceDate());
				orderData.setOperatorContact(operator.getContact());
				orderData.setRenewContract(operator.getRenewContract());
				orderData.setOperatorInvoiceNum(operator.getInvoiceNum());
				orderData.setPrimaryCharge(operator.getPrimaryCharge());
			}
			OrderNewBusinessEntity newBusiness = null;
			if(null != order.getNewBusinessId()) {
				newBusiness =  em.find(OrderNewBusinessEntity.class, order.getNewBusinessId());
				orderData.setRequestOrder(newBusiness.getRequestOrder());
				orderData.setMonth(newBusiness.getMonth());
				orderData.setDate(newBusiness.getDate());
				orderData.setCounterpart(newBusiness.getCounterpart());
			}
			orderData.setOrderNo(order.getOrderNo());
			orderData.setOwnerProject(order.getOwnerProject());
			dictValueEntity = dictRepository.findByDictTypeIdAndValue(DictType.TRADE_STATUS, order.getStatus());
			if(null != dictValueEntity) {
				orderData.setStatus(dictValueEntity.getName());
			}
			orderData.setIsUseNetWork(order.getIsUseNetwork());
			orderData.setAreaNumber(order.getAreaNumber());
			orderData.setAreaName(order.getAreaName());
			results.add(orderData);
		}
		return results;
	}
}
