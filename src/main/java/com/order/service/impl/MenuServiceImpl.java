package com.order.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.service.MenuService;

/**
* @Description: TODO
* @author lguochao
* @date 2019年11月7日
* @version V1.0
*/
@Service(value = "menuService")
@Transactional
public class MenuServiceImpl implements MenuService {

	@Autowired
	private EntityManager em;
	
	@Override
	public List<Map<String, Object>> queryList() {
		StringBuilder sql = new StringBuilder();
		sql.append("select id, name from e_menu \n");
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
	public List<Map<String, Object>> queryListByRoleId(Long roleId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select name, path from e_menu m, e_role_menu rm where m.id = rm.menu_id and rm.role_id = :roleId");
		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("roleId", roleId);
		List resultList = query.getResultList();
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for(Object row : resultList) {
			Object[] cells = (Object[]) row;
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", cells[0]);
			map.put("path", cells[1]);
			list.add(map);
		}
		return list;
	}

}
