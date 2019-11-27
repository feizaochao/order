package com.order.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.common.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.utils.R;
import com.order.entity.MenuEntity;
import com.order.entity.RoleEntity;
import com.order.entity.RoleMenuEntity;
import com.order.repository.RoleMenuRepository;
import com.order.service.RoleService;

/**
* @Description: TODO
* @author lguochao
* @date 2019年11月7日
* @version V1.0
*/
@Service(value = "roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private EntityManager em;
	@Autowired
	private RoleMenuRepository roleMenuRepository;
	
	@Override
	public ResultUtils addRole(String name) {
		RoleEntity role = new RoleEntity();
		role.setName(name);
		role.setCreateTime(new Date());
		em.persist(role);
		return ResultUtils.ok();
	}

	@Override
	public ResultUtils editRole(Long id, String name) {
		RoleEntity role = em.find(RoleEntity.class, id);
		if (null != role) {
			role.setName(name);
			role.setUpdateTime(new Date());
			em.merge(role);
		}
		return ResultUtils.ok();
	}

	@Override
	public ResultUtils deleteRole(Long id) {
		// 先删除角色和菜单的中间表
		roleMenuRepository.deleteByRole(id);
		RoleEntity role = em.find(RoleEntity.class, id);
		if (null != role) {
			em.remove(role);
		}
		return ResultUtils.ok();
	}

	@Override
	public List<Map<String, Object>> queryList() {
		StringBuilder sql = new StringBuilder();
		sql.append("select id, name from e_role \n");
		Query query = em.createNativeQuery(sql.toString());
		List resultList = query.getResultList();
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for(Object row : resultList) {
			Object[] cells = (Object[]) row;
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", cells[0]);
			map.put("name", cells[1]);
			list.add(map);
		}
		return list;
	}

	@Override
	public Long queryRoleByUserId(Long userId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select role_id from e_user_role where user_id = :userId");
		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("userId", userId);
		return ((BigInteger)query.getSingleResult()).longValue();
	}

	@Override
	public ResultUtils saveMenus(Long roleId, Long[] menuIds) {
		roleMenuRepository.deleteByRole(roleId);
		for (Long menuId : menuIds) {
			RoleMenuEntity rm = new RoleMenuEntity();
			rm.setRoleId(roleId);
			rm.setMenuId(menuId);
			rm.setCreateTime(new Date());
			em.persist(rm);
		}
		return ResultUtils.ok();
	}
}
