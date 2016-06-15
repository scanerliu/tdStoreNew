package com.tiandu.custom.service;

import java.util.List;

import com.tiandu.custom.entity.TdRole;
import com.tiandu.custom.entity.TdRolePermission;
import com.tiandu.custom.search.TdRoleSearchCriteria;

/**
 * 
 * @author liuxinbing
 *
 */
public interface TdRoleService {

	public int insert(TdRole role);
	public TdRole findOne(Integer id);
	
	public List<TdRole> findBySearchCriteria(TdRoleSearchCriteria sc);
	public TdRole findOneWithPermiisions(Integer id);
	public Integer save(TdRole role);
	public Integer delete(Integer id);
	public Integer saveRolePermission(TdRole role);
	public List<TdRolePermission> findRolePermissionsByRoleId(Integer roleId);
}
