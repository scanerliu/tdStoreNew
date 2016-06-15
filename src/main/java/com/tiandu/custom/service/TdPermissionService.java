package com.tiandu.custom.service;

import java.util.List;

import com.tiandu.custom.entity.TdPermission;
import com.tiandu.custom.search.TdPermissionSearchCriteria;

/**
 * 
 * @author liuxinbing
 *
 */
public interface TdPermissionService {
	public List<TdPermission> findBySearchCriteria(TdPermissionSearchCriteria sc);
}
