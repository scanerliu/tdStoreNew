package com.tiandu.custom.service;

import java.util.List;

import com.tiandu.custom.entity.TdRole;
import com.tiandu.custom.search.TdRoleSearchCriteria;

public interface TdRoleService {

	public int insert(TdRole u);
	public TdRole findOne(Integer id);
	
	public List<TdRole> findBySearchCriteria(TdRoleSearchCriteria sc);
	public TdRole findOneWithPermiisions(Integer id);
}
