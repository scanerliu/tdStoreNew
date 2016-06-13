package com.tiandu.custom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.common.db.DBContextHolder;
import com.tiandu.custom.entity.TdRole;
import com.tiandu.custom.entity.mapper.TdRoleMapper;
import com.tiandu.custom.search.TdRoleSearchCriteria;
import com.tiandu.custom.service.TdRoleService;

@Service("tdRoleService")
public class TdRoleServiceImpl implements TdRoleService {

	@Autowired
	TdRoleMapper roleMapper;

	public int insert(TdRole u) {
		DBContextHolder.setDbType(DBContextHolder.DB_RW);
		return roleMapper.insert(u);
	}

	public TdRole findOne(Integer id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	public TdRole findOneWithPermiisions(Integer id) {
		return roleMapper.selectByPrimaryKeyPermiision(id);
	}

	public List<TdRole> findBySearchCriteria(TdRoleSearchCriteria sc) {
		Integer count = roleMapper.countByCriteria(sc);
		sc.setTotalCount(count);
		return roleMapper.findBySearchCriteria(sc);
	}

	
	
	
}
