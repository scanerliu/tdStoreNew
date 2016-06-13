package com.tiandu.custom.entity.mapper;

import com.tiandu.custom.entity.TdRolePermission;

public interface TdRolePermissionMapper {
    int insert(TdRolePermission record);

    int insertSelective(TdRolePermission record);
}