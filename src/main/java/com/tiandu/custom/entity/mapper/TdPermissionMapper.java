package com.tiandu.custom.entity.mapper;

import java.util.List;

import com.tiandu.custom.entity.TdPermission;
import com.tiandu.custom.search.TdPermissionSearchCriteria;

public interface TdPermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdPermission record);

    int insertSelective(TdPermission record);

    TdPermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdPermission record);

    int updateByPrimaryKey(TdPermission record);
    
    public List<TdPermission> findBySearchCriteria(TdPermissionSearchCriteria sc);
    public Integer 		countByCriteria(TdPermissionSearchCriteria sc);
}