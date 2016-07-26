package com.tiandu.custom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.custom.entity.TdPermission;
import com.tiandu.custom.entity.TdRole;
import com.tiandu.custom.entity.mapper.TdPermissionMapper;
import com.tiandu.custom.search.TdPermissionSearchCriteria;
import com.tiandu.custom.service.TdPermissionService;

/**
 * 
 * @author liuxinbing
 *
 */
@Service("tdPermissionService")
public class TdPermissionServiceImpl implements TdPermissionService {

	@Autowired
	TdPermissionMapper permissionMapper;


	public List<TdPermission> findBySearchCriteria(TdPermissionSearchCriteria sc) {
		return permissionMapper.findBySearchCriteria(sc);
	}
	
	
}
