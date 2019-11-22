package com.order.service.impl;

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
		customer.setMallNo((String) params.get("mallNo"));
		customer.setName((String) params.get("customerName"));
		customer.setAreaId(Long.valueOf((String) params.get("areaId")));
//		customer.setPersist((Integer) params.get("persist"));
		customer.setAddress((String) params.get("address"));
		customer.setContact((String) params.get("contact"));
		customer.setLicenseNo((String) params.get("licenseNo"));
		customer.setLicenseAddress((String) params.get("licenseAddress"));
		customer.setCreateTime(new Date());
		em.persist(customer);
		// 保存合同信息
		ContractEntity contract = new ContractEntity();
		contract.setContractNo((String) params.get("contractNo"));
		contract.setContractAmount(MathUtils.getBigDecimal(params.get("contractAmount")));
		contract.setContractStartTime((String) params.get("contractStartTime"));
		contract.setContractEndTime((String) params.get("contractEndTime"));
		contract.setCreateTime(new Date());
		em.persist(contract);
		// 保存宽带信息
		OrderBroadbandEntity broadband = new OrderBroadbandEntity();
		broadband.setOperator((String) params.get("broadbandOperator"));
		broadband.setType((String) params.get("broadbandType"));
		broadband.setPrice(MathUtils.getBigDecimal(params.get("broadbandPrice")));
		broadband.setPhone((String) params.get("broadbandPhone"));
		broadband.setPhoneNum(Integer.parseInt((String) params.get("broadbandPhoneNum")));
		broadband.setVoiceTariff(MathUtils.getBigDecimal(params.get("voiceTariff")));
		broadband.setCreateTime(new Date());
		em.persist(broadband);
		// 保存收费信息
		OrderChargeEntity chargeEntity = new OrderChargeEntity();
		chargeEntity.setChargeAmount(MathUtils.getBigDecimal(params.get("chargeAmount")));
		chargeEntity.setChargeDate((String) params.get("chargeDate"));
		chargeEntity.setNextChargeDate((String) params.get("nextChargeDate"));
		chargeEntity.setCreateTime(new Date());
		em.persist(chargeEntity);
		// 保存开票信息
		OrderInvoiceEntity invoiceEntity = new OrderInvoiceEntity();
		invoiceEntity.setIsInvoice(Integer.parseInt((String) params.get("isInvoice")));
		invoiceEntity.setType(Integer.parseInt((String) params.get("invoiceType")));
		invoiceEntity.setInvoiceDate((String) params.get("invoiceDate"));
		invoiceEntity.setInvoiceNum((String) params.get("invoiceNum"));
		invoiceEntity.setCreateTime(new Date());
		em.persist(invoiceEntity);
		// 保存缴纳运营商套餐成本信息
		OrderOperatorEntity operatorEntity = new OrderOperatorEntity();
		operatorEntity.setProductNum((String) params.get("productNum"));
		operatorEntity.setAccountStatus(Integer.parseInt((String) params.get("accountStatus")));
		operatorEntity.setInternetAccount((String) params.get("internetAccount"));
		operatorEntity.setBroadband((String) params.get("broadband"));
		operatorEntity.setPhone((String) params.get("operatorPhone"));
		operatorEntity.setPaymentCycle((String) params.get("paymentCycle"));
		operatorEntity.setPaymentDate((String) params.get("paymentDate"));
		operatorEntity.setInvoiceDate((String) params.get("operatorInvoiceDate"));
		operatorEntity.setContact((String) params.get("operatorContact"));
		operatorEntity.setRenewContract((String) params.get("renewContract"));
		operatorEntity.setInvoiceNum((String) params.get("operatorInvoiceNum"));
		operatorEntity.setPrimaryCharge((MathUtils.getBigDecimal(params.get("primaryCharge"))));
		operatorEntity.setCreateTime(new Date());
		em.persist(operatorEntity);
		// 保存新业务归属信息
		OrderNewBusinessEntity newBusinessEntity = new OrderNewBusinessEntity();
		newBusinessEntity.setRequestOrder((String) params.get("requestOrder"));
		newBusinessEntity.setMonth((String) params.get("newBusinessMonth"));
		newBusinessEntity.setDate((String) params.get("newBusinessDate"));
		newBusinessEntity.setCounterpart((String) params.get("counterpart"));
		newBusinessEntity.setCreateTime(new Date());
		em.persist(newBusinessEntity);
		// 保存订单信息
		em.flush();
		OrderEntity order = new OrderEntity();
		order.setOrderNo((String) params.get("orderNo"));
		order.setOwnerProject((String) params.get("ownerProject"));
//		order.setStatus((int) params.get("status"));
//		order.setIsUseNetwork((int) params.get("isUseNetwork"));
		order.setRemarks((String) params.get("remarks"));
		order.setCreateTime(new Date());
		order.setCustomerId(customer.getId());
		order.setContractId(contract.getId());
		order.setBroadbandId(broadband.getId());
		order.setChargeId(chargeEntity.getId());
		order.setInvoiceId(invoiceEntity.getId());
		order.setOperatorId(operatorEntity.getId());
		order.setNewBusinessId(newBusinessEntity.getId());
		em.persist(order);
		return R.ok();
	}

	@Override
	public R editOrder(Map<String, Object> params) {
		// 修改客户信息
		return null;
	}

	@Override
	public R deleteOrder(Long id) {
		OrderEntity order = em.find(OrderEntity.class, id);
		if(null == order) {
			return R.error();
		}
		// 删除宽带信息
		OrderBroadbandEntity broadbandEntity = em.find(OrderBroadbandEntity.class, order.getBroadbandId());
		if(null == broadbandEntity) {
			return R.error();
		}
		// 删除收费信息
		OrderChargeEntity chargeEntity = em.find(OrderChargeEntity.class, order.getChargeId());
		if(null == chargeEntity) {
			return R.error();
		}
		// 删除开票信息
		OrderInvoiceEntity invoiceEntity = em.find(OrderInvoiceEntity.class, order.getInvoiceId());
		if(null == invoiceEntity) {
			return R.error();
		}
		// 删除需缴纳运营商套餐成本信息
		OrderOperatorEntity operatorEntity = em.find(OrderOperatorEntity.class, order.getOperatorId());
		if(null == operatorEntity) {
			return R.error();
		}
		// 删除新业务信息
		OrderNewBusinessEntity newBusinessEntity = em.find(OrderNewBusinessEntity.class, order.getNewBusinessId());
		if(null == newBusinessEntity) {
			return R.error();
		}
		em.remove(broadbandEntity);
		em.remove(chargeEntity);
		em.remove(invoiceEntity);
		em.remove(operatorEntity);
		em.remove(newBusinessEntity);
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

	
}
