package com.tiandu.custom.entity.mapper;

import com.tiandu.custom.entity.TdPermission;

public interface TdPermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdPermission record);

    int insertSelective(TdPermission record);

    TdPermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdPermission record);

    int updateByPrimaryKey(TdPermission record);
}