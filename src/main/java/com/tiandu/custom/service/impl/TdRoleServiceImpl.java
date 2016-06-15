package com.tiandu.custom.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.common.db.DBContextHolder;
import com.tiandu.custom.entity.TdRole;
import com.tiandu.custom.entity.TdRolePermission;
import com.tiandu.custom.entity.mapper.TdRoleMapper;
import com.tiandu.custom.entity.mapper.TdRolePermissionMapper;
import com.tiandu.custom.search.TdRoleSearchCriteria;
import com.tiandu.custom.service.TdRoleService;

/**
 * 
 * @author liuxinbing
 *
 */
@Service("tdRoleService")
public class TdRoleServiceImpl implements TdRoleService {

	@Autowired
	TdRoleMapper roleMapper;
	@Autowired
	TdRolePermissionMapper rolePermissionMapper;

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

	public Integer save(TdRole role) {
		if(null!=role){
			if(null!=role.getId()){//更新
				return roleMapper.updateByPrimaryKey(role);
			}else{
				return roleMapper.insert(role);
			}
		}
		return null;
	}

	public Integer delete(Integer id) {
		return roleMapper.deleteByPrimaryKey(id);
	}

	public Integer saveRolePermission(TdRole role) {
		if(null!=role&&null!=role.getId()){
			rolePermissionMapper.deleteByRole(role);
			if(null!=role.getPermissionList()&&role.getPermissionList().size()>0){
				role.getPermissionList().removeAll(Collections.singleton(null));
				rolePermissionMapper.insertRolePermissions(role);
			}
		}
		return null;
	}

	public List<TdRolePermission> findRolePermissionsByRoleId(Integer roleId) {
		return rolePermissionMapper.findRolePermissionsByRoleId(roleId);
	}

	
	
	
}
