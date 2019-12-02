package com.order.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import com.order.entity.UserEntity;
import com.order.entity.UserRoleEntity;
import com.order.repository.UserRepository;
import com.order.repository.UserRoleRepository;
import com.order.service.UserService;

/**
* @Description: TODO
* @author Robb G C LUO
* @date 2019年11月15日
* @version v1.0
*/
@Service(value = "userService")
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private EntityManager em;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Override
	public UserEntity queryUserByName(String username) {
		UserEntity user = userRepository.findOneByName(username);
		return user;
	}

	@Override
	public ResultUtils addUser(String name, String password, Long roleId) {
		UserEntity user = userRepository.findOneByName(name);
		if (null != user) {
			return ResultUtils.error(201, "用户已存在");
		}
		user = new UserEntity();
		user.setName(name);
		user.setPassword(password);
		user.setCreateTime(new Date());
		em.persist(user);
		em.flush(); // 只有在flush之后才能读取实体的ID
		
		userRoleRepository.deleteRoleByUser(user.getId());
		UserRoleEntity ur = new UserRoleEntity();
		ur.setUserId(user.getId());
		ur.setRoleId(roleId);
		ur.setCreateTime(new Date());
		em.persist(ur);
		
		return ResultUtils.ok();
	}

	@Override
	public ResultUtils editUser(Long userId, String name, String password, Long roleId) {
		UserEntity user = em.find(UserEntity.class, userId);
		if (null == user) {
			return ResultUtils.error(201, "用户不存在");
		}
		user.setName(name);
		user.setPassword(password);
		user.setUpdateTime(new Date());
		em.merge(user);
		
		userRoleRepository.deleteRoleByUser(userId);
		UserRoleEntity ur = new UserRoleEntity();
		ur.setUserId(user.getId());
		ur.setRoleId(roleId);
		ur.setCreateTime(new Date());
		em.persist(ur);
		
		return ResultUtils.ok();
	}

	@Override
	public ResultUtils deleteUser(Long id) {
		userRoleRepository.deleteRoleByUser(id);
		UserEntity user = em.find(UserEntity.class, id);
		if (null == user) {
			return ResultUtils.error(201, "用户不存在");
		}
		em.remove(user);
		return ResultUtils.ok();
	}

	@Override
	public PageUtils2<UserEntity> queryList(final Query query) {
		Specification<UserEntity> specification = new Specification<UserEntity>() {
			@Override
			public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> qy, CriteriaBuilder cb) {
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
		Page<UserEntity> page = userRepository.findAll(specification, pageable);
		
		StringBuilder sql = new StringBuilder();
		sql.append("select r.name from e_role r, e_user_role ur where r.id = ur.role_id and ur.user_id = :userId");
		List<UserEntity> list = page.getContent();
		for(UserEntity user : list) {
			javax.persistence.Query qy = em.createNativeQuery(sql.toString());
			qy.setParameter("userId", user.getId());
			String roleName = (String) qy.getSingleResult();
			user.setRoleName(roleName);
		}
		PageUtils2<UserEntity> pageUtils = new PageUtils2<UserEntity>(Long.valueOf(page.getTotalElements()).intValue(), query.getLimit(), query.getPage(), page.getContent());
		return pageUtils;
	}

}
