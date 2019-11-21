package com.order.service.impl;

import java.util.ArrayList;
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
import com.order.entity.AreaEntity;
import com.order.entity.ContractEntity;
import com.order.entity.CustomerEntity;
import com.order.entity.OrderEntity;
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
		customer.setPersist((int) params.get("persist"));
		customer.setAddress((String) params.get("address"));
		customer.setContact((String) params.get("contact"));
		customer.setLicenseNo((String) params.get("licenseNo"));
		customer.setLicenseAddress((String) params.get("licenseAddress"));
		em.persist(customer);
		// 保存合同信息
		ContractEntity contract = new ContractEntity();
		contract.setContractNo((String) params.get("contractNo"));
		contract.setContractAmount(MathUtils.getBigDecimal(params.get("contractAmount")));
		contract.setContractStartTime((String) params.get("contractStartTime"));
		contract.setContractEndTime((String) params.get("contractEndTime"));
		em.persist(contract);
		// 保存订单信息
		em.flush();
		OrderEntity order = new OrderEntity();
		order.setOrderNo((String) params.get("orderNo"));
		order.setOwnerPorject((String) params.get("ownerProject"));
		order.setStatus((int) params.get("status"));
		order.setIsUseNetwork((int) params.get("isUseNetword"));
		order.setCustomerId(customer.getId());
		order.setContractId(contract.getId());
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
		em.remove(order);
		return R.ok();
	}

	@Override
	public PageUtils quertList(final Query query) {
		Specification<OrderEntity> specfication = new Specification<OrderEntity>() {
			@Override
			public Predicate toPredicate(Root<OrderEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				return null; 
			}
		};
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(Direction.DESC, "createTime"));
		Pageable pageable = new PageRequest(query.getPage() - 1, query.getLimit(), new Sort(orders));
		Page<OrderEntity> page = orderRepository.findAll(specfication, pageable);
		
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
